package org.example.config;

import org.example.contact.ContactInitializer;
import org.example.contact.ContactStoreService;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    private final ContactStoreService contactStoreService;

    public AppConfig(ContactStoreService contactStoreService) {
        this.contactStoreService = contactStoreService;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }

    @Bean
    @Profile("init")
    public ContactInitializer contactInitializer() {
        return new ContactInitializer(contactStoreService);
    }
}
