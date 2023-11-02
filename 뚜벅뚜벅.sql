CREATE TABLE `users` (
	`id`	bigint	NOT NULL,
	`password`	varchar(45)	NULL,
	`email`	varchar(45)	NULL,
	`name`	varchar(45)	NULL,
	`admin`	boolean	NULL
);

CREATE TABLE `groups` (
	`id`	bigint	NOT NULL,
	`name`	varchar(45)	NULL,
	`user_id`	bigint	NOT NULL,
	`houseinfo_id`	bigint	NOT NULL
);

CREATE TABLE `notices` (
	`id`	bigint	NOT NULL,
	`title`	varchar(45)	NULL,
	`content`	varchar(2000)	NULL,
	`view`	int	NULL,
	`created_at`	datetime	NULL,
	`user_id`	bigint	NULL
);

CREATE TABLE `apart_checklists` (
	`id`	bigint	NOT NULL,
	`form`	varchar(2000)	NULL,
	`user_id`	bigint	NOT NULL,
	`houseinfo_id`	bigint	NOT NULL
);

CREATE TABLE `likes` (
	`id`	bigint	NOT NULL,
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
	`id`	bigint	NOT NULL,
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
	`id`	bigint	NOT NULL,
	`form`	varchar(2000)	NULL,
	`user_id`	bigint	NOT NULL,
	`houseinfo_id`	bigint	NOT NULL
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`id`
);

ALTER TABLE `groups` ADD CONSTRAINT `PK_GROUPS` PRIMARY KEY (
	`id`
);

ALTER TABLE `notices` ADD CONSTRAINT `PK_NOTICES` PRIMARY KEY (
	`id`
);

ALTER TABLE `apart_checklists` ADD CONSTRAINT `PK_APART_CHECKLISTS` PRIMARY KEY (
	`id`
);

ALTER TABLE `likes` ADD CONSTRAINT `PK_LIKES` PRIMARY KEY (
	`id`
);

ALTER TABLE `questions` ADD CONSTRAINT `PK_QUESTIONS` PRIMARY KEY (
	`id`
);

ALTER TABLE `answers` ADD CONSTRAINT `PK_ANSWERS` PRIMARY KEY (
	`id`
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

ALTER TABLE `apart_move_checklists` ADD CONSTRAINT `PK_APART_MOVE_CHECKLISTS` PRIMARY KEY (
	`id`
);

