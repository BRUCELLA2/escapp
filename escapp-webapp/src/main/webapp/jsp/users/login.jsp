<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="utf-8" />
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="/escapp/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/escapp/_include/css/escapp.css">
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
   		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    	<!--[if lt IE 9]>
      		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    	<![endif]-->
		<title>Enregistrement</title>
	</head>

<body>
	<div class="container">
		<s:a action="index" class="deco-none">
			<header class="page-header header-site">
			<div class="jumbotron">
				<h1>EscApp</h1>
				<p>L'Application des sites d'Escalade</p>
			</div>
			</header>
		</s:a>
		
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<s:a class="navbar-brand" action="index">EscApp</s:a>
				</div>
				<ul class="nav navbar-nav">
					<li><s:a action="index">Accueil</s:a></li>
					<li><s:a action="searching_site">Sites</s:a></li>
					<li><s:a action="searching_topo">Topos</s:a></li>
				</ul>
				
				<%@include file="/_include/jsp/navbar_right.jsp" %>
			</div>
		</nav>
		
		<div class="row">
    		<div class="col-xs-6 col-xs-offset-3">
		      	<div class="panel panel-primary">
		      		<div class="panel-heading text-center">Connexion</div>
		      		<div class="panel-body">
			      		<div class="col-xs-6 col-xs-offset-3">
			      			<form class="form-horizontal" method="post" action="login">
			      				
			      				<div class="form-group">				      			
					      			<label for="login">Identifiant :</label>
					      			<input type="text" class="form-control" id="login" name="login" value='<s:property value="login"/>'>
					      			<div class="has-error"><span class="help-block"><s:fielderror fieldName="login"/></span></div> 
					      		</div>
					      		
					      		<div class="form-group">
					      			<label for="password">Mot de passe :</label>
					      			<input type="password" class="form-control" id="password" name="password">
					      			<div class="has-error"><span class="help-block"><s:fielderror fieldName="password"/></span></div> 	
					      		</div>
					      		
					      		<button type="submit" class="btn btn-primary">Se connecter</button>
			      				
			      			</form>
			      		</div>
			      	</div>
			   	</div>
			</div>
		</div>	
	</div>
</body>
</html>



