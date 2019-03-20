create database product_manager collate SQL_Latin1_General_CP1_CI_AS
go

create table product_manager.customers
(
  customer_id           int identity
    constraint customers_pk
      primary key nonclustered,
  customer_name         nvarchar(100) not null,
  gender                varchar(1)    not null,
  email_address         nvarchar(100) not null,
  phone_number          nvarchar(10)  not null,
  address_line          nvarchar(200) not null,
  town_city             nvarchar(100) not null,
  state_county_province nvarchar(100) not null,
  country               nvarchar(50)  not null
)
go

create table product_manager.employees
(
  employee_id   int identity
    constraint employees_pk
      primary key nonclustered,
  employee_name nvarchar(100) not null,
  phone_number  nvarchar(10)  not null,
  email_address nvarchar(50)  not null,
  gender        nvarchar(1)   not null
)
go

create table product_manager.invoices
(
  invoice_number int identity
    constraint invoices_pk
      primary key nonclustered,
  order_id       int  not null,
  employee_id    int  not null,
  customer_id    int  not null,
  invoice_date   date not null
)
go

create table product_manager.order_items
(
  order_item_id    int identity
    constraint order_items_pk
      primary key nonclustered,
  order_id         int   not null,
  product_id       int   not null,
  product_price    float not null,
  product_quantity int   not null
)
go

create table product_manager.orders
(
  order_id      int identity
    constraint orders_pk
      primary key nonclustered,
  customer_id   int           not null,
  employee_id   int           not null,
  order_date    date          not null,
  order_address nvarchar(200) not null
)
go

create table product_manager.price
(
  product_id    int identity
    constraint price_pk
      primary key nonclustered,
  vat           float not null,
  promotion     float,
  product_price float
)
go

create table product_manager.products
(
  product_id          int identity
    constraint products_pk
      primary key nonclustered,
  product_name        nvarchar(200) not null,
  product_price       float         not null,
  product_color       nvarchar(50)  not null,
  product_size        float,
  product_description nvarchar(200) not null
)
go


