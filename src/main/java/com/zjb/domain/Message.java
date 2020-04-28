package com.zjb.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zjb on 18-6-29.
 * #您有新访客！ 姓名：{{vistor.DATA}} 来访时间：{{time.DATA}} 请您及时审核。
 *
 * 您的访问申请已通过。 被访人：{{vistor.DATA}} 访问时间：{{time.DATA}} 请您及时访问，以免耽误行程。
 *
 */
@Data
public class Message {

    @NotBlank
    private String openId;
    // /**
    //  * 姓名
    //  */
    // private String userName;
    /**
     * 访客姓名
     */
    private String vistorName;
    /**
     * 访客登记编号
     */
    private String visitorId;
    /**
     * 访问时间
     */
    @NotBlank
    private String time;
    /**
     * 消息类型
     * 1为审核消息
     * 2为审核通过消息
     */
    @NotNull
    private Integer messageType;

}
