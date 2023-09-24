package com.example.telegrambot.amqp;

import com.example.telegrambot.dispatchers.NotificationDispatcher;
import com.example.telegrambot.models.TelegramMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@DependsOn({"notificationDispatcher"})
public class NotificationConsumer {
    private final NotificationDispatcher notificationDispatcher;

    /***
     * @param telegramMessage
     * @description Уведомляет о предстоящих распродажах, акциях книг для клиентов
     */
    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "book-sale-queue", durable = "true"),
            exchange = @Exchange(value = "book-sale-exchange")),
            autoStartup = "${rabbitmq.consumer.book-sale-queue.is-running:true}")
    public void listenEcmEventMessageOrDefault(TelegramMessage telegramMessage) {
        notificationDispatcher.dispatch(telegramMessage);
    }
}
