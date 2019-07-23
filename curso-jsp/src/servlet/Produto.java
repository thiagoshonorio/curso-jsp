package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.daoProduto;
import beans.BeanProduto;

@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private daoProduto DaoProduto = new daoProduto();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				DaoProduto.delete(produto);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", DaoProduto.list());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanProduto beanProduto = DaoProduto.consult(produto);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", beanProduto);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", DaoProduto.list());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao != null && acao.equalsIgnoreCase("reset")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", DaoProduto.list());
				view.forward(request, response);
			} else {
				String id = request.getParameter("id");
				String codigo = request.getParameter("codigo");
				String nome = request.getParameter("nome");
				String quantidade = request.getParameter("quantidade");
				String preco = request.getParameter("preco");
				String msg = null;
				boolean podeInserir = true;

				if (preco == null || preco.isEmpty()) {
					msg = "O preço deve ser informado";
					podeInserir = false;
				//	request.setAttribute("msg", msg);
				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "A quantidade do produto deve ser informada";
					podeInserir = false;
				//	request.setAttribute("msg", msg);
				} else if (nome == null || nome.isEmpty()) {
					msg = "O nome do produto deve ser informado";
					podeInserir = false;
					//request.setAttribute("msg", msg);
				} else if (codigo == null || codigo.isEmpty()) {
					msg = "O codigo do produto deve ser informado";
					podeInserir = false;
					//request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && !DaoProduto.validateName(nome)) {
					msg = "O produto já existe";
					podeInserir = false;
				//	request.setAttribute("msg", msg);
				} else if (codigo == null || codigo.isEmpty() && !DaoProduto.validateCod(codigo)) {
					msg = "O codigo já existe";
					podeInserir = false;
					//request.setAttribute("msg", msg);
				}

				BeanProduto produto = new BeanProduto();
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
				produto.setCodigo(codigo);
				produto.setNome(nome);
				produto.setQuantidade(Integer.parseInt(quantidade));
				produto.setPreco(Double.parseDouble(preco));

				if (quantidade != null && quantidade.isEmpty()) {
					produto.setQuantidade(Integer.parseInt(quantidade));
				}
				if (preco != null && preco.isEmpty()) {
					produto.setPreco(Double.parseDouble(preco));
				}

				
				if (id == null || id.isEmpty() && DaoProduto.validateName(nome) && podeInserir) {
					DaoProduto.insert(produto);
				} 
				
				if (id != null && !id.isEmpty() && podeInserir) {
					DaoProduto.update(produto);
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}
				
				if (msg != null) {
					request.setAttribute("msg", msg);
				} 

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", DaoProduto.list());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
