package mothership;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class hello
 */
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name = request.getParameter("name");
		if(name == null ) {
			name ="JSPPP";
		}
		
		String hello = "hello" + name;
		request.setAttribute("hello", hello);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/hello.jsp");
		dispatcher.forward(request, response);
	}
}
