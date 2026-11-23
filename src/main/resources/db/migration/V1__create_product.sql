create table product(
    id BIGINT GENERATED ALWAYS As IDENTITY primary key,
    name varchar Not Null,
    description TEXT,
    price decimal(8,3)
)