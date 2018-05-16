<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
		<title>Détails du topo</title>
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
					<li class="active"><s:a action="searching_topo">Topos</s:a></li>
				</ul>
				
				<%@include file="/_include/jsp/navbar_right.jsp" %>
			</div>
		</nav>
	
		<header class="page-header text-center">
        	<h1>Détails du topo : <s:property value="topo.name"/></h1>
      	</header>
		
		<div class="panel panel-primary">
			<div class="panel-heading"><h4>Description</h4></div>
			<div class="panel-body">
				<p><b>Propriétaire : </b><s:property value="ownerTopo.login"/></p>
				<p><b>Département : </b><s:property value="topo.department"/></p>
				<p><b>Commune : </b><s:property value="topo.municipality"/></p>
				<hr />
				<p><b>Description : </b><br /><br /><s:property value="topo.description"/></p>
				<hr />
				<div class="panel-body"><s:a data-toggle="collapse" href="#collapseCommentTopo">Commentaires <span class="badge"><s:property value="nbCommentsTopo"/></span></s:a></div> 
		      		  <div class="panel-group">
						  <div class="panel panel-default">
						    <div id="collapseCommentTopo" class="panel-collapse collapse">
						      <div class="panel-body">
						      	      						
		      						<s:actionmessage/>
		      						<s:iterator value="commentsTopoList" status="statusComTopoList" var="commentTopo">
		      							<div class="panel panel-info">
		      								<div class="panel-heading">
		      									<p>Commentaire <s:property value="#statusComTopoList.count"/> - <s:property value="#commentTopo.value"/></p>
		      								</div>
		      								<div class="panel-body">
		      									<p><s:property value="#commentTopo.key.text"/></p>
		      								</div>
		      							</div>
		      						</s:iterator>
		      						
		      						<hr/>
		      						
		      						<s:if test="#session.user">
						      		<form action="comment_topo">
		      							<div class="form-group">
		      								<label for="commentTopo">Ajouter un commentaire :</label>
		      								<textarea class="form-control" rows="5" name="commentTopo" id="commentTopo" placeholder="Saisissez votre commentaire ici."></textarea>
		      								<div class="has-error"><span class="help-block"><s:fielderror fieldName="commentTopo"/></span></div>
		      								<input type="hidden" name="topoId" id="topoId" value="<s:property value='topo.id'/>"/>
		      							</div>
		      							<button type="submit" class="btn btn-default">Envoyer</button>
		      						</form>
		      						</s:if>
		      						<s:else>
		      							<p><span class="help-block text-center">Veuillez vous connecter pour poster un commentaire</span></p>
		      						</s:else>
						      </div>
						    </div>
						  </div>
					</div> 
			</div>
		
			<div class="panel panel-primary">
				<div class="panel-heading"><h4>Réservation</h4></div>				
				<div class="panel-body">			
					<s:if test="topo.isBorrowable">
						<s:if test="topo.endDateBorrow == null">
							<p>Ce topo est disponible.</p>
							<s:if test="#session.user && #session.user.id != ownerTopo.id">
								<div class="col-xs-6 col-xs-offset-3">
								<form class="form-inline" method="post" action="book_topo">
								    <div class="form-group">
								      <label for="nbDays">Réserver le topo pour :</label>
								      <input type="number" min="0" max="14" class="form-control" id="nbDays" placeholder="XX" name="nbDays">
								      <label for="nbDays"> jours</label>
								      <input type="hidden" name="id" id="id" value="<s:property value='topo.id'/>"/>
								    </div>
							    	<button type="submit" class="btn btn-primary">Valider</button>
							  	</form>
							  	</div>
						  	</s:if>
						  	<s:elseif test="#session.user.id == ownerTopo.id">
							  	<ul>
							  		<li><s:a action="change_borrowable">
											<s:param name="id" value="topo.id" />
											<s:param name="borrowable" value="false" />
											Ne plus prêter ce topo.
										</s:a>
									</li>
									<li><s:a action="delete_topo">
											<s:param name="id" value="topo.id"></s:param>
										Supprimer le topo</s:a>
									</li>
								</ul>
						  	</s:elseif>
						  	<s:else>
						  		<p>Pour réserver ce topo, il est nécessaire de vous connecter.</p>
						  	</s:else>
						</s:if>
						<s:else>
							<s:if test="#session.user.id == borrowerTopo.id">
								<p>Vous avez réservé ce topo</p>
								<p>Télécharger le topo : <s:a action="download"><s:property value="topo.pdfFileName"/><s:param name="fileName" value="topo.pdfFileName" /></s:a></p>
							</s:if>
							<s:else>
								Ce topo est déjà réservé jusqu'au <fmt:parseDate value="${topo.endDateBorrow}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
																  <fmt:formatDate value="${parsedDate}" var="stdDatum" type="date" pattern="dd/MM/yyyy" />
																  <c:out value="${stdDatum}"></c:out>
							</s:else>
						</s:else>
					</s:if>
					<s:elseif test="#session.user.id == ownerTopo.id">
						<ul>
							<li><s:a action="change_borrowable">
									<s:param name="id" value="topo.id" />
									<s:param name="borrowable" value="true" />
									Prêter ce topo.
								</s:a>
							</li>
						</ul>
					</s:elseif>
					<s:else>
						Ce topo ne peut être emprunté.
					</s:else>			
				</div>
			</div>
		</div>
	</div>
	<!--  Java Scripts -->
	<script src="bootstrap/js/jquery-3.3.1.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>