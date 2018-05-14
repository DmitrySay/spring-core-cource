SET MODE MYSQL;

CREATE TABLE IF NOT EXISTS USER (
  id           BIGINT NOT NULL AUTO_INCREMENT,
  firstName    VARCHAR(30),
  lastName     VARCHAR(30),
  email        VARCHAR(50) /*UNIQUE*/,
  birthDay     TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS EVENT (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  name        VARCHAR(30) /*UNIQUE*/,
  basePrice   DOUBLE,
  rating      ENUM ('LOW', 'MID', 'HIGH'),
  airDates    ARRAY,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS AUDITORIUM (
 id            BIGINT NOT NULL AUTO_INCREMENT,
 name          VARCHAR(30) /*UNIQUE*/,
 numberOfSeats BIGINT,
 vipSeats      ARRAY,
 PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS EVENT_AUDITORIUM (
  id                BIGINT NOT NULL AUTO_INCREMENT,
  event_name        VARCHAR(30),
  auditorium_name   VARCHAR(30),
  event_airdate     TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (event_name) REFERENCES  event (name),
  FOREIGN KEY (auditorium_name) REFERENCES  AUDITORIUM (name)
);

CREATE TABLE IF NOT EXISTS TICKET (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  user_id     BIGINT,
  event_id    BIGINT,
  dateTime    TIMESTAMP,
  seat        BIGINT ,
  FOREIGN KEY (user_id) REFERENCES  user(id),
  FOREIGN KEY (event_id) REFERENCES  event(id)
);

CREATE TABLE IF NOT EXISTS eventsByNameStorage (
id          BIGINT NOT NULL AUTO_INCREMENT,
event_name        VARCHAR(30),
counter     BIGINT
);

