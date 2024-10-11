DROP SCHEMA IF EXISTS `starcast`;
CREATE SCHEMA `starcast`;
USE `starcast`;

CREATE TABLE `auth` (
    `auth_uid` VARCHAR(36) NOT NULL,
    `kakao_uid` VARCHAR(36) NOT NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    PRIMARY KEY (`auth_uid`)
);

CREATE TABLE `user_info_tmp` (
    `user_info_tmp_uid`    VARCHAR(36)    NOT NULL,
    `auth_uid`    VARCHAR(36)    NOT NULL,
    `email`    VARCHAR(320)    NULL,
    `name`    VARCHAR(20)    NULL,
    `nickname`    VARCHAR(14)    NULL,
    `profile_image_num`    VARCHAR(20)    NULL,
    `consent_gps`    BOOLEAN    NULL,
    `consent_notice`    BOOLEAN    NULL,
    `created_date`    TIMESTAMP    NULL,
    `modified_date`    TIMESTAMP    NULL,
    PRIMARY KEY (`user_info_tmp_uid`),
    FOREIGN KEY (`auth_uid`) REFERENCES auth (`auth_uid`)
);

CREATE TABLE `rank` (
    `rank_uid` VARCHAR(36) NOT NULL,
    `name` VARCHAR(50) NULL,
    PRIMARY KEY (`rank_uid`)
);

CREATE TABLE `profile` (
    `profile_uid` VARCHAR(36) NOT NULL,
    `auth_uid` VARCHAR(36) NOT NULL,
    `rank_uid` VARCHAR(36) NOT NULL,
    `email` VARCHAR(50) NULL,
    `name` VARCHAR(20) NULL,
    `nickname` VARCHAR(14) NULL,
    `profile_image_num` VARCHAR(20) NULL,
    `exp` INT NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    `is_deleted` BOOLEAN NULL,
	`action_place_type` VARCHAR(20) NULL,
    PRIMARY KEY (`profile_uid`),
    FOREIGN KEY (`auth_uid`) REFERENCES `auth` (`auth_uid`),
    FOREIGN KEY (`rank_uid`) REFERENCES `rank` (`rank_uid`)
);

CREATE TABLE `place` (
    `place_uid` VARCHAR(36) NOT NULL,
    `name` VARCHAR(100) NULL,
    `type` VARCHAR(20) NULL,
    `address1` VARCHAR(20) NULL,
    `address2` VARCHAR(20) NULL,
    `address3` VARCHAR(20) NULL,
    `address4` VARCHAR(20) NULL,
    `nx` INT,
    `ny` INT,
    `image` VARCHAR(2000) NULL,
    `phone_num` VARCHAR(20) NULL,
    `web_address` VARCHAR(2000) NULL,
    `light_pollution` decimal(5, 2) NOT NULL,
    PRIMARY KEY (`place_uid`)
);

CREATE TABLE `plan` (
    `plan_uid` VARCHAR(36) NOT NULL,
    `profile_uid` VARCHAR(36) NOT NULL,
    `place_uid` VARCHAR(36) NOT NULL,
    `date_time` TIMESTAMP NULL,
    `castar_point` INT NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    `is_deleted` BOOLEAN NULL,
    PRIMARY KEY (`plan_uid`),
    FOREIGN KEY (`profile_uid`) REFERENCES `profile` (`profile_uid`),
    FOREIGN KEY (`place_uid`) REFERENCES `place` (`place_uid`)
);

CREATE TABLE `community` (
    `community_uid` VARCHAR(36) NOT NULL,
    `profile_uid` VARCHAR(36) NOT NULL,
    `place_uid` VARCHAR(36) NOT NULL,
    `title` VARCHAR(200) NULL,
    `content` VARCHAR(500) NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    `plan_uid` VARCHAR(36) NULL,  -- `nullable`로 변경
    `is_deleted` BOOLEAN NULL,
    PRIMARY KEY (`community_uid`),
    FOREIGN KEY (`profile_uid`) REFERENCES `profile` (`profile_uid`),
    FOREIGN KEY (`place_uid`) REFERENCES `place` (`place_uid`),
    FOREIGN KEY (`plan_uid`) REFERENCES `plan` (`plan_uid`)
);

CREATE TABLE `consent` (
    `consent_uid` VARCHAR(36) NOT NULL,
    `profile_uid` VARCHAR(36) NOT NULL,
    `type` VARCHAR(100) NULL,
    `flag` BOOLEAN NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    PRIMARY KEY (`consent_uid`),
    FOREIGN KEY (`profile_uid`) REFERENCES `profile` (`profile_uid`)
);

CREATE TABLE `favourite_spot` (
    `favourite_spot_uid` VARCHAR(36) NOT NULL,
    `profile_uid` VARCHAR(36) NOT NULL,
    `place_uid` VARCHAR(36) NOT NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    `is_deleted` BOOLEAN NULL,
    PRIMARY KEY (`favourite_spot_uid`),
    FOREIGN KEY (`profile_uid`) REFERENCES `profile` (`profile_uid`),
    FOREIGN KEY (`place_uid`) REFERENCES `place` (`place_uid`)
);

CREATE TABLE `community_image` (
    `image_uid` VARCHAR(36) NOT NULL,
    `community_uid` VARCHAR(36) NOT NULL,
    `url` VARCHAR(50) NULL,
    `image_seq` INT NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    `is_deleted` BOOLEAN NULL,
    PRIMARY KEY (`image_uid`),
    FOREIGN KEY (`community_uid`) REFERENCES `community` (`community_uid`)
);

CREATE TABLE `reaction` (
    `reaction_uid` VARCHAR(36) NOT NULL,
    `community_uid` VARCHAR(36) NOT NULL,
    `profile_uid` VARCHAR(36) NOT NULL,
    `reaction_type` VARCHAR(20) NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    PRIMARY KEY (`reaction_uid`),  
    FOREIGN KEY (`community_uid`) REFERENCES `community` (`community_uid`),
    FOREIGN KEY (`profile_uid`) REFERENCES `profile` (`profile_uid`)
);

CREATE TABLE `celestial_events` (
    `celestial_event_uid` VARCHAR(13) NOT NULL,
    `title` VARCHAR(1000) NULL,
    `event` VARCHAR(1000) NULL,
    `celestial_event_hour` INT NULL,
    `celestial_event_min` INT NULL,
    PRIMARY KEY (`celestial_event_uid`)
);

CREATE TABLE `lunar_cycle` (
    `lunar_cycle_uid` VARCHAR(47) NOT NULL,
    `lun_age` DECIMAL(5,2) NULL,
    PRIMARY KEY (`lunar_cycle_uid`)
);

CREATE TABLE `moonrise_moonset_times` (
    `moon_rise_set_time_uid` VARCHAR(47) NOT NULL,
    `place_uid` VARCHAR(36) NOT NULL,
    `moonrise_hour` INT NULL,
    `moonrise_min` INT NULL,
    `moonset_hour` INT NULL,
    `moonset_min` INT NULL,
    PRIMARY KEY (`moon_rise_set_time_uid`),
    FOREIGN KEY (`place_uid`) REFERENCES `place` (`place_uid`)
);

CREATE TABLE `notice` (
    `notice_uid` VARCHAR(36) NOT NULL,
    `profile_uid` VARCHAR(36) NOT NULL,
    `title` VARCHAR(255) NULL,
    `content` VARCHAR(255) NULL,
    `is_read` BOOLEAN NULL,   -- 변경
    `created_date` TIMESTAMP NULL,   -- 변경
    `modified_date` TIMESTAMP NULL,  -- 변경
    PRIMARY KEY (`notice_uid`),
    FOREIGN KEY (`profile_uid`) REFERENCES `profile` (`profile_uid`)
);

CREATE TABLE `forecast` (
    `forecast_uid` VARCHAR(47) NOT NULL,
    `place_uid` VARCHAR(36) NOT NULL,
    `sky_00` INT NULL,
    `pty_00` INT NULL,
    `humidity_00` DECIMAL(5,2) NULL,  -- 변경
    `sky_01` INT NULL,
    `pty_01` INT NULL,
    `humidity_01` DECIMAL(5,2) NULL,  -- 변경
    `sky_02` INT NULL,
    `pty_02` INT NULL,
    `humidity_02` DECIMAL(5,2) NULL,  -- 변경
    `sky_21` INT NULL,
    `pty_21` INT NULL,
    `humidity_21` DECIMAL(5,2) NULL,  -- 변경
    `sky_22` INT NULL,
    `pty_22` INT NULL,
    `humidity_22` DECIMAL(5,2) NULL,  -- 변경
    `sky_23` INT NULL,
    `pty_23` INT NULL,
    `humidity_23` DECIMAL(5,2) NULL,  -- 변경
    PRIMARY KEY (`forecast_uid`),
    FOREIGN KEY (`place_uid`) REFERENCES `place` (`place_uid`)
);

CREATE TABLE `my_spot` (
    `my_spot_uid` VARCHAR(36) NOT NULL,
    `profile_uid` VARCHAR(36) NOT NULL,
    `place_uid` VARCHAR(36) NOT NULL,
    `created_date` TIMESTAMP NULL,
    `modified_date` TIMESTAMP NULL,
    `is_deleted` BOOLEAN NULL,
    PRIMARY KEY (`my_spot_uid`), 
    FOREIGN KEY (`profile_uid`) REFERENCES `profile` (`profile_uid`),
    FOREIGN KEY (`place_uid`) REFERENCES `place` (`place_uid`)
);

CREATE TABLE `adverb` (
	`adverb_uid`	VARCHAR(5)	NOT NULL,
	`content`	VARCHAR(36)	NULL,
	PRIMARY KEY (`adverb_uid`)
);

CREATE TABLE `adjective` (
	`adjective_uid`	VARCHAR(5)	NOT NULL,
	`content`	VARCHAR(36)	NULL,
	PRIMARY KEY (`adjective_uid`)
);

CREATE TABLE `random_nickname` (
	`random_nickname_uid`	VARCHAR(14)	NOT NULL,
	`adverb_uid`	VARCHAR(5)	NOT NULL,
	`adjective_uid`	VARCHAR(5)	NOT NULL,
	`sequence`	INT	NULL,
	PRIMARY KEY (`random_nickname_uid`),
    	FOREIGN KEY (`adverb_uid`) REFERENCES `adverb` (`adverb_uid`),
   	FOREIGN KEY (`adjective_uid`) REFERENCES `adjective` (`adjective_uid`)
);

CREATE TABLE BATCH_JOB_INSTANCE  (
	JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT ,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(32) NOT NULL,
	constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
	JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT  ,
	JOB_INSTANCE_ID BIGINT NOT NULL,
	CREATE_TIME DATETIME(6) NOT NULL,
	START_TIME DATETIME(6) DEFAULT NULL ,
	END_TIME DATETIME(6) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED DATETIME(6),
	constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
	references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	PARAMETER_NAME VARCHAR(100) NOT NULL ,
	PARAMETER_TYPE VARCHAR(100) NOT NULL ,
	PARAMETER_VALUE VARCHAR(2500) ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
	STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID BIGINT NOT NULL,
	CREATE_TIME DATETIME(6) NOT NULL,
	START_TIME DATETIME(6) DEFAULT NULL ,
	END_TIME DATETIME(6) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	COMMIT_COUNT BIGINT ,
	READ_COUNT BIGINT ,
	FILTER_COUNT BIGINT ,
	WRITE_COUNT BIGINT ,
	READ_SKIP_COUNT BIGINT ,
	WRITE_SKIP_COUNT BIGINT ,
	PROCESS_SKIP_COUNT BIGINT ,
	ROLLBACK_COUNT BIGINT ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED DATETIME(6),
	constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
	references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);




INSERT INTO `auth` (`auth_uid`, `kakao_uid`, `created_date`, `modified_date`) VALUES
('auth-uid-01', 1001, NOW(), NOW()),
('auth-uid-02', 1002, NOW(), NOW()),
('auth-uid-03', 1003, NOW(), NOW()),
('auth-uid-04', 1004, NOW(), NOW()),
('auth-uid-05', 1005, NOW(), NOW()),
('auth-uid-06', 1006, NOW(), NOW()),
('auth-uid-07', 1007, NOW(), NOW()),
('auth-uid-08', 1008, NOW(), NOW()),
('auth-uid-09', 1009, NOW(), NOW()),
('auth-uid-10', 1010, NOW(), NOW()),
('auth-uid-11', 1011, NOW(), NOW()),
('auth-uid-12', 1012, NOW(), NOW()),
('auth-uid-13', 1013, NOW(), NOW()),
('auth-uid-14', 1014, NOW(), NOW()),
('auth-uid-15', 1015, NOW(), NOW()),
('auth-uid-16', 1016, NOW(), NOW()),
('auth-uid-17', 1017, NOW(), NOW()),
('auth-uid-18', 1018, NOW(), NOW()),
('auth-uid-19', 1019, NOW(), NOW()),
('auth-uid-20', 1020, NOW(), NOW());

INSERT INTO `rank` (`rank_uid`, `name`) VALUES
('rank-uid-01', 'BASIC'),
('rank-uid-02', 'BRONZE'),
('rank-uid-03', 'SILVER'),
('rank-uid-04', 'GOLD'),
('rank-uid-05', 'PLATINUM');

INSERT INTO `profile` (`profile_uid`, `auth_uid`, `rank_uid`, `email`, `name`, `nickname`, `profile_image_num`, `exp`, `created_date`, `modified_date`, `is_deleted`, `action_place_type`) VALUES
('profile-uid-01', 'auth-uid-01', 'rank-uid-05', 'somi1@test.com', '박소미', '하늘을 나는 캐스타', 'BASIC_1', 100, NOW(), NOW(), FALSE, 'MY_SPOT'),
('profile-uid-02', 'auth-uid-02', 'rank-uid-04', 'dayoung2@test.com', '강다영', '노래가 좋은 캐스타', 'BASIC_2', 200, NOW(), NOW(), FALSE, 'GPS'),
('profile-uid-03', 'auth-uid-03', 'rank-uid-03', 'harim3@test.com', '이하림', '춤추는 별빛 캐스타', 'BASIC_3', 300, NOW(), NOW(), FALSE, 'GPS'),
('profile-uid-04', 'auth-uid-04', 'rank-uid-02', 'seun4@test.com', '박세은', '산을 넘는 캐스타', 'BASIC_1', 400, NOW(), NOW(), FALSE, 'MY_SPOT'),
('profile-uid-05', 'auth-uid-05', 'rank-uid-01', 'sungjae5@test.com', '곽성재', '바람을 가르는 캐스타', 'BASIC_2', 500, NOW(), NOW(), FALSE, 'MY_SPOT'),
('profile-uid-06', 'auth-uid-06', 'rank-uid-05', 'wonwoo6@test.com', '조원우', '햇살을 받는 캐스타', 'BASIC_3', 600, NOW(), NOW(), FALSE, 'MY_SPOT'),
('profile-uid-07', 'auth-uid-07', 'rank-uid-04', 'paul7@test.com', '폴킴', '하늘을 달리는 캐스타', 'BASIC_1', 700, NOW(), NOW(), FALSE, 'GPS'),
('profile-uid-08', 'auth-uid-08', 'rank-uid-03', 'freddie8@test.com', '프레디 머큐리', '은하수를 걷는 캐스타', 'BASIC_1', 800, NOW(), NOW(), FALSE, 'GPS'),
('profile-uid-09', 'auth-uid-09', 'rank-uid-02', 'bowie9@test.com', '데이비드 보이', '노을을 바라보는 캐스타', 'BASIC_1', 900, NOW(), NOW(), FALSE, 'GPS'),
('profile-uid-10', 'auth-uid-10', 'rank-uid-01', 'prince10@test.com', '프린스', '별을 잡는 캐스타', 'BASIC_1', 1000, NOW(), NOW(), FALSE, 'MY_SPOT'),
('profile-uid-11', 'auth-uid-11', 'rank-uid-01', 'eddie11@test.com', '에드베더', '구름 위를 걷는 캐스타', 'BASIC_1', 1100, NOW(), NOW(), FALSE, 'MY_SPOT'),
('profile-uid-12', 'auth-uid-12', 'rank-uid-01', 'david12@test.com', '고롱', '달빛 속의 캐스타', 'BASIC_1', 1200, NOW(), NOW(), FALSE, 'MY_SPOT'),
('profile-uid-13', 'auth-uid-13', 'rank-uid-01', 'stevie13@test.com', '스원', '노래를 부르는 캐스타', 'BASIC_1', 1300, NOW(), NOW(), FALSE, 'GPS'),
('profile-uid-14', 'auth-uid-14', 'rank-uid-01', 'aretha14@test.com', '알사', '별빛을 비추는 캐스타', 'BASIC_1', 1400, NOW(), NOW(), FALSE, 'GPS'),
('profile-uid-15', 'auth-uid-15', 'rank-uid-01', 'tina15@test.com', '티터', '노을 아래의 캐스타', 'BASIC_1', 1500, NOW(), NOW(), FALSE, 'GPS');


INSERT INTO `place` (`place_uid`, `name`, `type`, `address1`, `address2`, `address3`, `address4`, `nx`, `ny`, `image`, `phone_num`, `web_address`, `light_pollution`)
VALUES 
('888bf8b6-9434-45f9-b48f-d74968916d8a', '5173025000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '횡성읍', 77, 125, '', '', '', 48.1),
('4d21f8bb-cab0-4bdf-9d42-f21f3c7e0744', '5173031000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '우천면', 79, 125, '', '', '', 48.1),
('cc46dabd-70c0-4cb5-9788-ff70fdeeda7e', '5173032000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '안흥면', 80, 124, '', '', '', 48.1),
('63a8b162-2f31-4ba3-b03b-6d52e582a8d0', '5173033000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '둔내면', 81, 126, '', '', '', 48.1),
('730f8c44-c101-4d68-8c7c-7c2dd6ae1344', '5173034000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '갑천면', 79, 127, '', '', '', 48.1),
('5ecf126c-8136-4f21-83a0-a1e4c6ed4cbd', '5173035000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '청일면', 80, 127, '', '', '', 48.1),
('b4363c7f-48e3-4a30-b598-acbecca8173d', '5173036000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '공근면', 77, 126, '', '', '', 48.1),
('6f6d9539-8d98-4559-ae19-963f2da0a2a9', '5173037000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '서원면', 75, 125, '', '', '', 48.1),
('23add251-9be5-431c-b78e-f91fbac4e7a2', '5173038000', 'EUP_MYUN_DONG', '강원특별자치도', '횡성군', NULL, '강림면', 80, 123, '', '', '', 48.1),
('d091a009-80d7-4498-8df2-1607ae47fc25', '5179025000', 'EUP_MYUN_DONG', '강원특별자치도', '화천군', NULL, '화천읍', 72, 139, '', '', '', 48.1),
('3020d92d-7544-418c-94f7-927fad490b14', '5179031000', 'EUP_MYUN_DONG', '강원특별자치도', '화천군', NULL, '간동면', 74, 137, '', '', '', 48.1),
('cdbe48d7-a6ee-4e7e-beb3-7d94e7bd0cd3', '5179032000', 'EUP_MYUN_DONG', '강원특별자치도', '화천군', NULL, '하남면', 71, 138, '', '', '', 48.1),
('e9ef9f79-2f24-483f-b814-fbb27203842c', '5179033000', 'EUP_MYUN_DONG', '강원특별자치도', '화천군', NULL, '상서면', 71, 140, '', '', '', 48.1),
('9e0b2b76-0058-4441-bdf2-fed6499196d8', '5179034000', 'EUP_MYUN_DONG', '강원특별자치도', '화천군', NULL, '사내면', 69, 138, '', '', '', 48.1),
('469b2a13-bed7-41c8-9aac-f981e0b27e30', '5172025000', 'EUP_MYUN_DONG', '강원특별자치도', '홍천군', NULL, '홍천읍', 75, 130, '', '', '', 48.1),
('7e72187e-baeb-4e66-960f-38fb1b1f5e2d', '5172031000', 'EUP_MYUN_DONG', '강원특별자치도', '홍천군', NULL, '화촌면', 77, 131, '', '', '', 48.1),
('66a4178b-de57-49de-99bc-32dc37b3906a', '5172032000', 'EUP_MYUN_DONG', '강원특별자치도', '홍천군', NULL, '두촌면', 78, 134, '', '', '', 48.1),
('41e03c22-c040-4d60-8d06-1e9cd1296762', '5172033000', 'EUP_MYUN_DONG', '강원특별자치도', '홍천군', NULL, '내촌면', 79, 132, '', '', '', 48.1),
('ea5814e0-9f97-4387-a7fd-874c98bde701', '5172034000', 'EUP_MYUN_DONG', '강원특별자치도', '홍천군', NULL, '서석면', 81, 130, '', '', '', 48.1),
('1d670c74-a15d-4b26-a0ee-7504b36a47e7', '5172035200', 'EUP_MYUN_DONG', '강원특별자치도', '홍천군', NULL, '영귀미면', 76, 129, '', '', '', 48.1),
('41e03c22-c040-4d60-8d06-123gas23c762', '5172033000', 'OBSERVATORY', '경기도', '홍천군', '수지구', '주니어천문스쿨 수지점', 79, 132, '', '', '', 48.1),
('ea5814e0-9f97-4387-a7fd-13c2t2vggge3', '5172034000', 'OBSERVATORY', '서울특별시', '관악구', '봉천동', '스텔라에떼', 60, 125, '', '', '', 33),
('1d670c74-a15d-4b26-a0ee-1134vcxrcghs', '5172035200', 'OBSERVATORY', '경상북도', '군위군', '감천면', '화북별빛산장', 87, 109, '', '', '', 4.3);

INSERT INTO `plan` (`plan_uid`, `profile_uid`, `place_uid`, `date_time`, `castar_point`, `created_date`, `modified_date`, `is_deleted`) VALUES
('plan-uid-01', 'profile-uid-01', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 57, NOW(), NOW(), FALSE),
('plan-uid-02', 'profile-uid-02', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 45, NOW(), NOW(), FALSE),
('plan-uid-03', 'profile-uid-03', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 83, NOW(), NOW(), FALSE),
('plan-uid-04', 'profile-uid-04', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 0, NOW(), NOW(), FALSE),
('plan-uid-05', 'profile-uid-05', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 14, NOW(), NOW(), FALSE),
('plan-uid-06', 'profile-uid-06', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 17, NOW(), NOW(), FALSE),
('plan-uid-07', 'profile-uid-07', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 96, NOW(), NOW(), FALSE),
('plan-uid-08', 'profile-uid-08', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 43, NOW(), NOW(), FALSE),
('plan-uid-09', 'profile-uid-09', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 54, NOW(), NOW(), FALSE),
('plan-uid-10', 'profile-uid-10', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 89, NOW(), NOW(), FALSE),
('plan-uid-11', 'profile-uid-11', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 78, NOW(), NOW(), FALSE),
('plan-uid-12', 'profile-uid-12', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 24, NOW(), NOW(), FALSE),
('plan-uid-13', 'profile-uid-13', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 56, NOW(), NOW(), FALSE),
('plan-uid-14', 'profile-uid-14', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 86, NOW(), NOW(), FALSE),
('plan-uid-15', 'profile-uid-15', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), 76, NOW(), NOW(), FALSE);

INSERT INTO `community` (`community_uid`, `profile_uid`, `place_uid`, `title`, `content`, `created_date`, `modified_date`, `plan_uid`, `is_deleted`) VALUES
('community-uid-01', 'profile-uid-01', '888bf8b6-9434-45f9-b48f-d74968916d8a', '밤하늘의 별을 관측하다', '오늘은 맑은 날씨 덕분에 별들을 선명하게 볼 수 있었습니다. 특히 오리온자리와 북두칠성이 인상적이었어요.', NOW(), NOW(), 'plan-uid-01', FALSE),
('community-uid-02', 'profile-uid-02', '888bf8b6-9434-45f9-b48f-d74968916d8a', '달과 별의 만남', '오늘 밤에는 달과 함께 여러 별자리들을 관측했습니다. 특히 화성과의 위치가 매우 아름다웠습니다.', NOW(), NOW(), 'plan-uid-02', FALSE),
('community-uid-03', 'profile-uid-03', '888bf8b6-9434-45f9-b48f-d74968916d8a', '은하수의 매력', '은하수가 잘 보이는 곳에서 밤하늘을 바라보니 너무나 아름다웠습니다. 정말 감동적인 경험이었어요.', NOW(), NOW(), 'plan-uid-03', FALSE),
('community-uid-04', 'profile-uid-04', '888bf8b6-9434-45f9-b48f-d74968916d8a', '행성 관측!', '오늘은 목성과 토성을 관측했습니다. 망원경으로 자세히 보니 링 모양이 정말 인상적이었어요.', NOW(), NOW(), 'plan-uid-04', FALSE),
('community-uid-05', 'profile-uid-05', '888bf8b6-9434-45f9-b48f-d74968916d8a', '별똥별을 잡다!', '별똥별을 여러 개 보았습니다! 정말 짧은 순간이었지만, 그 아름다움은 잊을 수 없을 것 같아요.', NOW(), NOW(), 'plan-uid-05', FALSE),
('community-uid-06', 'profile-uid-06', '888bf8b6-9434-45f9-b48f-d74968916d8a', '소행성과의 만남', '오늘은 소행성이 지구에 가까워지는 날이라고 해서 기대했는데, 망원경으로 가까이서 관측할 수 있었습니다!', NOW(), NOW(), 'plan-uid-06', FALSE),
('community-uid-07', 'profile-uid-07', '888bf8b6-9434-45f9-b48f-d74968916d8a', '별자리 탐험', '친구와 함께 별자리를 찾아보며 여름철 별자리들을 관측했어요. 대접전이 특히 멋졌습니다!', NOW(), NOW(), 'plan-uid-07', FALSE),
('community-uid-08', 'profile-uid-08', '888bf8b6-9434-45f9-b48f-d74968916d8a', '천체 사진 찍기', '오늘은 망원경으로 찍은 별 사진을 공유합니다. 정말 선명하게 찍혔어요!', NOW(), NOW(), 'plan-uid-08', FALSE),
('community-uid-09', 'profile-uid-09', '888bf8b6-9434-45f9-b48f-d74968916d8a', '행성과 별들의 조화', '저녁에 하늘을 보니 여러 행성과 별들이 조화를 이루고 있었습니다. 감탄하며 관측했어요.', NOW(), NOW(), 'plan-uid-09', FALSE),
('community-uid-10', 'profile-uid-10', '888bf8b6-9434-45f9-b48f-d74968916d8a', '환상적인 별빛', '오늘 밤의 별빛은 정말 환상적이었습니다. 친구와 함께 별을 세며 즐거운 시간을 보냈습니다.', NOW(), NOW(), 'plan-uid-10', FALSE),
('community-uid-11', 'profile-uid-11', '888bf8b6-9434-45f9-b48f-d74968916d8a', '우주에 대한 꿈', '오늘은 별을 보며 우주에 대한 다양한 생각을 했습니다. 정말 마음이 편안해지는 시간이었어요.', NOW(), NOW(), 'plan-uid-11', FALSE),
('community-uid-12', 'profile-uid-12', '888bf8b6-9434-45f9-b48f-d74968916d8a', '자연과의 교감', '별을 관측하며 자연과의 교감을 느꼈습니다. 고요한 밤하늘이 주는 평화로움이 너무 좋았어요.', NOW(), NOW(), 'plan-uid-12', FALSE),
('community-uid-13', 'profile-uid-13', '888bf8b6-9434-45f9-b48f-d74968916d8a', '천체망원경 사용법', '처음으로 천체망원경을 사용해 보았습니다. 별을 더 가까이서 볼 수 있는 기회를 가졌어요.', NOW(), NOW(), 'plan-uid-13', FALSE),
('community-uid-14', 'profile-uid-14', '888bf8b6-9434-45f9-b48f-d74968916d8a', '별빛 속으로', '밤하늘을 보며 별빛 속으로 빠져드는 느낌이었어요. 다음에도 꼭 다시 관측하고 싶습니다.', NOW(), NOW(), 'plan-uid-14', FALSE),
('community-uid-15', 'profile-uid-15', '888bf8b6-9434-45f9-b48f-d74968916d8a', '하늘의 보물', '하늘에는 정말 많은 보물이 숨겨져 있네요. 별을 찾는 재미가 쏠쏠합니다.', NOW(), NOW(), 'plan-uid-15', FALSE);

INSERT INTO `consent` (`consent_uid`, `profile_uid`, `type`, `flag`, `created_date`, `modified_date`) VALUES
('consent-uid-01', 'profile-uid-01', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-02', 'profile-uid-01', 'NOTICE', FALSE, NOW(), NOW()),
('consent-uid-03', 'profile-uid-02', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-04', 'profile-uid-02', 'NOTICE', TRUE, NOW(), NOW()),
('consent-uid-05', 'profile-uid-03', 'GPS', FALSE, NOW(), NOW()),
('consent-uid-06', 'profile-uid-04', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-07', 'profile-uid-05', 'GPS', FALSE, NOW(), NOW()),
('consent-uid-08', 'profile-uid-06', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-09', 'profile-uid-07', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-10', 'profile-uid-08', 'GPS', FALSE, NOW(), NOW()),
('consent-uid-11', 'profile-uid-09', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-12', 'profile-uid-10', 'GPS', FALSE, NOW(), NOW()),
('consent-uid-13', 'profile-uid-11', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-14', 'profile-uid-12', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-15', 'profile-uid-13', 'GPS', TRUE, NOW(), NOW()),
('consent-uid-16', 'profile-uid-14', 'GPS', FALSE, NOW(), NOW()),
('consent-uid-17', 'profile-uid-15', 'GPS', TRUE, NOW(), NOW());

INSERT INTO `favourite_spot` (`favourite_spot_uid`, `profile_uid`, `place_uid`, `created_date`, `modified_date`, `is_deleted`) VALUES
('fav-uid-01', 'profile-uid-01', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), NOW(), FALSE),
('fav-uid-02', 'profile-uid-01', '4d21f8bb-cab0-4bdf-9d42-f21f3c7e0744', NOW(), NOW(), FALSE),
('fav-uid-03', 'profile-uid-01', '9e0b2b76-0058-4441-bdf2-fed6499196d8', NOW(), NOW(), FALSE);

INSERT INTO `community_image` (`image_uid`, `community_uid`, `url`, `image_seq`, `created_date`, `modified_date`, `is_deleted`) VALUES
('img-uid-01', 'community-uid-01', 'http://image1.com', 1, NOW(), NOW(), FALSE),
('img-uid-02', 'community-uid-02', 'http://image2.com', 2, NOW(), NOW(), FALSE),
('img-uid-03', 'community-uid-03', 'http://image3.com', 3, NOW(), NOW(), FALSE),
('img-uid-04', 'community-uid-04', 'http://image4.com', 4, NOW(), NOW(), FALSE),
('img-uid-05', 'community-uid-05', 'http://image5.com', 5, NOW(), NOW(), FALSE);

INSERT INTO `reaction` (`reaction_uid`, `community_uid`, `profile_uid`, `reaction_type`, `created_date`, `modified_date`) VALUES
('reaction-uid-01', 'community-uid-01', 'profile-uid-01', 'VISIT_AGAIN', NOW(), NOW()),
('reaction-uid-02', 'community-uid-01', 'profile-uid-02', 'VISIT_AGAIN', NOW(), NOW()),
('reaction-uid-03', 'community-uid-01', 'profile-uid-03', 'VISIT_AGAIN', NOW(), NOW()),
('reaction-uid-04', 'community-uid-01', 'profile-uid-04', 'VISIT_AGAIN', NOW(), NOW()),
('reaction-uid-05', 'community-uid-01', 'profile-uid-05', 'VISIT_AGAIN', NOW(), NOW()),
('reaction-uid-06', 'community-uid-02', 'profile-uid-01', 'HELPFUL', NOW(), NOW()),
('reaction-uid-07', 'community-uid-02', 'profile-uid-02', 'HELPFUL', NOW(), NOW()),
('reaction-uid-08', 'community-uid-02', 'profile-uid-03', 'HELPFUL', NOW(), NOW()),
('reaction-uid-09', 'community-uid-02', 'profile-uid-04', 'HELPFUL', NOW(), NOW()),
('reaction-uid-10', 'community-uid-02', 'profile-uid-05', 'HELPFUL', NOW(), NOW()),
('reaction-uid-11', 'community-uid-03', 'profile-uid-01', 'NICE_PHOTOS', NOW(), NOW()),
('reaction-uid-12', 'community-uid-03', 'profile-uid-02', 'NICE_PHOTOS', NOW(), NOW());

INSERT INTO `celestial_events` (`celestial_event_uid`, `title`, `event`, `celestial_event_hour`, `celestial_event_min`) VALUES
('2024_10_01_01', '수성의 외합', '수성의 외합', 6, 0),
('2024_10_03_01', '합삭', '합삭', 3, 49),
('2024_10_03_02', '달의 원지점', '달의 원지점(406,500km)', 4, 39),
('2024_10_09_01', '목성 유', '목성 유(동-서)', 16, 0),
('2024_10_11_01', '상현달', '상현달', 3, 55),
('2024_10_15_01', '달과 토성의 근접', '달과 토성의 근접(1.0°)', 3, 5),
('2024_10_17_01', '달의 근지점', '달의 근지점(357,200km)', 9, 51),
('2024_10_17_02', '망', '망', 20, 26),
('2024_10_20_01', '달과 플레이아데스 성단의 근접', '달과 플레이아데스 성단의 근접(0.1°)', 4, 59),
('2024_11_01_01', '합삭', '합삭', 21, 47),
('2024_11_05_01', '황소자리 남쪽 유성우', '황소자리 남쪽 유성우 극대(ZHR=10)', 15, 26),
('2024_11_09_01', '상현달', '상현달', 14, 55),
('2024_11_14_01', '달의 근지점', '달의 근지점(360,100 km)', 20, 16),
('2024_11_16_01', '망', '망', 6, 28),
('2024_11_16_02', '토성 유', '토성 유(서-동)', 15, 0),
('2024_11_16_03', '수성의 동방최대이각', '수성의 동방최대이각(23°)', 17, 0),
('2024_11_17_01', '천왕성의 충', '천왕성의 충', 12, 0),
('2024_11_17_02', '사자자리 유성우', '사자자리 유성우 극대(ZHR=15)', 13, 0),
('2024_12_01_01', '합삭', '합삭', 15, 21),
('2024_12_03_01', '화성과 벌집 성단의 근접', '화성과 벌집 성단의 근접(1.6°)', 21, 8),
('2024_12_06_01', '수성의 내합', '수성의 내합', 11, 0),
('2024_12_08_01', '목성의 충', '목성의 충', 6, 0),
('2024_12_08_02', '화성 유', '화성 유(동-서)', 6, 0),
('2024_12_08_03', '달과 토성의 근접', '달과 토성의 근접(0.3°)', 17, 49),
('2024_12_08_04', '해왕성 유', '해왕성 유(서-동)', 20, 0),
('2024_12_09_01', '상현달', '상현달', 0, 27),
('2024_12_09_02', '해왕성식', '해왕성식', 17, 4);


INSERT INTO `lunar_cycle` (`lunar_cycle_uid`, `lun_age`) VALUES
('2024_10_01', 0.5),
('2024_10_02', 1.2),
('2024_10_03', 2.8),
('2024_10_04', 4.6),
('2024_10_05', 6.3),
('2024_10_06', 8.1),
('2024_10_07', 9.7),
('2024_10_08', 11.5),
('2024_10_09', 13.2),
('2024_10_10', 14.8);

INSERT INTO `moonrise_moonset_times` (`moon_rise_set_time_uid`, `place_uid`, `moonrise_hour`, `moonrise_min`, `moonset_hour`, `moonset_min`) VALUES
('2024_10_01_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 18, 45, 6, 20),
('2024_10_02_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 19, 00, 6, 35),
('2024_10_03_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 19, 15, 6, 50),
('2024_10_04_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 19, 30, 7, 05),
('2024_10_05_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 19, 45, 7, 20),
('2024_10_06_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 20, 00, 7, 35),
('2024_10_07_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 20, 15, 7, 50),
('2024_10_07_4d21f8bb-cab0-4bdf-9d42-f21f3c7e0744', '888bf8b6-9434-45f9-b48f-d74968916d8a', 21, 00, 8, 35),
('2024_10_07_9e0b2b76-0058-4441-bdf2-fed6499196d8', '888bf8b6-9434-45f9-b48f-d74968916d8a', 21, 00, 8, 35),
('2024_10_08_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 20, 30, 8, 05),
('2024_10_09_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 20, 45, 8, 20),
('2024_10_10_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 21, 00, 8, 35);

INSERT INTO `notice` (`notice_uid`, `profile_uid`, `title`, `content`, `is_read`, `created_date`, `modified_date`) VALUES
('notice-uid-01', 'profile-uid-01', '오늘 밤에 {예정된 천문 이벤트}를 볼 수 있어요!', '오늘 밤에 {예정된 천문 이벤트}를 볼 수 있어요!', FALSE, NOW(), NOW()),
('notice-uid-02', 'profile-uid-02', '2024.09.12에 진행한 관측은 어떠셨나요?', '2024.09.12에 진행한 관측은 어떠셨나요? 사진과 후기를 남겨주세요!', TRUE, NOW(), NOW()),
('notice-uid-03', 'profile-uid-03', '익명의 스타캐스터가 내 후기에 반응했어요.', '익명의 스타캐스터가 내 후기에 반응했어요. 도움이 됐어요 +1', FALSE, NOW(), NOW()),
('notice-uid-04', 'profile-uid-04', '별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.', '별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.', TRUE, NOW(), NOW()),
('notice-uid-05', 'profile-uid-05', 'Notice 5', 'This is notice 5 content', FALSE, NOW(), NOW());


INSERT INTO `forecast` (`forecast_uid`, `place_uid`, `sky_00`, `pty_00`, `humidity_00`, `sky_01`, `pty_01`, `humidity_01`, `sky_02`, `pty_02`, `humidity_02`, `sky_21`, `pty_21`, `humidity_21`, `sky_22`, `pty_22`, `humidity_22`, `sky_23`, `pty_23`, `humidity_23`) VALUES
('2024_10_01_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 1, 0, 70.0, 1, 0, 68.0, 2, 0, 65.0, 1, 0, 75.0, 1, 0, 72.0, 1, 0, 70.0),
('2024_10_02_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 2, 0, 72.0, 2, 0, 70.0, 3, 0, 68.0, 2, 0, 78.0, 2, 0, 75.0, 2, 0, 72.0),
('2024_10_03_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 3, 0, 74.0, 3, 0, 72.0, 4, 0, 70.0, 3, 0, 80.0, 3, 0, 77.0, 3, 0, 74.0),
('2024_10_04_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 4, 0, 76.0, 4, 0, 74.0, 5, 0, 72.0, 4, 0, 82.0, 4, 0, 79.0, 4, 0, 76.0),
('2024_10_05_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 5, 0, 78.0, 5, 0, 76.0, 6, 0, 74.0, 5, 0, 84.0, 5, 0, 81.0, 5, 0, 78.0),
('2024_10_06_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 1, 0, 70.0, 1, 0, 68.0, 2, 0, 65.0, 1, 0, 75.0, 1, 0, 72.0, 1, 0, 70.0),
('2024_10_07_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 1, 0, 30.0, 1, 0, 40.0, 2, 1, 50.0, 2, 1, 60.0, 3, 2, 70.0, 2, 1, 65.0),
('2024_10_08_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 1, 0, 74.0, 1, 1, 72.0, 2, 2, 70.0, 2, 3, 80.0, 3, 4, 77.0, 3, 5, 74.0),
('2024_10_09_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 4, 0, 76.0, 4, 0, 74.0, 5, 0, 72.0, 4, 0, 82.0, 4, 0, 79.0, 4, 0, 76.0),
('2024_10_10_888bf8b6-9434-45f9-b48f-d74968916d8a', '888bf8b6-9434-45f9-b48f-d74968916d8a', 5, 0, 78.0, 5, 0, 76.0, 6, 0, 74.0, 5, 0, 84.0, 5, 0, 81.0, 5, 0, 78.0),
('2024_10_07_4d21f8bb-cab0-4bdf-9d42-f21f3c7e0744', '888bf8b6-9434-45f9-b48f-d74968916d8a', 5, 0, 78.0, 5, 0, 76.0, 6, 0, 74.0, 5, 0, 84.0, 5, 0, 81.0, 5, 0, 78.0),
('2024_10_07_9e0b2b76-0058-4441-bdf2-fed6499196d8', '888bf8b6-9434-45f9-b48f-d74968916d8a', 5, 0, 78.0, 5, 0, 76.0, 6, 0, 74.0, 5, 0, 84.0, 5, 0, 81.0, 5, 0, 78.0);


INSERT INTO `my_spot` (`my_spot_uid`, `profile_uid`, `place_uid`, `created_date`, `modified_date`, `is_deleted`) VALUES
('myspot-uid-01', 'profile-uid-01', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), NOW(), FALSE),
('myspot-uid-02', 'profile-uid-02', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), NOW(), FALSE),
('myspot-uid-03', 'profile-uid-03', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), NOW(), FALSE),
('myspot-uid-04', 'profile-uid-04', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), NOW(), FALSE),
('myspot-uid-05', 'profile-uid-05', '888bf8b6-9434-45f9-b48f-d74968916d8a', NOW(), NOW(), FALSE);

INSERT INTO `adverb` (`adverb_uid`, `content`) VALUES
('ADV01', '갑자기'),
('ADV02', '가까이'),
('ADV03', '가만히'),
('ADV04', '간신히'),
('ADV05', '거의'),
('ADV06', '결국'),
('ADV07', '곧'),
('ADV08', '꾸준히'),
('ADV09', '꼭'),
('ADV10', '나중에'),
('ADV11', '당연히'),
('ADV12', '대체로'),
('ADV13', '더욱'),
('ADV14', '따로'),
('ADV15', '때때로'),
('ADV16', '또한'),
('ADV17', '마침내'),
('ADV18', '매우'),
('ADV19', '무엇보다'),
('ADV20', '반드시'),
('ADV21', '별로'),
('ADV22', '살짝'),
('ADV23', '서서히'),
('ADV24', '설마'),
('ADV25', '심지어'),
('ADV26', '아마도'),
('ADV27', '아주'),
('ADV28', '아직'),
('ADV29', '얼른'),
('ADV30', '완전히'),
('ADV31', '왠지'),
('ADV32', '우연히'),
('ADV33', '의도적으로'),
('ADV34', '이미'),
('ADV35', '일부러'),
('ADV36', '자주'),
('ADV37', '절대로'),
('ADV38', '점점'),
('ADV39', '정말로'),
('ADV40', '조금'),
('ADV41', '조용히'),
('ADV42', '즉시'),
('ADV43', '차라리'),
('ADV44', '천천히'),
('ADV45', '틀림없이'),
('ADV46', '확실히'),
('ADV47', '훨씬'),
('ADV48', '갑작스럽게'),
('ADV49', '극도로'),
('ADV50', '상당히');

INSERT INTO `adjective` (`adjective_uid`, `content`) VALUES
('ADJ01', '예쁜'),
('ADJ02', '멋진'),
('ADJ03', '귀여운'),
('ADJ04', '아름다운'),
('ADJ05', '똑똑한'),
('ADJ06', '착한'),
('ADJ07', '강한'),
('ADJ08', '빠른'),
('ADJ09', '느린'),
('ADJ10', '조용한'),
('ADJ11', '시끄러운'),
('ADJ12', '행복한'),
('ADJ13', '슬픈'),
('ADJ14', '화난'),
('ADJ15', '기쁜'),
('ADJ16', '놀라운'),
('ADJ17', '무서운'),
('ADJ18', '친절한'),
('ADJ19', '정직한'),
('ADJ20', '용감한'),
('ADJ21', '따뜻한'),
('ADJ22', '차가운'),
('ADJ23', '달콤한'),
('ADJ24', '쓴'),
('ADJ25', '짠'),
('ADJ26', '매운'),
('ADJ27', '싱거운'),
('ADJ28', '부드러운'),
('ADJ29', '단단한'),
('ADJ30', '넓은'),
('ADJ31', '좁은'),
('ADJ32', '높은'),
('ADJ33', '낮은'),
('ADJ34', '깊은'),
('ADJ35', '얕은'),
('ADJ36', '밝은'),
('ADJ37', '어두운'),
('ADJ38', '가벼운'),
('ADJ39', '무거운'),
('ADJ40', '깨끗한'),
('ADJ41', '더러운'),
('ADJ42', '건조한'),
('ADJ43', '축축한'),
('ADJ44', '날카로운'),
('ADJ45', '둔한'),
('ADJ46', '부끄러운'),
('ADJ47', '자랑스러운'),
('ADJ48', '피곤한'),
('ADJ49', '졸린'),
('ADJ50', '배고픈');

INSERT INTO `random_nickname` (`random_nickname_uid`, `adverb_uid`, `adjective_uid`, `sequence`) VALUES
('ADV01ADJ010001', 'ADV01', 'ADJ01', 0001),
('ADV01ADJ010002', 'ADV01', 'ADJ01', 0002),
('ADV02ADJ020001', 'ADV02', 'ADJ02', 0001),
('ADV02ADJ020002', 'ADV02', 'ADJ02', 0002),
('ADV03ADJ030001', 'ADV03', 'ADJ03', 0001),
('ADV03ADJ030002', 'ADV03', 'ADJ03', 0002),
('ADV04ADJ040001', 'ADV04', 'ADJ04', 0001),
('ADV04ADJ040002', 'ADV04', 'ADJ04', 0002),
('ADV05ADJ050001', 'ADV05', 'ADJ05', 0001),
('ADV05ADJ050002', 'ADV05', 'ADJ05', 0002),
('ADV06ADJ060001', 'ADV06', 'ADJ06', 0001),
('ADV06ADJ060002', 'ADV06', 'ADJ06', 0002),
('ADV07ADJ070001', 'ADV07', 'ADJ07', 0001),
('ADV07ADJ070002', 'ADV07', 'ADJ07', 0002),
('ADV08ADJ080001', 'ADV08', 'ADJ08', 0001),
('ADV08ADJ080002', 'ADV08', 'ADJ08', 0002),
('ADV09ADJ090001', 'ADV09', 'ADJ09', 0001),
('ADV09ADJ090002', 'ADV09', 'ADJ09', 0002),
('ADV10ADJ100001', 'ADV10', 'ADJ10', 0001),
('ADV10ADJ100002', 'ADV10', 'ADJ10', 0002),
('ADV11ADJ110001', 'ADV11', 'ADJ11', 0001),
('ADV11ADJ110002', 'ADV11', 'ADJ11', 0002),
('ADV12ADJ120001', 'ADV12', 'ADJ12', 0001),
('ADV12ADJ120002', 'ADV12', 'ADJ12', 0002),
('ADV13ADJ130001', 'ADV13', 'ADJ13', 0001),
('ADV13ADJ130002', 'ADV13', 'ADJ13', 0002),
('ADV14ADJ140001', 'ADV14', 'ADJ14', 0001),
('ADV14ADJ140002', 'ADV14', 'ADJ14', 0002),
('ADV15ADJ150001', 'ADV15', 'ADJ15', 0001),
('ADV15ADJ150002', 'ADV15', 'ADJ15', 0002),
('ADV16ADJ160001', 'ADV16', 'ADJ16', 0001),
('ADV16ADJ160002', 'ADV16', 'ADJ16', 0002),
('ADV17ADJ170001', 'ADV17', 'ADJ17', 0001),
('ADV17ADJ170002', 'ADV17', 'ADJ17', 0002),
('ADV18ADJ180001', 'ADV18', 'ADJ18', 0001),
('ADV18ADJ180002', 'ADV18', 'ADJ18', 0002),
('ADV19ADJ190001', 'ADV19', 'ADJ19', 0001),
('ADV19ADJ190002', 'ADV19', 'ADJ19', 0002),
('ADV20ADJ200001', 'ADV20', 'ADJ20', 0001),
('ADV20ADJ200002', 'ADV20', 'ADJ20', 0002),
('ADV21ADJ210001', 'ADV21', 'ADJ21', 0001),
('ADV21ADJ210002', 'ADV21', 'ADJ21', 0002),
('ADV22ADJ220001', 'ADV22', 'ADJ22', 0001),
('ADV22ADJ220002', 'ADV22', 'ADJ22', 0002),
('ADV23ADJ230001', 'ADV23', 'ADJ23', 0001),
('ADV23ADJ230002', 'ADV23', 'ADJ23', 0002),
('ADV24ADJ240001', 'ADV24', 'ADJ24', 0001),
('ADV24ADJ240002', 'ADV24', 'ADJ24', 0002);