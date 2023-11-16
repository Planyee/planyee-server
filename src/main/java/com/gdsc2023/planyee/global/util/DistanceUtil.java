package com.gdsc2023.planyee.global.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DistanceUtil {
    private static final BigDecimal EARTH_RADIUS = new BigDecimal("6371000"); // 지구의 반지름 (미터 단위)
    private static final int SCALE = 5; // BigDecimal 연산의 정밀도;

    public static BigDecimal calculateDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        BigDecimal latDistance = toRad(lat2.subtract(lat1));
        BigDecimal lonDistance = toRad(lon2.subtract(lon1));
        BigDecimal sinLat = sin(latDistance.divide(BigDecimal.valueOf(2)));
        BigDecimal sinLon = sin(lonDistance.divide(BigDecimal.valueOf(2)));
        BigDecimal a = sinLat.multiply(sinLat).add(cos(lat1).multiply(cos(lat2)).multiply(sinLon).multiply(sinLon));
        BigDecimal c = BigDecimal.valueOf(2).multiply(atan2(sqrt(a), sqrt(BigDecimal.ONE.subtract(a))));
        return EARTH_RADIUS.multiply(c).setScale(SCALE, RoundingMode.HALF_UP);
    }

    private static BigDecimal toRad(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(Math.PI / 180));
    }

    private static BigDecimal sin(BigDecimal value) {
        return BigDecimal.valueOf(Math.sin(value.doubleValue()));
    }

    private static BigDecimal cos(BigDecimal value) {
        return BigDecimal.valueOf(Math.cos(value.doubleValue()));
    }

    private static BigDecimal sqrt(BigDecimal value) {
        return BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
    }

    private static BigDecimal atan2(BigDecimal y, BigDecimal x) {
        return BigDecimal.valueOf(Math.atan2(y.doubleValue(), x.doubleValue()));
    }

}
