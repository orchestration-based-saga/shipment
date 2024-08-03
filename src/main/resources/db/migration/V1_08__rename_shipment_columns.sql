alter table shipment drop sender_address;
alter table shipment drop recipient_address;

alter table shipment add column sender_id uuid;
alter table shipment add column recipient_id uuid;