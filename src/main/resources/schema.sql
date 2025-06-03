-- 各種テーブル削除
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tasks;


-- users（ユーザー）
CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(20),
password VARCHAR(20)
);

-- tasks（一覧）
CREATE TABLE tasks (
id SERIAL PRIMARY KEY,
date DATE,
content VARCHAR(255),
ganbari VARCHAR(30),
memo VARCHAR(300)
);
