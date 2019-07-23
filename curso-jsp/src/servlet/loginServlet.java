package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bean;
import dao.daoLogin;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private daoLogin daologin = new daoLogin();

	public loginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		Bean bean = new Bean(); 
		
		String usuario = request.getParameter("usuario"); //pega o usuario do html
		String senha = request.getParameter("senha"); //pega o senha do html
		
		if (daologin.validarLogin(usuario, senha)) {//acesso ok
			RequestDispatcher disp = request.getRequestDispatcher("acessoliberado.jsp");
			disp.forward(request, response);
		}else { //acesso negado
			RequestDispatcher disp = request.getRequestDispatcher("acessonegado.jsp");
			disp.forward(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
