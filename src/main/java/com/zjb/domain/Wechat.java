package com.zjb.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zjb on 18-6-28.
 */
@ConfigurationProperties(prefix = "wechat")
@Data
@Component
public class Wechat  {

    public String appid;
    public String secret;
    /**
     * 有人访问 模板ID
     */
    public String vistorTemplateId;
    /**
     * 审批通过模板ID
     */
    public String auditedTemplateId;
    /**
     * 微信授权成功后 跳转的页面url
     */
    public String redirectURL;
    /**
     * 微信授权url
     */
    public String loginURL;

    // @Override
    public void run(String... strings) throws Exception {
        System.out.println(appid);
        System.out.println(secret);
    }
}
