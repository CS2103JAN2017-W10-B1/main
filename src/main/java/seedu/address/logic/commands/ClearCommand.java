//@@author A0138474X
package seedu.address.logic.commands;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.TaskManager;

/**
 * Clears Dueue.
 */
public class ClearCommand extends AbleUndoCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_CLEAR = "clear command";
    public static final String MESSAGE_SUCCESS = "Dueue has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all tasks in Dueue \n"
            + "Parameters: Nil\n"
            + "Example: " + COMMAND_WORD;

    private TaskManager tasks = TaskManager.getStub();

    @Override
    public CommandResult execute() {
        return execute(MESSAGE_SUCCESS, false);
    }

    public CommandResult execute(String message, Boolean isUndo) {
        assert model != null;
        TaskManager tasks = TaskManager.getStub();
        tasks.resetData(model.getTaskManager());
        model.resetData(this.tasks);
        this.tasks = tasks;
        if (isUndo) {
            return new CommandResult(CommandFormatter.undoMessageFormatter(message, getUndoCommandWord()));
        } else {
            return new CommandResult(CommandFormatter.undoFormatter(message, COMMAND_CLEAR));
        }
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public CommandResult undo(String message) throws CommandException {
        return execute(message, true);
    }

    @Override
    public Command getUndoCommand() throws IllegalValueException {
        return this;
    }

    @Override
    public String getUndoCommandWord() {
        return COMMAND_WORD + COMMAND_SUFFIX;
    }

    @Override
    public String getRedoCommandWord() {
        return COMMAND_WORD + COMMAND_SUFFIX;
    }
}
