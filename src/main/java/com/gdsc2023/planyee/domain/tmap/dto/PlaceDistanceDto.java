package com.gdsc2023.planyee.domain.tmap.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PlaceDistanceDto {

        public long id;
        public BigDecimal placeLongitude;
        public BigDecimal placeLatitude;
        public BigDecimal distance;

        public PlaceDistanceDto(long id, BigDecimal placeLatitude, BigDecimal placeLongitude,  BigDecimal distance) {
            this.id = id;
            this.placeLatitude = placeLatitude;
            this.placeLongitude = placeLongitude;
            this.distance = distance;
        }


}
