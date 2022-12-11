package top.puppetdev.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author puppet
 * @since 2022/4/21 14:37
 */
@Controller
public class DemoController {
    
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("DemoController控制器被执行了");
        return "success";
    }
}
