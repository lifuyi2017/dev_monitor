package com.lifuyi.dev_monitor.intercept;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lifuyi.dev_monitor.annotation.FrontPassToken;
import com.lifuyi.dev_monitor.annotation.FrontUserLoginToken;
import com.lifuyi.dev_monitor.annotation.PassToken;
import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.dao.UserMapper;
import com.lifuyi.dev_monitor.model.user.Resp.UserResp;
import com.lifuyi.dev_monitor.model.user.User;
import com.lifuyi.dev_monitor.service.UserService;
import com.lifuyi.dev_monitor.util.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;


public class AuthenticationInterceptor implements HandlerInterceptor {


    @Resource
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //进入方法之前进行的操作
        //获取token
        String token  =  request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //后台接口判断部分
        if(method.isAnnotationPresent(PassToken.class))
        {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }
        String userId = null;
        if(method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                if(token == null){
                    throw new RuntimeException("无token，请重新登录");
                }
                //获取token的userid
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }
                catch (JWTDecodeException e){
                    throw new RuntimeException("401");
                }
                if (token.equals(TokenCache.backendCache.getIfPresent(userId))){
                    throw new RuntimeException("已经登出注销的token，请重新登录");
                }
                User user1 = new User();
                user1.setId(userId);
                List<UserResp> usersByUser = userMapper.getUserByEntity(user1);
                if(usersByUser==null){
                    throw new RuntimeException("用户不存在");
                }
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(usersByUser.get(0).getUser_password())).build();
                try{
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e){
                    throw new RuntimeException("token验证不通过或者已经过期，请重新登录");
                }
                return true;
            }
        }
        //前台接口判断部分
        if(method.isAnnotationPresent(FrontPassToken.class))
        {
            FrontPassToken passToken = method.getAnnotation(FrontPassToken.class);
            if(passToken.required()){
                return true;
            }
        }
        if(method.isAnnotationPresent(FrontUserLoginToken.class)) {
            FrontUserLoginToken frontUserLoginToken = method.getAnnotation(FrontUserLoginToken.class);
            if(frontUserLoginToken.required()){
                if(token == null){
                    throw new RuntimeException("无token，请重新登录");
                }
                //获取token的userid
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }
                catch (JWTDecodeException e){
                    throw new RuntimeException("401");
                }
                if (token.equals(TokenCache.frontCache.getIfPresent(userId))){
                    throw new RuntimeException("已经登出注销的token，请重新登录");
                }
                User u = new User();
                u.setId(userId);
                List<UserResp> usersByUser = userMapper.getUserByEntity(u);
                if(usersByUser==null){
                    throw new RuntimeException("用户不存在");
                }
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(usersByUser.get(0).getUser_password())).build();
                try{
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e){
                    throw new RuntimeException("token验证不通过或者已经过期，请重新登录");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //方法处理之后但是并未渲染视图的时候进行的操作
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //渲染视图之后进行的操作
    }

}
