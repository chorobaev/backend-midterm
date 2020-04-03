use root;
create database Customers;
create user 'midterm'@'%' identified by 'password';
grant all on Customers.* to 'midterm'@'%';

create table customers
(
    id           int          not null auto_increment,
    firstName    nvarchar(50) not null,
    lastName     nvarchar(50) not null,
    emailAddress nvarchar(50) not null unique,
    jobTitle     nvarchar(50),
    mobilePhone  nvarchar(50),
    city         nvarchar(50),
    webPage      nvarchar(50)
);