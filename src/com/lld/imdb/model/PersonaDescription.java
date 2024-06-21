package com.lld.imdb.model;

public class PersonaDescription {
    private Persona persona;
    // The description for a persona can change for any show
    // hence, this field is kept outside the `Persona` entity
    private String description;

    public PersonaDescription(Persona persona) {
        this.persona = persona;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
