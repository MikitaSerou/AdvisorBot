package com.example.advisorbot.bot.enums;

public enum Links {
    WIKIPEDIA("Wikipedia", "https://ru.wikipedia.org/wiki/");


    private final String title;
    private final String link;

    Links(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
