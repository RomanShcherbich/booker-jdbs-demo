DROP DATABASE restful_booker;

CREATE DATABASE `restful_booker` /*!40100 DEFAULT CHARACTER
    SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE restful_booker.booking_dates
(
    id       BIGINT auto_increment NOT NULL,
    checkin  DATE                  NOT NULL,
    checkout DATE                  NOT NULL,
    CONSTRAINT booking_dates_pk PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- restful_booker.booking definition

CREATE TABLE `booking`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT,
    `firstname`       varchar(100) NOT NULL,
    `lastname`        varchar(100) NOT NULL,
    `total_price`     bigint       NOT NULL,
    `deposit_paid`    tinyint(1)   NOT NULL,
    `bookingdates_id` bigint       NOT NULL,
    PRIMARY KEY (`id`),
    KEY `booking_FK` (`bookingdates_id`),
    CONSTRAINT `booking_FK` FOREIGN KEY (`bookingdates_id`) REFERENCES `booking_dates` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

