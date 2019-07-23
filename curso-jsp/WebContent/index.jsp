<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Curso JSP + Servelets</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link href="https://fonts.googleapis.com/css?family=Hind+Madurai" rel="stylesheet">
	<meta charset="utf-8"/>
</head>
<body>
	<div class="header">
		<div class="center">
			<img src="img/logo.png"/>
		</div><!--center-->
	</div> <!--header-->
	<div class="container-banner">
		<div class="center">
			<div class="form">
				<form action=loginServlet method="post">
					<h2>Login:</h2>
					<form>
						<div class="input-container">
							<span>USUÁRIO:</span>
							<input type="text" name="usuario" required />						
						</div><!--input-container-->
						<div class="input-container">
							<span>SENHA:</span>
							<input type="password" name="senha" required />						
						</div><!--input-container-->
						<div class="input-container">
						</div><!--input-container-->
						<div class="input-submit-container">			
						<input type="submit" name="acao" value="Acessar">					
						</div><!--input-submit-container-->				
					</form>

				</form>
			</div><!--form-->
		</div><!--center-->
	</div><!--container-baner-->

		<div class="footer">
		<p>Todos os direitos reservados</p>
		<p>Thiago da Silva Honório</p>
	</div><!--footer-->

</body>
</html>