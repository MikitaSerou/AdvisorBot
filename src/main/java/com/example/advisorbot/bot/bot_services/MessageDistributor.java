package com.example.advisorbot.bot.bot_services;

import com.example.advisorbot.bot.enums.Commands;
import com.example.advisorbot.bot.enums.Links;
import com.example.advisorbot.entity.City;
import com.example.advisorbot.entity.Country;
import com.example.advisorbot.repository.CityRepository;
import com.example.advisorbot.repository.CountryRepository;
import com.example.advisorbot.service.CityService;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class MessageDistributor {

    private final CityService cityService;

    private final KeyboardsProvider keyboardsProvider;

    private final List<Commands> allCommands = Arrays.asList(Commands.values());


    @Autowired
    public MessageDistributor(CityService cityService, KeyboardsProvider keyboardsProvider) {
        this.cityService = cityService;
        this.keyboardsProvider = keyboardsProvider;
    }

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
            message.setText(EmojiParser.parseToUnicode("Не можем ничего вам рассказать про этот город. И город ли это вообще?:rolling_eyes:"));
        }

        return message;
    }


    private String cityMessageConstructor(City city) {
        StringBuilder cityAnswer = new StringBuilder();

        cityAnswer.append("<b>" + city.getName() + "</b>\n\n" +
                "Страна: " + EmojiParser.parseToUnicode(city.getCountry().getName() +
                city.getCountry().getEmojiCode() + ":\n"));
        if (city.getIsCapital()) {
            cityAnswer.append(EmojiParser.parseToUnicode("Столица :crown:\n"));
        }
        cityAnswer.append(EmojiParser.parseToUnicode("Валюта:moneybag:: (" +
                city.getCountry().getCurrency() + ")\n\n" +
                "<i>" + city.getDescription() + "</i>\n\n" +
                "<a href='" + Links.WIKIPEDIA.getLink() + city.getName() +
                "'>Полная информация</a>"
        ));

        return cityAnswer.toString();
    }

//    public void init() {
//        //TODO убрать
//        countryRepository.save(new Country("Беларусь", ":by:", "BYN"));
//        countryRepository.save(new Country("Украина", ":ua:", "UAH"));
//        countryRepository.save(new Country("США", ":us:", "USD"));
//        countryRepository.save(new Country("Россия", ":ru:", "RUB"));
//
//
//        cityRepository.save(new City("Минск",
//                "Столица республики Беларусь, город-герой. Обязательно загляните на Немигу," +
//                        " а также летние площадки вроде Песочницы.",
//                countryRepository.findById(1).get(), true));
//
//        cityRepository.save(new City("Гомель",
//                "Самый Красивый парк Республики Беларусь! " +
//                        "Гомельский дворцово-парковый ансамбль, куда входит немало интересных объектов, включая Дворец Румянцевых – " +
//                        "Паскевичей и Зимний сад.",
//                countryRepository.findById(1).get(), false));
//
//        cityRepository.save(new City("Гродно",
//                "Культурная столица Беларуси, столица беларусского католицизма.",
//                countryRepository.findById(1).get(), false));
//        cityRepository.save(new City("Брест",
//                "Уютный компактный городок, примечателен историческими объектами. Брестская крепость - " +
//                        "один из главных исторических объектов, связанных с историей Великой Отечественной войны.",
//                countryRepository.findById(1).get(), false));
//        cityRepository.save(new City("Витебск",
//                "Когда приезжаешь в Витебск, то сразу ловишь это странное ощущение –" +
//                        "с одной стороны, кажется, будто находишься дома, а с другой – " +
//                        "где-то на просторах Центральной Европы.",
//                countryRepository.findById(1).get(), false));
//        cityRepository.save(new City("Могилев",
//                "Маленькая Прага – так назвал Могилев белорусский этнограф и политический деятель Иван Луцкевич, " +
//                        "впервые прогулявшись по улицам этого города.",
//                countryRepository.findById(1).get(), false));
//    }

}
