package za.co.momentum.automatedwithdrawal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomatedWithdrawalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatedWithdrawalApplication.class, args);
	}

}
