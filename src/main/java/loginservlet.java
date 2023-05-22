

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * Servlet implementation class RegisterOrLogin
 */
@WebServlet("/RegisterOrLogin")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
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
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		String first = null,last = null;
		SessionFactory factory = HibernateUtil.getfactorysession();
		Session session = factory.openSession();
		
		Query query= session.createQuery("from LoginDetails where username=:user");
		query.setParameter("user", username);
		@SuppressWarnings("unchecked")
		List<LoginDetails> list1=query.list();
		//Query query= session.createQuery("select first from LoginDetails where username=:user");
		//query.setParameter("user", username);
		if(list1 == null) {
			out.println("no data");
		}
		boolean f = false;
		if(username!=null) {
			for(LoginDetails l:list1) {
				if(username.equals(l.getUsername()) && password.equals(l.getPassword())) {
					//f=true;
					first=l.getFirstname();
					last=l.getLastname();
					HttpSession sess = request.getSession();
					sess.setAttribute("username",username);
					ServletContext servletcontext = getServletContext();
					servletcontext.setAttribute("first", first);
					servletcontext.setAttribute("last", last);
					servletcontext.setAttribute("username", username);
					out.println("<html><body>");
					out.println("<marquee><h3><font color=green><h2>HI "+first+" "+last+"</font></h3></marquee>");
					RequestDispatcher requestDispatcher = 
			    			request.getRequestDispatcher("/AirlinesPortal.html");
					requestDispatcher.include(request,response); 
					//response.sendRedirect("AirlinesPortal.html");
					out.println("</body></html>");
				}
				/*if ((!(username.equals(l.getUsername())) && (password.equals(l.getPassword()))) || ((username.equals(l.getUsername())) && !(password.equals(l.getPassword())))) {
					out.println("<marquee><h2>Invalid Username/Passowrd, Please re-enter the Valid Credentials to login</h2></marquee>");
					RequestDispatcher requestDispatcher = 
			    			request.getRequestDispatcher("/mainpage.html");
					requestDispatcher.include(request,response);
				}
				if (!((username.equals(l.getUsername())) && (password.equals(l.getPassword())))) */
				else{
					out.println("<html><body>");
					out.println("<font color=red><Marquee><h2>USER IS NOT REGISTERED OR ENTERED INCORRECT CREDENTIALS</h2></Marquee></font>");
					RequestDispatcher requestDispatcher = 
			    			request.getRequestDispatcher("/mainpage.html");
					requestDispatcher.include(request,response); 
					out.println("</html></body>");
					
				}
			}
		/*	if(f) {
				
				ServletContext servletcontext = getServletContext();
				servletcontext.setAttribute("username", username);
				HttpSession sess = request.getSession();
				sess.setAttribute("firstname",first);
				sess.setAttribute("lastname", last);
				response.sendRedirect("AirlinesPortal.html");
				/*out.println("Hi "+" "+first+" "+last);
				out.println("<h1> Welcome to Airline booking</h1>");
				out.println("<form name=\"booking\" method=\"post\" action=\"booking\">");
				out.println("<input type=\"text\" name=\"source\" placeholder=\"source\">");
				out.println("<input type=\"text\" name=\"destination\" placeholder=\"destination\"><br>");
				out.println("</form>"); 
				
			} */
		}
		
	}

}
