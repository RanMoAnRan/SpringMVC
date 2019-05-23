package com.jing.practice.exception;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:MyHandlerExceptionResolver
 * @Description TODO
 * @author:RanMoAnRan
 * @Date:2019/5/23 17:55
 * @Version 1.0
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        //判断是什么异常
        if (ex instanceof MaxUploadSizeExceededException) {
            //添加异常信息
            mv.addObject("msg", "上传文件大小不得超过5M");
            mv.setViewName("hello");
        }
        return mv;
    }

}
