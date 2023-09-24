package com.example.telegrambot.support.utils;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@UtilityClass
public class TelegramUtils {

    public static String getUsernameFromUpdate(Update update) {
        User user = update.hasMessage() ? update.getMessage().getFrom() : update.getCallbackQuery().getFrom();
        return user.getUserName();
    }

    public static String getChatIdByUpdate(Update update) {
        Long chatId = null;
        if (update.hasMessage()) chatId = update.getMessage().getChatId();
        if (update.hasCallbackQuery()) chatId = update.getCallbackQuery().getMessage().getChatId();

        return String.valueOf(chatId);
    }
}
