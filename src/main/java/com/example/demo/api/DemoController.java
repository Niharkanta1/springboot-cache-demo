package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.DemoService;

@RestController
@RequestMapping("data")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("{id}")
    // @Cacheable("mydata")
    public String getMyData(@PathVariable("id") Integer id) {
        return demoService.getData(id);
    }
}
