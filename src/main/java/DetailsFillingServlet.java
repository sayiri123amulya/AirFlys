

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Servlet implementation class DetailsFillingServlet
 */
@WebServlet("/DetailsFillingServlet")
public class DetailsFillingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsFillingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out= response.getWriter();
		SessionFactory factory = HibernateUtil.getfactorysession();
		Session session = factory.openSession();
		//String flight_number = (String)request.getAttribute("n");
		ServletContext servletcontext = getServletContext();
		String members = servletcontext.getAttribute("members").toString();
		int m = Integer.parseInt(members);
		String flight_number = servletcontext.getAttribute("n").toString();
		out.println("<html><body>");
		//out.println("<h2>Fill the details to book the tickets</h2>");
		
		//for(int i =1;i<=m;i++) {
			
			//out.println("<form method=\"post\" action=\"Insertingdataservlet\">");
			
			/*out.println("Passenger Name<input name=\"passenger_name\" placeholder=\"Passenger Name\"><br>");
			out.println("Passenger Age<input type=\"text\" name=\"passenger_age\" placeholder=\"Passenger Age\"><br>");
			out.println("Passenger Unique ID<input name=\"passenger_unique_id\" placeholder=\"Passenger Unique ID\"><br>");
			out.println("Passenger Gender<input name=\"passenger_gender\" placeholder=\"Passenger Gender\"><br>");
			out.println("<button>Continue</button><br>");
			out.println("</form><br>"); */
			
			RequestDispatcher requestDispatcher = 
	    			request.getRequestDispatcher("/DetailsFilling.html");
			requestDispatcher.include(request,response); 
			
			
			/*if(i==m) {
				out.println("<form name=\"insert\" method=\"post\" action=\"confirmationservlet\" >");
				out.println("<button>Book</button><br>");
				out.println("</form>");
			} */
			 
		}
		
		
	}

//}
