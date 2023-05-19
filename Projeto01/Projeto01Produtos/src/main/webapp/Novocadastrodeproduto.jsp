<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Controlador de produtos</title>
<link rel="icon" href="webapp/phone1.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1> Criar Novo Cadastro de produto </h1>
	<form name="frmcadastro" action="inserir">
		<table>
			<tr>
				<td><input type="text" name="codigo" placeholder="Codigo" class="Caixa1"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" placeholder="Nome" class="Caixa2"><td>
			</tr>
			<tr>
				<td><input type="text" name="categoria"  placeholder="Categoria" class="Caixa3"><td>
			</tr>
			<tr>
				<td><input type="text" name="valor"  placeholder="Valor" class="Caixa4"><td>
			</tr>
			<tr>
				<td><input type="text" name="quantidade"  placeholder="Quantidade" class="Caixa5"><td>
			</tr>
		</table>
		<input type="button" value="Adicionar" class="botao1" onclick="Validar()">
		</form>
<script src="scripts/Validar.js"></script>
</body>
</html>