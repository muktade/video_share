package com.example.myyoutube.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import nz.net.ultraq.thymeleaf.layoutdialect.decorators.strategies.GroupingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class WebConfig {
    @Autowired
    public void templateEngine(SpringTemplateEngine templateEngine) {
        templateEngine.addDialect(new LayoutDialect(new GroupingStrategy()));
    }
}
