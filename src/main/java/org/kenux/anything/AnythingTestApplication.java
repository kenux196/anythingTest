package org.kenux.anything;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackageClasses = {ComponentScanBasePackage.class})
public class AnythingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnythingTestApplication.class, args);
    }

}
