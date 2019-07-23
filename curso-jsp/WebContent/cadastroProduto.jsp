<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="https://fonts.googleapis.com/css?family=Hind+Madurai"
	rel="stylesheet">
<meta charset="utf-8" />
<title>Cadastro de Produtos</title>
</head>
<body>
	<a href="acessoliberado.jsp">Inicío</a>
	<center>
		<h1>Cadastrar Produtos</h1>
	</center>
	<h3>${msg}</h3>
	<form action="salvarProduto" method="post" id="formProduto"
		onsubmit="return validarCampos() ? true : false">
		<center>
			<table class="blueTable">
				<tr>
					<td>Id:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${produto.id}"></td>
				</tr>
				<tr>

					<td>Codigo:</td>
					<td><input type="text" id="codigo" name="codigo"
						value="${produto.codigo}"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${produto.nome}"></td>
				</tr>
				<tr>
					<td>Quantidade:</td>
					<td><input type="text" id="quantidade" name="quantidade"
						value="${produto.quantidade}"></td>
				</tr>
				<tr>
					<td>Preço:</td>
					<td><input type="text" id="preco" name="preco"
						value="${produto.preco}"></td>
				</tr>
			</table>
			<input type="submit" value="Salvar Usuário" class="butn"> <input
				type="submit" value="Cancelar" class="butn"
				onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'">
	</form>
	</center>

	<table class="blueTable">
		<tr>
			<th>ID</th>
			<th>Codigo</th>
			<th>Produto</th>
			<th>Quantidade</th>
			<th>Preço R$</th>
			<th>Deletar</th>
			<th>Editar</th>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td style="width: 150px; border: 2px solid #778899;"><c:out
						value="${produto.id}"></c:out></td>
				<td style="width: 150px; border: 2px solid #778899;"><c:out
						value="${produto.codigo}"></c:out></td>
				<td style="border: 2px solid #778899"><c:out
						value="${produto.nome}"></c:out></td>
				<td style="border: 2px solid #778899"><c:out
						value="${produto.quantidade}"></c:out></td>
				<td style="border: 2px solid #778899"><c:out
						value="${produto.preco}"></c:out></td>
				<td><a href="salvarProduto?acao=delete&produto=${produto.id}"><img
						src="http://icons.iconarchive.com/icons/hopstarter/button/48/Button-Close-icon.png"
						alt="deletar" title="Deletar" width="32px" height="32px" /></a></td>
				<td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img
						src="http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/48/Actions-document-edit-icon.png"
						alt="editar" title="Editar" width="32px" height="32px" /></a></td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("codigo").value == "") {
				alert('Informe o codigo');
				return false;
			} else if (document.getElementById("nome").value == "") {
				alert('Informe o nome do produto');
				return false;
			} else if (document.getElementById("quantidade").value == "") {
				alert('Informe a quantidade de produto');
				return false;
			} else if (document.getElementById("preco").value == "") {
				alert('Informe o preço do produto');
				return false;
			}
			return true;
		}
	</script>
</body>
</html>