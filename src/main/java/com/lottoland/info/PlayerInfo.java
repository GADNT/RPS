package com.lottoland.info;

/**
 * POJO for the player. </p>
 *
 * @author gabriel.deaconu
 * @since October
 */
public class PlayerInfo {

    private String name;
    private String choice;
    private Integer gameWins;
    private Integer gameDraw;

    public PlayerInfo() {
    }

    public PlayerInfo(String name, String choice, Integer gameWins, Integer gameDraw) {
        this.name = name;
        this.choice = choice;
        this.gameWins = gameWins;
        this.gameDraw = gameDraw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Integer getGameWins() {
        return gameWins;
    }

    public void setGameWins(Integer gameWins) {
        this.gameWins = gameWins;
    }

    public Integer getGameDraw() {
        return gameDraw;
    }

    public void setGameDraw(Integer gameDraw) {
        this.gameDraw = gameDraw;
    }
}
