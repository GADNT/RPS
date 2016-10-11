package com.lottoland;

import com.lottoland.info.PlayerInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Class for Junit tests. </p>
 *
 * @author gabriel.deaconu
 * @since October
 */
public class RockPaperScissorsApplicationTest {

    private static RockPaperScissorsApplication rockPaperScissorsApplication;

    @BeforeClass
    public static void initRockPaperScissorsApplication() {
        rockPaperScissorsApplication = new RockPaperScissorsApplication();
    }

    private PlayerInfo constructPlayerRock() {
        return new PlayerInfo("Michael", PlayerChooseEnum.ROCK.getCode(), 0, 0);
    }

    private PlayerInfo constructPlayerPaper() {
        return new PlayerInfo("Michael", PlayerChooseEnum.PAPER.getCode(), 0, 0);
    }

    private PlayerInfo constructPlayerScissors() {
        return new PlayerInfo("Michael", PlayerChooseEnum.SCISSORS.getCode(), 0, 0);
    }

    private PlayerInfo constructPlayerNoChoice() {
        return new PlayerInfo("Michael", null, 0, 0);
    }

    private PlayerInfo constructPlayerWrongChoice() {
        return new PlayerInfo("Michael", "B", 0, 0);
    }

    private PlayerInfo constructComputerRock() {
        return new PlayerInfo("Mike-boot", PlayerChooseEnum.ROCK.getCode(), 0, 0);
    }

    private PlayerInfo constructComputerPaper() {
        return new PlayerInfo("Mike-boot", PlayerChooseEnum.PAPER.getCode(), 0, 0);
    }

    private PlayerInfo constructComputerScissors() {
        return new PlayerInfo("Mike-boot", PlayerChooseEnum.SCISSORS.getCode(), 0, 0);
    }

    private PlayerInfo constructComputerNoChoice() {
        return new PlayerInfo("Mike-boot", null, 0, 0);
    }

    private PlayerInfo constructComputerWrongChoice() {
        return new PlayerInfo("Mike-boot", "C", 0, 0);
    }

    private PlayerInfo constructDummyPlayer() {
        PlayerInfo dummyPlayer = new PlayerInfo();
        dummyPlayer.setGameWins(0);
        dummyPlayer.setGameDraw(0);
        dummyPlayer.setChoice(PlayerChooseEnum.valueOf("ROCK").getCode());
        dummyPlayer.setName("DummyUser");

        return dummyPlayer;
    }


    private Object invokePrivateMethod(Object declaringObject, Method method, Object... inputParameterValues)
            throws InvocationTargetException, IllegalAccessException {

        return method.invoke(declaringObject, inputParameterValues);
    }

    private Method accessPrivateDeclaredMethod(Object declaringObject, String methodName, Class... inputParameterClasses)
            throws NoSuchMethodException {

        Method method = declaringObject.getClass().getDeclaredMethod(methodName, inputParameterClasses);
        method.setAccessible(true);

        return method;
    }

    @Test
    public void mainTest() throws Exception {
        PlayerInfo playerInfo = constructDummyPlayer();
        PlayerInfo computer = constructDummyPlayer();

        RockPaperScissorsApplication.main(new String[]{playerInfo.getName(), computer.getName()});

    }

    @Test
    public void playRockPaperScissorsTest() throws Exception {
        PlayerInfo playerInfo = constructDummyPlayer();
        PlayerInfo computer = constructDummyPlayer();

        Method playRockPaperScissorsMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "playRockPaperScissors", PlayerInfo.class, PlayerInfo.class);
        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, playRockPaperScissorsMethod, playerInfo, computer);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player winning status.", playerInfo.getGameWins() > 0);
        Assert.assertTrue("Computer draw status.", playerInfo.getGameDraw() > 0);
    }

    @Test
    public void comparePlayerChoicesWithPlayerRockComputerRockTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerChoicesMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);
        PlayerInfo playerInfo = constructPlayerRock();
        PlayerInfo computerRock = constructComputerRock();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerChoicesMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player should have one draw.", playerInfo.getGameDraw() == 1);
        Assert.assertTrue("Computer should have one draw.", computerRock.getGameDraw() == 1);
        Assert.assertTrue("Both players have choose Rock with id ='1'.", PlayerChooseEnum.ROCK.getId().equals(PlayerChooseEnum.getById(1).getId()));

    }

    @Test
    public void comparePlayerChoicesWithPlayerPaperAndWinsTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerPaperChoicesMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerPaper();
        PlayerInfo computerRock = constructComputerRock();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerPaperChoicesMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player should have one win.", playerInfo.getGameWins() == 1);
        Assert.assertTrue("Computer with not win and no draw.", computerRock.getGameWins() == 0 && computerRock.getGameDraw() == 0);

    }

    @Test
    public void comparePlayerChoicesWithPlayerPaperAndLooseTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerPaperLooseChoicesMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerPaper();
        PlayerInfo computerRock = constructComputerScissors();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerPaperLooseChoicesMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player with not win and no draw.", playerInfo.getGameWins() == 0);
        Assert.assertTrue("Computer should have one win.", computerRock.getGameWins() == 1);

    }

    @Test
    public void comparePlayerChoicesWithPlayerRockAndWinsTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerPaperWinsChoicesMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerRock();
        PlayerInfo computerRock = constructComputerScissors();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerPaperWinsChoicesMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player should win.", playerInfo.getGameWins() == 1);
        Assert.assertTrue("Computer should loose.", computerRock.getGameWins() == 0 && computerRock.getGameDraw() == 0);

    }

    @Test
    public void comparePlayerChoicesWithPlayerScissorsAndLooseTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerScissorsLooseChoicesMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerScissors();
        PlayerInfo computerRock = constructComputerRock();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerScissorsLooseChoicesMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player should loose.", playerInfo.getGameWins() == 0 && playerInfo.getGameDraw() == 0);
        Assert.assertTrue("Computer should win.", computerRock.getGameWins() == 1 && computerRock.getGameDraw() == 0);

    }

    @Test
    public void comparePlayerChoicesWithPlayerScissorsAndWinsTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerScissorsWinsChoicesMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerScissors();
        PlayerInfo computerRock = constructComputerPaper();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerScissorsWinsChoicesMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player should win with 'S' .", playerInfo.getGameWins() == 1 && playerInfo.getGameDraw() == 0);
        Assert.assertTrue("Computer should loose with 'P'.", computerRock.getGameWins() == 0 && computerRock.getGameDraw() == 0);

    }

    @Test
    public void comparePlayerChoicesWithPlayerRockAndLooseTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerRockLooseChoicesMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerRock();
        PlayerInfo computerRock = constructComputerPaper();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerRockLooseChoicesMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

        Assert.assertTrue("Player should loose with 'R' .", playerInfo.getGameWins() == 0 && playerInfo.getGameDraw() == 0);
        Assert.assertTrue("Computer should win with 'P'.", computerRock.getGameWins() == 1);

    }

    @Test
    public void comparePlayerChoicesWithPlayerAndComputerNoChoiceTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerAndComputerNoChoiceMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerNoChoice();
        PlayerInfo computerRock = constructComputerNoChoice();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerAndComputerNoChoiceMethod, playerInfo, computerRock);

        Assert.assertNull(privateMethod);

    }

    @Test
    public void comparePlayerChoicesWithPlayerAndComputerWrongChoiceTests() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructComparePlayerAndComputerWrongChoiceMethod = accessPrivateDeclaredMethod(rockPaperScissorsApplication, "comparePlayerChoices", PlayerInfo.class, PlayerInfo.class);

        PlayerInfo playerInfo = constructPlayerWrongChoice();
        PlayerInfo computerRock = constructComputerWrongChoice();

        Object privateMethod = invokePrivateMethod(rockPaperScissorsApplication, constructComparePlayerAndComputerWrongChoiceMethod, playerInfo, computerRock);

    }
}