package com.zjb.controller;

import com.zjb.domain.Wechat;
import com.zjb.service.wechatproxy.WechatProxyAPIService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zjb on 18-6-28.
 */
public class BaseController {
    /**
     * 微信accesstokne的key
     */
    private static final String WECHAT_ACCESSTOKEN_KEY = "wechat.accesstoken";
    /**
     * 微信accesstokne的key 创建时间
     */
    private static final String WECHAT_ACCESSTOKEN_KEY_EXPIRE = "wechat.accesstoken.expire";
    /**
     * 用户session key
     */
    private static final String USER_SESSION = "userSession";

    @Autowired
    private Wechat wechat;
    @Autowired
    private WechatProxyAPIService wechatService;



}
