package com.example.demo.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**   创建session以及获取session
 * Created by DJ010199 on 2020/6/4.
 */
@RestController
public class TestSessionController {
    @Value("${server.port}")
    private Integer projectPort;// 项目端口

    @RequestMapping("/createSession")
    public String createSession(HttpSession session, String name) {
        session.setAttribute("name", name);
        return "当前项目端口：" + projectPort + " 当前sessionId :" + session.getId() + "在Session中存入成功！";
    }

    @RequestMapping("/getSession")
    public String getSession(HttpSession session) {
        return "当前项目端口：" + projectPort + " 当前sessionId :" + session.getId() + "  获取的姓名:" + session.getAttribute("name");
    }
}
