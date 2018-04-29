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
		<title>Détails du site</title>
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
				
				<%@include file="/_include/jsp/navbar_right.jsp" %>
			</div>
		</nav>
		
		<header class="page-header text-center">
        	<h1>Détails du site : <s:property value="site.name"/></h1>
      	</header>

      	<div class="panel-group">
      	
	      	<div class="panel panel-primary">
	      		<div class="panel-heading"><h4>Description</h4></div>
	      		<div class="panel-body"><p><s:property value="site.description"/></p></div>
	      	</div>	
	  
	 		<s:if test="sectorsList != null">  			
	      		<s:iterator value="sectorsList" var="sector">
	      			<div class="panel panel-primary">
	      				
	      				<div class="panel-heading"><h4><s:property value="#sector.name"/></h4></div>
	      				<div class="panel-body">
	      					
	      					<div class="panel panel-default">
	      						<div class="panel-heading"><h4>Description du secteur</h4></div>
	      						<div class="panel-body"><p><s:property value="#sector.description"/></p></div>
	      					</div>
	
	      					<div class="panel panel-default">
	      						<div class="panel-heading"><h4>Liste des voies :</h4></div>
	      						<div class="panel-body">
	      							<ul>
	      								<s:iterator value="routesList" var="route">
		      								<s:if test="#route.sectorId == #sector.id">
		      								<li><s:a action="route_details">
		      										<s:param name="id" value="#route.id"/>
		      										<s:property value="#route.name"/> - Cotation : <s:property value="#route.grade"/></s:a></li>
		      								</s:if>
	      								</s:iterator>
	      							</ul>
	      						</div>
	      					</div>
	      				</div>
	      			</div>
	      		</s:iterator>
			</s:if>
		</div>
	</div>
		<!--  Java Scripts -->
	<script src="bootstrap/js/jquery-3.3.1.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>
