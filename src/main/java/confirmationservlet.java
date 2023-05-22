

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
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
 * Servlet implementation class confirmationservlet
 */
@WebServlet("/confirmationservlet")
public class confirmationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmationservlet() {
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
	@SuppressWarnings({ "unchecked", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

     PrintWriter out= response.getWriter();;
     SessionFactory factory = HibernateUtil.getfactorysession();
	 Session session = factory.openSession();
	 ServletContext servletcontext = getServletContext();
	 String flight_number = servletcontext.getAttribute("n").toString();
	 //String members = (String) servletcontext.getAttribute("members");
		//String seats_available=(String) servletcontext.getAttribute("se");
		//int seats = Integer.parseInt(seats_available);
		//int m = Integer.parseInt(members);
	  
	  
	String username =  servletcontext.getAttribute("username").toString();
	/* out.println("<html><body>");
	 out.println(flight_number+" "+username);
	 out.println("</html></body>"); */
	 
	 
     Query q1 = session.createQuery("select booking_ID from booking where username=:user").setMaxResults(1);
     q1.setParameter("user",username);
     List<Integer> booking_id = q1.list(); 
     /*Query q2 = session.createQuery("select flight_number from booking where username=:user and booking_ID=:book_id").setMaxResults(1);
     q2.setParameter("user",username);
     q2.setParameter("book_id", booking_id.get(0));
     List<String> flight_num = q2.list();  */
     
     Query q5 = session.createQuery("select seq from booking where booking_ID=:id order by seq desc").setMaxResults(1);
     q5.setParameter("id",booking_id.get(0));
     List<Integer> sequence=q5.list();
     Integer m = sequence.get(0);
     Query q6 = session.createQuery("select seats_available from Flights where flight_number=:flight_num");
     q6.setParameter("flight_num", flight_number);
     List<Integer> number= q6.list();
     Integer seats = number.get(0);
     if(seats>m) {
    	 Transaction tx = session.beginTransaction();
	 Query q3 = session.createQuery("update Flights set seats_available=:seats where flight_number=:flight_num ");
	 q3.setParameter("seats", seats-m);
	 q3.setParameter("flight_num", flight_number);
	 q3.executeUpdate();
	 tx.commit();
	 
     }
     Query q4 = session.createQuery("select ticket_price from Flights where flight_number=:flight_num");
     q4.setParameter("flight_num",flight_number);
     
     List<Integer> price = q4.list();
     out.println("<html><body>");
     out.println("<h2><font color=green>BOOKING IS SUCCESSFULY COMPLETED</h2><br>");
     out.println("<h3>TOTAL NO OF TICKETS "+m+"</h3><br>");
     out.println("<h3>BOOKING ID IS "+booking_id.get(0)+"</h3><br>");
     int total_price = price.get(0)*m;
     out.println("<h3>TOTAL AMOUNT FOR "+m+" TICKETS IS "+total_price+"</h3><br>");
     out.println("<form action=\"logoutservlet\" method=\"GET\">");
     out.println("<button>LOGOUT</button>");
     //out.println("<button><a href=\"mainpage.html\">LOG OUT</a></button>");
     out.println("</body></html>");    
     
	}

}
