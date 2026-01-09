INSERT INTO project_users (username, password, enabled, roles, image_path) VALUES ('user', 'password', true, 'ROLE_USER', null);
INSERT INTO project_users (username, password, enabled, roles, image_path) VALUES ('admin', 'password', true, 'ROLE_ADMIN', null);
INSERT INTO project_users (username, password, enabled, roles, image_path) VALUES ('member3', 'password', true, 'ROLE_USER', null);

-- サンプル課題提出データ
INSERT INTO project_submissions (user_id, project_name, score) VALUES
(1, 'Spring Boot課題', 85),
(2, 'Spring Boot課題', 75),
(3, 'Spring Boot課題', 95);
