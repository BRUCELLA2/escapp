<%@ page contentType="text/html;charset=UTF-8" language="java" %>

	<s:if test="#session.user">
		<ul class="nav navbar-nav navbar-right">
			<li><s:a action="my_topos">Mes topos</s:a></li>
			<li><s:a action="logout"><span class="glyphicon glyphicon-user"></span> Se déconnecter</s:a></li>
		</ul>
	</s:if>
	<s:else>
		<ul class="nav navbar-nav navbar-right">
			<li><s:a action="register"><span class="glyphicon glyphicon-user"></span> S'inscrire</s:a></li>
			<li><s:a action="login"><span class="glyphicon glyphicon-log-in"></span> Login</s:a></li>
		</ul>
	</s:else>