package edu.fiuba.algo3.modelo;

<<<<<<< HEAD
import java.util.ArrayList;

public class Player {
    private String name;
    private String color;
    private ArrayList dominatedCountries = new ArrayList();

    public Player(String color) {
        this.color = color;
        //this.name = name;
    }
    public int cantidadPaises(){
        return dominatedCountries.size();
    }
    public void addCountry(Country country){
        dominatedCountries.add(country);
    }

    public Player dominatedCountry(Country country){
        boolean isDominated = dominatedCountries.contains(country);
        if(isDominated){
            return this;
        }
        return null;
=======
import edu.fiuba.algo3.modelo.exceptions.*;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private Color color;
    private ArrayList <Country>dominatedCountries;

    public Player(Color color) {
        this.color = color;
        dominatedCountries = new ArrayList();
    }

    private void checkValidCountryParameter(Country country) throws EmptyCountryParameterException {
        if(country == null) {
            throw new EmptyCountryParameterException();
        }
    }

    public void checkPlayerOwnsCountry(Country country) throws NonExistentCountry {
        if(dominatedCountries.contains(country) == false){
            throw new NonExistentCountry();
        }
    }

    public Integer amountOfDominatedCountries(){
        return dominatedCountries.size();
    }

    public void addCountry(Country country) throws EmptyCountryParameterException {
        checkValidCountryParameter(country);
        dominatedCountries.add(country);
        country.addArmy(1);
    }

    public void addArmyinCountry(int amount, Country country) throws EmptyCountryParameterException, NonExistentCountry {
        checkValidCountryParameter(country);
        checkPlayerOwnsCountry(country);
        if(dominatedCountries.contains(country)){
            country.addArmy(amount);
        }

    }

    public boolean isSearchedCountry (Country country) throws EmptyCountryParameterException {
        checkValidCountryParameter(country);
        for( Country eachCountry: dominatedCountries ){
            if(eachCountry == country){
                return true;
            }
        }
        return false;
    }

    public boolean removeArmy(Integer lostArmy, Country country) throws EmptyCountryParameterException, NonExistentCountry {
        checkValidCountryParameter(country);
        checkPlayerOwnsCountry(country);

        country.removeArmy(lostArmy);
        if(country.stillHasArmy() == false){
            removeCountry(country);
            return true;
        }
        return false;
    }

    public boolean canInvade(Country country, int amountDice) throws EmptyCountryParameterException, NonExistentCountry {
        checkValidCountryParameter(country);
        checkPlayerOwnsCountry(country);

        if(country.canInvade(amountDice)){
            return true;
        }
        return false;
    }

    public void removeCountry(Country country){
        dominatedCountries.remove(country);
    }

    public void firstPlacementRound(Integer maxPlacement) { //Countries and amount of army is chosen randomly
        //Random rand = new Random();

        Country countryToAdd = dominatedCountries.get(0);
        countryToAdd.addArmy(maxPlacement);

        /*while(maxPlacement > 0){

            Integer randIndex = rand.nextInt(dominatedCountries.size());
            Integer randIndex2 = rand.nextInt(maxPlacement) + 1;

            Country countryToAdd = dominatedCountries.get(randIndex);
            countryToAdd.addArmy(randIndex2);

            maxPlacement -= randIndex2;
        }*/
    }


    public void setDominatedCountries(ArrayList<Country> continent) throws EmptyCountryParameterException {
        for(Country country : continent){
            addCountry(country);
        }
    }

    public Country getADominatedCountry() {
        return(dominatedCountries.get(0));
    }

    public boolean correctAmountOfCountries( Integer expectedAmount){
        return (dominatedCountries.size() == expectedAmount);
    }

    public boolean correctAmountOfArmy( Integer expectedAmount){
        Integer amount = 0;
        for(Country eachCountry: dominatedCountries){
            amount = amount + eachCountry.getArmyAmount();
        }
        return (amount == expectedAmount);
    }

    public boolean dominatedCountry(Country country) throws EmptyCountryParameterException {
        checkValidCountryParameter(country);
        return dominatedCountries.contains(country);
    }

    public boolean correctAmountOfArmyInCountry(Country country, Integer expectedAmount) throws EmptyCountryParameterException, NonExistentCountry {
        checkValidCountryParameter(country);
        checkPlayerOwnsCountry(country);
        return country.correctAmountOfArmyInCountry(expectedAmount);
>>>>>>> develop
    }
}
