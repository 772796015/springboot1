package com.example.demo.httpclient;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class RequsetHeader {
    @JSONField(name = "Header")
    private Header header;
}
