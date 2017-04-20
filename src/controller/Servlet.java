package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbException;
import domain.DomainException;
import domain.User;
import service.MenuItem;
import service.ServiceException;
import service.UserService;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService;
	
    public Servlet() {
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		String destination="";
		
		
		if(action==null){
			action="";
		}
		
		switch (action) {
		/*
		 * Navigation
		 */
		case "":
		case "goToHome":
			destination=goToHome(request, response);
			break;
		case "goToLogin":
			destination = goToLogin(request, response);
			break;
		case "goToRegister":
			destination=goToRegister(request, response);
			break;
		case "goToChat":
			destination=goToChat(request,response);
			break;
		/*
		 * Actions
		 */
		case "login":
			destination=login(request, response);
			break;
		case "register":
			destination=register(request, response);
			break;
		case "logout":
			destination=logout(request, response);
			break;
		case "switchStatus":
			switchStatus(request,response);
		default:
			destination=goToHome(request, response);
			break;
		}
		
		setMenuItems(request);
		RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
	}
	
    private void setMenuItems(HttpServletRequest request) {
        List<MenuItem> menuItems = userService.getMenu(isUserAuthenticated(request));
        request.setAttribute("menuItems", menuItems);
    }

    private boolean isUserAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    private void remember(HttpServletRequest request, HttpServletResponse response, String email) {
        String remember = request.getParameter("remember");
        boolean rememberMe = Boolean.parseBoolean(remember);
        if (rememberMe) {
            Cookie cookie = new Cookie("email", email);
            response.addCookie(cookie);
        } else {
            Cookie cookie = getCookie(request, "email");
            if (cookie != null) {
                cookie.setMaxAge(0);
            }
        }
    }
    
    private Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie cookieSearched;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookieSearched = cookie;
                    return cookieSearched;
                }
            }
        }
        return null;
    }
	
	/*
	 * Navigation
	 */
	
	private String goToHome(HttpServletRequest request,HttpServletResponse response){
		return "index.jsp";
	}
	
	private String goToRegister(HttpServletRequest request,HttpServletResponse response){
		return "register.jsp";
	}
	
    private String goToLogin(HttpServletRequest request, HttpServletResponse respons) {
        Cookie emailCookie = null;
        emailCookie = getCookie(request, "email");
        if (emailCookie != null) {
            request.setAttribute("email", emailCookie.getValue());
        }
        return "login.jsp";
    }
	
    private String goToChat(HttpServletRequest request,HttpServletResponse response){
    	String destination = "chat.jsp";
    	request.setAttribute("user", request.getSession().getAttribute("user"));
    	List<User> users;
    	try {
			users = userService.getAllUsers();
			request.setAttribute("users", users);
		} catch (ServiceException e) {
			List<String> errorMessages = new ArrayList<>();
			errorMessages.add(e.getMessage());
			request.setAttribute("errorMessages", errorMessages);
			destination = "index.jsp";
		}
    	
    	return destination;
    }
    
	/*
	 * Actions
	 */
	
	private String register(HttpServletRequest request,HttpServletResponse response){
		List<String> errorMessages = new ArrayList<>();
		String destination = "";
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User u = new User();

		
		
		try{
			u.setEmail(email);
		}catch (DomainException e) {
			errorMessages.add(e.getMessage());
		}
		
		try{
			u.setUsername(name);
		}catch (DomainException e) {
			errorMessages.add(e.getMessage());
		}
		
		try{
			u.setPassword(password);
		}catch (DomainException e) {
			errorMessages.add(e.getMessage());
		}
		
		if(errorMessages.isEmpty()){
			try{
				userService.addUser(u);
				destination = goToHome(request, response);
			}catch(ServiceException e){
				errorMessages.add(e.getMessage());
			}
		}
		
		if(!errorMessages.isEmpty()){
			destination = goToRegister(request, response);
			request.setAttribute("errorMessages", errorMessages);			
		}
		
		return destination;
	}
	
	private String login(HttpServletRequest request,HttpServletResponse response){
		List<String> errorMessages = new ArrayList<>();
		String destination = "";
		
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        User u = null;
        try {
            u = userService.getAuthenticatedUser(email, password);
        } catch (ServiceException e) {
        	errorMessages.add(e.getMessage());
        }
		
		
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            destination = goToLogin(request, response);
        } else {
            remember(request, response, email);
            request.getSession().setAttribute("user", u);
            destination = goToChat(request, response);
        }
		
		return destination;
	}
	
	private String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().invalidate();
		return goToHome(request, response);
	}

	private String switchStatus(HttpServletRequest request,HttpServletResponse response) throws IOException{
		User u = (User) request.getSession().getAttribute("user");
		
		String status = u.getCurrentStatus();
		
		if(status.equals("online")){
			u.setCurrentStatus("offline");
		}else if(status.equals("offline")){
			u.setCurrentStatus("online");
		}
		
		request.setAttribute("user", u);
		
		response.getWriter().write(u.getCurrentStatus());
		
		return null;
	}
	
}
