package com.zjb.service.wechatproxy.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.zjb.domain.AreaMenu;
import com.zjb.domain.Wechat;
import com.zjb.service.wechatproxy.WechatProxyAPIService;
import com.zjb.util.UnionWechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * Created by zjb on 17-6-29.
 */
@Service
@Slf4j
public class WeChatProxyAPIServiceImpl implements WechatProxyAPIService {

    //@Autowired
   //private Wechat wechat;

    //@Override
    //public String getWechatToken() {
    //
    //    return UnionWechatUtil.getAccessToken(wechat.appid, wechat.secret).getToken();
    //
    //}
    private String getWechatToken(String appid, String secret) {

        return UnionWechatUtil.getAccessToken(appid, secret).getToken();

    }


    @Override
    public Mono<JSONObject> getMenu(String appid, String secret) {

        String wechatToken = this.getWechatToken(appid,secret);
        String s = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + wechatToken);
        JSONObject jsonObject = JSON.parseObject(s);
        if (Objects.equals(jsonObject.getString("errcode"),"46003")){
            jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("button", new JSONObject());
            jsonObject.put("menu", jsonObject1);
        }

        Mono<JSONObject> just = Mono.just(jsonObject);


        return just;
    }

    @Override
    public Mono<JSONObject> createMenu(AreaMenu areaMenu) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getWechatToken(areaMenu.getAppid(),areaMenu.getSecret());
        String post = HttpUtil.post(url, areaMenu.getMenu());

        return Mono.just(JSON.parseObject(post));
    }

    @Override
    public  Flux<JSONObject> getMaterial(String appid,String secret, String type) {
        // String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+this.getWechatToken(area);
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + getWechatToken(appid,secret);
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("offset", 0);
        map.put("count", 20);
        String post = HttpUtil.post(url, JSON.toJSONString(map));
        JSONObject object = JSON.parseObject(post);

        List<JSONObject> list = new ArrayList<>();
        JSONObject obj=null;
        if (Objects.equals(type, "image")) {
            JSONArray item = JSON.parseArray(object.getString("item"));
            for (int i = 0; i < item.size(); i++) {
                obj = new JSONObject();
                JSONObject curObj = item.getJSONObject(i);
                obj.put("mediaId", curObj.getString("media_id"));
                obj.put("name", curObj.getString("name"));
                list.add(obj);
            }

        } else if (Objects.equals(type, "news")) {

            JSONArray item = JSON.parseArray(object.getString("item"));
            for (int i = 0; i < item.size(); i++) {
                obj = new JSONObject();
                JSONObject curObj = item.getJSONObject(i);
                obj.put("mediaId", curObj.getString("media_id"));
                List<String> eval = (List<String>) JSONPath.eval(curObj, "$..title");
                obj.put("name",eval.get(0));
                list.add(obj);
            }


        }
        return Flux.fromIterable(list);
    }


}
