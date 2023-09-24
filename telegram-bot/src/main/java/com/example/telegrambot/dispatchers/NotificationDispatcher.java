package com.example.telegrambot.dispatchers;

import com.example.telegrambot.models.TelegramMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationDispatcher {

    public void dispatch(TelegramMessage telegramMessage) {

    }
}
