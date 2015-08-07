package tools;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class makes it easy to obtain the color of text.
 */
public class ColoredString {
    String string;
    Color color;

    public String getString() {
        return string;
    }

    public void setString(String message) {
        this.string = message;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method sets color based on rgba color format string.
     *
     * @param colorString - a string in such format, e.g. rgba(145,54,54,8).
     */
    public void setColorFromString(String colorString) {
        Pattern c = Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher m = c.matcher(colorString);

        if (m.matches()) {
            this.color = new Color(Integer.valueOf(m.group(1)),  // r
                    Integer.valueOf(m.group(2)),  // g
                    Integer.valueOf(m.group(3))); // b
        } else {
            this.color = null;
        }
    }
}
