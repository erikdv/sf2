CREATE TABLE "account"
(
    id  UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username  VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL

);
