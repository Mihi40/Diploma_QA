package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class DebitCardTest {
    PaymentPage paymentPage;

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
    }

    @BeforeEach
    void setUp() {
        paymentPage = open("http://localhost:8080", PaymentPage.class);
    }

    @Test
    void successPayment() {
        var cardInfo = new DataHelper().getValidCardInfo("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifySuccessMessage();
        assertEquals ("APPROVED", SQLHelper.getDebitStatus());
    }

    @Test
    void unSuccessPaymentWithEmptyCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCardNumber();
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithEmptyMonthNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyMonth("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormatMonth();
    }

    @Test
    void unSuccessPaymentWithEmptyYearNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyYear("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithEmptyName() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyName("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyNullName();
    }

    @Test
    void unSuccessPaymentWithEmptyCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCvc("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithInvalidCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCardNumber();
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithInvalidMonthNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidMonth("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormatMonth();
    }
    @Test
    void unSuccessPaymentWithInvalidYearNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidYear("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormatYear();
    }

       @Test
    void unSuccessPaymentWithInvalidCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCvc("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }
    @Test
    void unSuccessPaymentDeclinedCard() {
        var cardInfo = new DataHelper().getValidCardInfo("declined");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyErrorMessage();
        assertEquals ("DECLINED", SQLHelper.getDebitStatus());
    }

}