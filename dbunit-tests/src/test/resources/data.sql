-- Inserting first admin into users table
INSERT INTO users (username, password, enabled)
VALUES ('admin', '$2a$10$cyztG895P5ViBcTF7WM60eQ7TRreIvXdNc/WWIgBIQT563PhOyCGe',TRUE);

INSERT INTO contacts (first_name, last_name, email)
VALUES
    ('Pradeep', 'Jain','pd@pd-test.com'),
    ('Sandeep', 'Jain','sdj@sdj-test.com'),
    ('Yugdeep', 'Jain','ydj@ydj-test.com'),
    ('Jay', 'Testing','jay@testing-test.com');

-- Inserting initial roles for the first admin user
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN');
INSERT INTO roles (name)
VALUES ('ROLE_MGR');
INSERT INTO roles (name)
VALUES ('ROLE_USER');

-- Inserting the roles for the first admin user
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
