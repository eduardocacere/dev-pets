package br.com.pets.api.enums;

public enum GenderEnum {
    MALE(0),
    FEMALE(1);

    private final Integer genderEnum;

    GenderEnum(Integer value){
        genderEnum = value;
    }

    public Integer getValue() {
        return genderEnum;
    }
}
