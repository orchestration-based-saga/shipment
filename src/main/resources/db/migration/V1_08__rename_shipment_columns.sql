alter table shipment drop column if exists sender_address;
alter table shipment drop column if exists recipient_address;

alter table shipment add column if not exists sender_id uuid;
alter table shipment add column if not exists recipient_id uuid;