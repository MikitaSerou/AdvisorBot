package com.example.advisorbot.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message = "Введите название")
    private String name;

    @Column(name = "abbreviation", nullable = false, unique = true)
    @NotEmpty(message = "Введите сокращение")
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

    public Country(Integer id, @NotEmpty(message = "Введите название") String name, @NotEmpty(message = "Введите сокращение") String abbreviation, Currency currency, List<City> cities) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.currency = currency;
        this.cities = cities;
    }

    public Country() {
    }

    public Integer getId() {
        return this.id;
    }

    public @NotEmpty(message = "Введите название") String getName() {
        return this.name;
    }

    public @NotEmpty(message = "Введите сокращение") String getAbbreviation() {
        return this.abbreviation;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public List<City> getCities() {
        return this.cities;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(@NotEmpty(message = "Введите название") String name) {
        this.name = name;
    }

    public void setAbbreviation(@NotEmpty(message = "Введите сокращение") String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Country)) return false;
        final Country other = (Country) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$abbreviation = this.getAbbreviation();
        final Object other$abbreviation = other.getAbbreviation();
        if (this$abbreviation == null ? other$abbreviation != null : !this$abbreviation.equals(other$abbreviation))
            return false;
        final Object this$currency = this.getCurrency();
        final Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$cities = this.getCities();
        final Object other$cities = other.getCities();
        if (this$cities == null ? other$cities != null : !this$cities.equals(other$cities)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Country;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $abbreviation = this.getAbbreviation();
        result = result * PRIME + ($abbreviation == null ? 43 : $abbreviation.hashCode());
        final Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final Object $cities = this.getCities();
        result = result * PRIME + ($cities == null ? 43 : $cities.hashCode());
        return result;
    }

    public String toString() {
        return "Country(id=" + this.getId() + ", name=" + this.getName() + ", abbreviation=" + this.getAbbreviation() + ", currency=" + this.getCurrency() + ")";
    }
}
