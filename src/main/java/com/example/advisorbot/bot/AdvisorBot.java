package com.example.advisorbot.bot;

import com.example.advisorbot.bot.bot_services.MessageDistributor;
import org.slf4j.Logger;
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

@Component
public class AdvisorBot extends TelegramLongPollingBot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AdvisorBot.class);

    final int RECONNECT_PAUSE = 10000;

    @Value("${bot.name}")
    private String userName;

    @Value("${bot.token}")
    private String token;

    @Autowired
    private MessageDistributor messageDistributor;


    public AdvisorBot(String userName, String token, MessageDistributor messageDistributor) {
        this.userName = userName;
        this.token = token;
        this.messageDistributor = messageDistributor;
    }

    public AdvisorBot() {
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

    public int getRECONNECT_PAUSE() {
        return this.RECONNECT_PAUSE;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getToken() {
        return this.token;
    }

    public MessageDistributor getMessageDistributor() {
        return this.messageDistributor;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessageDistributor(MessageDistributor messageDistributor) {
        this.messageDistributor = messageDistributor;
    }

    public String toString() {
        return "AdvisorBot(RECONNECT_PAUSE=" + this.getRECONNECT_PAUSE() + ", userName=" + this.getUserName() + ", token=" + this.getToken() + ", messageDistributor=" + this.getMessageDistributor() + ")";
    }
}
