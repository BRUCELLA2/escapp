<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>

<html>

	<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/escapp/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css"
		href="/escapp/_include/css/escapp.css">
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	      		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	      		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    	<![endif]-->
	<title>Les sites d'escalades</title>
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
						<li class="active"><s:a action="searching_site">Sites</s:a></li>
						<li><s:a action="searching_topo">Topos</s:a></li>
					</ul>
	
					<%@include file="/_include/jsp/navbar_right.jsp"%>
				</div>
			</nav>
	
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Recherche de sites d'escalade</h3>
				</div>
				<div class="panel-body">
	
					<form class="form-horizontal" method="post" action="searching_site">
						<div class="form-group">
							<label class="control-label col-sm-5" for="departmentSite">Département
								(sur 3 caractères) :</label> <input class="col-sm-1" type="text"
								class="form-control" id="departmentSite" name="departmentSite"
								maxlength="3" placeholder="xxx">
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="municipalitySite">Commune
								:</label> <input class="col-sm-4" type="text" class="form-control"
								id="municipalitySite" name="municipalitySite" maxlength="100"
								placeholder="Nom de la ville">
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="minGrade">Cotation
								min (sur 2 caractères) :</label> <input class="col-sm-1" type="text"
								class="form-control" id="minGrade" name="minGrade" maxlength="2"
								placeholder="xx">
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="maxGrade">Cotation
								max (sur 2 caractères) :</label> <input class="col-sm-1" type="text"
								class="form-control" id="maxGrade" name="maxGrade" maxlength="2"
								placeholder="xx">
						</div>
						<div class="form-group">
							<div class="col-sm-offset-5">
								<button type="submit" class="btn btn-primary">Rechercher</button>
							</div>
						</div>
					</form>
				</div>
			</div>
	
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Sites d'escalades enregistrés</h3>
				</div>
				<div class="panel-body">
					<table class="table table-bordered table-striped table-condensed">
						<thead>
							<tr>
								<th class="text-center">Identifiant</th>
								<th>Nom du site</th>
								<th class="text-center">Département</th>
								<th>Commune</th>
								<th>Description</th>
								<th></th>
							</tr>
						</thead>
	
						<s:iterator value="sitesList">
							<tr>
								<td class="text-center"><s:property value="id" /></td>
								<td><s:property value="name" /></td>
								<td class="text-center"><s:property value="department" /></td>
								<td><s:property value="municipality" /></td>
								<td><s:property value="description" /></td>
								<td><s:a class="btn btn-primary btn-block"
										action="site_details">
										<s:param name="id" value="id" />
										<span class="glyphicon glyphicon-search"></span>
									Voir le détail
								</s:a></td>
							</tr>
						</s:iterator>
					</table>
	
					<s:iterator value="#session.roles" var="role">
						<s:if test="#role.userRole == 'Admin'">
							<s:a action="add_site">Ajouter un site</s:a>
						</s:if>
					</s:iterator>
				</div>
			</div>
		</div>
		<!--  Java Scripts -->
		<script src="bootstrap/js/jquery-3.3.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>
