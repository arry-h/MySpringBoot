package cn.arry.myspringboot;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public interface WebServer {
    /**
     * 启动服务器
     */
    void start(WebApplicationContext webApplicationContext);
}
