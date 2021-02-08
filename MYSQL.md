use vintage;

CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(15) NULL,
  price int not null,
  qty int not null,
  PRIMARY KEY (id));

select *from product;







CREATE TABLE shoppingcar (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(15) NULL,
  price int not null,
  qty int not null,
  PRIMARY KEY (id));  
/*
insert into shoppingcar(name, price, qty)
			value('Apple', 55, 5);
*/
select *from shoppingcar;
