
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
genre_name varchar(30) NOT NULL
);

CREATE TABLE media_types(
media_type_id serial PRIMARY KEY,
name varchar(60) NOT NULL
);


CREATE TABLE media(
media_id serial PRIMARY KEY,
title varchar(60) NOT NULL,
creator varchar(60),
media_type integer REFERENCES media_types,
genre_id integer REFERENCES genres,
publication_date date
);

CREATE TABLE users(
user_id serial PRIMARY KEY,
username varchar(50) UNIQUE NOT NULL,
pass varchar(60) NOT NULL,
salt varchar(30)
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
review_date date DEFAULT current_date,
rating integer CHECK(rating >= 0), CHECK(rating <= 100),
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



--------------------------------------------------------------
-- Populating the database

INSERT INTO users(user_id, username, pass, salt) VALUES(DEFAULT, 'kyle', 'S/6dS5fZL0iCryr6kk+dlQiEhO0=', '+4leCIBVJ2g=');

INSERT INTO friendships VALUES
	(DEFAULT, 1, 1, 'test');
	
INSERT INTO genres(genre_id, genre_name) VALUES(DEFAULT, 'Science Fantasy');

INSERT INTO media_types(media_type_id, name) VALUES (DEFAULT, 'Book');

INSERT INTO media(media_id, title, creator, media_type, genre_id, publication_date) values
	(DEFAULT, 'The Shadow of the Torturer', 'Gene Wolfe', 1, 1, '1980-05-01'),
	(DEFAULT, 'The Claw of the Conciliator', 'Gene Wolfe', 1, 1, '1981-05-01'),
	(DEFAULT, 'The Sword of the Lictor', 'Gene Wolfe', 1, 1, '1982-05-01'),
	(DEFAULT, 'The Citadel of the Autarch', 'Gene Wolfe', 1, 1, '1983-05-01');

INSERT INTO user_tags VALUES
	(DEFAULT, 'Complex', 1, 1),
	(DEFAULT, 'Complex', 1, 2),
	(DEFAULT, 'Complex', 1, 3),
	(DEFAULT, 'Complex', 1, 4);

INSERT INTO friendships VALUES
	(DEFAULT, 1, 1, 'test');
	
INSERT INTO shelves(shelf_id, user_id, shelf_name) VALUES(default, 1, 'Favorite Books');	

INSERT INTO reviews VALUES
	--(DEFAULT, DEFAULT, 80, 1, 1), --removed for testing purposes
	(DEFAULT, DEFAULT, 80, 1, 2),
	(DEFAULT, DEFAULT, 85, 1, 3),
	(DEFAULT, DEFAULT, 90, 1, 4);

insert into shelf_assignments(shelf_assignment_id, shelf_assignment_date, user_id, shelf_id, media_id) values(default, null, 1, 1, 1);
insert into shelf_assignments(shelf_assignment_id, shelf_assignment_date, user_id, shelf_id, media_id) values(default, null, 1, 1, 2);
insert into shelf_assignments(shelf_assignment_id, shelf_assignment_date, user_id, shelf_id, media_id) values(default, null, 1, 1, 3);
insert into shelf_assignments(shelf_assignment_id, shelf_assignment_date, user_id, shelf_id, media_id) values(default, null, 1, 1, 4);
