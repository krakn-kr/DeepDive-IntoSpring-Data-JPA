package com.example.demo.controller;

import com.example.demo.service.LoadingDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private LoadingDemoService demoService;

    @GetMapping("/lazy")
    public String lazyDemo() {
        demoService.demonstrateLazyLoading();
        return "Check console for lazy loading demo";
    }

    @GetMapping("/eager")
    public String eagerDemo() {
        demoService.demonstrateEagerLoading();
        return "Check console for eager loading demo";
    }
}
