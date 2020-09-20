CREATE USER 'ski11up'@'localhost' IDENTIFIED BY 'dbadmin';
GRANT ALL PRIVILEGES ON * . * TO 'ski11up'@'localhost';
ALTER USER 'ski11up'@'localhost' IDENTIFIED WITH mysql_native_password BY 'dbadmin';