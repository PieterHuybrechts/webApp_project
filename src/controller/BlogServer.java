package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet("/BlogServlet")
@ServerEndpoint("/echo")
public class BlogServer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9153572736191221505L;

	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

	private BlogMessageService service;
	
	public BlogServer() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		System.out.println("test");
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();

		Properties properties = new Properties();
		properties.setProperty("user", context.getInitParameter("user"));
		properties.setProperty("password", context.getInitParameter("password"));
		properties.setProperty("url", context.getInitParameter("url"));
		properties.setProperty("ssl", context.getInitParameter("ssl"));
		properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
		service = new BlogMessageService(properties);
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
