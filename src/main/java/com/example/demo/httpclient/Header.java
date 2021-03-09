package com.example.demo.httpclient;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Header {
    @JSONField(name = "SeriaNo")
    private String SeriaNo;
    @JSONField(name = "TransCode")
    private String TransCode;
    @JSONField(name = "BussNoType")
    private String BussNoType;
    @JSONField(name = "TransDate")
    private String TransDate;
    @JSONField(name = "TransTime")
    private String TransTime;
    @JSONField(name = "SysCode")
    private String SysCode;
}
