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
	<title>Ajouter une voie</title>
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
	
					<%@include file="/_include/jsp/navbar_right.jsp"%>
				</div>
			</nav>
	
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<div class="panel panel-primary">
						<div class="panel-heading text-center">
							<h2>Ajouter une voie :</h2>
						</div>
						<div class="panel-body">
							<div class="col-xs-8 col-xs-offset-2">
	
								<form class="form-horizontal" method="post" action="add_route">
									<div class="form-group row">
										<div class="col-xs-12">
											<label for="routeName">Nom de la voie :</label> <input
												type="text" class="form-control" id="routeName"
												name="routeName" value='<s:property value="routeName"/>'>
											<div class="has-error">
												<span class="help-block"><s:fielderror
														fieldName="routeName" /></span>
											</div>
										</div>
									</div>
	
									<div class="form-group row">
										<div class="col-xs-12">
											<label for="routeDescription">Description :</label>
											<textarea class="form-control" rows="5"
												name="routeDescription" id="routeDescription"
												placeholder="Saisissez la description de la voie."><s:property
													value="routeDescription" /></textarea>
											<div class="has-error">
												<span class="help-block"><s:fielderror
														fieldName="routeDescription" /></span>
											</div>
										</div>
									</div>
	
									<div class="form-group row">
										<div class="col-xs-12">
											<label for="routeGrade">Cotation de la voie :</label>
										</div>
										<div class="col-xs-3">
											<input type="text" class="form-control" id="routeGrade"
												name="routeGrade" value='<s:property value="routeGrade"/>'>
										</div>
										<div class="has-error">
											<span class="help-block"><s:fielderror
													fieldName="routeGrade" /></span>
										</div>
									</div>
	
									<div class="form-group row">
										<div class="col-xs-12">
											<label for="routePointsNb">Nombre de points :</label>
										</div>
										<div class="col-xs-3">
											<input type="number" min="0" class="form-control"
												id="routePointsNb" name="routePointsNb">
										</div>
										<div class="has-error">
											<span class="help-block"><s:fielderror
													fieldName="routePointsNb" /></span>
										</div>
									</div>
	
									<input type="hidden" name="sectorId" id="sectorId"
										value="<s:property value='sectorId'/>" />
	
									<div class="row">
										<div class="col-xs-12 col-xs-offset-4">
											<button type="submit" class="btn btn-primary">Valider</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>