package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    Integer firstPlayerNumber = 1;
    Integer secondPlayerNumber = 2;
    Integer thirdPlayerNumber = 3;
    @Test
    public void assignCardNoCountriesLeft() throws NonExistentPlayer, InvalidNumberOfPlayers, EmptyCountryParameterException, IOException, NonExistentCountry {
        Integer numberOfPlayers = 2;

        Integer expectedAmountOfCountries = 25;

        Game game = new Game(numberOfPlayers);
        game.dealCountryCards();

        assertEquals(true, game.correctAmountOfCountries(firstPlayerNumber, expectedAmountOfCountries ));
    }

    @Test
    public void assignCountryCardsWithLeftCountries() throws NonExistentPlayer, InvalidNumberOfPlayers, EmptyCountryParameterException, IOException, NonExistentCountry {
        Integer numberOfPlayers = 3;

        Integer expectedAmountOfCountriesFirstPlayer = 17;
        Integer expectedAmountOfCountriesSecondPlayer = 17;
        Integer expectedAmountOfCountriesThirdPlayer = 16;

        Game game = new Game(numberOfPlayers);
        game.dealCountryCards();

        assertEquals(true, game.correctAmountOfCountries(firstPlayerNumber, expectedAmountOfCountriesFirstPlayer ));
        assertEquals(true, game.correctAmountOfCountries(secondPlayerNumber, expectedAmountOfCountriesSecondPlayer ));
        assertEquals(true, game.correctAmountOfCountries(thirdPlayerNumber, expectedAmountOfCountriesThirdPlayer ));
    }

    @Test
    public void successFullAttack() throws EmptyCountryParameterException, NonExistentPlayer, InvalidNumberOfPlayers, NonExistentCountry, IOException, InvalidAttack {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);

        Country francia = new Country("Francia");
        Country alemania = new Country("Alemania");

        game.addCountryToPlayer(francia,firstPlayerNumber);
        game.addCountryToPlayer(alemania,secondPlayerNumber);

        game.playersSetArmies(5, francia);
        game.playersSetArmies(1, alemania);

        game.attack(francia, 3,alemania);

        assertEquals(false, (alemania.hasALargerArmy(2)));
    }
    @Test
    public void attackerCountryConquers() throws EmptyCountryParameterException, NonExistentPlayer, InvalidNumberOfPlayers, NonExistentCountry, IOException, InvalidAttack {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);

        Country francia = new Country("Francia");
        Country alemania = new Country("Alemania");

        game.addCountryToPlayer(francia,firstPlayerNumber);
        game.addCountryToPlayer(alemania,secondPlayerNumber);

        game.playersSetArmies(5, francia);
        game.attack(francia, 1,alemania);

        assertEquals(true, game.playerDominatedCountry(firstPlayerNumber,alemania));
    }

    @Test
    public void gameWithoutEnoughPlayersRaisesException(){
        Integer numberOfPlayers = 1;
        assertThrows(InvalidNumberOfPlayers.class, () -> new Game(numberOfPlayers));
    }

    @Test
    public void gameWithTooManyPlayersRaisesException(){
        Integer numberOfPlayers = 8;
        assertThrows(InvalidNumberOfPlayers.class, () -> new Game(numberOfPlayers));
    }

    @Test
    public void attackerAmountOfDicesIsIncorrectRaisesException() throws InvalidNumberOfPlayers, IOException, NonExistentPlayer, NonExistentCountry, EmptyCountryParameterException {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);

        Country francia = new Country("Francia");
        Country alemania = new Country("Alemania");

        game.addCountryToPlayer(francia,firstPlayerNumber);
        game.addCountryToPlayer(alemania,secondPlayerNumber);

        assertThrows(InvalidAttack.class, () -> game.attack(francia,3,alemania));
    }

    @Test
    public void nonExistentPlayerRaisesException() throws InvalidNumberOfPlayers, IOException {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);
        Country francia = new Country("Francia");

        assertThrows(NonExistentPlayer.class, () -> game.addCountryToPlayer(francia,thirdPlayerNumber));
    }

    @Test
    public void nonExistentCountryRaisesException() throws InvalidNumberOfPlayers, IOException {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);
        Country croacia = new Country("Croacia");

        assertThrows(NonExistentCountry.class, () -> game.addCountryToPlayer(croacia,thirdPlayerNumber));
    }

    @Test
    public void emptyCountryParameterRaisesException() throws InvalidNumberOfPlayers, IOException {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);

        assertThrows(EmptyCountryParameterException.class, () -> game.addCountryToPlayer(null,secondPlayerNumber));
    }

    @Test
    public void emptyContinentParameterRaisesException() throws InvalidNumberOfPlayers, IOException {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);

        assertThrows(EmptyContinentParameterException.class, () -> game.addContinentToPlayer(secondPlayerNumber,null));
    }

    @Test
    public void oneRoundGameCountryPlacement() throws InvalidNumberOfPlayers, IOException, NonExistentCountry, EmptyCountryParameterException, NonExistentPlayer {
        Integer numberOfPlayers = 2;

        Integer armyToPlace = 5;
        Integer expectedAmount = 30;

        Game game = new Game(numberOfPlayers);

        game.dealCountryCards();
        game.placementRound(armyToPlace);

        assertEquals(true ,game.correctAmountOfArmy(firstPlayerNumber, expectedAmount));
    }

    @Test
    public void oneRoundGameCountryPlacementAndSecondPlayerControlsAsia() throws InvalidNumberOfPlayers, IOException, NonExistentCountry, EmptyCountryParameterException, NonExistentPlayer, NonExistentContinent, EmptyContinentParameterException {
        Integer numberOfPlayers = 3;

        Integer armyToPlace = 8;
        Integer expectedAmount = 23;

        Game game = new Game(numberOfPlayers);
        Continent continent = new Continent("Asia");

        game.addContinentToPlayer(firstPlayerNumber, continent);
        game.addCountryToPlayer(new Country("Suecia"), secondPlayerNumber);
        game.addCountryToPlayer(new Country("Brasil"), thirdPlayerNumber);

        game.placementRound(armyToPlace);

        assertEquals(true ,game.correctAmountOfArmy(firstPlayerNumber, expectedAmount));
    }

    @Test
    public void oneAttackRound() throws InvalidNumberOfPlayers, IOException, NonExistentCountry, EmptyCountryParameterException, NonExistentPlayer {
        Integer numberOfPlayers = 2;

        Game game = new Game(numberOfPlayers);

        Country china = new Country("China");
        Country iran = new Country("Iran");
        Country siberia = new Country("Siberia");
        Country gobi = new Country("Gobi");

        game.addCountryToPlayer(china, firstPlayerNumber);
        game.addCountryToPlayer(iran, secondPlayerNumber);
        game.addCountryToPlayer(siberia, secondPlayerNumber);
        game.addCountryToPlayer(gobi, secondPlayerNumber);
        game.playersSetArmies(5, china);

        game.attackRound();
        game.attackRound();

        assertEquals(true ,game.playerDominatedCountry(firstPlayerNumber, iran));
        assertEquals(true ,game.playerDominatedCountry(firstPlayerNumber, siberia));
        assertEquals(false ,game.playerDominatedCountry(secondPlayerNumber, siberia));
        assertEquals(false ,game.playerDominatedCountry(secondPlayerNumber, iran));
    }
/*
    @Test
    public void pruebaBuffer() throws InvalidNumberOfPlayers, IOException {
        Integer numberOfPlayers = 2;
        Game game = new Game(numberOfPlayers);
    }
*/
    @Test
    public void correctAmountOfCountryCards() throws InvalidNumberOfPlayers, IOException {
        Integer numberOfPlayers = 2;
        Game game = new Game(numberOfPlayers);
        assertEquals(true ,game.correctRemainingNumberOfCountryCards(50));
    }

    @Test
    public void conquerAndWinACountryCard() throws InvalidNumberOfPlayers, IOException, NonExistentPlayer, NonExistentCountry, EmptyCountryParameterException, InvalidAttack {

        Integer numberOfPlayers = 2;
        Integer expectedAmount = 1;

        Game game = new Game(numberOfPlayers);

        Country francia = new Country("Francia");
        Country alemania = new Country("Alemania");

        game.addCountryToPlayer(francia,firstPlayerNumber);
        game.addCountryToPlayer(alemania,secondPlayerNumber);

        game.playersSetArmies(5, francia);
        game.attack(francia, 1,alemania);

        assertEquals(true, game.playerDominatedCountry(firstPlayerNumber,alemania));
        assertEquals(true, game.correctAmountOfCountryCards(firstPlayerNumber, expectedAmount));
        assertEquals(true, game.correctRemainingNumberOfCountryCards(49));

    }

    @Test
    public void playerRegroupThreeArmies() throws InvalidNumberOfPlayers, IOException, NonExistentPlayer, NonExistentCountry, EmptyCountryParameterException, InvalidAttack {

        Integer numberOfPlayers = 2;
        Integer armyToRegroup = 3;
        Integer expectedAmountGermany = 3;
        Integer expectedAmountPoland = 4;

        Game game = new Game(numberOfPlayers);

        Country francia = new Country("Francia");
        Country polonia = new Country("Polonia");
        Country alemania = new Country("Alemania");

        game.addCountryToPlayer(alemania,firstPlayerNumber);
        game.addCountryToPlayer(polonia,firstPlayerNumber);

        game.addCountryToPlayer(francia,secondPlayerNumber);


        game.playersSetArmies(5, alemania);
        game.regroup(firstPlayerNumber, alemania, polonia, armyToRegroup);

        assertEquals(true, game.playerDominatedCountry(firstPlayerNumber,alemania));
        assertEquals(true, game.playerDominatedCountry(firstPlayerNumber,polonia));
        assertEquals(true, game.correctAmountOfArmyInCountry(firstPlayerNumber, alemania, expectedAmountGermany));
        assertEquals(true, game.correctAmountOfArmyInCountry(firstPlayerNumber, polonia, expectedAmountPoland));


    }

    @Test
    public void iorrectRegroupOfPlayer() throws InvalidNumberOfPlayers, IOException, NonExistentPlayer, NonExistentCountry, EmptyCountryParameterException, InvalidAttack {

        Integer numberOfPlayers = 2;
        Integer armyToRegroup = 3;
        Integer expectedAmountGermany = 3;
        Integer expectedAmountPoland = 4;

        Game game = new Game(numberOfPlayers);

        Country islandia = new Country("Islandia");
        Country suecia = new Country("Suecia");
        Country rusia = new Country("Rusia");
        Country polonia = new Country("Polonia");
        Country alemania = new Country("Alemania");

        game.addCountryToPlayer(islandia,firstPlayerNumber);
        game.addCountryToPlayer(suecia,firstPlayerNumber);
        game.addCountryToPlayer(rusia,firstPlayerNumber);
        game.addCountryToPlayer(polonia,firstPlayerNumber);

        game.addCountryToPlayer(alemania,secondPlayerNumber);


        game.playersSetArmies(5, islandia);
        game.regroup(firstPlayerNumber, islandia, polonia, armyToRegroup);


        assertEquals(true, game.correctAmountOfArmyInCountry(firstPlayerNumber, islandia, expectedAmountGermany));
        assertEquals(true, game.correctAmountOfArmyInCountry(firstPlayerNumber, polonia, expectedAmountPoland));


    }

    @Test
    public void incorrectRegroupOfPlayer() throws InvalidNumberOfPlayers, IOException, NonExistentPlayer, NonExistentCountry, EmptyCountryParameterException, InvalidAttack {

        Integer numberOfPlayers = 2;
        Integer armyToRegroup = 3;
        Integer expectedAmountGermany = 3;
        Integer expectedAmountPoland = 4;

        Game game = new Game(numberOfPlayers);

        Country islandia = new Country("Islandia");
        Country suecia = new Country("Suecia");

        Country polonia = new Country("Polonia");
        Country alemania = new Country("Alemania");

        game.addCountryToPlayer(islandia,firstPlayerNumber);
        game.addCountryToPlayer(suecia,firstPlayerNumber);

        game.addCountryToPlayer(polonia,firstPlayerNumber);

        game.addCountryToPlayer(alemania,secondPlayerNumber);


        game.playersSetArmies(5, islandia);
        game.regroup(firstPlayerNumber, islandia, polonia, armyToRegroup);


        assertEquals(false, game.correctAmountOfArmyInCountry(firstPlayerNumber, islandia, expectedAmountGermany));
        assertEquals(false, game.correctAmountOfArmyInCountry(firstPlayerNumber, polonia, expectedAmountPoland));


    }




}
