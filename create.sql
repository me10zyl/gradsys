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

alter table duty add constraint FK_������ foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table duty add constraint FK_���� foreign key (subject_id)
      references subject (subject_id) on delete restrict on update restrict;

alter table notice add constraint FK_release foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table predom add constraint FK_�� foreign key (userGroup_id)
      references userGroup (userGroup_id) on delete restrict on update restrict;

alter table predom add constraint FK_��2 foreign key (func_id)
      references func (func_id) on delete restrict on update restrict;

alter table student add constraint FK_belong foreign key (userGroup_id)
      references userGroup (userGroup_id) on delete restrict on update restrict;

alter table student add constraint FK_select foreign key (subject_id)
      references subject (subject_id) on delete restrict on update restrict;

alter table teacher add constraint FK_belong2 foreign key (userGroup_id)
      references userGroup (userGroup_id) on delete restrict on update restrict;


