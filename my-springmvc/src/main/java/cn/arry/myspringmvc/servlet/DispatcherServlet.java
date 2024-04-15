package cn.arry.myspringmvc.servlet;

import cn.arry.myspringmvc.context.WebApplicationContext;

/**
 * @author arry
 * @description
 * @since 1.0
 */
public class DispatcherServlet extends BaseHttpServlet{

    public DispatcherServlet(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    //组件初始化
    @Override
    protected void onRefresh() {

    }
}
