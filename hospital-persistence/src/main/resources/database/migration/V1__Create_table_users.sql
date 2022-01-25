CREATE TABLE users (
user_id BIGINT NOT NULL AUTO_INCREMENT,
username VARCHAR(15) NOT NULL,
password VARCHAR(100) NOT NULL,
enabled TINYINT(1),
name VARCHAR(15) NOT NULL,
surname VARCHAR(15) NOT NULL,
email VARCHAR(50) NOT NULL,
phone CHAR(9) NULL DEFAULT NULL,
UNIQUE (username),
PRIMARY KEY (user_id)
);

