package controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import db.DbException;
import domain.User;
import service.ServiceException;
import service.UserService;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/StatusServlet")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public StatusServlet() {
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
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "goOnline":
			goOnline(request, response);
			break;
		case "goOffline":
			goOffline(request, response);
			break;
		case "goAway":
			goAway(request, response);
			break;
		case "goCustom":
			goCustom(request,response);
		default:
			break;
		}
	}

	private void goOnline(HttpServletRequest request, HttpServletResponse response) throws IOException {
		changeUserStatus(request,response,"Online");
	}

	private void goOffline(HttpServletRequest request, HttpServletResponse response) throws IOException {
		changeUserStatus(request,response,"Offline");
	}

	private void goAway(HttpServletRequest request, HttpServletResponse response) throws IOException {
		changeUserStatus(request,response,"Away");
	}
	
	private void goCustom(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String status = request.getParameter("status");
		changeUserStatus(request, response, status);
	}
	
	private void changeUserStatus(HttpServletRequest request,HttpServletResponse response,String status) throws IOException{
		User u = (User) request.getSession().getAttribute("user");
		u.setStatus(status);
		
		try {
			userService.updateUser(u);
			request.getSession().setAttribute("user", u);
			String statusNewJSON = this.toJSON(u);
			response.getWriter().write(statusNewJSON);
		} catch (ServiceException e) {
			
		}
	}
	
	private String toJSON(User user) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(user);
	}

}
