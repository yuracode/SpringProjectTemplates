INSERT INTO project_users (username, password, enabled, roles) VALUES ('user', 'password', true, 'ROLE_USER');
INSERT INTO project_users (username, password, enabled, roles) VALUES ('admin', 'password', true, 'ROLE_ADMIN');
INSERT INTO project_users (username, password, enabled, roles) VALUES ('member3', 'password', true, 'ROLE_USER');

-- サンプル課題提出データ
INSERT INTO project_submissions (user_id, project_name, score) VALUES
(1, 'Spring Boot課題', 85),
(2, 'Spring Boot課題', 75),
(3, 'Spring Boot課題', 95);
