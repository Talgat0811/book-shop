package com.example.telegrambot.rest;

import com.example.telegrambot.TelegramBot;
import com.example.telegrambot.models.TelegramMessage;
import com.example.telegrambot.support.annotations.HasSecureKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@HasSecureKey
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final TelegramBot telegramBot;

    @PostMapping
    public ResponseEntity<Boolean> send(@RequestBody TelegramMessage telegramMessage) {
        log.info("got telegram message: {}", telegramMessage);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(telegramMessage.getMessage());
        sendMessage.setChatId(telegramMessage.getChatId());
        sendMessage.setParseMode(telegramMessage.getParseMode());
        telegramBot.sendAnswerMessage(sendMessage);
        return ResponseEntity.ok(true);
    }
}
