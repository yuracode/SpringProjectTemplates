CREATE TABLE IF NOT EXISTS project_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL,
    roles VARCHAR(200)
);

-- 課題提出テーブル
CREATE TABLE IF NOT EXISTS project_submissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    project_name VARCHAR(100) NOT NULL,
    score INT,
    FOREIGN KEY (user_id) REFERENCES project_users(id)
);
