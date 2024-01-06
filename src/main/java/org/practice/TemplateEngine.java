package org.practice;

import java.util.Map;

import static org.practice.TemplateConstants.TEMPLATE;
import static org.practice.TemplateConstants.TEMPLATE_CHARS;

public class TemplateEngine {

    public String createMessage(Map<String, String> tagValues) {
        StringBuilder sb = new StringBuilder(TEMPLATE);
        int tagValueLengthDiff = 0;
        for (int i = 0; i < TEMPLATE_CHARS.length; i++) {
            if (isTagStart(i)) {
                int tagEndIndex = getTagEndIndex(i);
                int tagStartIndexInclusive = i + tagValueLengthDiff;
                int tagEndIndexExclusive = tagEndIndex + tagValueLengthDiff + 1;
                String tagName = sb.substring(tagStartIndexInclusive, tagEndIndexExclusive);
                String tagValue = tagValues.get(tagName);
                sb.replace(tagStartIndexInclusive, tagEndIndexExclusive, tagValue);
                tagValueLengthDiff += tagValue.length() - (tagEndIndex - i + 1);
                i = tagEndIndex;
            }
        }
        return sb.toString();
    }

    private static boolean isTagStart(int i) {
        return TEMPLATE_CHARS[i] == '{' && TEMPLATE_CHARS[i + 1] == '#';
    }

    private static int getTagEndIndex(int i) {
        int j = i + 2;
        while (isTagEnd(j)) {
            j++;
        }
        return j;
    }

    private static boolean isTagEnd(int j) {
        return TEMPLATE_CHARS[j] != '}';
    }
}
