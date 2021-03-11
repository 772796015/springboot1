package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor //需要无惨构造器
@XmlRootElement
public class ClassA {

    private String file1;
    private String file2;

    private User user;
}
