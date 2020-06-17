package com.starry.web;

import com.starry.entity.Saying;
import com.starry.service.SayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/saying")
@RestController
public class SayingController {
    @Autowired
    private SayingService service;

    @RequestMapping("/get/comment/{id}")
    public Saying showCommment(@PathVariable(value = "id") int id) {
        System.out.println("============这里是sayingController=================");
        return service.selectOneById(id);
    }
    @RequestMapping(value="/likes", method= RequestMethod.POST)
    public void changeLikes(@RequestParam int id, @RequestParam String likes) {
        System.out.println("=========更新赞数============");
        service.upadateLikesById(id, likes);
    }
}
