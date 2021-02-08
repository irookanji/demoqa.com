package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class FormsFillsTests {
  @BeforeAll
  public static void config() {
    Configuration.startMaximized = true;
  }

  @Test
  void fillAllFormTest() {
    step(
        "Open main page",
        () -> {
          final String url = "https://demoqa.com/automation-practice-form";
          open(url);
          Allure.link("Testing", url);
        });

    String name = "John",
        lastname = "Doe",
        email = "john@doe.com",
        mobile = "1234567890",
        gender = "Male",
        hobbies = "Music",
        current_address = "Storgatan, 7";
    File file = new File("src/test/resources/Cover.jpg");

    step(
        "Fill requisites",
        () -> {
          $("#firstName").val(name);
          $("#lastName").val(lastname);
          $("#userEmail").val(email);
          $x("//input[@value='Male']/following-sibling::label").click();
          $("#userNumber").val(mobile);
        });

    step(
        "Fill date of Birth",
        () -> {
          $("#dateOfBirthInput").click();
          $(".react-datepicker__month-select").selectOption("June");
          $(".react-datepicker__year-select").selectOption("1990");
          $(".react-datepicker__day--005").click();
        });

    step(
        "Fill subjects",
        () -> {
          $("#subjectsInput").click();
          $("#subjectsInput").val("Math");
          $$("div[id^=\"react-select-2-option\"]").find(text("Math")).click();
        });

    step(
        "Add hobby",
        () -> {
          $("#hobbiesWrapper").$(byText(hobbies)).click();
        });

    step(
        "Upload picture",
        () -> {
          $("#uploadPicture").uploadFile(file);
        });

    step(
        "Add current address",
        () -> {
          $("#currentAddress").val(current_address);
        });

    step(
        "Add state and city",
        () -> {
          $("#state").scrollTo().click();
          $("#stateCity-wrapper").$(byText("Haryana")).click();
          $("#city").click();
          $("#stateCity-wrapper").$(byText("Karnal")).click();
          $("#submit").scrollTo().click();
        });

    step(
        "Verify modal window is present",
        () -> {
          $x("//div[@class='modal-header']//div")
              .shouldHave(text("Thanks for submitting the form"));
        });

    step(
        "Verify modal window is present",
        () -> {
          $x("//div[@class='modal-header']//div")
              .shouldHave(text("Thanks for submitting the form"));
        });

    step(
        "Verify text as expected",
        () -> {
          $x("*//tr[1]/td[2]").shouldHave(text(name + " " + lastname));
          $x("*//tr[2]/td[2]").shouldHave(text(email));
          $x("*//tr[3]/td[2]").shouldHave(text(gender));
          $x("*//tr[4]/td[2]").shouldHave(text(mobile));
          $x("*//tr[5]/td[2]").shouldHave(text("05 June,1990"));
          $x("*//tr[6]/td[2]").shouldHave(text("Maths"));
          $x("*//tr[7]/td[2]").shouldHave(text(hobbies));
          $x("*//tr[8]/td[2]").shouldHave(text("Cover.jpg"));
          $x("*//tr[9]/td[2]").shouldHave(text(current_address));
          $x("*//tr[10]/td[2]").shouldHave(text("Haryana Karnal"));
        });

    step(
        "Closing modal",
        () -> {
          $("#closeLargeModal").click();
        });
  }
}
