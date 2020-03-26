package ru.yksvelobos.appliancecontrollerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.yksvelobos.appliancecontrollerserver.config.ApplineceControllerServerConfig;

@Import(ApplineceControllerServerConfig.class)
@SpringBootApplication
public class ApplianceControllerServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApplianceControllerServerApplication.class, args);
	}

}
