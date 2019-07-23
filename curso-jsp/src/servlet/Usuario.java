package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bean;
import dao.daoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private daoUsuario daoUsuario = new daoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		try {
			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(Integer.parseInt(user));
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());
				view.forward(request, response);
			}
			if (acao.equalsIgnoreCase("editar")) {
				Bean bean = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", bean);
				view.forward(request, response);
			}
			if (acao.equalsIgnoreCase("listartodos")) {
				// Bean bean = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			Bean usuario = new Bean();

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0); // ser o campo ID ficar vazio seta 0
			usuario.setUsuario(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);

			try {

				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "Usuário já existe com o mesmo login, usuario não validado!");
				}

				/*if (id == null || id.isEmpty() && !daoUs uario.validarSenha(senha)) {
					request.setAttribute("msg", "Usuário já existe com o mesmo login, usuario não validado!");
				}*/

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login)) {
					if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
						request.setAttribute("msg", "Usuário já existe com a mesma senha, usuario não validado!");
					} else {
						daoUsuario.salvar(usuario);
					}

				} else if (id != null && !id.isEmpty()) {
					if (!daoUsuario.validarUpdate(login, id)) {
						request.setAttribute("msg", "Usuário já existe com o mesmo login, editar não validado!");
					} else if (!daoUsuario.validarSenha(senha)) {
						request.setAttribute("msg", "Usuário já existe com a mesma senha, editar não validado!");
					} else {
						daoUsuario.editar(usuario);
					}
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
