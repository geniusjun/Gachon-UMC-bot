package umc.bot.umc_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UmcBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmcBotApplication.class, args);
	}

}