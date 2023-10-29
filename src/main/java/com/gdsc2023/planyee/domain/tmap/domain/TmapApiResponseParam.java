package com.gdsc2023.planyee.domain.tmap.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class TmapApiResponseParam {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeatureCollection {

        String type;
        private List<Feature> features;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Feature {

        String type;
        private Geometry geometry;


    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(using = GeometryDeserializer.class)
    public static class Geometry {
        private String type;
        private List<Coordinate> coordinates;
    }
    @Getter
    @ToString
    public static class Coordinate {

        @JsonCreator
        public Coordinate(@JsonProperty("0") BigDecimal latitude, @JsonProperty("1") BigDecimal longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        private final BigDecimal latitude;

        private final BigDecimal longitude;



    }



}
