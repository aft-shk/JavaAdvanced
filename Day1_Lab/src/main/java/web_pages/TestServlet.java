package web_pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/hello")
/*
 * default value of webservlet is "value"
 * run time, class level and mandatory annotation meant for WC
 * When you deploy a web app , WC created a Map
 * key ---url pattern (/hello)
 * value -- fully qualified servelet class  name =>(web_pages.TestServlet)
 * URL -- http://host:port//Day1_lab/hello
 * http -applcn layer protocol or called as scheme
 * host - can be either qualified host name(www.abc.com) or IP Address
 * port - TCP layer port no ( def - 8080)
 * uri - uniform resource identifier 
 * 	- /Day1_lab - context(=web app)root
 *	/hello -- url pattern
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		System.out.println("in init");
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.set resp content type
		response.setContentType("text/html");
		System.out.println("in doGet");
		//2.get the printwriter from httpservletresponse to send the buffered text contents
		//--> to the client
		try(PrintWriter pw = response.getWriter()){
			//add dyn contents to pw's buffer
			pw.print("<h4> Hello from servlets..."+ LocalDateTime.now()+"</h4>");
		}
	}

}
