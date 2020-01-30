create table review
(
    id          int(11) auto_increment primary key,
    review      text            not null,
    product_id  int(11)         not null,
    time        date 		null
);

-- auto-generated definition
create table product
(
    id      int(11) auto_increment primary key,
    name    varchar(255) not null,
    price   int(11) not null,
    image_url text not null
);

-- auto-generated definition
create table comment
(
    id           int(11) auto_increment primary key,
    comment      varchar(10000)                      null,
    review_id    int                                 not null,
    time         date     			     null
);
