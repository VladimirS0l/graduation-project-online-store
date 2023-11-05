create table if not exists  users
(
    id bigserial primary key,
    name varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    phone      varchar(50) unique,
    enabled  BOOLEAN NOT NULL DEFAULT false,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table if not exists users_roles (
    user_id bigint not null,
    role varchar(255) not null,
    primary key (user_id, role),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    constraint fk_users_roles_users foreign key (user_id) references users(id) on delete cascade
                                                                            on update no action
);

create table if not exists products
(
    id         bigserial primary key,
    title      varchar(255)  not null unique,
    price      numeric(9, 2) not null,
    description      varchar(500)  not null,
    pathname   varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table if not exists  orders
(
    id           bigserial primary key,
    username     varchar(255)  not null,
    full_name    varchar(200) not null,
    total_price  numeric(9, 2) not null,
    address      varchar(255),
    phone        varchar(50),
    order_status varchar(20)   not null default 'CREATED',
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table if not exists  order_items
(
    id                bigserial primary key,
    product_id        bigint        not null references products (id),
    order_id          bigint        not null references orders (id),
    quantity          int           not null,
    price_per_product numeric(9, 2) not null,
    price             numeric(9, 2) not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);