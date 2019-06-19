insert into skill (skill_id, name) values (1200, 'java');
insert into skill (skill_id, name) values (1201, 'react');
insert into skill (skill_id, name) values (1202, 'spring');
insert into skill (skill_id, name) values (1203, 'struts');
insert into skill (skill_id, name) values (1204, 'oracle');

insert into user (user_name, designation) values ('mbandara', 'Technical Specialist');
insert into user (user_name, designation) values ('cananda', 'Senior Technical Lead');
insert into user (user_name, designation) values ('uhoranagama', 'Senior Technical Specialist');
insert into user (user_name, designation) values ('cprathiba', 'Senior Software Engineer');
insert into user (user_name, designation) values ('mkpchiran@gmail.com', 'Software Engineer');

insert into skill_level (skill_level_id,  value, description) values (2500, 5, 'Do not have experience');
insert into skill_level (skill_level_id,  value, description) values (2501, 6, 'Have average experience');
insert into skill_level (skill_level_id,  value, description) values (2502, 4, 'Well experienced');


insert into user_skill (user_skill_id, experience, skill_id, skill_level_id, user_name, starRate) values (3000, 9.5, 1200, 2502, 'mbandara',2);
insert into user_skill (user_skill_id, experience, skill_id, skill_level_id, user_name, starRate) values (3001, 9.5, 1202, 2502, 'mbandara',4);
insert into user_skill (user_skill_id, experience, skill_id, skill_level_id, user_name, starRate) values (3002, 9.5, 1201, 2501, 'mbandara',3);
insert into user_skill (user_skill_id, experience, skill_id, skill_level_id, user_name, starRate) values (3003, 3.5, 1201, 2500, 'cprathiba',2);
insert into user_skill (user_skill_id, experience, skill_id, skill_level_id, user_name, starRate) values (3004, 3.5, 1200, 2501, 'cprathiba',1);

insert into AUTH_USER_GROUP (USERNAME, AUTH_GROUP) values ('mkpchiran@gmail.com', 'ADMIN');
insert into AUTH_USER_GROUP (USERNAME, AUTH_GROUP) values ('mkpchiran@gmail.com', 'USER');

