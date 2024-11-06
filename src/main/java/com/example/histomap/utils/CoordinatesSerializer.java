package com.example.histomap.utils;

import com.example.histomap.entity.CoordinatesEntity;
import com.example.histomap.entity.PointCoordinates;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CoordinatesSerializer extends StdSerializer<CoordinatesEntity> {

    public CoordinatesSerializer() {
        this(null);
    }

    public CoordinatesSerializer(Class<CoordinatesEntity> t) {
        super(t);
    }

    @Override
    public void serialize(
            CoordinatesEntity entity, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        if ( entity instanceof PointCoordinates) {
            jgen.writeArray( ((PointCoordinates) entity).getCoords(), 0, 2 );
        }

    }
}