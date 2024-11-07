CREATE TABLE features
                                   (
                                       id         SERIAL PRIMARY KEY,
                                       year       INTEGER,
                                       annotation TEXT,
                                       color      VARCHAR(7),
                                       geometry   GEOMETRY(MultiPolygon, 4326),
                                       map_id     INTEGER,
                                       FOREIGN KEY (map_id) REFERENCES maps (id)
                                   );