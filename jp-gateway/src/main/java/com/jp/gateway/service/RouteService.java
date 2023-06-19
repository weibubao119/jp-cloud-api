package com.jp.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * 路由配置服务
 * @author : xh
 * @date : Created in 2022/7/20 11:07
 */
public interface RouteService {
    /**
     * 更新路由配置
     *
     * @param routeDefinition
     */
    void update(RouteDefinition routeDefinition);

    /**
     * 添加路由配置
     *
     * @param routeDefinition
     */
    void add(RouteDefinition routeDefinition);
}
