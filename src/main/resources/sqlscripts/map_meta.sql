create table map_meta
(
id   SERIAL PRIMARY KEY,
start INTEGER,
"end" INTEGER,
step INTEGER,
map_id INTEGER REFERENCES map_names(id)
)