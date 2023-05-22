

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
 * Servlet implementation class changepassword
 */
@WebServlet("/changepassword")
public class changepasswordservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepasswordservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String currentpassword =request.getParameter("password");
		String newpassword=request.getParameter("newpassword");
		PrintWriter out = response.getWriter();
		SessionFactory factory = HibernateUtil.getfactorysession();
		Session s=factory.openSession();
		out.println("<html><body>");
		
		Query q=s.createQuery("from LoginDetails");
		@SuppressWarnings("unchecked")
		List<LoginDetails> list3 = q.list();
		boolean f=false;
		for(LoginDetails ld1: list3) {
			if(username.equals(ld1.getUsername()) && currentpassword.equals(ld1.getPassword())) {
				f=true;
			}
			/* else if (username!=ld1.getUsername() || currentpassword!=ld1.getPassword()) {
				out.println("invalid username/passowrd");
			} */
		}
		if(f) {
			String sql = "update LoginDetails l set l.password=:pass where l.username=:name";
			Transaction tx = s.beginTransaction();
			Query q2 = s.createQuery(sql);
			q2.setParameter("pass", newpassword);
			q2.setParameter("name", username);
			q2.executeUpdate();
			tx.commit();
			out.println("<html><body>");
			out.println("<h2><font color=green>CHANGED PASSWORD SUCCESSFULLY</font></h2><br>");
			//out.println("<h2><a href=\"mainpage.html\">LoginPage</a></h2>");
			RequestDispatcher requestDispatcher = 
	    			request.getRequestDispatcher("/mainpage.html");
			requestDispatcher.include(request,response); 
			out.println("</body></html>");
		}
		else {
			out.println("<html><body>");
			out.println("<h2><font color=RED>PLEASE ENTER CORRECT DETAILS</font></h2><br>");
			RequestDispatcher requestDispatcher = 
	    			request.getRequestDispatcher("/ChangePassword.html");
			requestDispatcher.include(request,response); 
		}
		
	}

}
