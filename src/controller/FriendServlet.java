package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.FriendService;

@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FriendService service;
	
    public FriendServlet() {
        super();
        
        service = new FriendService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response){
		String action = request.getParameter("action");
		
		switch (action) {
		case "addFriend":
			addFriend(request, response);
			break;

		default:
			break;
		}
	}
	
	private void addFriend(HttpServletRequest request,HttpServletResponse response){
		
	}

}
