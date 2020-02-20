package net.work100.training.stage2.iot.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Title: MainController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-mvc.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-20 15:19
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-20   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Controller
public class MainController {

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
