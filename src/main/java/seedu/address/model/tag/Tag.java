//@@author A0147984L
package seedu.address.model.tag;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.task.TaskField;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag implements TaskField, Comparable<Tag> {

    public static final String MESSAGE_TAG_CONSTRAINTS_1 = "Tags names should be alphanumeric without space";
    public static final String MESSAGE_TAG_CONSTRAINTS_2 = " is reserved so cannot be used as a tag name";
    public static final String TAG_VALIDATION_REGEX = "\\p{Alnum}+";
    public static final String TAG_RESERVED_NAME =
            "(?i)"
            + "(list)|(finished)|(unfinished)|(favorite)|(favourite)|"
            + "(today)|(tomorrow)|(all)|"
            + "(add)|(delete)|(edit)|(update)|(find)|(help)|(list)|(clear)|(finish)|(scroll)|(load)";
    public static final String DEFAULT_TAG_NAME = "Inbox";
    public String tagName;


    /**
     * Validates given tag name.
     *
     * @throws IllegalValueException if the given tag name string is invalid.
     */
    public Tag(String name) throws IllegalValueException {
        assert name != null;
        String trimmedName = name.trim();
        if (!isValidTagName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_TAG_CONSTRAINTS_1);
        }
        if (isReservedName(trimmedName)) {
            throw new IllegalValueException(trimmedName + MESSAGE_TAG_CONSTRAINTS_2);
        }
        this.tagName = trimmedName.isEmpty() ? DEFAULT_TAG_NAME : trimmedName;
    }

    /**
     * Creates a copy of the given Tag.
     */
    public Tag(Tag source) throws IllegalValueException {
        this(source.getName());
    }

     /**
     * Returns true if a given string is a valid tag name.
     * @throws IllegalValueException
     */
    public static boolean isValidTagName(String test) throws IllegalValueException {
        if (test.isEmpty()) {
            return true;
        }
        return test.matches(TAG_VALIDATION_REGEX);
    }

    public static boolean isReservedName(String test) {
        return test.matches(TAG_RESERVED_NAME);
    }

    public String getName() {
        return this.tagName;
    }
    //@@author

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && this.tagName.equalsIgnoreCase(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

    @Override
    public String getValue() {
        return tagName;
    }

    @Override
    public int compareTo(Tag other) {
        return this.tagName.compareToIgnoreCase(other.tagName);
    }

//@@author A0147996E
    @Override
    public String getDisplayText() {
        if (" ".equals(tagName) || "".equals(tagName)) {
            return "";
        } else {
            return "[" + tagName + "]";
        }
    }
}
