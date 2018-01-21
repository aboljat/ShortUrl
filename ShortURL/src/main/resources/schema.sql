DROP TABLE IF EXISTS app_user;
CREATE TABLE app_user (
    user_id BIGINT PRIMARY KEY auto_increment,
    user_name VARCHAR(128) UNIQUE,
    password VARCHAR(256),
);

DROP TABLE IF EXISTS registered_url;
CREATE TABLE registered_url (
    url_id BIGINT PRIMARY KEY auto_increment,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES PUBLIC.app_user(user_id),
    initial_url VARCHAR(256) UNIQUE,
    short_url VARCHAR(256) UNIQUE,
    redirect_type BIGINT,
    counter BIGINT NOT NULL
);

