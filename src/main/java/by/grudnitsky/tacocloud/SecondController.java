package by.grudnitsky.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecondController {

    @RequestMapping({"/test", "/test2"})
    public String test(){
        return "test";
    }

    @RequestMapping("/test3")
    public String test2(){
        return "test2";
    }
}
