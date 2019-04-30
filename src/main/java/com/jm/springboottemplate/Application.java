package com.jm.springboottemplate;

import com.jm.springboottemplate.system.config.ServerConfiguration;
import com.jm.springboottemplate.system.constant.ProjectProperty;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.TimeZone;

/**
 * Description: Application, entrance of the project.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-02-17 17:11
 **/
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EnableEncryptableProperties
public class Application {
    private static ProjectProperty projectProperty;
    private static ServerConfiguration serverConfiguration;

    public Application(ProjectProperty projectProperty, ServerConfiguration serverConfiguration) {
        Application.projectProperty = projectProperty;
        Application.serverConfiguration = serverConfiguration;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(Application.class, args);
        long endTime = System.currentTimeMillis();
        log.error("🍃 Congratulations! 🎉");
        log.error("[{}:{}:v{}] started successfully! Deployment duration: {} seconds ({} ms).",
                  projectProperty.getName(),
                  projectProperty.getCurrentEnvironment(),
                  projectProperty.getVersion(),
                  (endTime - startTime) / 1000,
                  (endTime - startTime));
        log.error("Server started at {} (timezone - {}), base URL: {}",
                  new Date(),
                  TimeZone.getDefault().getDisplayName(),
                  serverConfiguration.getUrl());
    }
}
