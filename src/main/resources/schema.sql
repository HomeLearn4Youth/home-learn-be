drop database ddubeok;
create schema ddubeok;
use ddubeok;

CREATE TABLE `users` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`password`	varchar(45)	NULL,
	`email`	varchar(45)	NULL,
	`name`	varchar(45)	NULL,
	`admin`	boolean	DEFAULT false
    
);

CREATE TABLE `groups` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name`	varchar(45)	NULL,
	`user_id`	bigint	NOT NULL,
	`houseinfo_id`	bigint	NOT NULL
);

CREATE TABLE `notices` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title`	varchar(45)	NULL,
	`content`	varchar(2000)	NULL,
	`view`	int	NULL,
	`created_at`	datetime	NULL,
	`user_id`	bigint	NULL
);

CREATE TABLE `apart_checklists` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`form`	varchar(2000)	NULL,
	`user_id`	bigint	NOT NULL,
	`houseinfo_id`	bigint	NOT NULL
);

CREATE TABLE `likes` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_id`	bigint	NOT NULL,
	`houseinfo_id`	bigint	NOT NULL
);

CREATE TABLE `questions` (
	`id`	bigint	NOT NULL,
	`title`	varchar(100)	NULL,
	`content`	varchar(2000)	NULL,
	`password`	varchar(45)	NULL
);

CREATE TABLE `answers` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`content`	varchar(2000)	NULL,
	`question_id`	bigint	NOT NULL
);

CREATE TABLE `houseinfo` (
	`aptCode`	bigint	NOT NULL,
	`buildYear`	int	NULL,
	`roadName`	varchar(40)	NULL,
	`roadNameBonbun`	varchar(5)	NULL,
	`roadNameBubun`	varchar(5)	NULL,
	`roadNameSeq`	varchar(2)	NULL,
	`roadNameBasementCode`	varchar(1)	NULL,
	`dong`	varchar(40)	NULL,
	`bonbun`	varchar(4)	NULL,
	`bubun`	varchar(4)	NULL,
	`sigunguCode`	varchar(5)	NULL,
	`eubmyundongCode`	varchar(5)	NULL,
	`dongCode`	varchar(10)	NULL,
	`landCode`	varchar(1)	NULL,
	`apartmentName`	varchar(40)	NULL,
	`jibun`	varchar(10)	NULL,
	`lng`	varchar(30)	NULL,
	`lat`	varchar(30)	NULL
);

CREATE TABLE `housedeal` (
	`no`	bigint	NOT NULL,
	`dealAmount`	varchar(20)	NULL,
	`dealYear`	int	NULL,
	`dealMonth`	int	NULL,
	`dealDay`	int	NULL,
	`area`	varchar(20)	NULL,
	`floor`	varchar(4)	NULL,
	`cancelDealType`	varchar(1)	NULL,
	`aptCode`	bigint	NOT NULL
);

CREATE TABLE `houselease` (
	`no`	bigint	NOT NULL,
	`buildYear`	int	NULL,
	`Field`	varchar(40)	NULL,
	`dong`	varchar(40)	NULL,
	`jibun`	varchar(10)	NULL,
	`apartmentName`	varchar(40)	NULL,
	`regionalCode`	varchar(5)	NULL,
	`deposit`	varchar(20)	NULL,
	`dealYear`	int	NULL,
	`dealMonth`	int	NULL,
	`dealDay`	int	NULL,
	`monthlyRent`	varchar(20)	NULL,
	`areaForExclusiveUse`	varchar(20)	NULL,
	`previousDeposit`	varchar(20)	NULL,
	`previousMonthlyRent`	varchar(20)	NULL,
	`floor`	varchar(4)	NULL
);

CREATE TABLE `apart_move_checklists` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`form`	varchar(2000)	NULL,
	`user_id`	bigint	NOT NULL,
	`houseinfo_id`	bigint	NOT NULL
);


ALTER TABLE `houseinfo` ADD CONSTRAINT `PK_HOUSEINFO` PRIMARY KEY (
	`aptCode`
);

ALTER TABLE `housedeal` ADD CONSTRAINT `PK_HOUSEDEAL` PRIMARY KEY (
	`no`
);

ALTER TABLE `houselease` ADD CONSTRAINT `PK_HOUSELEASE` PRIMARY KEY (
	`no`
);

insert into users(password, email, name)
values ("1234", "ssafy@ssafy.com", "김싸피");
insert into users(password, email, name)
values ("1234","xptmxm@safy.com", "홍길동");
insert into users(password, email, name)
values ("1234", "test2@ssafy.com", "김유신");
insert into users(password, email, name)
values ("1234", "test3@ssafy.com", "이순신");
insert into users(password, email, name)
values ("1234", "test4@ssafy.com", "세종대왕");