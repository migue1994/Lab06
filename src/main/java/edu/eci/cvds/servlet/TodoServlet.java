package edu.eci.cvds.servlet;

import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.eci.cvds.servlet.model.Todo;


@WebServlet(
    urlPatterns = "/todoServlet"
)

public class TodoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lanza el error para que aparezca en la página.
	 * @param res es el HttpServletResponse.
	 * @param message es el mensaje que se quiere que aparezca en la página.
	 * @param error es el número del error. Ej:404, 400, etc..
	 */
	private void sendError(HttpServletResponse res, String message, int error) {
		try {
			res.sendError(error, message);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		String ids=req.getParameter("id");
		if(ids.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			sendError(res,"The requested page is invalid: id is missing",HttpServletResponse.SC_BAD_REQUEST);

		}
		else {
			res.setContentType("text/html");
		    Writer resWriter=res.getWriter();
			try {
				int id; Todo todo=null;
				
				if(ids.equals("0")) id=-1; 
				else {
					try{
						id=Integer.parseInt(ids);
						todo=Service.getTodo(id);
					}
					catch(Exception e) {
						id=-1;
					}
				}
				
				if(id==-1) {
					res.setStatus(HttpServletResponse.SC_NOT_FOUND);
					sendError(res,"The requested page with id ["+ ids+"] not found.",HttpServletResponse.SC_NOT_FOUND);
				}
				else {
					res.setStatus(HttpServletResponse.SC_OK);
					List<Todo> todoList=new ArrayList<Todo>();
					todoList.add(todo);
					String ans=Service.todosToHTMLTable(todoList);
					resWriter.write(ans);
				}
			}
			catch(NumberFormatException e) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				sendError(res,"The id ["+ ids+"] has an invalid format",HttpServletResponse.SC_BAD_REQUEST);
			}
			catch(MalformedURLException e) {
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				sendError(res,"Internal Server Error",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			catch(Exception e) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				sendError(res,"The requested page with id ["+ ids+"] is invalid.",HttpServletResponse.SC_BAD_REQUEST);

			}
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doGet(req,res);
	}
	
}
