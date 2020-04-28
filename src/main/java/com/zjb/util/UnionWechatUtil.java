package com.zjb.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by zjb on 18-5-10.
 */
public class UnionWechatUtil {


    // 获取access_token的接口地址（GET） 限200（次/天）
    private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private final static  String jsapiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    private UnionWechatUtil() {
    }

    /**
     * 获取access_token
     *
     *            密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid,String secret) {
        AccessToken accessToken = null;
        String requestUrl =String.format(access_token_url, appid, secret);

        try {
            String resp = HttpUtil.get(requestUrl);
            JSONObject jsonObject = JSONObject.parseObject(resp);
            accessToken = new AccessToken();
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    //public static String getJsapiTicket(String area) {
    //
    //    try {
    //        String accessToken = getAccessToken(area).getToken();
    //        String url = String.format(jsapiUrl, accessToken);
    //        String resp = HttpUtil.get(url);
    //        JSONObject jsonObject = JSONObject.parseObject(resp);
    //        String ticket = jsonObject.getString("ticket");
    //        if (StringUtils.isEmpty(ticket)) {
    //            ticket = getJsapiTicket(area);
    //        }
    //
    //        return ticket;
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return "";
    //}


}
