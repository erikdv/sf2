CREATE TABLE comment (
    uuid UUID PRIMARY KEY,
    message_id VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX comment_message_id_idx ON comment(message_id);
