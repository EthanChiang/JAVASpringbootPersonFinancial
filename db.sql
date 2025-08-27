use firstdemoapp;

create table cash (transaction_id integer primary key, taiwanese_dollars integer, us_dollars real, note varchar(30), date_info date);

create table stock (transaction_id integer primary key, stock_id varchar(10), stock_num integer, stock_price real, processing_fee integer, tax integer, date_info date);

ALTER TABLE cash MODIFY transaction_id INT NOT NULL AUTO_INCREMENT;

ALTER TABLE stock MODIFY transaction_id INT NOT NULL AUTO_INCREMENT;
