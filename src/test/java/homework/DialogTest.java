package homework;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class DialogTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1280x800";
    }

    @Test
    void shouldOpenAndCloseDialog() {
        open("https://jqueryui.com/dialog/");

        // Переходим в iframe с демонстрацией
        switchTo().frame($("iframe.demo-frame"));

        // Проверяем, что кнопка открытия существует
        $("#opener").shouldBe(visible);

        // Открываем модальное окно
        $("#opener").click();

        // Проверяем, что окно открылось
        $(".ui-dialog")
                .shouldBe(visible);

        // Проверяем заголовок
        $(".ui-dialog-title")
                .shouldHave(text("Basic dialog"));

        // Проверяем содержимое
        $(".ui-dialog-content")
                .shouldBe(visible)
                .shouldHave(text("This is the default dialog"));

        // Закрываем окно
        $(".ui-dialog-titlebar-close").click();

        // Проверяем, что окно закрылось
        $(".ui-dialog")
                .shouldNotBe(visible);
    }
}
