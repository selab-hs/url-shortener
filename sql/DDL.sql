CREATE TABLE `member`
(
    `id`         bigint       NOT NULL AUTO_INCREMENT,
    `email`      varchar(256) NOT NULL COMMENT '아이디',
    `password`   varchar(512) NOT NULL COMMENT '비밀번호',
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 200000  DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
CREATE UNIQUE INDEX uidx__email ON member (email);

CREATE TABLE `short_url`
(
    `id`         bigint        NOT NULL AUTO_INCREMENT,
    `member_id`  bigint        NOT NULL,
    `origin_url` varchar(2048) NOT NULL,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE INDEX idx__member_id ON short_url (member_id);

CREATE TABLE `system_action_log`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `member_id`    bigint        DEFAULT NULL,
    `short_url_id` bigint        DEFAULT NULL,
    `host`         varchar(512)  DEFAULT NULL,
    `http_method`  varchar(128)  DEFAULT NULL,
    `ip_address`   varchar(256)  DEFAULT NULL,
    `path`         varchar(256)  DEFAULT NULL,
    `referer`      varchar(2048) DEFAULT NULL,
    `user_agent`   varchar(512)  DEFAULT NULL,
    `created_at`   datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
CREATE INDEX idx__member_id__short_url_id ON system_action_log (member_id, short_url_id);
