CREATE TABLE refresh_token
(
    id              UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username        VARCHAR(32) NOT NULL,
    refresh_token   VARCHAR(512) NOT NULL
);
