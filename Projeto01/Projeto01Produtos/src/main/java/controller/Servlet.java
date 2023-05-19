package controller;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Servlet", "/main", "/inserir", "/selecionar", "/atualizarProduto", "/delete" })
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans cadastro = new JavaBeans();

	public Servlet() {
		super();

	}

//METODOS REQUISIÇÃO
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			produtos(request, response);
		} else if (action.equals("/inserir")) {
			novoCadastro(request, response);
		} else if (action.equals("/selecionar")) {
			listarProdutos(request, response);
		} else if (action.equals("/atualizarProduto")) {
			editarProdutos(request, response);
		} else if (action.equals("/delete")) {
			deletarproduto(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}

	}

// lista Produtos
	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// criar objeto que ira receber o objeto que vai receber o s dados javabeans

		ArrayList<JavaBeans> lista = dao.listarprodutos();
		// encaminhar a lista ao index

		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("controladorprodutos.jsp");
		rd.forward(request, response);
	}

//Novo cadastro
	protected void novoCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar variaves teve que converter
		cadastro.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		cadastro.setNome(request.getParameter("nome"));
		cadastro.setCategoria(request.getParameter("categoria"));
		cadastro.setValor(Float.parseFloat(request.getParameter("valor")));
		cadastro.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

		// invocar o meto inserirproduto
		dao.inserirproduto(cadastro);
		// redirecionar para o cadastro.jsp
		response.sendRedirect("main");

	}
	
	//Editando o produto
	protected void listarProdutos (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recebimento do id do contato que vai ser modificado
		String id = request.getParameter("id");
		System.out.println(id);
		// setar a variavel
		cadastro.setId(id);
		// metodo de selecionar o produto DAO
		dao.selecionarproduto(cadastro);
		// setar os atributos do cadastro
		
		request.setAttribute("id", cadastro.getId());
		request.setAttribute("codigo", cadastro.getCodigo());
		request.setAttribute("nome", cadastro.getNome());
		request.setAttribute("categoria", cadastro.getCategoria());
		request.setAttribute("valor", cadastro.getValor());
		request.setAttribute("quantidade", cadastro.getQuantidade());
		//enviar ao doc editor.jsp
		RequestDispatcher rd = request.getRequestDispatcher("Editor.jsp");
		rd.forward(request, response);
	}
	
	protected void editarProdutos (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// setar variaveis java
		cadastro.setId(request.getParameter("id"));
		cadastro.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		cadastro.setNome(request.getParameter("nome"));
		cadastro.setCategoria(request.getParameter("categoria"));
		cadastro.setValor(Float.parseFloat(request.getParameter("valor")));
		cadastro.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		// executar a alteracao
		dao.editarproduto(cadastro);
		// mandar para a pagina controlador com atualizado
		
		response.sendRedirect("main");
		
		
	}
	
	protected void deletarproduto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//recebimento do id a ser excluido
		String id =request.getParameter("id");
		// setar a variavel id
		cadastro.setId(id);
		// metodo deletar
		dao.deletarproduto(cadastro);
		
	// mandar para a pagina controlador com atualizado
		
		response.sendRedirect("main");
		
		
}	
	
	
	
}
