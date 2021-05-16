package com.example.advisorbot.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"cities"})
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "emoji_code", nullable = false, unique = true)
    private String emojiCode;

    @Column(name="currency", nullable = false)
    private String currency;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<City> cities;

    public Country(String name, String emojiCode, String currency) {
        this.name = name;
        this.emojiCode = emojiCode;
        this.currency = currency;
    }
}
