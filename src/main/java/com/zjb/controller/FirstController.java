package com.zjb.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjb.domain.AreaMenu;
import com.zjb.service.wechatproxy.WechatProxyAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by zjb on 17-11-24.
 */
@Controller
@Slf4j
public class FirstController extends BaseController{

    @GetMapping("/hello_world")
    public Mono<String> sayHelloWorld() {
        
        return Mono.just("Hello1 World");
    }

    @Autowired
    private WechatProxyAPIService wechatService;


    @GetMapping("/")
    public Mono<String> firt() {

        return Mono.create(stringMonoSink -> stringMonoSink.success("hello"));
    }


    @GetMapping("/getMenu")
    @ResponseBody
    public Mono<JSONObject> getMenu(String appid,String secret) {

        return wechatService.getMenu(appid,secret);
    }

    @PostMapping("/syncMenu")
    @ResponseBody
    public Mono<JSONObject> syncMenu(@RequestBody AreaMenu menu) {

        return wechatService.createMenu(menu);
    }

    @GetMapping("/listMeterial")
    @ResponseBody
    public Flux<JSONObject> listMeterial(String appid,String secret,String type) {

        return wechatService.getMaterial(appid,secret, type);
    }


}
