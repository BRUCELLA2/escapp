<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
		<title>Erreur</title>
	</head>

<body>

	<div class="container">
		<header class="page-header header-site">
		<div class="jumbotron">
			<h1>EscApp</h1>
			<p>L'Application des sites d'Escalade</p>
		</div>
		</header>
		
		<header class="page-header text-center">
        	<h2>Malgré tous nos efforts, une erreur est survenue !</h2>
      	</header>		
      	
      		<div class="panel panel-danger">
      			<div class="panel-heading"><h4><s:actionerror/></h4></div>
      			<div class="panel-body"><p>Veuillez réessayer ou si nécessaire contacter l'administrateur du site.</div>
      		</div>
	</div>



</body>
</html>