A **CMS** System. Graduate Design Management System is a management system aimed at graduates need to design. It’s convenient for college graduates and graduate view about graduation design at any time, and to choose a topic of graduation design. 

Test Accouts:<Br>
username 12310320304 password 123123 (*student*)<br>
username 611033 password 123123 (*teacher*)
SQL DDL
-----

```
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/3/10 13:14:00                           */
/*==============================================================*/

create database graduationsystem;

use graduationsystem;
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/3/10 15:05:24                           */
/*==============================================================*/


drop table if exists duty;

drop table if exists func;

drop table if exists notice;

drop table if exists predom;

drop table if exists student;

drop table if exists subject;

drop table if exists teacher;

drop table if exists userGroup;

/*==============================================================*/
/* Table: duty                                                  */
/*==============================================================*/
create table duty
(
   duty_id              int not null auto_increment,
   subject_id           int not null,
   teacher_id           int not null,
   primary key (duty_id)
);

/*==============================================================*/
/* Table: func                                                  */
/*==============================================================*/
create table func
(
   func_id              int not null auto_increment,
   func_name            varchar(50) not null,
   primary key (func_id)
);

/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   noticce_id           int not null auto_increment,
   teacher_id           int not null,
   notice_title         varchar(50) not null,
   notice_detail        varchar(700) not null,
   primary key (noticce_id)
);

/*==============================================================*/
/* Table: predom                                                */
/*==============================================================*/
create table predom
(
   predom_id            int not null auto_increment,
   userGroup_id         int not null,
   func_id              int not null,
   primary key (predom_id)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   student_id           int not null auto_increment,
   subject_id           int,
   userGroup_id         int,
   student_num          varchar(30) not null,
   student_name         varchar(12) not null,
   student_gender       char(2) not null,
   student_grade        char(4) not null,
   student_major        varchar(50) not null,
   student_telphone     varchar(11) not null,
   student_password     varchar(12) not null,
   primary key (student_id)
);

/*==============================================================*/
/* Index: uni_num                                               */
/*==============================================================*/
create unique index uni_num on student
(
   student_num
);

/*==============================================================*/
/* Table: subject                                               */
/*==============================================================*/
create table subject
(
   subject_id           int not null auto_increment,
   subject_title        varchar(100) not null,
   subject_description  varchar(10240),
   primary key (subject_id)
);

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   teacher_id           int not null auto_increment,
   userGroup_id         int,
   teacher_num          varchar(30) not null,
   teacher_name         varchar(20) not null,
   teacher_gender       char(2) not null,
   teacher_telephone    varchar(11) not null,
   teacher_password     varchar(12) not null,
   primary key (teacher_id)
);

/*==============================================================*/
/* Index: uni_num                                               */
/*==============================================================*/
create unique index uni_num on teacher
(
   teacher_num
);

/*==============================================================*/
/* Table: userGroup                                             */
/*==============================================================*/
create table userGroup
(
   userGroup_id         int not null auto_increment,
   userGroup_name       varchar(20) not null,
   primary key (userGroup_id)
);

alter table duty add constraint FK_被负责 foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table duty add constraint FK_负责 foreign key (subject_id)
      references subject (subject_id) on delete restrict on update restrict;

alter table notice add constraint FK_release foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table predom add constraint FK_有 foreign key (userGroup_id)
      references userGroup (userGroup_id) on delete restrict on update restrict;

alter table predom add constraint FK_有2 foreign key (func_id)
      references func (func_id) on delete restrict on update restrict;

alter table student add constraint FK_belong foreign key (userGroup_id)
      references userGroup (userGroup_id) on delete restrict on update restrict;

alter table student add constraint FK_select foreign key (subject_id)
      references subject (subject_id) on delete restrict on update restrict;

alter table teacher add constraint FK_belong2 foreign key (userGroup_id)
      references userGroup (userGroup_id) on delete restrict on update restrict;
```

Diagram
------
**需求**:
----------------
1.	老师选题
	1.1	老师出题
	1.2	__老师删题__
	1.3	老师查题	
	1.4	老师选题	
2.	学生选题	
	2.1	学生查题	
	2.2	学生选题	
3.	老师信息	
	3.1	老师查看信息	(包括自己与其他人信息)
	3.2	老师修改信息	
4.	学生信息	
	4.1	学生修改信息	(包括自己与其他人信息)
	4.2	学生查看信息
5.    __国际化(中英双语切换) __
6.    __信息发布__

**业务用例**:<br><img src="bcd.png"><br>

**系统用例**:<br><img src="scd.png"><br>

**活动图**:<br><img src="ad.png"><br>

**概念数据模型**:<br>!["cdm"](cdm.png)<br>

**物理数据模型**:<br>!["pdm"](pdm.png)<br>
