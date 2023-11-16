package com.gdsc2023.planyee.domain.tmap.domain;

import java.math.BigDecimal;
import java.util.List;
import com.gdsc2023.planyee.domain.tmap.common.GeometryDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class TmapRoutesResponse {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeatureCollection {
        private String type;
        private List<Feature> features;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Feature {
        private String type;
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
        private final BigDecimal latitude;
        private final BigDecimal longitude;

        @JsonCreator
        public Coordinate(@JsonProperty("0") BigDecimal longtitude, @JsonProperty("1") BigDecimal latitude) {
            this.longitude = longtitude;
            this.latitude = latitude;
        }
    }
}
