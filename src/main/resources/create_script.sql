SET MODE MYSQL;

CREATE TABLE USER (
  id           BIGINT NOT NULL AUTO_INCREMENT,
  firstName    VARCHAR(30),
  lastName     VARCHAR(30),
  email        VARCHAR(50) UNIQUE,
  birthDay     TIMESTAMP,
  PRIMARY KEY (`id`)
);


CREATE TABLE AUDITORIUM (
 id            BIGINT NOT NULL AUTO_INCREMENT,
 name          VARCHAR(30) UNIQUE,
 numberOfSeats BIGINT,
 vipSeats      ARRAY,
 PRIMARY KEY (`id`)
);

CREATE TABLE EVENT (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  name        VARCHAR(30) UNIQUE,
  basePrice   DOUBLE,
  rating      ENUM ('LOW', 'MID', 'HIGH'),
  airDates    ARRAY
);

CREATE TABLE EVENT_AUDITORIUM (
  id              BIGINT NOT NULL AUTO_INCREMENT,
  event_name        VARCHAR(30),
  auditorium_name   VARCHAR(30),
  event_airdate   TIMESTAMP
);
