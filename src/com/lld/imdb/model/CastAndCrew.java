package com.lld.imdb.model;

import java.util.List;

public class CastAndCrew {
    private String title;
    private List<PersonaDescription> personaDescriptions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PersonaDescription> getPersonaDescriptions() {
        return personaDescriptions;
    }

    public void setPersonaDescriptions(List<PersonaDescription> personaDescriptions) {
        this.personaDescriptions = personaDescriptions;
    }
}
