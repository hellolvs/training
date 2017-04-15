DROP TABLE IF EXISTS diff;

CREATE TABLE diff (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  owner_name varchar(20) NOT NULL DEFAULT '' COMMENT '所有者姓名',
  diff_time timestamp NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '对比时间',
  source_file_content varchar(1024) NOT NULL DEFAULT '' COMMENT '源文件内容',
  target_file_content varchar(1024) NOT NULL DEFAULT '' COMMENT '目标文件内容',
  diff_content varchar(2560) NOT NULL DEFAULT '' COMMENT '差异内容',
  PRIMARY KEY (id),
  INDEX idx_owner_name(owner_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件对比表';