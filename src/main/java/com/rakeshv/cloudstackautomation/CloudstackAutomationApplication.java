package com.rakeshv.cloudstackautomation;

import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackClient;
import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackRequest;
import br.com.autonomiccs.apacheCloudStack.client.beans.ApacheCloudStackUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudstackAutomationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CloudstackAutomationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
