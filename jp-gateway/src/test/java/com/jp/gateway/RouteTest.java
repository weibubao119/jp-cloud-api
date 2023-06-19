package com.jp.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.gateway.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @author : xh
 * @date : Created in 2022/7/21 13:08
 */
@SpringBootTest
public class RouteTest {

    @Autowired
    private RouteService routeService;

    @Test
    public void ruteTest() throws JsonProcessingException {
        String initConfigInfo = "[{\"predicates\":[{\"args\":{\"pattern\":\"/mdx-shop-order/**\"},\"name\":\"Path\"}],\"id\":\"mdx-shop-order\",\"filters\":[{\"args\":{\"parts\":1},\"name\":\"StripPrefix\"}],\"uri\":\"lb://mdx-shop-order\",\"order\":1}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<RouteDefinition> routeDefinitions = objectMapper.readValue(initConfigInfo, new TypeReference<List<RouteDefinition>>() {
        });
        for (RouteDefinition definition : routeDefinitions) {
            routeService.add(definition);
        }
    }
}
