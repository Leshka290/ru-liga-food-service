create table images
(
    image_id       bigint generated by default as identity
        primary key,
    file_extension varchar(255) not null,
    file_path      varchar(255) not null,
    file_size      bigint       not null,
    media_type     varchar(255) not null
);

alter table images
    owner to postgres;

create table address
(
    address_id bigint generated by default as identity
        primary key,
    building   varchar(255) not null,
    city       varchar(255) not null,
    street     varchar(255) not null
);

alter table address
    owner to postgres;

create table couriers
(
    courier_id  bigint generated by default as identity
        primary key,
    coordinates integer     not null,
    name       varchar(20) not null,
    phone       varchar(20) not null,
    status      varchar(255)
);

alter table couriers
    owner to postgres;

create table customers
(
    customer_id bigint generated by default as identity
        primary key,
    distance    integer      not null,
    name        varchar(255) not null,
    address_id  bigint
);

alter table customers
    owner to postgres;

create table payments
(
    payment_id  bigint generated by default as identity
        primary key,
    amount      integer      not null,
    card_number varchar(255) not null
);

alter table payments
    owner to postgres;

create table restaurants
(
    restaurant_id bigint generated by default as identity
        primary key,
    distance      integer      not null,
    name          varchar(255) not null,
    address_id    bigint
        constraint address_id_restaurants
            references address,
    payment_id    bigint
        constraint payment_id_restaurants
            references payments
);

alter table restaurants
    owner to postgres;


create table restaurant_menu_items
(
    restaurant_menu_item_id bigint generated by default as identity
        primary key,
    description             varchar(255) not null,
    name                    varchar(255) not null,
    price                   integer      not null,
    image_id                bigint
        constraint image_id_restaurant_menu_items
            references images,
    restaurant_id           bigint
        constraint restaurant_id_restaurant_menu_items
            references restaurants
);

alter table restaurant_menu_items
    owner to postgres;

create table orders
(
    order_id      bigint generated by default as identity
        primary key,
    status        varchar(255),
    timestamp     timestamp,
    courier_id    bigint
        constraint courier_id_orders
            references couriers,
    customer_id   bigint
        constraint customer_id_orders
            references customers,
    restaurant_id bigint
);

alter table orders
    owner to postgres;

create table items
(
    item_id                 bigint generated by default as identity
        primary key,
    description             varchar(255) not null,
    name                    varchar(255) not null,
    price                   integer      not null,
    quantity                integer      not null,
    image_id                bigint,
    order_id                bigint
        constraint order_id_items
            references orders,
    restaurant_menu_item_id bigint
);

alter table items
    owner to postgres;

create table delivery
(
    delivery_id   bigint generated by default as identity
        primary key,
    status        varchar(255),
    customer_id   bigint
        constraint customer_id_delivery
            references customers,
    order_id      bigint
        constraint order_id_delivery
            references orders,
    payment_id    bigint,
    restaurant_id bigint
);

alter table delivery
    owner to postgres;