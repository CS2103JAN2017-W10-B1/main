package seedu.address.model.tag;


import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Field;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag implements Field {

    public static final String MESSAGE_TAG_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String TAG_VALIDATION_REGEX = "\\p{Alnum}+";

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
            throw new IllegalValueException(MESSAGE_TAG_CONSTRAINTS);
        }
        this.tagName = trimmedName;
    }

     /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return true;
    	//return test.matches(TAG_VALIDATION_REGEX);
    }

    public String getName() {
        return this.tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && this.tagName.equals(((Tag) other).tagName)); // state check
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
	public String getDisplayText() {
		if ((tagName == null) || (tagName == "")) {
			return "";
		} else {
			return tagName;
		}
	}
}
