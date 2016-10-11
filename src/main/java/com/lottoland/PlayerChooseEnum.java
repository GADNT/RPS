package com.lottoland;

/**
 * @author gabriel.deaconu
 * @since October
 */
public enum PlayerChooseEnum {

    ROCK(1, "R"),
    PAPER(2, "P"),
    SCISSORS(3, "S");

    private Integer id;
    private String code;

    PlayerChooseEnum(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public static PlayerChooseEnum getById(Integer id) {
        PlayerChooseEnum chooseEnum = null;

        for (int i = 0; i < values().length && chooseEnum == null; i++) {
            PlayerChooseEnum otherChooseEnum = values()[i];

            if (otherChooseEnum.getId().equals(id)) {
                chooseEnum = otherChooseEnum;
            }
        }

        return chooseEnum;
    }
}
