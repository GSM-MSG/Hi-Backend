package msg.team1.Hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
@ConfigurationProperties
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class HiHombaseInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiHombaseInterfaceApplication.class, args);
	}

}
