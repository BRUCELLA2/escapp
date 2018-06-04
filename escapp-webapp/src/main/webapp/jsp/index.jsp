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
	<title>Accueil</title>
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
						<li class="active"><s:a action="index">Accueil</s:a></li>
						<li><s:a action="searching_site">Sites</s:a></li>
						<li><s:a action="searching_topo">Topos</s:a></li>
					</ul>
	
					<%@include file="/_include/jsp/navbar_right.jsp"%>
				</div>
			</nav>
	
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>L'escalade c'est fantastique et enrichissant !</h4>
				</div>
				<div class="panel-body">
					<p>L'escalade c'est aussi un partage de moment et de sensation,
						alors pourquoi ne pas partager nos sites, nos topos, nos avis ?
					<p>
					<p>C'est l'objet de ce site. Dans la partie "Sites" vous pourrez
						faire une recherche de sites d'escalades et dans la partie "Topos"
						vous pourrez partager et emprunter des topos.</p>
				</div>
			</div>
		</div>
		<!--  Java Scripts -->
		<script src="bootstrap/js/jquery.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>
