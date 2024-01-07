package org.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Tag("UnitTest")
class TemplateEngineTest {

    private final TemplateEngine templateEngine = new TemplateEngine();

    @Test
    void should_returnMessage_when_allValuesPassed() {
        //given
        var map = Map.of("{#subject}", "This is subject",
                "{#signature}", "Best regards", "{#line}", "Test line");
        String expected = """
                This is subject
                This is first line
                Test line
                Best regards
                """;
        //when
        String result = templateEngine.createMessage(map);

        //then
        Assertions.assertEquals(expected, result);
    }
}
