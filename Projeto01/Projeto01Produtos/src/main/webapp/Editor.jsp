<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Controlador de produtos</title>
<link rel="icon" href="webapp/phone1.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Produto</h1>
	<form name="frmcadastro" action="atualizarProduto">
		<table>
			<tr>
				<td><span> Id </span><input type="text" name="id" class="Caixa1" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><span> Codigo </span><input type="text" name="codigo" placeholder="codigo" class="Caixa1" value="<%out.print(request.getAttribute("codigo"));%>"> </td>
			</tr>
			<tr>
				<td><span> Nome </span><input type="text" name="nome" placeholder="Nome" class="Caixa2" value="<%out.print(request.getAttribute("nome"));%>">
				<td>
			</tr>
			<tr>
				<td><span> Categoria </span><input type="text" name="categoria" placeholder="categoria" class="Caixa3" value="<%out.print(request.getAttribute("categoria"));%>">
				<td>
			</tr>
			<tr>
				<td><span> Valor </span><input type="text" name="valor" placeholder="valor" class="Caixa4" value="<%out.print(request.getAttribute("valor"));%>">
				<td>
			</tr>
			<tr>
				<td><span> Quantidade </span><input type="text" name="quantidade" placeholder="quantidade" class="Caixa5" value="<%out.print(request.getAttribute("quantidade"));%>">
				<td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="botao1" onclick="Validar()">
	</form>
	<script src="scripts/Validar.js"></script>
</body>
</html>