package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data_access.StudentDAO;

@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		StudentDAO studentDao = new StudentDAO();
		
		int id = Integer.parseInt(request.getParameter("studentID"));
	
		int errorCode = studentDao.deleteStudent(id);
		
		out.print("{\"errorCode\": " + errorCode + "}");
	}
}