create table if not exists claim
(
    id            integer not null primary key,
    refund_amount numeric(15, 2),
    status        text    not null
);

alter table shipment
    add column claim_id integer;
alter table shipment
    add constraint fk_claim foreign key (claim_id) references claim;