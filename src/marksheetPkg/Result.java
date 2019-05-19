package marksheetPkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PreparedStatement pst,prt;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try
		{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dmatics","root","shubham@mysql");
		  pst=cn.prepareStatement("select sem,sub1,sub2,sub3,tot from markx where rno=?");
		  prt=cn.prepareStatement("select fname,lname from studs where rno=?");
		  
		}  
		
		catch(ClassNotFoundException e)
		{
			System.out.println("Driver not found -"+e.getMessage());
		}
		
		catch(SQLException e)
		{
			System.out.println("SQL Alert[1] -"+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String roll,dob;
		
		ResultSet rs,rr;
		roll=request.getParameter("roll");
		dob=request.getParameter("dob");
		ServletContext sc=getServletContext();
	    sc.setAttribute("pst",pst);
		sc.setAttribute("prt",prt);
		sc.setAttribute("roll",roll);
		
		String fname=null,lname=null;
	
		try
		{
		    pst.setString(1,roll);
	     	prt.setString(1,roll);
		
		    
		    rr=prt.executeQuery();
		    rr.next();
		    
		 
		  fname=rr.getString(1);
		  lname=rr.getString(2);
		 System.out.println("first name " +fname);

		}
		
		catch (SQLException e) 
		 {
		  System.out.println("SQL Alert[2] - "+e.getMessage());
	 	 } 
		
		
		String html="";
		html="<html>"+
		     "<head><title>Results</title></head>"+
				"<body bgcolor=yellow>"+
		     "<style></style>"+
				"<center>"+
				"<h1 style=\"color:blue\">Result of the Student</h1>"+
				"<font>Name of Student :&nbsp;"+ fname + " "+ lname +"</font><br>"+
				"<font>Roll Number  of Student :&nbsp;"+ roll +"</font><br>"+
		     "<table>"+
				"<tr>"+
		          "<td>Sem&nbsp;</td>"+
				  "<td>Sub1&nbsp;</td>"+
				  "<td>Sub2&nbsp;</td>"+
				  "<td>Sub3&nbsp;</td>"+
				  "<td>tot</td>"+
				 "</tr>";
		       try
		       {
		    	rs=pst.executeQuery();
				while(rs.next())
				{
					html=html+
							"<tr style=\"fontcolor:red\" >"+
							"<td>"+rs.getInt(1)+"</td>"+
							"<td>"+rs.getInt(2)+"</td>"+
							"<td>"+rs.getInt(3)+"</td>"+
							"<td>"+rs.getInt(4)+"</td>"+
							"<td>"+rs.getInt(5)+"</td>"+
							"</tr>";
				}
		       } 
					
		       catch (SQLException e) 
				 {
				  System.out.println("SQL Alert[2] - "+e.getMessage());
			 	 } 
		       
		       html=html+
		    		   "</table>"+
		    		   "<br><br>"+
		    		   "<form  action=\"Download\"> "+
		    		   "<input type=\"submit\" name=\"submit\" value=Download "+
		    		   "</form>"+
		    		   "</center>"+
		    		   "</body>"+
		    		   "</html>";
		       
		      
		       
		       
		    		   						 
		       response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
