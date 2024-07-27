create table if not exists shipment
(
    id                serial  not null primary key,
    status            text    not null,
    order_id          text    not null,
    recipient_address integer not null,
    sender_address    integer not null
)