<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="utf-8" />
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="/escapp-webapp/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/escapp-webapp/_include/css/escapp.css">
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
   		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    	<!--[if lt IE 9]>
      		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    	<![endif]-->
		<title>Détails de la voie</title>
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
					<li class="active"><s:a action="sites">Sites</s:a></li>
					<li><s:a action="topo">Topos</s:a></li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
					<li><s:a action="sign_up"><span class="glyphicon glyphicon-user"></span> S'inscrire</s:a></li>
					<li><s:a action="login"><span class="glyphicon glyphicon-log-in"></span> Login</s:a></li>
				</ul>
			</div>
		</nav>
	
		<header class="page-header text-center">
        	<h1>Détails de la voie : <s:property value="route.name"/></h1>
      	</header>
      	
      	<div class="panel-group">
      		<div class="panel panel-primary">
      			<div class="panel-heading"><h4>Description</h4></div>
      			<div class="panel-body">
      				<h4><s:a action="site_details"><s:param name="id" value="site.id"/>Site : <s:property value="site.name"/></s:a>
      					 - <s:a action="site_details"><s:param name="id" value="site.id"/>Secteur : <s:property value="sector.name"/></s:a></h4>
      				<p><s:property value="route.description"/><p>
      				<p>Cotation : <s:property value="route.grade"/></p>
      				<p>Nombre de points : <s:property value="nbPoints"/></p>      				
      			</div>
      		</div>
      		
      		<div class="panel panel-primary">
      			<div class="panel-heading"><h4>Longueurs :</h4></div>
      			<div class="panel-body">
      				<table class="table table-bordered table-striped table-condensed">
      					<thead>
      						<tr>
      							<th>Identifiant</th>
      							<th>Cotation</th>
      							<th>Longueur</th>
      							<th>Nb points</th>
      							<th>Description</th>
      						</tr>
      					</thead>
      					
      					<s:iterator value="lengthsList" var="length">
      						<tr>
      							<td><s:property value="#length.id"/></td>
      							<td><s:property value="#length.grade"/></td>
      							<td><s:property value="#length.length"/> m</td>
      							<td><s:property value="#length.pointsNb"/></td>
      							<td><s:property value="#length.description"/></td>
      						</tr>      					
      					</s:iterator>
      				</table>
      			</div>
      		</div>
      	</div> 	
    </div>
    	<!--  Java Scripts -->
	<script src="bootstrap/js/jquery-3.3.1.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
</body>