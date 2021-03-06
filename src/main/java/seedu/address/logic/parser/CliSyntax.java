//@@author A0138474X
package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DATE = new Prefix("due/");
    public static final Prefix PREFIX_TIME = new Prefix("dueT/");
    public static final Prefix PREFIX_TAG = new Prefix("#");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_VENUE = new Prefix("@");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");
    public static final Prefix PREFIX_FAVOURITE = new Prefix("*f");
    public static final Prefix PREFIX_UNFAVOURITE = new Prefix("*u");
    public static final Prefix PREFIX_START = new Prefix("start/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("startT/");
    public static final Prefix PREFIX_FREQUENCY = new Prefix("f/");
    public static final Prefix PREFIX_ONCE = new Prefix("once/");
    public static final Prefix PREFIX_ALL = new Prefix("all");

    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT_LIST =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

}
