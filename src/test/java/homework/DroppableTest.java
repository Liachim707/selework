package homework;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class DroppableTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1280x800";
    }

    @Test
    void shouldDragElementIntoDroppableArea() {
        open("https://jqueryui.com/droppable/");

        // Переключаемся в iframe
        switchTo().frame($("iframe.demo-frame"));

        // Проверяем начальное состояние
        $("#draggable").shouldBe(visible);
        $("#droppable")
                .shouldHave(text("Drop here"))
                .shouldNotHave(cssClass("ui-state-highlight"));

        // Выполняем drag-and-drop через jQuery UI
        executeJavaScript("""
            const draggable = $('#draggable');
            const droppable = $('#droppable');

            const draggableOffset = draggable.offset();
            const droppableOffset = droppable.offset();

            draggable.trigger({
                type: 'mousedown',
                which: 1,
                pageX: draggableOffset.left + 10,
                pageY: draggableOffset.top + 10
            });

            draggable.trigger({
                type: 'mousemove',
                which: 1,
                pageX: droppableOffset.left + 10,
                pageY: droppableOffset.top + 10
            });

            draggable.trigger({
                type: 'mouseup',
                which: 1,
                pageX: droppableOffset.left + 10,
                pageY: droppableOffset.top + 10
            });
        """);

        // Проверяем результат перемещения
        $("#droppable")
                .shouldHave(cssClass("ui-state-highlight"))
                .shouldHave(text("Dropped!"));
    }
}
