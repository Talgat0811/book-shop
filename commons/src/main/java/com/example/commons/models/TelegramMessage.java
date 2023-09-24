package com.example.commons.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramMessage {

    @NonNull
    Long chatId;

    @NonNull
    String message;

    String parseMode;
    boolean enableKeyboard;
}
