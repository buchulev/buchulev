

CREATE TABLE game
(
  name char(128) NOT NULL UNIQUE,
  id int NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE website_user
(
  nickname char(128) NOT NULL,
  firstname char(128),
  password char(128) NOT NULL,
  phonenumber char(128),
  email char(128) NOT NULL UNIQUE,
  salt char(128),
  isadmin boolean,
  PRIMARY KEY (nickname)
);


CREATE TABLE review
(
  id int NOT NULL,
  title char(128) NOT NULL,
  Text text,
  game int,
  user char(128),
  PRIMARY KEY (id),
   FOREIGN KEY (user) REFERENCES website_user(nickname)  ,
  FOREIGN KEY (game) REFERENCES game (id) 
);

CREATE TABLE news
(
  id int NOT NULL,
  topic char(128) NOT NULL,
  article text NOT NULL,
  news_date date,
  user_name char(128),
  PRIMARY KEY (id),
    FOREIGN KEY(user_name) REFERENCES website_user(nickname)
);
