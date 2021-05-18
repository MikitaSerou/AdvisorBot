package com.example.advisorbot.bot.enums;

import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Optional;

public enum Commands {
    START("/start", "Начнем же!"),
    HELP("/help", "Просто введите город по которому хотите получить информацию." +
            "Если это возможно - мы подберем вам похожие результаты" + EmojiParser.parseToUnicode(":slight_smile:")),
    NOT_FOUND("/", "Нет такой команды.");

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Commands.class);
    private final String command;
    private final String answer;

    Commands(String command, String answerForCommand) {
        this.command = command;
        this.answer = answerForCommand;
    }

    public String getCommand() {
        return command;
    }

    public String getAnswer() {
        return answer;
    }

    public static String getAnswerByCommand(String command) {
        Optional<Commands> requestCommand = Arrays.stream(Commands.values())
                .filter(c -> c.getCommand().equalsIgnoreCase(command)).findFirst();
        requestCommand.ifPresentOrElse(
                c -> {
                    log.info("Request command is: " + c);
                },
                () -> log.error("Request command is not available"));

        return requestCommand.orElse(NOT_FOUND).getAnswer();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
