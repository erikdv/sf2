CREATE TABLE category
(
    uuid           UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    slug           VARCHAR(32) UNIQUE,
    title          TEXT NOT NULL,
    order_         INTEGER UNIQUE

);
