package com.example.advisorbot.bot;

import com.example.advisorbot.bot.bot_services.MessageDistributor;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Slf4j
@Component
public class AdvisorBot extends TelegramLongPollingBot {

    final int RECONNECT_PAUSE = 10000;

    @Value("${bot.name}")
    private String userName;

    @Value("${bot.token}")
    private String token;

    private MessageDistributor messageDistributor;

    @Autowired
    public AdvisorBot(MessageDistributor messageDistributor) {
        this.messageDistributor = messageDistributor;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Update: " + update.getMessage());
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage answer = messageDistributor.getAnswer(update);
            try {
                execute(answer); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void botConnect() {
        log.info("Connection...");
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        try {
            telegramBotsApi.registerBot(this);

        } catch (TelegramApiRequestException e) {
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
