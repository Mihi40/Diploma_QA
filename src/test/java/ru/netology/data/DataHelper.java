package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    private static String getCardNumber(String card) {
        if (card.equalsIgnoreCase("approved")) {
            return "4444 4444 4444 4441";
        } else if (card.equalsIgnoreCase("declined")) {
            return "4444 4444 4444 4442";
        } else return card;
    }

    private static String generateRandomMonth() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM");
        String month = LocalDate.now().plusMonths(5).format(format);
        return month;
    }

    private static String generateRandomYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String year = LocalDate.now().plusYears(2).format(format);
        return year;
    }

    private static String generateRandomName(String locale) {
        var faker = new Faker(new Locale(locale));
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    private static String generateRandomCvc() {
        String cvc = Integer.toString(faker.number().numberBetween(100, 999));
        return cvc;
    }

    public CardInfo getValidCardInfo(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomName("en"),
                generateRandomCvc());
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String name;
        String cvc;
    }

    private static String generateInvalidCardNumber() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en"), new RandomService());
        String cardNumber = fakeValuesService.numerify("000000000000000");
        return cardNumber;
    }

    private static String generateSpecSymbolsString() {
        String specSymbolsString = "*&^";
        return specSymbolsString;
    }

    private static String generateInvalidMonth() {
        String month = Integer.toString(faker.number().numberBetween(13, 99));
        return month;
    }

    private static String generateOneNumber() {
        String oneNumber = Integer.toString(faker.number().numberBetween(0, 9));
        return oneNumber;
    }

    private static String generateLetters() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en"), new RandomService());
        String letters = fakeValuesService.letterify("???");
        return letters;
    }

    private static String generateInvalidYear() {
        String year = Integer.toString(faker.number().numberBetween(10, 22));
        return year;
    }

    private String generateInvalidCvc() {
        String cvc = Integer.toString(faker.number().numberBetween(00, 99));
        return cvc;
    }

    public static CardInfo getInvalidCardInfoWithEmptyCardNumber() {
        return new CardInfo(
                " ",
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithEmptyMonth(String card) {
        return new CardInfo(
                getCardNumber(card),
                " ",
                generateRandomYear(),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithEmptyYear(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateRandomMonth(),
                " ",
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithEmptyName(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateRandomMonth(),
                generateRandomYear(),
                " ",
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithEmptyCvc(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomName("en"),
                " ");
    }

    public static CardInfo getInvalidCardInfoWithInvalidCardNumber() {
        return new CardInfo(
                generateInvalidCardNumber(),
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithInvalidMonth(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateInvalidMonth(),
                generateRandomYear(),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithInvalidYear(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateRandomMonth(),
                generateInvalidYear(),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithInvalidCvc(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateRandomMonth(),
                generateRandomYear(),
                generateRandomName("en"),
                generateOneNumber());
    }
}
