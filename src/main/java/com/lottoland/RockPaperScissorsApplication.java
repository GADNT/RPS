package com.lottoland;

import com.lottoland.info.PlayerInfo;

import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Main class for Rock-Paper-Scissors game.
 *
 * @author gabriel.deaconu
 * @since October
 */
public class RockPaperScissorsApplication {

    public static void main(String[] args) {
        PlayerInfo computer = new PlayerInfo("Niklas", PlayerChooseEnum.ROCK.getCode(), 0, 0);
        PlayerInfo player = new PlayerInfo("Marco", null, 0, 0);

        RockPaperScissorsApplication.playRockPaperScissors(player, computer);

    }

    /**
     * Method that execute <rps> game and give the status.
     *
     * @param player   the player object
     * @param computer the computer-player object
     */
    private static void playRockPaperScissors(PlayerInfo player, PlayerInfo computer) {

        for (int index = 0; index < 100; index++) {
            IntStream intStream = ThreadLocalRandom.current().ints(PlayerChooseEnum.ROCK.getId(), PlayerChooseEnum.SCISSORS.getId() + 1);
            OptionalInt firstRandomValue = intStream.findFirst();

            if (firstRandomValue.isPresent()) {
                player.setChoice(PlayerChooseEnum.getById(firstRandomValue.getAsInt()).getCode());
            }

            comparePlayerChoices(player, computer);

        }

        System.out.println("Player situation: " + player.getName() + " ,wins= " + player.getGameWins() + " ,draw= " + player.getGameDraw() + ";");
        System.out.println("Computer situation: " + computer.getName() + " ,wins= " + computer.getGameWins() + " ,draw= " + computer.getGameDraw() + ";");

    }

    /**
     * Method used to check player choices and update status for every player.</p>
     *
     * @param player         the player object
     * @param computerPlayer the player object represented as computer
     */
    private static void comparePlayerChoices(PlayerInfo player, PlayerInfo computerPlayer) {

        if (player != null && computerPlayer != null) {

            //extract status for player choice
            String computerChoice = computerPlayer.getChoice();
            String playerChoice = player.getChoice();

            //extract current status for draw
            Integer gameDraw = player.getGameDraw();
            Integer computerPlayerGameDraw = computerPlayer.getGameDraw();

            //extract current status about winning
            Integer playerGameWin = player.getGameWins();
            Integer computerGameWin = computerPlayer.getGameWins();

            if (playerChoice != null && computerChoice != null) {
                //1.same choice for both players
                if (playerChoice.equals(computerChoice)) {
                    player.setGameDraw(++gameDraw);
                    computerPlayer.setGameDraw(++computerPlayerGameDraw);

                }
                //2.based on player random choice check against computer choice
                switch (playerChoice) {
                    case "R":
                        if (computerChoice.equals(PlayerChooseEnum.PAPER.getCode())) {
                            computerPlayer.setGameWins(++computerGameWin);

                        } else if (computerChoice.equals(PlayerChooseEnum.SCISSORS.getCode())) {
                            player.setGameWins(++playerGameWin);
                        }

                        break;

                    case "P":
                        if (computerChoice.equals(PlayerChooseEnum.ROCK.getCode())) {
                            player.setGameWins(++playerGameWin);

                        } else if (computerChoice.equals(PlayerChooseEnum.SCISSORS.getCode())) {
                            computerPlayer.setGameWins(++computerGameWin);
                        }

                        break;

                    case "S":
                        if (computerChoice.equals(PlayerChooseEnum.PAPER.getCode())) {
                            player.setGameWins(++playerGameWin);

                        } else if (computerChoice.equals(PlayerChooseEnum.ROCK.getCode())) {
                            computerPlayer.setGameWins(++computerGameWin);
                        }

                        break;
                    default:
                        System.out.println("player = [" + player + "], computerPlayer = [" + computerPlayer + "]");
                        break;
                }
            }
        }
    }

}
