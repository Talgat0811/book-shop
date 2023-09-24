package com.example.telegrambot.configs.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramBotProperties {
    String name;
    String token;
    String link;
}
