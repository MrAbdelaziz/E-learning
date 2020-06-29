CREATE TABLE user (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(128) NOT NULL UNIQUE,
  password VARCHAR(256) NOT NULL,
  nom VARCHAR(128) NOT NULL,
  prenom VARCHAR(128) NOT NULL,
  email VARCHAR(128) NOT NULL UNIQUE,
  date_inscription DATE NOT NULL,
  detail VARCHAR(1024),
  imgurl VARCHAR(1024) NOT NULL
);

CREATE TABLE auth_user_group (
  auth_user_group_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(128) NOT NULL,
  auth_group VARCHAR(128) NOT NULL,
  CONSTRAINT user_auth_user_group_fk FOREIGN KEY(username) REFERENCES user(username),
  UNIQUE (username, auth_group)
);

CREATE TABLE tuteur (
    tuteur_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(128) NOT NULL,
    prenom VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    description VARCHAR(256) NOT NULL,
    detail VARCHAR(1024),
    imgurl VARCHAR(1024) NOT NULL
);


CREATE TABLE formation (
  formation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titre VARCHAR(128) NOT NULL UNIQUE,
  description VARCHAR(256) NOT NULL,
  detail VARCHAR(1024) NOT NULL ,
  difficulte VARCHAR(128) NOT NULL,
  tuteur_id BIGINT NOT NULL,
  url VARCHAR(1024) NOT NULL ,
  imgurl VARCHAR(1024) NOT NULL ,
  CONSTRAINT formation_fk FOREIGN KEY(tuteur_id) REFERENCES tuteur(tuteur_id)
);

CREATE TABLE progression (
    progression_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    formation_id BIGINT NOT NULL,
    dateins DATE NOT NULL,
    CONSTRAINT progression_user_fk FOREIGN KEY(user_id) REFERENCES user(user_id),
    CONSTRAINT progression_formation_fk FOREIGN KEY(formation_id) REFERENCES formation(formation_id)
);

