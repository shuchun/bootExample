package com.example.oauth.extention;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IBM on 2016/9/7.
 * 认证适配器
 */
@Component
public class AuthAdapter extends HandlerInterceptorAdapter {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private RequestAuth auth;

    /**
     * 请求发生前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //debug环境记录调用起始时间
        if(logger.isDebugEnabled()){
            request.setAttribute("startTime",System.currentTimeMillis());
        }

        //TODO:需要认证,待完善
        if(!auth.isAnonymousPath(request.getServletPath())){
            response.addHeader("method","get");
            response.sendRedirect("/oauth/noAuth");
            return false;
        }

        //无需认证,直接通过
        return true;
    }

    /**
     * 请求发生后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        //debug环境输出调用用时
        if(logger.isDebugEnabled()){
            Long startTime=(Long) request.getAttribute("startTime");
            request.removeAttribute("startTime");
            logger.debug(request.getServletPath()+" 耗时："+(System.currentTimeMillis()-startTime)+"ms");
        }

    }
}
