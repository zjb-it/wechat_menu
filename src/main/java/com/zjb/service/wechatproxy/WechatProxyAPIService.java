package com.zjb.service.wechatproxy;

import com.alibaba.fastjson.JSONObject;
import com.zjb.domain.AreaMenu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by zjb on 17-6-29.
 */
public interface WechatProxyAPIService {


    ///**
    // * 获取公众号token
    // * @return
    // */
    //String getWechatToken();


    /**
     * 获取公众号token
     * @return
     */
    Mono<JSONObject> getMenu(String appid, String secret);


    /**
     * 获取公众号token
     * @return
     */
    Mono<JSONObject> createMenu(AreaMenu areaMenu);


    /**
     * 获取公众号token
     * @return
     */
    Flux<JSONObject> getMaterial(String appid,String secret, String type);

}
