DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `holiday`;
DROP TABLE IF EXISTS `leave_holiday`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `staff_id` int(11) unsigned DEFAULT '0' COMMENT '员工工号',
  `name`varchar(20) NOT NULL DEFAULT '' COMMENT '中文姓名',
  `mobile`varchar(20) NOT NULL DEFAULT '' COMMENT '电话号码',
  `area`varchar(32) NOT NULL DEFAULT '' COMMENT '工作地区，例如北京、上海等',
  `gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别，1 男2 女',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1:在职2：离职',
  PRIMARY KEY (`id`),
  UNIQUE INDEX uniq_staff_id(`staff_id`),
  INDEX idx_name(`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息表';

CREATE TABLE `holiday` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `staff_id` int(11) unsigned DEFAULT '0' COMMENT '员工工号',
  `sick_num` int(10) NOT NULL DEFAULT '0' COMMENT '病假',
  `annual_num` int(10) NOT NULL DEFAULT '0' COMMENT '年假',
  PRIMARY KEY (`id`),
  UNIQUE INDEX uniq_staff_id(`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工假期表';

CREATE TABLE `leave_holiday` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `staff_id` int(11) unsigned DEFAULT '0' COMMENT '员工工号',
  `start_date` datetime NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '开始时间',
  `end_date` datetime NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '开始时间',
  `day_num` int(20) NOT NULL DEFAULT '0' COMMENT '使用库存',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1:年假2：病假',
  `area`varchar(32) NOT NULL DEFAULT '' COMMENT '工作地区，例如北京、上海等',
  PRIMARY KEY (`id`),
  INDEX idx_staff_id(`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工请假表';

SET NAMES utf8;

insert into `employee` (`id`, `staff_id`, `name`, `mobile`, `area`, `gender`, `is_valid`) values('1','1','赵四','13111111111','北京','1','1');
insert into `employee` (`id`, `staff_id`, `name`, `mobile`, `area`, `gender`, `is_valid`) values('2','2','张三','15122222222','北京','1','1');
insert into `employee` (`id`, `staff_id`, `name`, `mobile`, `area`, `gender`, `is_valid`) values('3','3','李四','18910082222','上海','2','2');
insert into `employee` (`id`, `staff_id`, `name`, `mobile`, `area`, `gender`, `is_valid`) values('4','4','张方','15110072222','上海','1','1');
insert into `employee` (`id`, `staff_id`, `name`, `mobile`, `area`, `gender`, `is_valid`) values('5','5','小明','15110062222','上海','1','1');
insert into `employee` (`id`, `staff_id`, `name`, `mobile`, `area`, `gender`, `is_valid`) values('6','6','旺财','15110052222','上海','1','1');
insert into `employee` (`id`, `staff_id`, `name`, `mobile`, `area`, `gender`, `is_valid`) values('7','7','翠花','15110042222','上海','1','1');

insert into `holiday` (`id`, `staff_id`, `sick_num`, `annual_num`) values('1','1','10','10');
insert into `holiday` (`id`, `staff_id`, `sick_num`, `annual_num`) values('2','2','8','8');
insert into `holiday` (`id`, `staff_id`, `sick_num`, `annual_num`) values('3','3','6','10');
insert into `holiday` (`id`, `staff_id`, `sick_num`, `annual_num`) values('4','4','10','6');
insert into `holiday` (`id`, `staff_id`, `sick_num`, `annual_num`) values('5','5','10','10');
insert into `holiday` (`id`, `staff_id`, `sick_num`, `annual_num`) values('6','6','10','10');
insert into `holiday` (`id`, `staff_id`, `sick_num`, `annual_num`) values('7','7','10','10');

insert into `leave_holiday` (`id`, `staff_id`, `start_date`, `end_date`, `day_num`, `type`, `area`) values('1','2','2016-01-17
00:00:00','2016-01-19 00:00:00','2','1','北京');
insert into `leave_holiday` (`id`, `staff_id`, `start_date`, `end_date`, `day_num`, `type`, `area`) values('2','4','2016-03-06
00:00:00','2016-03-07 00:00:00','4','1','上海');
insert into `leave_holiday` (`id`, `staff_id`, `start_date`, `end_date`, `day_num`, `type`, `area`) values('3','2','2016-01-21
00:00:00','2016-01-22 00:00:00','2','2','北京');
