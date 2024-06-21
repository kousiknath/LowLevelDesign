package com.lld.imdb.model;

import com.lld.imdb.constant.Gender;
import com.lld.imdb.constant.PersonaType;

import java.util.List;

public class Persona {
    private String name;
    // A single persona wear multiple hats like an actor,
    // director, scriptwriter etc.
    private List<PersonaType> type;
    private Gender gender;

    public Persona(String name, Gender gender) {
        this.name = name;
        this.gender = Gender.MALE;
    }

    public List<PersonaType> getType() {
        return type;
    }

    public void setType(List<PersonaType> type) {
        this.type = type;
    }
}
