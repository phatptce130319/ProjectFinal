package entity;

public enum Gender {
    //An enum use for mapping to combo box to select gender
    FEMALE("Female", "Female"), MALE("Male", "Male");
    //Code to choose type
    private String code;
    private String text;

    Gender(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    //If choose a code, the type is selected
    public static Gender getByCode(String genderCode) {
        for (Gender g : Gender.values()) {
            if (g.code.equals(genderCode)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
