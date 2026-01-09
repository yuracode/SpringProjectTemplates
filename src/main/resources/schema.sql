CREATE TABLE IF NOT EXISTS project_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL,
    roles VARCHAR(200)
);

-- 画像ファイルテーブル
CREATE TABLE IF NOT EXISTS image_files (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    filename VARCHAR(255) NOT NULL,
    file_data BLOB NOT NULL,
    file_type VARCHAR(50),
    file_size BIGINT,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES project_users(id)
);

-- 課題提出テーブル
CREATE TABLE IF NOT EXISTS project_submissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    project_name VARCHAR(100) NOT NULL,
    score INT,
    FOREIGN KEY (user_id) REFERENCES project_users(id)
);
