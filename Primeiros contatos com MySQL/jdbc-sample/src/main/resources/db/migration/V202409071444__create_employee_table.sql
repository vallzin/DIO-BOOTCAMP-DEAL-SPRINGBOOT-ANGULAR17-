CREATE TABLE employees(
    id BIGINT not null auto_increment,
    name VARCHAR(150) NOT NULL,
    salary decimal(10,2) NOT NULL,
    birthday TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;