# Configuration Files

You can find an example of each configuration files in the git folder "conf-files".

## Maven configuration file :

* tomcat-users -> in $CATALINA_HOME\conf\tomcat-users

Need to be updated before building and deploy the application with :
```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="password" roles="manager-gui,manager-script"/>
```

## Tomcat configuration file :

settings.xml -> in ${maven.home}/conf/settings.xml (or ${user.home}/.m2/settings.xml)

Need to be updated before building and deploy the application with :
```
<server>
    <id>tomcat</id>
    <username>admin</username>
    <password>password</password>
</server>
```


## Database configuration file :

context.xml -> in META-INF folder.

This file can be updated after deployement. In this file you can set up username and password for postgres database access (with parameter username and password).
You can also set up the database url and port with the url parameter.

After a modification you need to restart the application.



## Application configuration files :

### web.xml

* in WEB-INF folder

In production, currently no parameter need to be changed in this file.

### spring context.xml 

* boostrapContext.xml in escapp-webapp/src/main/resources/ 
* webappContext.xml in classpath:/fr/brucella/form/escapp/webapp/
* consumerContext.xml classpath:/fr/brucella/form/escapp/consumer/

This file don't need to be modify except for development. 
Currently used to configure jdbctemplate and datasource.

### log4j2

log4j2.xml -> in escapp-technical/src/main/resources/

This file is used to configure the logging system. 
You can change this file to configure the log level wanted (currently set to debug) with the level parameter.
You can also specify where you want the logs files are saved with the filename parameter


### Struts 2

struts.xml -> in escapp-webapp/src/main/resources/

In production, only parameters related to upload files can be changed (the max size value and in the action "download" parameter "bufferSize")
All other parameter are only used in development context.

struts.properties -> in escapp-webapp/src/main/resources/struts.properties

In production, the parameter struts.devMode need to change to false
