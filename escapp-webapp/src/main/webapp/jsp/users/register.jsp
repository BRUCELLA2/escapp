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
					<li><s:a action="sites">Sites</s:a></li>
					<li><s:a action="topo">Topos</s:a></li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
					<li><s:a action="sign_up"><span class="glyphicon glyphicon-user"></span> S'inscrire</s:a></li>
					<li><s:a action="login"><span class="glyphicon glyphicon-log-in"></span> Login</s:a></li>
				</ul>
			</div>
		</nav>
      	
      	<!--
      	<div class="row">
    		<div class="col-xs-6 col-xs-offset-3">
		      	<div class="panel panel-primary">
		      		<div class="panel-heading text-center">Création du compte utilisateur</div>
		      		<div class="panel-body col-xs-offset-3">
		      		      	<s:form action="sign_up">
		      					<s:textfield name="login" label="Identifiant" requiredLabel="true" placeholder="Saisissez votre identifiant"/>
		      					<s:password label="Mot de passe" key="password" requiredLabel="true" placeholder="Saisissez votre mot de passe"/>
		      					<s:password label="Confirmation" key="confPwd" requiredLabel="true" placeholder="Confirmez votre mot de passe"/>
		      					<s:textfield name="email" label="Email" requiredLabel="true" placeholder="Saisissez votre email"/>
		      				</s:form>
		      		</div>
		      		<div class="panel-body col-xs-offset-6">
		      			<input type="submit" class="btn btn-primary" form="sign_up" value="Valider"/>
		      		</div>
		      	</div>
		    </div>
      	</div>
      	-->
      	
      	<div class="row">
    		<div class="col-xs-6 col-xs-offset-3">
		      	<div class="panel panel-primary">
		      		<div class="panel-heading text-center">Création du compte utilisateur</div>
		      		<div class="panel-body">
			      		<div class="col-xs-6 col-xs-offset-3">
			      		
					      	<form class="form-horizontal" method="post" action="sign_up">
					      	
					      		<div class="form-group">				      			
					      			<label for="login">Identifiant :</label>
					      			<input type="text" class="form-control" id="login" name="login" value='<s:property value="login"/>'>
					      		</div>
					      		
					      		<div class="form-group">
					      			<label for="password">Mot de passe :</label>
					      			<input type="password" class="form-control" id="password" name="password">
					      			<div class="has-error"><span class="help-block"><s:fielderror fieldName="password"/></span></div> 
					      			
					      		</div>
					      		
					      		<div class="form-group">
					      			<label for="confPwd">Confirmation :</label>
					      			<input type="password" class="form-control" id="confPwd" name="confPwd">
					      			<div class="has-error"><span class="help-block"><s:fielderror fieldName="confPwd"/></span></div> 
					      		</div>
					      		
					      		<div class="form-group">
					      			<label for="email">Email :</label>
					      			<input type="email" class="form-control" id="email" name="email" value='<s:property value="email"/>'>
					      			<div class="has-error"><span class="help-block"><s:fielderror fieldName="email"/></span></div>
					      		</div>
					      		
					      		<button type="submit" class="btn btn-primary">Valider</button>
					      	</form>

			      		</div>
			      	</div>
		      	</div>
		    </div>
      	</div>
      	

	</div>
</body>
</html>