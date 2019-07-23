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

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<title>Cadastro de usuario</title>
</head>
<body>
	<a href="acessoliberado.jsp">Inicío</a>
	<center>
		<h1>cadastrar usuario</h1>
	</center>
	<h3>${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false">
		<center>
			<table class="blueTable">
				<tr>
					<td>Id:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${user.id}"></td>
				</tr>
				<tr>

					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.usuario}"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"
						value="${user.senha}"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${user.nome}"></td>
				</tr>
				<tr>
					<td>Telefone:</td>
					<td><input type="text" id="telefone" name="telefone"
						value="${user.telefone}"></td>
				</tr>
				<tr>
					<td>Cep:</td>
					<td><input type="text" id="cep" name="cep" value=""
						onblur="consultaCep()"></td>
				</tr>
				<tr>
					<td>Rua:</td>
					<td><input type="text" id="rua" name="rua" value=""></td>
				</tr>
				<tr>
					<td>Bairro:</td>
					<td><input type="text" id="bairro" name="bairro" value=""></td>
				</tr>
				<tr>
					<td>Cidade:</td>
					<td><input type="text" id="cidade" name="cidade" value=""></td>
				</tr>
				<tr>
					<td>UF:</td>
					<td><input type="text" id="uf" name="uf" value=""></td>
				</tr>
				<tr>
					<td>IBGE:</td>
					<td><input type="text" id="ibge" name="ibge" value=""></td>
				</tr>
			</table>
			<input type="submit" value="Salvar Usuário" class="butn"> <input
				type="submit" value="Cancelar" class="butn"
				onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">
	</form>
	</center>

	<table class="blueTable">
		<tr>
			<th>ID</th>
			<th>Login</th>
			<th>Nome</th>
			<th>Telefone</th>
			<th>Deletar</th>
			<th>Editar</th>
		</tr>
		<c:forEach items="${usuario}" var="user">
			<tr>
				<td style="width: 150px; border: 2px solid #778899;"><c:out
						value="${user.id}"></c:out></td>
				<td style="width: 150px; border: 2px solid #778899;"><c:out
						value="${user.usuario}"></c:out></td>
				<td style="border: 2px solid #778899"><c:out
						value="${user.nome}"></c:out></td>
				<td style="border: 2px solid #778899"><c:out
						value="${user.telefone}"></c:out></td>
				<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
						src="http://icons.iconarchive.com/icons/hopstarter/button/48/Button-Close-icon.png"
						alt="deletar" title="Deletar" width="32px" height="32px" /></a></td>
				<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
						src="http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/48/Actions-document-edit-icon.png"
						alt="editar" title="Editar" width="32px" height="32px" /></a></td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == "") {
				alert('Informe o login');
				return false;
			} else if (document.getElementById("senha").value == "") {
				alert('Informe a senha');
				return false;
			} else if (document.getElementById("nome").value == "") {
				alert('Informe o seu nome');
				return false;
			} else if (document.getElementById("telefone").value == "") {
				alert('Informe o seu telefone');
				return false;
			}
			return true;
		}

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#uf").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} //end if.
						else {
							//CEP pesquisado não foi encontrado.
							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#uf").val('');
							$("#ibge").val('');

							alert("CEP não encontrado.");
						}
					});
		}
	</script>

</body>
</html>

