

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Servlet implementation class Insertingdataservlet
 */
@WebServlet("/Insertingdataservlet")
public class Insertingdataservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int Transaction;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertingdataservlet() {
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
		String  passenger_name = request.getParameter("passenger_name");
		String  passenger_age = request.getParameter("passenger_age");
		int age=Integer.parseInt(passenger_age);
		String  passenger_unique_id = request.getParameter("passenger_unique_id");
		String  passenger_gender = request.getParameter("passenger_gender");
		String user = request.getParameter("username");
		
		ServletContext servletcontext = getServletContext();
		String flight_number = servletcontext.getAttribute("n").toString();
		//String username =  servletcontext.getAttribute("username").toString();
		String members = servletcontext.getAttribute("members").toString();
		String seats_available=(String) servletcontext.getAttribute("se");
		int seats = Integer.parseInt(seats_available);
		int m = Integer.parseInt(members);
		
			
		
		PrintWriter out = response.getWriter();
		SessionFactory factory = HibernateUtil.getfactorysession();
		Session session = factory.openSession();
		
		Transaction tc =session.beginTransaction();
	    if(seats>m) {
	    
	    int booking_id; int seat_number = 0,seq;
	    
		
		
	    Query q = session.createQuery("select distinct booking_ID from booking  where username=:u");
	    Query q2 = session.createQuery("select distinct flight_number from booking  where username=:u");
	    Query q1 = session.createQuery("select seat_number from booking  where username=:u order by seat_number desc").setMaxResults(1);
	    Query q3 = session.createQuery("select seat_number from booking order by seat_number desc").setMaxResults(1);
	    Query q4 = session.createQuery("select seq from booking  where username=:u order by seq desc").setMaxResults(1);
	    q4.setParameter("u", user);
	    q1.setParameter("u", user);
	    q.setParameter("u", user);
	    q2.setParameter("u", user);
	   
		List<Integer> id = q.list();
	   
		List<Integer> seat = q1.list();
	    List<String> flight_num = q2.list();
	    List<Integer> max_seat=q3.list();
	    List<Integer> sequence = q4.list();
	    
	  /*  if(id.isEmpty()) {
	    	seq=1;
        out.println("<html><body>");
		out.println(seq);
        out.println("<body></html>");
	    }
	    else {
	    	out.println("<html><body>");
			out.println((sequence.get(0))+1);
	        out.println("<body></html>"); */
	    //}
	    if(id.isEmpty()) {
	    	
	    	int m1=1;
	    	 Random r = new Random();
	 	     booking_id = r.nextInt(20000);
	 	    if(max_seat.isEmpty()) {
	 	     seat_number = m1;
	 	     }
	 	   // if ((max_seat.isEmpty())) 
	 	    else{
	 	    	 
	 	    	seat_number=max_seat.get(0)+1;
	 	     }
	 	     seq=1;
	 		booking b = new booking();
	 		b.setUsername(user);	
	 	    b.setBooking_ID(booking_id);
	 		b.setFlight_number(flight_number);
	 		b.setPassenger_name(passenger_name);
	 		b.setPassenger_age(age);
	 		b.setPassenger_unique_id(passenger_unique_id);
	 		b.setPassenger_gender(passenger_gender);
	 		b.setSeat_number(seat_number);
	 		b.setSeq(seq);
	 		session.save(b);
	 		
	    }
	    else{
	    	
	        
	    	
	 		booking b = new booking();
	 		b.setUsername(user);	
	 	    b.setBooking_ID(id.get(0));
	 		b.setFlight_number(flight_num.get(0));
	 		b.setPassenger_name(passenger_name);
	 		b.setPassenger_age(age);
	 		b.setPassenger_unique_id(passenger_unique_id);
	 		b.setPassenger_gender(passenger_gender);
	 		b.setSeat_number(max_seat.get(0)+1);
	 		b.setSeq(sequence.get(0)+1);
	 		session.save(b);
	 		
	    }
	   
		out.println("<html><body>");
		
		/*out.println("<form method=\"post\" action=\"\" >");
		out.println("<buttom>Previous Page</button>");
		out.println("</form>");*/
		Transaction tx1 = session.beginTransaction();
		int i = 0;
		if(sequence.isEmpty()) {
			i=1;
		}
		else {
		 i=sequence.get(0)+1;
		} 
		
			if(i<m) {
		//response.sendRedirect("DetailsFilling.html");
				out.println("<h3><font color=green>SUCCESSFULLY UPLOADED, PLEASE FILL OTHER PASSENDER DETAILS</font><h3><br>");
				out.println("<a href=\"DetailsFilling.html\">Click here to enter remaining passenger details</a><br>");
				
			}
			if(i==m){
				out.println("<h3><font color=green>THANKS FOR ENTERING THE DETAILS, PLEASE CLICK ON BOOK TO PROCEED</font></h3><br>");
				out.println("<form name=\"confirmation\" method=\"post\" action=\"confirmationservlet\" >");
				out.println("<button>Book</BUTTON><br>");
				out.println("</form>");
			}
		
		/*RequestDispatcher requestDispatcher = 
    			request.getRequestDispatcher("Insertingdataservlet");
		requestDispatcher.include(request,response);  */
		
		out.println("</body></html>"); 
		
	}
	    else {
	    	response.sendRedirect("mainpage.html");
	    }
 
	    tc.commit();
}
}
