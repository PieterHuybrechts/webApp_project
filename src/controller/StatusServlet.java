package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/StatusServlet")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatusServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		u.setCurrentStatus(status);
		request.getSession().setAttribute("user", u);
		String status1 = u.getCurrentStatus();
		response.getWriter().write(status1);
	}

}
