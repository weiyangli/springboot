package com.example.springBoot.controller;

import com.example.springBoot.bean.Target;
import com.example.springBoot.service.TargetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class.getName());
    @Autowired
    private TargetImpl targetImpl;

    @RequestMapping("/")
    ModelAndView  index() {
        logger.info("进入index");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello.html");
        modelAndView.addObject("key", "是11111呀");
        return modelAndView;
    }

    @GetMapping("/save")
    @ResponseBody
    public void save() {
        Target target = new Target();
        target.setTname("减肥");
        this.targetImpl.save(target);
    }
    @GetMapping("/find/data")
    @ResponseBody
    public List<Target> findAll() {
        return this.targetImpl.findAll();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        this.targetImpl.delete(id);
    }

    @GetMapping("/find/data/bu/name")
    @ResponseBody
    public List<Target> findByTnameLike(@RequestParam  String tname) {
        return targetImpl.findByTnameLike("%" + tname + "%");
    }
}
