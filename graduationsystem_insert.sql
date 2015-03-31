INSERT INTO `graduationsystem`.`userGroup`
(
`userGroup_name`)
VALUES
('student'
);
INSERT INTO `graduationsystem`.`userGroup`
(
`userGroup_name`)
VALUES
('teacher'
);

INSERT INTO `graduationsystem`.`student`
(`student_id`,
`subject_id`,
`userGroup_id`,
`student_num`,
`student_name`,
`student_gender`,
`student_grade`,
`student_major`,
`student_telphone`,
`student_password`)
VALUES
(null,
null,
1,
'12310320304',
'曾艺伦',
'男',
'12级',
'软件工程',
'13608069390',
'123123');
INSERT INTO `graduationsystem`.`teacher`
(`teacher_id`,
`userGroup_id`,
`teacher_num`,
`teacher_name`,
`teacher_gender`,
`teacher_telephone`,
`teacher_password`)
VALUES
(null,
2,
'611033',
'张小华',
'男',
'1251616151',
'123123');


INSERT INTO `graduationsystem`.`student`
(`student_id`,
`subject_id`,
`userGroup_id`,
`student_num`,
`student_name`,
`student_gender`,
`student_grade`,
`student_major`,
`student_telphone`,
`student_password`)
VALUES
(null,
null,
1,
'12310320320',
'郭珊',
'女',
'12级',
'软件工程',
'13608069391',
'123123');
INSERT INTO `graduationsystem`.`student`
(`student_id`,
`subject_id`,
`userGroup_id`,
`student_num`,
`student_name`,
`student_gender`,
`student_grade`,
`student_major`,
`student_telphone`,
`student_password`)
VALUES
(null,
null,
1,
'12310320309',
'王琰珩',
'男',
'12级',
'软件工程',
'13608069392',
'123123');
INSERT INTO `graduationsystem`.`teacher`
(`teacher_id`,
`userGroup_id`,
`teacher_num`,
`teacher_name`,
`teacher_gender`,
`teacher_telephone`,
`teacher_password`)
VALUES
(null,
2,
'611034',
'黄波',
'男',
'1251616152',
'123123');
INSERT INTO `graduationsystem`.`teacher`
(`teacher_id`,
`userGroup_id`,
`teacher_num`,
`teacher_name`,
`teacher_gender`,
`teacher_telephone`,
`teacher_password`)
VALUES
(null,
2,
'611035',
'仲宝才',
'男',
'1251616153',
'123123');

SELECT * FROM graduationsystem.student;