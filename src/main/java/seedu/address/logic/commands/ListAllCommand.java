//@@author A0143409J
package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_LIST_SUCCESS = "Listed unfinished tasks";
    public static final String MESSAGE_LIST_ALL_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_LIST_ALL_LIST_SUCCESS = "Listed all tasks in the list";
    public static final String MESSAGE_LIST_DOES_NOT_EXIST = "Given list name does not exist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List tasks as per the parameters\n"
            + "the specified listname and displays them as a list with index numbers.\n"
            + "Parameters: [all/favorite/finished] [LIST_NAME]\n"
            + "Example: " + COMMAND_WORD + "\n"
            + COMMAND_WORD + " all study\n"
            + COMMAND_WORD + " finished\n"
            + COMMAND_WORD + "study work\n";

    private final Set<String> keywords;

    public ListAllCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    public ListAllCommand() {
        this.keywords = new HashSet<String>();
    }

    @Override
    public CommandResult execute() {
        assert keywords != null;
        LOGGER.info(getClass() + " handles current command");
        if (keywords.isEmpty()) {
            model.updateFilteredListToShowAllTasks();
            LOGGER.info("Listed all tasks");
            return new CommandResult(MESSAGE_LIST_ALL_SUCCESS);
        } else if (model.isListExist(keywords)) {
            model.updateFilteredTaskListGivenListNameAll(keywords);
            LOGGER.info("Listed all tasks in the given lists");
            return new CommandResult(CommandFormatter.listFormatter(MESSAGE_LIST_ALL_SUCCESS, keywords));
        } else {
            LOGGER.info("All the listnames given are not found");
            return new CommandResult(MESSAGE_LIST_DOES_NOT_EXIST);
        }
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
