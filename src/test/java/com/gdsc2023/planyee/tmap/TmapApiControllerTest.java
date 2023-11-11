package com.gdsc2023.planyee.tmap;


import com.gdsc2023.planyee.domain.tmap.controller.routesApiController;
import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.dto.MileStone;
import com.gdsc2023.planyee.domain.tmap.dto.UserRouteDto;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TmapApiControllerTest {

    @Autowired
    routesApiController routesApiController;


    @Test
    @DisplayName("컨트롤러 작동 테스트")
    void apiControllerTest() {
        ApiRequestParam requestParam = ApiRequestParam.builder()
            .endX(new BigDecimal("126.9235355222221"))
            .endY(new BigDecimal("37.460122514035135"))
            .startX(new BigDecimal("126.88462351538156"))
            .startY(new BigDecimal("37.47955275185523"))
            .build();

        UserRouteDto userRouteDto = routesApiController.calculateMilestones(requestParam);

        System.out.println(userRouteDto);
    }



}
