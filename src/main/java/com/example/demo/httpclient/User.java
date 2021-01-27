package com.example.demo.httpclient;

import lombok.Data;

@Data
public class User {
    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 性别 */
    private String gender;

    /** 座右铭 */
    private String motto;

}
