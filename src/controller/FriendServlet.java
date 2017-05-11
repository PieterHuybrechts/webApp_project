package controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbException;
import domain.User;
import service.FriendService;
import service.ServiceException;
import service.UserService;

@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FriendService friendService;
	UserService userService;
	
    public FriendServlet() {
        super();
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
		try {
			userService = new UserService(properties);
			friendService = new FriendService(properties);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String action = request.getParameter("action");
		
		switch (action) {
		case "addFriend":
			addFriend(request, response);
			break;
		case "getAllFriends":
			getAllFriends(request,response);
		default:
			break;
		}
	}
	
	private void addFriend(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String email = request.getParameter("email");
		User u1 = (User) request.getSession().getAttribute("user");
		User u2 = userService.getUser(email);
		
		try {
			friendService.addFriend(u1.getEmail(), email);
			response.getWriter().write(u2.getUsername());
		} catch (ServiceException e) {
			
		}
		
		
	}
	
	public void getAllFriends(HttpServletRequest requets,HttpServletResponse response){
		User u = (User) requets.getSession().getAttribute("user");
		try {
			List<User> friends = friendService.getAllFriendsOf(u);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
