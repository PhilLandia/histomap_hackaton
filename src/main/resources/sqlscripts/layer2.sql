create table layer2
(
    id    serial primary key,
    geom  geometry(MultiPolygon, 4326),
    note  varchar(255),
    year  integer,
    color varchar(8),
    map_id integer references map_names(id)
);

create index sidx_layer2_geom
    on layer1 using gist (geom);