package com.example.advisorbot.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message="Введите название")
    private String name;

    @Column(name = "description")
    @NotEmpty(message="Введите описание")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "capital", nullable = false)
    private Boolean isCapital;

    public City(String name, String description, Country country, Boolean isCapital) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.isCapital = isCapital;
    }
}
