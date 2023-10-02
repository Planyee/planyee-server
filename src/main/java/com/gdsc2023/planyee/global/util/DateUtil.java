package com.gdsc2023.planyee.global.util;

import java.time.Year;

public class DateUtil {

    public static Integer getCurrentYear() {
        return Year.now().getValue();
    }
}
