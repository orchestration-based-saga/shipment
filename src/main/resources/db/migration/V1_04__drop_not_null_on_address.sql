-- temporarily
alter table shipment
    alter column recipient_address drop not null;
alter table shipment
    alter column sender_address drop not null;