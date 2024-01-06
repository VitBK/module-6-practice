package org.practice;

import java.util.List;

public class TemplateConstants {

    public static final List<String> TAGS = List.of("{#subject}", "{#line}", "{#signature}");
    public static final String TEMPLATE = """
            {#subject}
            This is first line
            {#line}
            {#signature}
            """;
    public static final char[] TEMPLATE_CHARS = TEMPLATE.toCharArray();

    private TemplateConstants() {
    }
}
