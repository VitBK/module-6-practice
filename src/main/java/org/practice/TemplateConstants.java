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

    public static final String FILE_MODE_INTRO = """
                ######################
                Running in file mode
                ######################
                """;

    public static final String CONSOLE_MODE_INTRO = """
                ######################
                Running in console mode
                Please enter your input in following key-value format: "{#subject}=value"
                One pair per line
                To finish - type "exit"
                """ + "Required tags are: " + TemplateConstants.TAGS + "\n######################\n";

    private TemplateConstants() {
    }
}
