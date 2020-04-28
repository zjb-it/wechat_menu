package com.zjb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zjb on 18-7-2.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateMsgResult {
    /**
     * 0代表成功，其它参考微信文档
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234
     */
    private Integer errcode;
    private String errmsg;
    private String msgid;

    public TemplateMsgResult(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }
}
