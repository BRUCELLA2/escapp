<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					<li><s:a action="searching_topo">Topos</s:a></li>
				</ul>

				<%@include file="/_include/jsp/navbar_right.jsp"%>
			</div>
		</nav>

		<header class="page-header text-center">
			<h1>Mes topos</h1>
		</header>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Liste de mes topos :</h4>
			</div>
			<div class="panel-body">
				<s:if test="toposOwnerList != null">
					<table class="table table-bordered table-striped table-condensed">

						<thead>
							<tr>
								<th class="text-center">Identifiant</th>
								<th>Nom du topo</th>
								<th class="text-center">Département</th>
								<th>Commune</th>
								<th>Empruntable</th>
								<th>Réservé jusqu'au</th>
								<th></th>
							</tr>
						</thead>


						<s:iterator value="toposOwnerList">
							<tr>
								<td class="text-center"><s:property value="id" /></td>
								<td><s:property value="name" /></td>
								<td class="text-center"><s:property value="department" /></td>
								<td><s:property value="municipality" /></td>
								<td><s:if test="isBorrowable">oui</s:if> <s:else>non</s:else></td>
								<td><fmt:parseDate value="${endDateBorrow}"
										pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
										value="${parsedDate}" var="stdDatum" type="date"
										pattern="dd/MM/yyyy" /> <c:out value="${stdDatum}"></c:out></td>
								<td><s:a class="btn btn-primary btn-block"
										action="topo_details">
										<s:param name="id" value="id" />
										<span class="glyphicon glyphicon-search"></span>
										Voir le détail
									</s:a></td>
							</tr>
						</s:iterator>
					</table>
				</s:if>
				<s:else>
					<p>Vous n'avez déposé aucun topos.</p>
				</s:else>
				<p>
					<s:a action="add_topo">Ajouter un topo</s:a>
				</p>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Liste des topos empruntés :</h4>
			</div>
			<div class="panel-body">
				<s:if test="toposBorrowerList">
					<table class="table table-bordered table-striped table-condensed">
						<thead>
							<tr>
								<th class="text-center">Identifiant</th>
								<th>Nom du topo</th>
								<th class="text-center">Département</th>
								<th>Commune</th>
								<th>Réservé jusqu'au</th>
								<th></th>
							</tr>
						</thead>

						<s:iterator value="toposBorrowerList">
							<tr>
								<td class="text-center"><s:property value="id" /></td>
								<td><s:property value="name" /></td>
								<td class="text-center"><s:property value="department" /></td>
								<td><s:property value="municipality" /></td>
								<td><fmt:parseDate value="${endDateBorrow}"
										pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
										value="${parsedDate}" var="stdDatum" type="date"
										pattern="dd/MM/yyyy" /> <c:out value="${stdDatum}"></c:out></td>
								<td><s:a class="btn btn-primary btn-block"
										action="topo_details">
										<s:param name="id" value="id" />
										<span class="glyphicon glyphicon-search"></span>
										Voir le détail
									</s:a></td>
							</tr>
						</s:iterator>
					</table>
				</s:if>
				<s:else>
					<p>Vous n'avez pas fait d'emprunt de topos.</p>
				</s:else>
			</div>
		</div>
	</div>
</body>
</html>



