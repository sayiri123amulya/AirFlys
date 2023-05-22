

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Servlet implementation class AirlinesPortal
 */
@WebServlet("/AirlinesPortalservlet")
public class AirlinesPortalservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
	
	public static void click(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.sendRedirect("register.html");
		
		
	}
    public AirlinesPortalservlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory factory= HibernateUtil.getfactorysession();
		Session session = factory.openSession();
		
		String start_source = request.getParameter("start_source");
		String final_destination = request.getParameter("final_destination");
		int members = Integer.parseInt(request.getParameter("members"));
		String da= request.getParameter("date_of_travel");
		PrintWriter out = response.getWriter();
     
		Query query = session.createQuery("from Flights");
		
		out.println("<html>");
		out.println("<style>\r\n"
				+ "table, th, td {\r\n"
				+ "  border:1px solid black;\r\n"
				+ "}\r\n"
				+ "</style>");
		out.println("<body>");
		out.println("<h1>AVAILABLE <font color=red>FLIGHTS</font> FOR BOOKING</h2>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Flight Number</th>");
		out.println("<th>Flight Name</th>");
		out.println("<th>Start Source</th>");
		out.println("<th>Final Destination</th>");
		out.println("<th>Date Of Travel</th>");
		out.println("<th>Seats Available</th>");
		out.println("<th>Price for each person</th>");
		out.println("</tr>");
		
		String n,na,so,de,se;
		String da1;
		String price;
		String htmlString = "";
		@SuppressWarnings("unchecked")
		List<Flights> fly = query.list();
		for(Flights f1:fly) {
			if(start_source.equals(f1.getStart_source()) && final_destination.equals(f1.getFinal_destination()) /*&& da.equals(String.valueOf(f1.getDate_of_travel())*/) {
			n=f1.getFlight_number();
			na=f1.getFlight_name();
			so=f1.getStart_source();
			de=f1.getFinal_destination();
			da1=String.valueOf(f1.getDate_of_travel());
			se=String.valueOf(f1.getSeats_available());
			price=String.valueOf(f1.getTicket_price());
			htmlString += "<tr>\n"
			+"<td>"+n+"</td>\n"
			+"<td>"+na+"</td>\n"
			+"<td>"+so+"</td>\n"
			+"<td>"+de+"</td>\n"
			+"<td>"+da1+"</td>\n"
			+"<td>"+se+"</td>\n"
			+"<td>"+price+"</td>\n"
			+"<form method=\"post\" action=\"DetailsFillingServlet\">"
			+"<td><button >Book Seats</button></td>\n"
			+"</form>"
			+"</tr>\n";
			
			out.println(htmlString);
			ServletContext servletcontext = getServletContext();
			servletcontext.setAttribute("n", n);
			servletcontext.setAttribute("members", members);
			servletcontext.setAttribute("se",se);
			
		
			}
		}
		out.println("</table>");
		out.println("</html></body>");
		
	    
		
	} 

}

