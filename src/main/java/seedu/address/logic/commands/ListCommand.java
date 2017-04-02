//@@author A0138474X
package seedu.address.logic.commands;

import java.util.Set;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_LIST_SUCCESS = "Listed unfinished tasks";
    public static final String MESSAGE_LIST_ALL_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_LIST_ALL_LIST_SUCCESS = "Listed all tasks in the list";
    public static final String MESSAGE_LIST_FINISHED_SUCCESS = "Listed all finished tasks";
    public static final String MESSAGE_LIST_FAVORITE_SUCCESS = "Listed all favorite tasks";
    public static final String MESSAGE_LIST_DOES_NOT_EXIST = "Given list name does not exist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List tasks as per the parameters\n"
            + "the specified listname and displays them as a list with index numbers.\n"
            + "Parameters: [all/favorite/finished] [LIST_NAME]\n"
            + "Example: " + COMMAND_WORD + "\n"
            + COMMAND_WORD + " all study\n"
            + COMMAND_WORD + " finished\n";

    private final Set<String> keywords;

    public ListCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    public ListCommand() {
        this.keywords = null;
    }

    @Override
    public CommandResult execute() {
        if (keywords == null) {
            model.updateFilteredListToShowAllUnfinishedTasks();
            return new CommandResult(MESSAGE_LIST_SUCCESS);
        } else if (!model.isListExist(keywords)) {
            return new CommandResult(MESSAGE_LIST_DOES_NOT_EXIST);
        } else {
            model.updateFilteredTaskListGivenListName(keywords);
            return new CommandResult(CommandFormatter.listFormatter(MESSAGE_LIST_SUCCESS, keywords));
        }
    }

  //@@author A0138474X
    @Override
    public boolean isUndoable() {
        return false;
    }
}
