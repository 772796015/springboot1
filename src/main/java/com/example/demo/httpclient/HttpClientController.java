package com.example.demo.httpclient;

import org.springframework.web.bind.annotation.*;

/**
 * HttpClient测试
 *
 * @author JustryDeng
 * @date 2018年7月13日 下午4:05:57
 */
@RestController
public class HttpClientController {

    /**
     * GET无参
     *
     * @return 测试数据
     * @date 2018年7月13日 下午5:29:44
     */
    @RequestMapping("/doGetControllerOne")
    public String doGetControllerOne() {
        return "123";
    }

    /**
     * GET有参
     *
     * @param name
     *            姓名
     * @param age
     *            年龄
     * @return 测试数据
     * @date 2018年7月13日 下午5:29:47
     */
    @RequestMapping("/doGetControllerTwo")
    public String doGetControllerTwo(@RequestParam(name = "name") String name, @RequestParam(name = "age")Integer age) {
        System.out.println("controller--------没想到[" + name + "]都" + age + "岁了!");
        return "没想到[" + name + "]都" + age + "岁了!";
    }

    /**
     * POST无参
     *
     * @return 测试数据
     * @date 2018年7月13日 下午5:29:49
     */
    @RequestMapping(value = "/doPostControllerOne", method = RequestMethod.POST)
    public String doPostControllerOne() {
        return "这个post请求没有任何参数!";
    }

    /**
     * POST有参(对象参数)
     *
     * @return 测试数据
     * @date 2018年7月13日 下午5:29:52
     */
    @RequestMapping(value = "/doPostControllerTwo", method = RequestMethod.POST)
    public String doPostControllerTwo(@RequestBody User user) {
        return user.toString();
    }

    /**
     * POST有参(普通参数 + 对象参数)
     *
     * @return 测试数据
     * @date 2018年7月13日 下午5:29:52
     */
    @RequestMapping(value = "/doPostControllerThree", method = RequestMethod.POST)
    public String doPostControllerThree(@RequestBody User user,Integer flag, String meaning) {
        return user.toString() + "\n" + flag + ">>>" + meaning;
    }

    /**
     * POST有参(普通参数)
     *
     * @return 测试数据
     * @date 2018年7月14日 上午10:54:29
     */
    @RequestMapping(value = "/doPostControllerFour", method = RequestMethod.POST)
    public String doPostControllerThree1(String name, Integer age) {
        return "[" + name + "]居然才[" + age + "]岁!!!";
    }

}
