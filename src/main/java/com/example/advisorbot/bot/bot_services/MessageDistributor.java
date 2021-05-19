package com.example.advisorbot.bot.bot_services;

import com.example.advisorbot.bot.enums.Commands;
import com.example.advisorbot.bot.enums.Links;
import com.example.advisorbot.entity.City;
import com.example.advisorbot.service.CityService;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;


@Service
public class MessageDistributor {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MessageDistributor.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private KeyboardsProvider keyboardsProvider;

    private final List<Commands> allCommands = Arrays.asList(Commands.values());


    //Check of the user's request (standard bot commands or not)
    public SendMessage getAnswer(Update update) {
        log.info("getAnswer(" + update.toString() + ")");
        String updateText = update.getMessage().getText();
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setReplyMarkup(keyboardsProvider.getReplyKeyboard());
        if (allCommands.stream().anyMatch(command ->
                command.getCommand().equalsIgnoreCase(updateText))) {
            log.info("Command has been inputted - get answer for available command");
            message.setText(Commands.getAnswerByCommand(updateText));
        } else {
            log.info("City or another request has been inputted - get answer");
            try {
                message = answerForCityRequest(message, updateText);
            } catch (StringIndexOutOfBoundsException ex) {
                message.setText(EmojiParser.parseToUnicode(
                        "Некорректный ввод :heavy_exclamation_mark:"));
                ex.printStackTrace();
            }
        }
        return message;
    }

    //Get answer in case when user's input - supposed a city
    public SendMessage answerForCityRequest(SendMessage message, String updateText) {
        //   init();
        log.info("answerForCityRequest(SendMessage " + message + ", String " + updateText + ")");
        List<City> searchedCity = cityService.findByName(updateText);
        if (searchedCity != null) {
            if (updateText.equalsIgnoreCase(searchedCity.get(0).getName())) {
                message.enableHtml(true);
                message.setText(cityMessageConstructor(searchedCity.get(0)));
                message.enableWebPagePreview();
                message.setReplyMarkup(keyboardsProvider.getInlineKeyboardForCityResponse(searchedCity.get(0).getName()));
            } else {
                message.setText(EmojiParser.parseToUnicode("Мы не нашли такого города, однако есть" +
                        " с похожими названиями :arrow_down::arrow_down::arrow_down:"));
                message.setReplyMarkup(keyboardsProvider.getKeyboardWithSimilarCitiesLinks(searchedCity));
            }
        } else {
            message.setText(EmojiParser.parseToUnicode("Не можем ничего вам рассказать про этот город " +
                    "(может просто пока мы не добавили его в базу данных) :rolling_eyes:"));
        }

        return message;
    }

    private String cityMessageConstructor(City city) {
        StringBuilder cityAnswer = new StringBuilder();

        cityAnswer.append("<b>").append(city.getName()).append("</b>\n\n").append("Страна: ")
                .append(EmojiParser.parseToUnicode(city.getCountry().getName() + ":" +
                        city.getCountry().getAbbreviation().toLowerCase() + ":\n"));
        if (city.getIsCapital()) {
            cityAnswer.append(EmojiParser.parseToUnicode("Столица :crown:\n"));
        }
        cityAnswer.append(EmojiParser.parseToUnicode("Валюта: " +
                city.getCountry().getCurrency().getName() + " (" + city.getCountry().getCurrency().getIso() +
                "):moneybag:\n\n" +
                "<i>" + city.getDescription() + "</i>\n\n" +
                "<a href='" + Links.WIKIPEDIA.getLink() + city.getName().toLowerCase() +
                "'>Полная информация</a>"
        ));

        return cityAnswer.toString();
    }
}
