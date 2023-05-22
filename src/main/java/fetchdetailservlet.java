

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Servlet implementation class fetchdetailservlet
 */
@WebServlet("/fetchdetailservlet")
public class fetchdetailservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchdetailservlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String booking_id = request.getParameter("Booking_id").toString();
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(booking_id);
		SessionFactory factory = HibernateUtil.getfactorysession();
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		Query q = sess.createSQLQuery("select f.flight_number,f.flight_name,f.start_source,f.final_destination,"
				+ "f.date_of_travel,b.booking_ID,b.seat_number,b.passenger_name,b.passenger_age,b.passenger_unique_id,b.passenger_gender"
				+ " from flights f, booking b where f.flight_number=b.flight_number and booking_ID=:id");
		q.setParameter("id",id);
		List<Object[]> details= (List<Object[]>)q.list();
		out.println("<html>");
		out.println("<style>\r\n"
				+ "table, th, td {\r\n"
				+ "  border:1px solid black;\r\n"
				+ "}\r\n"
				+ "</style>");
		out.println("<body>");
		out.println("<h1>BOOKING <font color=red>DETAILS</font></h2>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Flight Number</th>");
		out.println("<th>Flight Name</th>");
		out.println("<th>Start Source</th>");
		out.println("<th>Final Destination</th>");
		out.println("<th>Date Of Travel</th>");
		out.println("<th>Booking ID</th>");
		out.println("<th>Seat Number</th>");
		out.println("<th>Passenger Name</th>");
		out.println("<th>Passenger Age</th>");
		out.println("<th>Passenger Gender</th>");
		out.println("</tr>");
		String htmlString=" ";
	     for(Object[] detail: details){
	    	 
	    	 String flight_number = detail[0].toString();
	    	 String flight_name = detail[1].toString();
	    	 String start = detail[2].toString();
	    	 String final_des = detail[3].toString();
	    	 Date dot = (Date) detail[4];
	    	 String b_id = detail[5].toString();
	    	 int b_id1 = Integer.parseInt(b_id);
	    	 String seat_num = detail[6].toString();
	    	 int seat = Integer.parseInt(seat_num);
	    	 String name = detail[7].toString();
	    	 String age = detail[8].toString();
	    	 int ag = Integer.parseInt(age);
	    	 //Integer seat_num = (Integer) detail[6];
	    	 //Integer age = (Integer) detail[7];
	    	 String gender = detail[10].toString();
	    	  htmlString += "<tr>\n"
	    				+"<td>"+flight_number+"</td>\n"
	    				+"<td>"+flight_name+"</td>\n"
	    				+"<td>"+start+"</td>\n"
	    				+"<td>"+final_des+"</td>\n"
	    				+"<td>"+dot+"</td>\n"
	    				+"<td>"+b_id1+"</td>\n"
	    				+"<td>"+seat+"</td>\n"
	    				+"<td>"+name+"</td>\n"
	    				+"<td>"+ag+"</td>\n"
	    				+"<td>"+gender+"</td>\n"
	    				+"</tr>\n";
	    				
	    				out.println(htmlString);
	    				 
	    	 
	    	 
	     }
	    String htm=" ";
	     htm += "<form method=\"post\" action=\"logoutservlet\">"
			+"<td><button >GO BACK</button></td>\n"
			+"</form>";
	     out.println(htm);
	     out.println("</body></html>");
	     tx.commit();
	    
	}

}
