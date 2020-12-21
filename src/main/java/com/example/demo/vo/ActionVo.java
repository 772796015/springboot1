package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * json格式转换用的实体类
 */
@lombok.Data
public class ActionVo  implements Serializable {
    private static final long serialVersionUID = -6957361951748382519L;
    private String id;
    private String suborderNo;
    private String organUnitType;
    private String action;
    private String parent;
    private String organUnitFullName;
    private Long ordinal;
}
