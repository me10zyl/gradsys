Graduate Design Management System
---
This is a **CMS** System. Graduate Design Management System is a management system aimed at graduates need to design. It’s convenient for college graduates and graduate view about graduation design at any time, and to choose a topic of graduation design.

Early phase there is no any frameworks, now add the *struts2* framework on this project, the DAO of project is built by _oRMappingTool_<br>
look here [https://github.com/me10zyl/ormappingtool](https://github.com/me10zyl/ormappingtool)

Test Accouts:<Br>
username 12310320304 password 123123 (:boy:*student*)<br>
username 12310320309 password 123123 (:boy:*student*)<br>
username 12310320320 password 123123 (:girl:*student*)<br>

username 611033 password 123123 (:man:*teacher*)<br>
username 611034 password 123123 (:man:*teacher*)<br>
username 611035 password 123123 (:man:*teacher*)<br>

SQL DDL & DML
-----

DDL `graduationsystem_create.sql` <br>
DML `graduationsystem_insert.sql`<br>

Diagram
------
**需求**<font color='#ccc' size="2">(粗体为最新添加需求)</font>:
----------------
1.	老师选题
	1. 老师出题
	2. __老师删题__
	3.	老师查题	
	4.	老师选题	
2.	学生选题	
	1.	学生查题	
	2.	学生选题	
3.	老师信息	
	1.	老师查看信息	(包括自己与其他人信息)
	2.	老师修改信息	
4.	学生信息	
	1.	学生修改信息	(包括自己与其他人信息)
	2.	学生查看信息
5.    __国际化(中英双语切换)__
6.    __信息发布__
	1.    信息发布
	2.    __信息删除__


**概念数据模型**:<br>!["cdm"](cdm.png)<br>

**物理数据模型**:<br>!["pdm"](pdm.png)<br>
