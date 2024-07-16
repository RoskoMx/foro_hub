create table topicos_table{
    id bigint not null auto_increment,
    nombre varchar(100) not null unique,
    mensaje varchar(500) not null,
    curso varchar(50) not null,
    titulo varchar(100) not null,

    primary key(id)
};