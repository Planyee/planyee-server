package com.gdsc2023.planyee.domain.tmap.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.gdsc2023.planyee.domain.tmap.domain.TmapApiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.domain.TmapApiResponseParam.Geometry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    @Override
    public Geometry deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        String type = node.get("type").asText();
        JsonNode coordinatesNode = node.get("coordinates");

        List<Coordinate> coordinates = new ArrayList<>();
        if ("Point".equals(type)) {
            coordinates.add(new Coordinate(coordinatesNode.get(0).decimalValue(),
                    coordinatesNode.get(1).decimalValue()));
        } else if ("LineString".equals(type)) {
            for (JsonNode coordNode : coordinatesNode) {
                coordinates.add(new Coordinate(coordNode.get(0).decimalValue(),
                        coordNode.get(1).decimalValue()));
            }
        }

        return new Geometry(type, coordinates);
    }
}