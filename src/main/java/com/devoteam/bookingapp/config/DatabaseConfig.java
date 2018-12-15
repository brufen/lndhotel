package com.devoteam.bookingapp.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.devoteam.bookingapp.repository")
@EnableTransactionManagement
public class DatabaseConfig {
}
