alter table shipment add column merchant_inventory_id integer;
alter table shipment add column item_id integer;

alter table shipment alter column merchant_inventory_id set not null;
alter table shipment alter column item_id set not null;