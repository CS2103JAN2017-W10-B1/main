//@@author generated
package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents an incorrect command. Upon execution, throws a CommandException with feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;
    public static final String COMMAND_WORD = "incorrect";

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() throws CommandException {
        throw new CommandException(feedbackToUser);
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

}

