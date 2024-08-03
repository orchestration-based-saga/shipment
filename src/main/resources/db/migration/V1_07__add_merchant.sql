create table if not exists merchant_address
(
    id            serial not null primary key,
    user_id       uuid   not null,
    type          text   not null,
    city          text   not null,
    zip           text   not null,
    street_name   text   not null,
    street_number text   not null
);

create table if not exists merchant
(
    id             serial                                             not null primary key,
    tin            text                                               not null,
    name           text                                               not null,
    pickup_address integer
        constraint fk_pickup_address references merchant_address (id) not null,
    return_address integer
        constraint fk_return_address references merchant_address (id) not null
);