package com.gdsc2023.planyee.domain.tmap.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Geometry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    private final String POINT = "Point";
    private final String LINESTRING = "LineString";


    @Override
    public Geometry deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        String spatialDataType = node.get("type").asText();
        JsonNode coordinatesNode = node.get("coordinates");

        List<Coordinate> coordinates = new ArrayList<>();
        if (spatialDataType.equals(POINT)) {
            coordinates.add(new Coordinate(coordinatesNode.get(0).decimalValue(),
                    coordinatesNode.get(1).decimalValue()));
        }

        if (spatialDataType.equals(LINESTRING)) {

            for (JsonNode coordinate : coordinatesNode) {
                coordinates.add(new Coordinate(coordinate.get(0).decimalValue(),
                        coordinate.get(1).decimalValue()));
            }
        }

        return new Geometry(spatialDataType, coordinates);
    }
}