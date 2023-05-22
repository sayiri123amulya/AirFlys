

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class registerationservlet
 */
@WebServlet("/registerationservlet")
public class registerationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerationservlet() {
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
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		PrintWriter out=response.getWriter();
		
		SessionFactory factory = HibernateUtil.getfactorysession();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Query query= session.createQuery("from LoginDetails");
		@SuppressWarnings("unchecked")
		List<LoginDetails> list1=query.list();
		boolean f = false;
		for(LoginDetails l2: list1) {
			if(username.equals(l2.getUsername()) || firstname.equals(l2.getFirstname()) || lastname.equals(l2.getLastname())) {
				
				f=true;
			}
		}
		if(f) {
			out.println("<html><body>");
			out.println("<h3><font colo=redr>USERNAME IS ALREADY EXISTS, PLEASE USE DIFFERENT USERNAME</font></h3>");
			RequestDispatcher requestDispatcher = 
	    			request.getRequestDispatcher("/register.html");
			requestDispatcher.include(request,response); 
			//response.sendRedirect("register.html");
			out.println("</body></html>");
			
		}
		else {
		LoginDetails ld = new LoginDetails();
		ld.setFirstname(firstname);
		ld.setLastname(lastname);
		ld.setUsername(username);
		ld.setPassword(password);
		session.save(ld);
		tx.commit();
		out.println("<html><body>");
		out.println("<h3><font color=green>REGISTERED SUCCESSFULLY, YOUR ID IS  "+ld.getId()+"</font><br><br>");
		
		
		out.println("<button><a href=\"mainpage.html\">LOGIN PAGE</a></button>");
		//out.println("<h1><a href =\"loginservlet\">Click here to login</a></h1>");
		out.println("</body></html>");
		}
		
	}

}