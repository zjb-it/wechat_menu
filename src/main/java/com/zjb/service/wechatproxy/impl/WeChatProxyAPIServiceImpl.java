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

    @Autowired
   private Wechat wechat;

    @Override
    public String getWechatToken() {

        return UnionWechatUtil.getAccessToken(wechat.appid, wechat.secret).getToken();

    }

    @Override
    public Mono<JSONObject> getMenu(String area) {

        String wechatToken = this.getWechatToken();

        String s = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + wechatToken);
        JSONObject jsonObject = JSON.parseObject(s);
        if (Objects.equals(jsonObject.getString("errcode"),"46003")){
            jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("button", new JSONObject());
            jsonObject.put("menu", jsonObject1);
        }

        Mono<JSONObject> just = Mono.just(jsonObject);

        // String button = "{\n" +
        //         "    \"button\":[\n" +
        //         "        {\n" +
        //         "            \"name\":\"帐户中心\",\n" +
        //         "            \"sub_button\":[\n" +
        //         "                {\n" +
        //         "                    \"type\":\"view\",\n" +
        //         "                    \"name\":\"玩家充值\",\n" +
        //         "                    \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx65dfcec0237c1d03&redirect_uri=http://pay.yayayouxi.com/payServer/commodity/getWxUserInfo&response_type=code&scope=snsapi_userinfo&state=pipi_playerpay_1_2#wechat_redirect\"\n" +
        //         "                },\n" +
        //         "                {\n" +
        //         "                    \"type\":\"view\",\n" +
        //         "                    \"name\":\"俱乐部\",\n" +
        //         "                    \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx65dfcec0237c1d03&redirect_uri=http://pay.yayayouxi.com/fy-club-api/api/club/auth/wx&response_type=code&scope=snsapi_userinfo&state=pipi&connect_redirect=1#wechat_redirect\"\n" +
        //         "                },\n" +
        //         "                {\n" +
        //         "                    \"type\":\"media_id\",\n" +
        //         "                    \"name\":\"申请代理\",\n" +
        //         "                    \"media_id\":\"pAr-wJPzHndwTgFflPKVGxiL4518WF_20GD-CMN8Upk\"\n" +
        //         "                },\n" +
        //         "                {\n" +
        //         "                    \"type\":\"media_id\",\n" +
        //         "                    \"name\":\"官方声明\",\n" +
        //         "                    \"media_id\":\"pAr-wJPzHndwTgFflPKVG4lDEtKxI22OZQ_UPuBGcuQ\"\n" +
        //         "                },\n" +
        //         "                {\n" +
        //         "                    \"type\":\"view\",\n" +
        //         "                    \"name\":\"新代理后台\",\n" +
        //         "                    \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx65dfcec0237c1d03&redirect_uri=http://pay.yayayouxi.com/NewAgent/wxLogin&response_type=code&scope=snsapi_userinfo&state=pipi&connect_redirect=1#wechat_redirect\"\n" +
        //         "                }" +
        //         "            ]\n" +
        //         "        }" +
        //         ",\n" +
        //         "        {\n" +
        //         "            \"name\":\"活动中心\",\n" +
        //         "            \"sub_button\":[\n" +
        //         "                {\n" +
        //         "                    \"type\":\"view\",\n" +
        //         "                    \"name\":\"游戏下载\",\n" +
        //         "                    \"url\":\"http://www.yayayouxi.com/hyphz/\"\n" +
        //         "                }\n" +
        //         "            ]\n" +
        //         "        }\n" +
        //         "    ]\n" +
        //         "}";
        // Mono<JSONObject> just = Mono.just(JSON.parseObject(button));

        return just;
    }

    @Override
    public Mono<JSONObject> createMenu(AreaMenu areaMenu) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getWechatToken();
        String post = HttpUtil.post(url, areaMenu.getMenu());

        return Mono.just(JSON.parseObject(post));
    }

    @Override
    public  Flux<JSONObject> getMaterial(String area, String type) {
        // String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+this.getWechatToken(area);
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + getWechatToken();



        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("offset", 0);
        map.put("count", 20);
        String post = HttpUtil.post(url, JSON.toJSONString(map));
        // List<String> names = (List<String>)JSONPath.eval(entities, "$.name");

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

    public static void main(String[] args) {
        //
        // Flux<JSONObject> material = getMaterial("niuniu", "news");
        //
        // JSONObject jsonObject = material.blockFirst();
        //
        // System.out.println(JSON.toJSONString(jsonObject));
    }
}
