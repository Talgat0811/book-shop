package com.example.telegrambot;

import com.example.telegrambot.configs.properties.TelegramBotProperties;
import com.example.telegrambot.support.utils.TelegramUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotProperties telegramBotProperties;

    @Override
    public String getBotUsername() {
        return telegramBotProperties.getName();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO посмотреть несовместимость версий (spring v3 with telegram-bot v6)
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(TelegramUtils.getChatIdByUpdate(update));
        sendMessage.setText("U are welcome " + TelegramUtils.getUsernameFromUpdate(update));

        sendAnswerMessage(sendMessage);
    }

    public void sendAnswerMessage(SendMessage sendMessage) {
        if (sendMessage != null) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }
}
