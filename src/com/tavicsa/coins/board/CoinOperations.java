package com.tavicsa.coins.board;

import com.tavicsa.coins.Coin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CoinOperations {
    private List<Coin> coins = new ArrayList<>();

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    List listByCountry(String countryName) {
        return coins.stream()
                .filter(coin -> coin.getCountry().equals(countryName))
                .collect(Collectors.toList());
    }

    List listByYear(int year) {
        return coins.stream()
                .filter(coin -> coin.getMintingYear() == year)
                .collect(Collectors.toList());

    }

    List listByCurrentvalue(int currentValue) {
        return coins.stream()
                .filter(coin -> coin.getCurrentValue() <= currentValue)
                .sorted(Comparator.comparingInt(Coin::getCurrentValue))
                .collect(Collectors.toList());
    }

    List searchByCountryAndDenomination(String countryName, int denomination) {
        return coins.stream()
                .filter(coin -> coin.getCountry().equals(countryName) && coin.getDenomination() == denomination)
                .collect(Collectors.toList());
    }

    List searchByCountryAndYearOfMinting(String countryName, int mintingYear) {
        return coins.stream()
                .filter(coin -> coin.getCountry().equals(countryName) && coin.getMintingYear() == mintingYear)
                .collect(Collectors.toList());
    }

    List searchByCountryAndDenominationAndYearOfMinting(String countryName, int denomination, int mintingYear) {
        return coins.stream()
                .filter(coin -> coin.getCountry().equals(countryName)
                        && coin.getDenomination() == denomination
                        && coin.getMintingYear() == mintingYear)
                .collect(Collectors.toList());
    }

    List searchByCountryAndAcquiredDate(String countryName, Date acquiredDate) {
        return coins.stream()
                .filter(coin -> coin.getCountry().equals(countryName) && coin.getAcquiredDate().equals(acquiredDate))
                .collect(Collectors.toList());
    }
}