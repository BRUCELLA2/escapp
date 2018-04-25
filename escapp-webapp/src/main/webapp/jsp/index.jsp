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
		<title>Affichage de sites</title>
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
	<div class="panel panel-primary">
		<div class="panel-heading"><h3 class="panel-title">Sites d'escalades enregistrés</h3></div>
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
						<td class="text-center"><s:property value="id"/></td>
						<td><s:property value="name"/></td>
						<td class="text-center"><s:property value="department"/></td>
						<td><s:property value="municipality"/></td>
						<td><s:property value="description"/></td> 
						<td><s:a class="btn btn-primary btn-block" action="site_details"><s:param name="id" value="id" /><span class="glyphicon glyphicon-search"></span>
								Voir le détail
							</s:a>
						</td>
					</tr>
				</s:iterator>			
			</table>
		</div>
	</div>
</div>
	<!--  Java Scripts -->
	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>
