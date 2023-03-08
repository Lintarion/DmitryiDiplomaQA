package ru.netology.data;

import lombok.Value;

public class DataHelper {

        static DataGenerator dataGenerator = new DataGenerator();

        @Value
        public static class CardInfo {
            String cardNumber;
            String year;
            String month;
            String owner;
            String cvc;
        }

        public static CardInfo getApprovedCardInfo() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getDeclinedCardInfo() {
            return new CardInfo(DataGenerator.getDeclinedCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getInvalidCardInfo() {
            return new CardInfo(DataGenerator.getInvalidCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getNotFullCardInfo() {
            return new CardInfo(DataGenerator.getNotFullCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getCardZeroNumber() {
            return new CardInfo(DataGenerator.getZeroCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getExpiredMonthCardInfo() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getCurrentYear().getYear(), dataGenerator.getExpiredMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getInvalidMonthCardInfo() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getCurrentYear().getYear(), dataGenerator.getInvalidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getZeroMonthCardInfo() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getCurrentYear().getYear(), dataGenerator.getZeroMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getExpiredYearCardInfo() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getExpiredYear().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getZeroYearCardInformation() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getZeroYear().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getInvalidCvc() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getInvalidCvc());
        }

        public static CardInfo getZeroCvc() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getZeroCvc());
        }

        public static CardInfo getEmptyCardInfo() {
            return new CardInfo(" ", " ", " ", " ", " ");
        }

        public static CardInfo getEmptyCardNumber() {
            return new CardInfo(" ", dataGenerator.getCurrentYear().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getEmptyMonth() {
            return new CardInfo(dataGenerator.getApprovedCardNumber(), dataGenerator.getCurrentYear().getYear(), "", DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getEmptyYear() {
            return new CardInfo(dataGenerator.getApprovedCardNumber(), "", dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
        }

        public static CardInfo getEmptyOwner() {
            return new CardInfo(dataGenerator.getApprovedCardNumber(), dataGenerator.getCurrentYear().getYear(), dataGenerator.getValidMonth().getMonth(), "", DataGenerator.getValidCvc());
        }

        public static CardInfo getEmptyCvc() {
            return new CardInfo(dataGenerator.getApprovedCardNumber(), dataGenerator.getCurrentYear().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getValidOwner(), "");
        }

        public static CardInfo getInvalidOwnerCard() {
            return new CardInfo(DataGenerator.getApprovedCardNumber(), dataGenerator.getValidExpirationDate().getYear(), dataGenerator.getValidMonth().getMonth(), DataGenerator.getInvalidOwner(), DataGenerator.getValidCvc());
        }

    }
