package marksheetPkg;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pst,prt;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc=getServletContext();
		//int n=10;
		
		//response.getWriter().println("<html><body><center><h1>Val : "+n +"</h1></center></body></html>");
	
		pst=(PreparedStatement)sc.getAttribute("pst");
		prt=(PreparedStatement)sc.getAttribute("prt");
		
		PrintWriter out = response.getWriter();
		String rno;
		rno=(String)sc.getAttribute("roll");
	    
	    try
		{
	     pst.setString(1,rno);
		 ResultSet rs1=pst.executeQuery();
		 System.out.println("Rs1 : wrong");
		 
		 
		 prt.setString(1,rno);
		 ResultSet rs2=prt.executeQuery();
		 rs2.next();
		 System.out.println("Rs2 : wrong");
		
		 System.out.println("LEts go");
		 
		 out.print("\t\t\t\tResult");
		 out.print("\n\t\t Roll Number \t"+rno+"\t");
		 //out.print("\t Name \t"+nam+"\t\t");
		 out.print("\n\t\t Sem\t");
		 out.print(" Sub1\t");
		 out.print(" Sub2\t");
		 out.print(" Sub3\t");
		 out.print(" Total\t");
		 while(rs1.next())
		 {
		  out.print("\n\t\t"+rs1.getInt(1)+"\t");
		  out.print(rs1.getInt(2)+"\t");
		  out.print(rs1.getInt(3)+"\t");
		  out.print(rs1.getInt(4)+"\t");
		  out.print(rs1.getInt(5)+"\t");
		 }
		}
	    catch(SQLException e)
		{
		 System.out.println("SQL Alert This-"+e.getMessage());
		 System.out.println("sorry try again");
		}
	    response.setContentType("application/vnd.ms-excel");
	    response.setHeader("content-disposition", "attachment;filename=marks.xls");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
