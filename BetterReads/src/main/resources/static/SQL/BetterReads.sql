
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS media_types CASCADE;
DROP TABLE IF EXISTS media CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS shelves CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS recommendations CASCADE;
DROP TABLE IF EXISTS friendships CASCADE;
DROP TABLE IF EXISTS user_media_comments CASCADE;
DROP TABLE IF EXISTS user_review_comments CASCADE;
DROP TABLE IF EXISTS user_tags CASCADE;
DROP TABLE IF EXISTS shelf_assignments CASCADE;

--DDL
---------------------------------------------
CREATE TABLE genres(
genre_id serial PRIMARY KEY,
genre_name varchar(30)
);

CREATE TABLE media_types(
media_type_id serial PRIMARY KEY,
name varchar(60) NOT NULL,
genre_id integer REFERENCES genres
);


CREATE TABLE media(
media_id serial PRIMARY KEY,
title varchar(60) NOT NULL,
creator varchar(60),
media_type integer REFERENCES media_types
);

CREATE TABLE users(
user_id serial PRIMARY KEY,
username varchar(50) UNIQUE NOT NULL,
pass varchar(60) NOT NULL
);

CREATE TABLE shelves(
shelf_id serial PRIMARY KEY,
user_id integer REFERENCES users NOT NULL,
shelf_name varchar(100) NOT NULL
);

-- JOIN TABLES
----------------------------------------------------
CREATE TABLE reviews(
review_id serial PRIMARY KEY,
review_date date default current_date,
rating numeric CHECK(rating >= 0 AND rating <= 10),
user_id integer REFERENCES users,
media_id integer REFERENCES media NOT NULL,
UNIQUE(user_id, media_id)
);


CREATE TABLE messages(
message_id serial PRIMARY KEY,
message_date date DEFAULT current_date,
parent_message_id integer REFERENCES messages DEFAULT NULL,
sender_id integer REFERENCES users,
recipient_id integer REFERENCES users,
message varchar(1000) NOT NULL
);


--CREATE TABLE recommendations(
--recommendation_id serial PRIMARY KEY,
--sender_id integer REFERENCES users,
--recipient_id integer REFERENCES users,
--media_id integer REFERENCES media,
--message varchar(1000) --optional personal message
--);


--NOTE: two entries per relationship for easier querying
--one with user_id = user1.ID, friend_id = user2.ID
--another with user_id = user2.ID, friend_id = user1.ID
CREATE TABLE friendships(
friendship_id serial PRIMARY KEY,
user_id integer REFERENCES users,
friend_id integer REFERENCES users,
message varchar(1000)
);


CREATE TABLE user_media_comments(
comment_id serial PRIMARY KEY,
comment_date date DEFAULT current_date,
media_id integer REFERENCES media,
parent_comment_id integer REFERENCES user_media_comments DEFAULT NULL,
user_id integer REFERENCES users,
message varchar(1000) NOT NULL
);

CREATE TABLE user_review_comments(
comment_id serial PRIMARY KEY,
comment_date date DEFAULT current_date,
review_id integer REFERENCES reviews DEFAULT NULL,
parent_comment_id integer REFERENCES user_review_comments DEFAULT NULL,
user_id integer REFERENCES users,
message varchar(1000) NOT NULL
);

CREATE TABLE user_tags(
tag_id serial PRIMARY KEY,
tag_name varchar(30) NOT NULL,
user_id integer REFERENCES users NOT NULL,
media_id integer REFERENCES media NOT NULL
);

CREATE TABLE shelf_assignments(
shelf_assignment_id serial PRIMARY KEY,
shelf_assignment_date date DEFAULT current_date,
user_id integer REFERENCES users NOT NULL,
shelf_id integer REFERENCES shelves NOT NULL,
media_id integer REFERENCES media NOT NULL
);


