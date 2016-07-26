drop table if exists slardartask;
drop table if exists slardaruser;
drop TABLE if EXISTS PAY_ACCOUNT;

create table PAY_ACCOUNT (
	id VARCHAR(32)  NOT NULL,
	name varchar(10) NOT NULL,
	pro_id varchar(64) ,
	create_time DATETIME ,
	balance DOUBLE DEFAULT 0,
	account_frozen varchar(64) ,
	frozen_money DOUBLE DEFAULT 0,
	available_balance DOUBLE default 0,
	primary key (id)
) engine=InnoDB;

insert PAY_ACCOUNT(id,name,pro_id) values('t1','t1','t_p1');