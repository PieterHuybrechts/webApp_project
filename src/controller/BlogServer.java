package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.BlogMessage;
import service.BlogMessageService;
import service.ServiceException;

@ServerEndpoint("/echo")
public class BlogServer{

	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

	private BlogMessageService service;
	
	public BlogServer() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		System.out.println("test");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		System.out.println("test2");
	}	
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session.getId() + " has opened a connection");
		//sendMessageToAll("User has connected");
		/*try {
			session.getBasicRemote().sendText("Connection Established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}*/
		sessions.add(session);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		if(service==null){
			service = new BlogMessageService();
		}
		
		System.out.println("Message from " + session.getId() + ": " + message);
		
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(message);
			JsonNode subjectNode = root.path("subject");
			JsonNode messageNode = root.path("message");
			
			BlogMessage bMessage = new BlogMessage();
			bMessage.setSubject(subjectNode.asText());
			bMessage.setMessage(messageNode.asText());
			
			try {
				service.addMessage(bMessage);
				sendMessageToAll(message);
			} catch (ServiceException e) {
				
			}
			
			
		} catch (IOException e) {
		}
		
		
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Chat " + session.getId() + " has ended");
		sessions.remove(session);
	}

	private void sendMessageToAll(String message) {
		for (Session s : sessions) {
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
