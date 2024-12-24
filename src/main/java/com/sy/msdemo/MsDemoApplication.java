package com.sy.msdemo;

import com.example.hello.autoConfig.HelloAutoConfiguration;
import com.example.hello.config.HelloConfig;
import com.example.hello.service.SayHello;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MsDemoApplication {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(MsDemoApplication.class, args);
    }

    @PostConstruct
    public void init() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(String.join("\n", beanDefinitionNames));
        HelloConfig bean2 = applicationContext.getBean(HelloConfig.class);
        System.out.println(bean2);
        System.out.println(bean2.getMsg());
        HelloAutoConfiguration bean = applicationContext.getBean(HelloAutoConfiguration.class);
        System.out.println(bean);
        SayHello bean1 = applicationContext.getBean(SayHello.class);
        System.out.println(bean1);

        Environment environment = applicationContext.getEnvironment();
        System.out.println("environment.getProperty(\"com.example.hello.msg\") = " + environment.getProperty("com.example.hello.msg"));
    }

    @Autowired
    private SayHello sayHello;


    @RequestMapping("hello")
    public String hello(@RequestParam(value = "msg", required = false) String msg) {
        return sayHello.sayHello(msg);
    }
}
