CREATE TABLE message
(
    uuid            UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title           TEXT NOT NULL,
    content         TEXT NOT NULL
);
