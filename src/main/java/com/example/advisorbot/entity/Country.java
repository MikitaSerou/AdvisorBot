package com.example.advisorbot.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"cities"})
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message="Введите название")
    private String name;

    @Column(name = "abbreviation", nullable = false, unique = true)
    @NotEmpty(message="Введите сокращение")
    private String abbreviation;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<City> cities;

    public Country(String name, String abbreviation, Currency currency) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.currency = currency;
    }
}
