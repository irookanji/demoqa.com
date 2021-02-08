package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {
    @BeforeAll
    static public void config() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {

        open("https://demoqa.com/text-box");

        $("#userName").val("Rodrigo");
        $("#userEmail").val("daSilva@company.com");
        $("#currentAddress").val("Oslo");
        $("#permanentAddress").val("Street 1");
        $("#submit").click();

        $("#output").shouldHave(text("Name:Rodrigo\n" +
                "Email:daSilva@company.com\n" +
                "Current Address :Oslo\n" +
                "Permananet Address :Street 1"));
    }

    @Test
    void fillFormWithVariablesTest() {
        String userName = "John",
                email = "Doe@firm.com",
                currentAddress = "Oslo",
                permanentAddress = "Street 1";


        open("https://demoqa.com/text-box");

        $("#userName").val(userName);
        $("#userEmail").val(email);
        $("#currentAddress").val(currentAddress);
        $("#permanentAddress").val(permanentAddress);
        $("#submit").click();

        $("#output").shouldHave(text(
                "Name:" + userName + "\n" +
                        "Email:Doe@firm.com\n" +
                        "Current Address :Oslo\n" +
                        "Permanent Address :Street 1"));

        $("#output").shouldHave(text(String.format(
                "Name:%s\n" +
                        "Email:%s\n" +
                        "Current Address :Oslo\n" +
                        "Permanent Address :Street 1", userName, email)));

        $("#name").shouldHave(text(userName));
    }


    @Test
    void wrongEmailTest() {
        open("https://demoqa.com/text-box");

        $("#userName").val("John");
        $("#userEmail").val("Doe");
        $("#currentAddress").val("Montenegro");
        $("#permanentAddress").val("Street 1");
        $("#submit").click();

        $("#userEmail").shouldHave(cssClass("field-error"));

    }
}