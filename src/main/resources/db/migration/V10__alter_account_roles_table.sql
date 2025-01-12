alter table account_roles
ADD COLUMN role UUID
CONSTRAINT jdd8sdhdfv REFERENCES role(id)
