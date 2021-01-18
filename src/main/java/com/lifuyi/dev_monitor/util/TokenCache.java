package com.lifuyi.dev_monitor.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class TokenCache {


    public  static Cache<Integer,String>  backendCache= CacheBuilder.newBuilder()
                    .expireAfterWrite(86400000, TimeUnit.MILLISECONDS)
                    .maximumSize(200)
                    .build();

    public  static Cache<Integer,String>  frontCache= CacheBuilder.newBuilder()
            .expireAfterWrite(86400000, TimeUnit.MILLISECONDS)
            .maximumSize(200)
            .build();
}
