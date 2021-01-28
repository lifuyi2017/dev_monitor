package com.lifuyi.dev_monitor.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lifuyi.dev_monitor.model.user.User;

import java.util.Date;

public class TokenUtil {

    /**
     * 生成token 的方法，过期时间为一天
     * @param user
     * @return
     */
    public static String  getToken(User user){
        String token = "";
        Date now = new Date();
        token = JWT.create().withExpiresAt(new Date(now.getTime() + 86400000)).withAudience(user.getId()+"")
                .sign(Algorithm.HMAC256(user.getUser_password()));
        return token;
    }

}
