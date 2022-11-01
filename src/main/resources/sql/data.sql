CREATE TABLE `user` (
                        `user_idx`	int	NOT NULL	COMMENT 'Auto Increment',
                        `email`	varchar(45)	NOT NULL	COMMENT '이메일 인증을 위한 필드',
                        `password`	varchar(50)	NOT NULL,
                        `number`	varchar(10)	NULL	COMMENT '홈베이스 예약 학번',
                        `name`	varchar(20)	NULL	COMMENT '홈베이스 예약자 명단에 넣을 이름',
                        `authorization`	archer(5)	NOT NULL	COMMENT '선생님 어드민 학생 권한 나누기'
);

CREATE TABLE `reservation` (
                               `homebase_idx`	VARCHAR(255)	NOT NULL	COMMENT '홈베 좌석번호',
                               `user_idx`	int	NOT NULL	COMMENT 'Auto Increment'
);

CREATE TABLE `homebase` (
                            `homebase_idx`	VARCHAR(255)	NOT NULL	COMMENT '홈베 좌석번호',
                            `floor`	INT(1)	NOT NULL	COMMENT '신청한 홈베의 층'
);

CREATE TABLE `personnal` (
                             `user_idx`	int	NOT NULL	COMMENT 'Auto Increment',
                             `homebase_idx`	VARCHAR(255)	NOT NULL,
                             `user_idx2`	int	NOT NULL	COMMENT 'Auto Increment'
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
                                                         `user_idx`
    );

ALTER TABLE `reservation` ADD CONSTRAINT `PK_RESERVATION` PRIMARY KEY (
                                                                       `homebase_idx`,
                                                                       `user_idx`
    );

ALTER TABLE `homebase` ADD CONSTRAINT `PK_HOMEBASE` PRIMARY KEY (
                                                                 `homebase_idx`
    );

ALTER TABLE `personnal` ADD CONSTRAINT `PK_PERSONNAL` PRIMARY KEY (
                                                                   `user_idx`,
                                                                   `homebase_idx`,
                                                                   `user_idx2`
    );

ALTER TABLE `reservation` ADD CONSTRAINT `FK_homebase_TO_reservation_1` FOREIGN KEY (
                                                                                     `homebase_idx`
    )
    REFERENCES `homebase` (
                           `homebase_idx`
        );

ALTER TABLE `reservation` ADD CONSTRAINT `FK_user_TO_reservation_1` FOREIGN KEY (
                                                                                 `user_idx`
    )
    REFERENCES `user` (
                       `user_idx`
        );

ALTER TABLE `personnal` ADD CONSTRAINT `FK_user_TO_personnal_1` FOREIGN KEY (
                                                                             `user_idx`
    )
    REFERENCES `user` (
                       `user_idx`
        );

ALTER TABLE `personnal` ADD CONSTRAINT `FK_reservation_TO_personnal_1` FOREIGN KEY (
                                                                                    `homebase_idx`
    )
    REFERENCES `reservation` (
                              `homebase_idx`
        );

ALTER TABLE `personnal` ADD CONSTRAINT `FK_reservation_TO_personnal_2` FOREIGN KEY (
                                                                                    `user_idx2`
    )
    REFERENCES `reservation` (
                              `user_idx`
        );

