create table products (
Product_ID int NOT NULL auto_increment PRIMARY KEY,
Product_Name varchar(255) NOT null,
Product_Cost decimal,
Product_Desc varchar(255));

insert into products values (default, "Mask", 10, "Mask");
insert into products values (default, "Sanitizer 50 ml", 50, "Sanitizer 50 ml");
insert into products values (default, "Sanitizer 100 ml", 80, "Sanitizer 100 ml");
insert into products values (default, "Sanitizer 250 ml", 200, "Sanitizer 250 ml");

create table user (
User_ID int NOT NULL auto_increment PRIMARY KEY,
Person_Name varchar(255) NOT null,
Person_Contact int,
Person_Email varchar(255));

create table KitDetail (
KitDetail_ID int NOT NULL auto_increment PRIMARY KEY,
Product_ID int NOT null,
CoronaKit_ID int,
Quantity int,
Amount float,
FOREIGN KEY (Product_ID) REFERENCES products(Product_ID),
FOREIGN KEY (CoronaKit_ID) REFERENCES CoronaKit(CoronaKit_ID));

create table CoronaKit (
CoronaKit_ID int NOT NULL PRIMARY KEY auto_increment,
Person_Name varchar(255),
Person_Contact varchar(255),
Person_Email varchar(255),
Delivery_Address varchar(255),
Amount float,
Order_Date varchar(50),
Order_Status varchar(255)
) auto_increment=1000;



