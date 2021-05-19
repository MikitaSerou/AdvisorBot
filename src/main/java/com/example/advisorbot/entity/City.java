package com.example.advisorbot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message = "Введите название")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Введите описание")
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

    public City(Integer id, @NotEmpty(message = "Введите название") String name, @NotEmpty(message = "Введите описание") String description, Country country, Boolean isCapital) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.country = country;
        this.isCapital = isCapital;
    }

    public City() {
    }

    public Integer getId() {
        return this.id;
    }

    public @NotEmpty(message = "Введите название") String getName() {
        return this.name;
    }

    public @NotEmpty(message = "Введите описание") String getDescription() {
        return this.description;
    }

    public Country getCountry() {
        return this.country;
    }

    public Boolean getIsCapital() {
        return this.isCapital;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(@NotEmpty(message = "Введите название") String name) {
        this.name = name;
    }

    public void setDescription(@NotEmpty(message = "Введите описание") String description) {
        this.description = description;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setIsCapital(Boolean isCapital) {
        this.isCapital = isCapital;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof City)) return false;
        final City other = (City) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$country = this.getCountry();
        final Object other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
        final Object this$isCapital = this.getIsCapital();
        final Object other$isCapital = other.getIsCapital();
        if (this$isCapital == null ? other$isCapital != null : !this$isCapital.equals(other$isCapital)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof City;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $country = this.getCountry();
        result = result * PRIME + ($country == null ? 43 : $country.hashCode());
        final Object $isCapital = this.getIsCapital();
        result = result * PRIME + ($isCapital == null ? 43 : $isCapital.hashCode());
        return result;
    }

    public String toString() {
        return "City(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", country=" + this.getCountry() + ", isCapital=" + this.getIsCapital() + ")";
    }
}
