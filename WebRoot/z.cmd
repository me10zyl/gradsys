cd e:\tomcat\apache-tomcat-7.0.56\webapps\GraduationSystem
e:
rsync --delete -rPv * root@zyl-me.xicp.net:/mnt/diskd7/linux/tomcat/apache-tomcat-7.0.54/webapps/gradsys
cd e:\jar\struts-2.3.16\apps\struts2-blank\WEB-INF\lib\
rsync -rPv * root@zyl-me.xicp.net:/mnt/diskd7/linux/tomcat/apache-tomcat-7.0.54/webapps/gradsys/WEB-INF/lib
cd e:\jar
rsync -rPv mysql-connector-java-5.1.27-bin.jar root@zyl-me.xicp.net:/mnt/diskd7/linux/tomcat/apache-tomcat-7.0.54/webapps/gradsys/WEB-INF/lib