package com.aleleone.WOD.Randomizer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("c1930970.ferozo.com"); // Set your SMTP host
        mailSender.setPort(465); // Set the port for SMTP (e.g., 587 for TLS)
        mailSender.setUsername("alejandroleone@terramas.com.ar"); // Set your email username
        mailSender.setPassword("CorreosNuevos2020"); // Set your email password

        return mailSender;
    }
}
