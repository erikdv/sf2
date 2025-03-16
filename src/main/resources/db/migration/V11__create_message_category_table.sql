create table public.message_category
(
    message_uuid  uuid not null
        constraint fkon4i2tjpq62ah5b2io4q8qg7s
            references message,
    category_uuid uuid not null
        constraint fk5vd6asa2wnrhfglh9luusl2fx
            references category,
    primary key (message_uuid, category_uuid)
);

