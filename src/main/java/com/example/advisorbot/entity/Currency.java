package com.example.advisorbot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "iso")
    private String iso;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message = "Введите название")
    private String name;

    @OneToMany(mappedBy = "currency", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Country> countries;

    public Currency(Integer id, String iso, @NotEmpty(message = "Введите название") String name, List<Country> countries) {
        this.id = id;
        this.iso = iso;
        this.name = name;
        this.countries = countries;
    }

    public Currency() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getIso() {
        return this.iso;
    }

    public @NotEmpty(message = "Введите название") String getName() {
        return this.name;
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setName(@NotEmpty(message = "Введите название") String name) {
        this.name = name;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Currency)) return false;
        final Currency other = (Currency) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$iso = this.getIso();
        final Object other$iso = other.getIso();
        if (this$iso == null ? other$iso != null : !this$iso.equals(other$iso)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$countries = this.getCountries();
        final Object other$countries = other.getCountries();
        if (this$countries == null ? other$countries != null : !this$countries.equals(other$countries)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Currency;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $iso = this.getIso();
        result = result * PRIME + ($iso == null ? 43 : $iso.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $countries = this.getCountries();
        result = result * PRIME + ($countries == null ? 43 : $countries.hashCode());
        return result;
    }

    public String toString() {
        return "Currency(id=" + this.getId() + ", iso=" + this.getIso() + ", name=" + this.getName() + ")";
    }
}
