CREATE TABLE log (
    id BIGSERIAL PRIMARY KEY,
    page VARCHAR(255),
    type VARCHAR(255),
    level VARCHAR(255),
    time TEXT,
    phase TEXT,
    message TEXT
);

CREATE INDEX idx_log_page ON log(page);