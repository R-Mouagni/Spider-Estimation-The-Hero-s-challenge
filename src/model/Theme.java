package model;

/**
 * Enumeration representing different theme
 */
public enum Theme {
    IMPROBABLE, 
    ENTERTAINMENT, 
    INFORMATICS, 
    EDUCATION; 

    /**
     * Retrieves the theme enum value corresponding to the given string
     *
     * @param theme The string representation of the theme
     * @return The corresponding theme enum value, or null if no matching theme is found
     */
    public static Theme getTheme(String theme) {
        for (Theme t : Theme.values()) {
            if (t.toString().equals(theme)) {
                return t;
            }
        }
        return null;
    }
}
