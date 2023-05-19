<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.JavaBeans"%>
    <%@ page import="java.util.ArrayList"%>
<% 
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("produtos");
//for (int i = 0; i < lista.size(); i++){
	//out.println(lista.get(i).getId());
	//out.println(lista.get(i).getCodigo());
	//out.println(lista.get(i).getNome());
	//out.println(lista.get(i).getCategoria());
	//out.println(lista.get(i).getValor());
//	out.println(lista.get(i).getQuantidade());

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">

<title>Controlador de produtos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1> Selecione a opção desejada </h1>
	
	<a href="Novocadastrodeproduto.jsp" class="botao1"> Cadastrar novo produto </a>
	<table id="tab">
	
		<thead>
			<tr>
				<th>ID </th>
				<th>Codigo </th>
				<th>Nome</th>
				<th>Categoria </th>
				<th>Valor</th>
				<th>Quantidade</th>
				<th>Opção </th>
			</tr>	
		</thead>
		<tbody>
				<%for (int i = 0; i < lista.size(); i++) { %>
					<tr>
						<td><%=lista.get(i).getId()%>
						<td><%=lista.get(i).getCodigo()%>
						<td><%=lista.get(i).getNome()%>
						<td><%=lista.get(i).getCategoria()%>
						<td><%=lista.get(i).getValor()%>
						<td><%=lista.get(i).getQuantidade()%>
						<td><a href="selecionar?id=<%=lista.get(i).getId() %>" class="botao1">Editar</a>
						
						<a href="javascript:confirmar(<%=lista.get(i).getId()%>)" class="botao2">Excluir</a>
						
					</tr>
				<%} %>
		</tbody>
	
		</table>
	
	 <script src="scripts/Confirmador.js"></script>
</body>
</html>