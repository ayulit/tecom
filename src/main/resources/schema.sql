CREATE TABLE IF NOT EXISTS car
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand             VARCHAR(255) NULL,
    model             VARCHAR(255) NULL,
    prod_year         SMALLINT(6)  NULL,
    prod_month        SMALLINT(255) NULL,
    engine            SMALLINT(6)  NULL,
    turbo             bit          NULL,
    power             SMALLINT(6)  NULL,
    transmission_type varchar(255) NULL,
    drive_type        VARCHAR(255) NULL,
    body_type         VARCHAR(255) NULL,
    color             VARCHAR(255) NULL,
    ext_id            VARCHAR(255) NULL
);
