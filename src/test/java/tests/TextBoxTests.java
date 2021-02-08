package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TextBoxTests {
    @BeforeAll
    static public void config() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {

        step("Open main page", () -> {
                    final String url = "https://demoqa.com/text-box";
                    open(url);
                    Allure.link("Testing", url);
                });

        step("Fill the forms", () -> {
                    $("#userName").val("Rodrigo");
                    $("#userEmail").val("daSilva@company.com");
                    $("#currentAddress").val("Oslo");
                    $("#permanentAddress").val("Street 1");
                    $("#submit").click();
                });

        step("Verify text as expected", () -> {
                    $("#output").shouldHave(text("Name:Rodrigo\n" +
                            "Email:daSilva@company.com\n" +
                            "Current Address :Oslo\n" +
                            "Permanent Address :Street 1"));
                });
    }

    @Test
    void fillFormWithVariablesTest() {
        String userName = "John",
                email = "Doe@firm.com",
                currentAddress = "Oslo",
                permanentAddress = "Street 1";


        step("Open main page", () -> {
            final String url = "https://demoqa.com/text-box";
            open(url);
            Allure.link("Testing", url);
        });

        step("Fill the forms", () -> {
            $("#userName").val(userName);
            $("#userEmail").val(email);
            $("#currentAddress").val(currentAddress);
            $("#permanentAddress").val(permanentAddress);
            $("#submit").click();
        });

        step("Verify text as expected", () -> {
            $("#output").shouldHave(text(
                    "Name:" + userName + "\n" +
                            "Email:Doe@firm.com\n" +
                            "Current Address :Oslo\n" +
                            "Permanent Address :Street 1"));
        });

        step("Verify format as expected", () -> {
            $("#output").shouldHave(text(String.format(
                    "Name:%s\n" +
                            "Email:%s\n" +
                            "Current Address :Oslo\n" +
                            "Permanent Address :Street 1", userName, email)));
        });

        step("Verify user name", () -> {
            $("#name").shouldHave(text(userName));
        });
    }


    @Test
    void wrongEmailTest() {
        step("Open main page", () -> {
            final String url = "https://demoqa.com/text-box";
            open(url);
            Allure.link("Testing", url);
        });

        step("Fill the forms with unexpected email", () -> {
            $("#userName").val("John");
            $("#userEmail").val("Doe");
            $("#currentAddress").val("Montenegro");
            $("#permanentAddress").val("Street 1");
            $("#submit").click();
        });

        step("Verify user email has error alert", () -> {
            $("#userEmail").shouldHave(cssClass("field-error"));
        });

    }
}