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
					<li class="active"><s:a action="searching_site">Sites</s:a></li>
					<li><s:a action="searching_topo">Topos</s:a></li>
				</ul>

				<%@include file="/_include/jsp/navbar_right.jsp"%>
			</div>
		</nav>

		<header class="page-header text-center">
			<h1>
				Détails de la voie :
				<s:property value="route.name" />
			</h1>
		</header>

		<div class="panel-group">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Description</h4>
				</div>
				<div class="panel-body">
					<h4>
						<s:a action="site_details">
							<s:param name="id" value="site.id" />Site : <s:property
								value="site.name" />
						</s:a>
						-
						<s:a action="site_details">
							<s:param name="id" value="site.id" />Secteur : <s:property
								value="sector.name" />
						</s:a>
					</h4>
					<p>
						<s:property value="route.description" />
					<p>
					<p>
						Cotation :
						<s:property value="route.grade" />
					</p>
					<p>
						Nombre de points :
						<s:property value="nbPoints" />
					</p>
					<p>Longueurs :</p>
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
								<td><s:property value="#length.id" /></td>
								<td><s:property value="#length.grade" /></td>
								<td><s:property value="#length.length" /> m</td>
								<td><s:property value="#length.pointsNb" /></td>
								<td><s:property value="#length.description" /></td>
							</tr>
						</s:iterator>
					</table>

					<s:a data-toggle="collapse" href="#collapseCommentRoute">Commentaires <span
							class="badge"><s:property value="nbCommentsRoute" /></span>
					</s:a>
					<s:iterator value="#session.roles" var="role">
						<s:if test="#role.userRole == 'Admin'">
							<s:a action="add_length">
								<s:param name="routeId" value="route.id" />
								<br />
								<br />
								Ajouter une longueur
							</s:a>
						</s:if>
					</s:iterator>

					<div class="panel-group">
						<div class="panel panel-default">
							<div id="collapseCommentRoute" class="panel-collapse collapse">
								<div class="panel-body">
									<s:actionmessage />
									<s:iterator value="commentsRouteList"
										status="statusComRouteList" var="commentRoute">
										<div class="panel panel-info">
											<div class="panel-heading">
												<p>
													Commentaire
													<s:property value="#statusComRouteList.count" />
													-
													<s:property value="#commentRoute.value" />
												</p>
											</div>
											<div class="panel-body">
												<p>
													<s:property value="#commentRoute.key.text" />
												</p>
											</div>
										</div>
									</s:iterator>

									<hr />

									<s:if test="#session.user">
										<form action="comment_route">
											<div class="form-group">
												<label for="commentSite">Ajouter un commentaire :</label>
												<textarea class="form-control" rows="5" name="commentRoute"
													placeholder="Saisissez votre commentaire ici."></textarea>
												<div class="has-error">
													<span class="help-block"><s:fielderror
															fieldName="commentRoute" /></span>
												</div>
												<input type="hidden" name="routeId" id="routeId"
													value="<s:property value='route.id'/>" />
											</div>
											<button type="submit" class="btn btn-default">Envoyer</button>
										</form>
									</s:if>
									<s:else>
										<p>
											<span class="help-block text-center">Veuillez vous
												connecter pour poster un commentaire</span>
										</p>
									</s:else>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  Java Scripts -->
	<script src="bootstrap/js/jquery-3.3.1.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
</body>