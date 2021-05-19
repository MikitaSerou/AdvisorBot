package com.example.advisorbot.bot.bot_services;

import com.example.advisorbot.bot.enums.Commands;
import com.example.advisorbot.bot.enums.Links;
import com.example.advisorbot.entity.City;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KeyboardsProvider {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(KeyboardsProvider.class);

    //Get keyboard buttons below users message input field
    public synchronized ReplyKeyboardMarkup getReplyKeyboard() {
        log.info("getReplyKeyboard()");
        // Create keyboard
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);

        // Create list of buttons rows
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        // First row for keyboard
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton helpCommandButton = new KeyboardButton(Commands.HELP.getCommand());
        keyboardFirstRow.add(helpCommandButton);

        // Add rows to keyboard
        keyboardRows.add(keyboardFirstRow);

        // и устанваливаем этот список нашей клавиатуре
        keyboard.setKeyboard(keyboardRows);

        return keyboard;
    }

    //Get keyboard with links for response of the found city
    public InlineKeyboardMarkup getInlineKeyboardForCityResponse(String cityName) {
        log.info("getInlineKeyboardForCityResponse(String " + cityName + ")");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        InlineKeyboardButton wikipediaLinkButton = new InlineKeyboardButton();

        //Set URL and title for button (not for simple answer button to bot)
        wikipediaLinkButton.setText(Links.WIKIPEDIA.getTitle());
        wikipediaLinkButton.setUrl(Links.WIKIPEDIA.getLink() + cityName);

        keyboardButtonsRow.add(wikipediaLinkButton);
        keyboard.setKeyboard(Collections.singletonList(keyboardButtonsRow));

        return keyboard;
    }

    public ReplyKeyboardMarkup getKeyboardWithSimilarCitiesLinks(List<City> similarCities) {
        log.info("getKeyboardWithSimilarCitiesLinks(List<City> " + similarCities + ")");
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        similarCities.forEach(c -> {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton cityButton = new KeyboardButton(c.getName()); //TODO еще и страну сделать
            keyboardRow.add(cityButton);

            keyboardRows.add(keyboardRow);
        });
        keyboard.setKeyboard(keyboardRows);

        return keyboard;
    }
}
