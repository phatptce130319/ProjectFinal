create database product_manager collate SQL_Latin1_General_CP1_CI_AS
go

create table product_manager.customers
(
    customer_id           int identity
        constraint customers_pk
            primary key nonclustered,
    customer_name         nvarchar(100) not null,
    gender                nvarchar(10)  not null,
    email_address         nvarchar(100) not null,
    phone_number          nvarchar(10)  not null,
    address_line          nvarchar(100),
    town_city             nvarchar(100) not null,
    state_county_province nvarchar(100),
    country               nvarchar(20)  not null
)
go

create table product_manager.employees
(
    employee_id   int identity
        constraint employees_pk
            primary key nonclustered,
    employee_name nvarchar(100) not null,
    phone_number  nvarchar(10)  not null,
    email_address nvarchar(100) not null,
    gender        nvarchar(10)  not null
)
go

create table product_manager.order_items
(
    order_item_id    int identity
        constraint order_items_pk
            primary key nonclustered,
    order_id         int   not null,
    product_id       int   not null,
    product_quantity int   not null,
    product_price    float not null
)
go

create table product_manager.orders
(
    order_id      int identity
        constraint orders_pk
            primary key nonclustered,
    customer_id   int  not null
        constraint orders_customer_id___fk
            references product_manager.customers,
    employee_id   int  not null
        constraint orders_employee_id__fk
            references product_manager.employees,
    order_date    date not null,
    order_address nvarchar(100)
)
go

create table product_manager.invoices
(
    invoice_number int identity
        constraint invoices_pk
            primary key nonclustered,
    order_id       int not null
        constraint invoices_orders__fk
            references product_manager.orders,
    employee_id    int
        constraint invoices_employees__fk
            references product_manager.employees,
    customer_id    int
        constraint invoices_customers__fk
            references product_manager.customers,
    invoice_date   date
)
go

create table product_manager.products
(
    product_id          int identity
        constraint products_pk
            primary key nonclustered,
    product_name        nvarchar(100) not null,
    product_price       nvarchar(100) not null,
    product_color       nvarchar(50)  not null,
    product_size        float         not null,
    product_description nvarchar(500) not null
)
go

create table product_manager.prices
(
    product_id    int   not null
        constraint prices_products_product_id_fk
            references product_manager.products,
    product_price float,
    vat_value     float,
    promotion     float not null
)
go


