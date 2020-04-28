package test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * Created by zjb on 18-7-9.
 */
public class button {

    @Test
    public void testButton() {
        String token = "11_KQ1su0vOmnH7tNZMDKYTXqTFjz-cxs7oLws8tzrlLbWTYK6tWMY78vCkm0xLUchgsuaugfGCd96YkGaMKnIxE9Ug12Z_zESQxX9eKqXyrbOVMS6iyxKMo6XEKiBVacpc6rTrsgnME3oB8_DVXCLaAAACHQ";
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;

        String button = "{\n" +
                "    \"button\":[\n" +
                "        {\n" +
                "            \"name\":\"微站\",\n" +
                "            \"sub_button\":[\n" +
                "                {\n" +
                "                    \"type\":\"view\",\n" +
                "                    \"name\":\"访客登记\",\n" +
                "                    \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxce929333dfe736f9&redirect_uri=http://pay.yayayouxi.com/kepa-test/wechat/login&response_type=code&scope=snsapi_userinfo&state=http%3A%2F%2Fpay.yayayouxi.com%2Fkepa-web-test%2Fdist%2Findex.html%23%2Fregister&connect_redirect=1#wechat_redirect\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\":\"view\",\n" +
                "                    \"name\":\"个人中心\",\n" +
                "                    \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxce929333dfe736f9&redirect_uri=http://pay.yayayouxi.com/kepa-test/wechat/login&response_type=code&scope=snsapi_userinfo&state=http%3A%2F%2Fpay.yayayouxi.com%2Fkepa-web-test%2Fdist%2Findex.html&connect_redirect=1#wechat_redirect\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        System.out.println(JSON.parseObject(button));
        System.out.println(button);
        // String s = HttpsUtil.doPost(url, button);
        // System.out.println(s);

    }
}
