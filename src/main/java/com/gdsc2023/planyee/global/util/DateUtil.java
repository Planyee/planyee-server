package com.gdsc2023.planyee.global.util;

import java.time.Year;

public class DateUtil {

    public static Integer getCurrentYear() {
        return Year.now().getValue();
    }

    public static Integer getAgeByBirthyear(Integer birthyear) {
        return getCurrentYear() - birthyear + 1;
    }
}
