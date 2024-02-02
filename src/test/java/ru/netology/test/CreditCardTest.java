package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class CreditCardTest {

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
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifySuccessMessage();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    void unSuccessPaymentWithEmptyCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCardNumber();
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithNullCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNullCardNumber();
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithEmptyMonthNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyMonth("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormatMonth();
    }

    @Test
    void unSuccessPaymentWithEmptyYearNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyYear("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithEmptyName() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyName("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyNullName();
    }

    @Test
    void unSuccessPaymentWithNumberAndSymBolName() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNumberAndSymBolName("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyNullName();
    }

    @Test
    void unSuccessPaymentWithEmptyCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCvc("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithNullCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNullCvc("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithInvalidCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCardNumber();
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentWithInvalidMonthNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidMonth("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormatMonth();
    }

    @Test
    void unSuccessPaymentWithInvalidYearNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidYear("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormatYear();
    }

    @Test
    void unSuccessPaymentWithInvalidCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCvc("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    void unSuccessPaymentDeclinedCard() {
        var cardInfo = new DataHelper().getValidCardInfo("declined");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyErrorMessage();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }
}