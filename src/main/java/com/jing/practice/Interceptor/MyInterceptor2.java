package com.jing.practice.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:MyInterceptor2
 * @Description TODO
 * @author:RanMoAnRan
 * @Date:2019/5/23 18:14
 * @Version 1.0
 */
public class MyInterceptor2 implements HandlerInterceptor {
    /**
     * 在Handler方法执行之前执行，顺序执行
     * 返回值，返回true拦截器放行 false拦截器不通过，后续业务逻辑不再执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("MyInterceptor2，预处理回调方法正在执行");
        return true;
    }

    /**
     *在执行完Handler方法之后执行，倒序执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor2，后处理回调方法正在执行");
    }

    /**
     * 在视图渲染完成之后执行，倒序执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("MyInterceptor2，请求完成回调方法正在执行");
    }

}
