# EscApp

EscApp is a site and climbing topos sharing application.

With this application users can search for a site among those already registered, upload topos to share it with other users or borrow a topo for up to 14 days.
This application being community-based, each user can comment on sites and topos.


## Requirement

This project need : 

	* Apache Tomcat 9 server
	* PostgreSQL 10.x

## Set-up
### Database
	
Scripts for database and data creation are in Database folder. 

- create user : adm_escapp with password : adm_escapp 
- create database named : escapp with port 5434 owned by adm_escapp user
- execute bdd_creation.sql : psql -p 5435 -f bdd_creation.sql -U adm_escapp escapp
- to add some testing data, execute data_creation.sql : psql -p 5435 -f data_creation.sql -U adm_escapp escapp

Database user and Database configuration can be change in context.xml in META-INF


### Maven

In $CATALINA_HOME\conf\tomcat-users:

```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="password" roles="manager-gui,manager-script"/>
```

### Tomcat

In ${maven.home}/conf/settings.xml (or ${user.home}/.m2/settings.xml):

```
<server>
    <id>tomcat</id>
    <username>admin</username>
    <password>password</password>
</server>
```

## Running EscApp

* If you deploy the application for the first time:
	`mvn clean install tomcat7:deploy`

* If want to redeploy the application : 
	`mvn clean install tomcat7:redeploy`

* The application will be then running on: http://localhost:8080/escapp 

## Post-deploiement modifications

* If you use the demos data, you need to add some pdf files in the folder : WEB-INF/files/
* If you run the application in production, you need to defined administrator user of escapp. To do this, after the creation of the user in EscApp, you need to execute this request on database :
`INSERT INTO public.role_user(role, escapp_user)VALUES ('Admin', X);` where X is the id of the administrator user. 