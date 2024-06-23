create table account_roles
(
    roles smallint
        constraint account_roles_roles_check
            check ((roles >= 0) AND (roles <= 1)),
    account_id uuid not null
        constraint fktp61eta5i06bug3w1qr6286uf
            references account
);


