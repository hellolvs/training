drop table user if exists;

CREATE TABLE user(
id int unsigned NOT NULL AUTO_INCREMENT,
name varchar(32) NOT NULL DEFAULT '',
gender boolean NOT NULL DEFAULT false,
age tinyint NOT NULL DEFAULT '0',
comment varchar(32) NOT NULL DEFAULT '',
validity boolean NOT NULL DEFAULT false,
create_time timestamp NOT NULL DEFAULT '1970-01-01 00:00:01',
last_modified_time timestamp NOT NULL DEFAULT '1970-01-01 00:00:01',
PRIMARY KEY (id)
);

insert into user(name,gender,age,comment,validity,create_time,last_modified_time)
VALUES
  ('老大',true,'14','这是老大',true,now(),now()),
  ('老二',true,'18','这是老二',false,now(),now()),
  ('老三',false,'6','这是老三',false,now(),now()),
  ('老四',true,'8','这是老四',true,now(),now()),
  ('老五',false,'21','这是老五',false,now(),now()),
  ('老六',true,'45','这是老六',false,now(),now()),
  ('老七',false,'124','这是老七',true,now(),now()),
  ('老八',true,'0','这是老八',false,now(),now()),
  ('老九',true,'67','这是老九',true,now(),now()),
  ('老十',true,'56','这是老十',false,now(),now()),
  ('老十一',false,'25','这是老十一',true,now(),now());