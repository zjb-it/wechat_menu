package com.zjb.domain;

/**
 * Created by zjb on 18-6-28.
 */
public class KepaInterfaceAddr {
    private static final String HOST="http://120.77.146.149";
    /**
     * 访客登记接口
     */
    public static final String VISITOR_REGIS =HOST+"/api/Visitor/VisitorRegis";
    /**
     * 个人信息完善接口
     */
    public static final String WXREGIS_REGIS=HOST+"/api/WXRegis/Regis";
    /**
     * 个人信息查询接口
     */
    public static final String GET_INFO =HOST+"/api/WXRegis/GetInfo";


    /**
     * 访客登记接口
     */
    public static final String VisitorRegis =HOST+"/api/Visitor/VisitorRegis";
    /**
     * 访问记录接口
     */
    public static final String GetVisitorRecord =HOST+"/api/Visitor/GetVisitorRecord";
    /**
     * 访问详情接口
     */
    public static final String GetVisitorDetails =HOST+"/api/Visitor/GetVisitorDetails";

    /**
     *被访记录接口
     */
    public static final String GetVisitedRecord =HOST+"/api/Visitor/GetVisitedRecord";
    /**
     *被访详情接口
     */
    public static final String GetVisitedDetails =HOST+"/api/Visitor/GetVisitedDetails";
    /**
     *被访人获取访客登记接口
     */
    public static final String GetVisitor =HOST+"/api/Visitor/GetVisitor";
    /**
     *被访人审核接口
     */
    public static final String AuditVisitor =HOST+"/api/Visitor/AuditVisitor";
}
