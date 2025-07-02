-- member 테이블 생성 SQL (MariaDB용)
CREATE TABLE member (
    id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    pw VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    school_name VARCHAR(200),
    role ENUM('TEACHER', 'ADMIN', 'GUEST') DEFAULT 'TEACHER',
    is_email_verified BOOLEAN DEFAULT FALSE,
    is_secondary_verified BOOLEAN DEFAULT FALSE,
    last_verified_at DATETIME,
    next_verify_due DATETIME,
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 인덱스 추가
CREATE INDEX idx_member_email ON member(email);
CREATE INDEX idx_member_role ON member(role);