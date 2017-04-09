//@@author A0143409J
package seedu.address.logic.commands;

import java.util.Set;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.JumpToTagListRequestEvent;
import seedu.address.model.TaskManager;

/**
 * Lists all favorite unfinished tasks in Dueue or in a specified list.
 */
public class ListFavoriteCommand extends ListCommand {

    public static final String MESSAGE_LIST_SUCCESS = "Favorite tasks are listed!";

    /**
     * Create a ListFavoriteCommand using a set of keywords
     *
     * @param keywords A set of keywords which cannot be empty
     */
    public ListFavoriteCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute() {
        assert keywords != null;
        if (keywords.isEmpty()) {
            model.updateFilteredListToShowAllFavoriteTasks();
            LOGGER.info(getClass() + " listed all favorite tasks");
            return new CommandResult(MESSAGE_LIST_SUCCESS);
        } else if (model.isListExist(keywords)) {
            highlightCurrentTagName(keywords);
            model.updateFilteredTaskListGivenListNameAllFavorite(keywords);
            LOGGER.info(getClass() + " listed all favorite tasks in the given lists");
            return new CommandResult(CommandFormatter.listFormatter(MESSAGE_LIST_SUCCESS, keywords));
        } else {
            LOGGER.info(getClass() + " all the listnames given are not found");
            return new CommandResult(MESSAGE_LIST_DOES_NOT_EXIST);
        }
    }

  //@@author A0147996E
    /**
     * Highlight the single tag if user requests to filter tasks under a single list.
     * Does not support highlighting multiple list names concurrently.
     * @param keywords
     */
    private void highlightCurrentTagName(Set<String> keywords) {
        int index = TaskManager.getInstance().find(keywords.toString());
        if (index != -1) {
            EventsCenter.getInstance().post(new JumpToTagListRequestEvent(index));
        }
    }
    @Override
    public boolean isUndoable() {
        return false;
    }
}
