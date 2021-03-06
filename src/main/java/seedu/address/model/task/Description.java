//@@author A0147984L
package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's description in the task manager.
 * Guarantees: is valid as declared in {@link #isValidDescription(String)}
 */
public class Description implements TaskField, Comparable<Description> {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS = "Task description can be in any form.";
    public static final String DESCRIPTION_VALIDATION_REGEX = ".*";

    private final String value;

    /**
     * Validates given task description.
     *
     * @throws IllegalValueException if given description string is invalid.
     */
    public Description(String description) throws IllegalValueException {
        assert description != null;
        String trimmedDescription = description.trim();
        if (!isValidDescription(trimmedDescription)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.value = trimmedDescription;
    }

    /**
     * Returns true if a given string is a valid task description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Description other) {
        return this.value.compareTo(other.value);
    }

//@@author A0143409J
    @Override
    public String getDisplayText() {
        if ((" ".equals(value)) || ("".equals(value))) {
            return "";
        } else {
            return value;
        }
    }
}
