drop database cartdb;
create database cartdb;
use cartdb;
create table tblmycart
(
  id integer primary key AUTO_INCREMENT,
  customerId integer,
  transactionId varchar(64)
);

create table tblcartitem
(
  id integer primary key AUTO_INCREMENT,
  cartId integer references tblmycart(id),
  productId varchar(64),
  quantity integer
);
