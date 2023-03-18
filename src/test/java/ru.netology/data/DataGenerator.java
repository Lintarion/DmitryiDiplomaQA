package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    LocalDate today = LocalDate.now();
    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
    DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");

    protected static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    protected static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    protected static String getInvalidCardNumber() {
        return "1111 1111 1111 1111";
    }

    protected static String getNotFullCardNumber() {
        return "1111 1111 1111 111";
    }

    protected static String getZeroCardNumber() {
        return "0000 0000 0000 0000";
    }

    protected static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    protected static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    protected static String getValidCvc() {
        return "111";
    }

    protected static String getInvalidCvc() {
        return "23";
    }

    protected static String getZeroCvc() {
        return "000";
    }

    @Value
    protected static class Year {
        String year;
    }

    protected Year getCurrentYear() {
        LocalDate currentYear = LocalDate.now();
        return new Year(yearFormatter.format(currentYear));
    }

    protected Year getValidExpirationDate() {// действительный срок действия карты
        LocalDate newYear = today.plusYears(1);
        return new Year(yearFormatter.format(newYear));
    }

    protected Year getExpiredYear() { //получить просроченный год
        LocalDate newYear = today.minusYears(1);
        return new Year(yearFormatter.format(newYear));
    }


    protected Year getZeroYear() {
        return new Year("00");
    }

    @Value
    protected static class Month {
        String month;
    }

    protected Month getValidMonth() {
        LocalDate newMonth = today.plusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    protected Month getExpiredMonth() {
        LocalDate newMonth = today.minusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    protected Month getInvalidMonth() {
        return new Month("13");
    }

    protected Month getZeroMonth() {
        return new Month("00");
    }


}
