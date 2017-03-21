package seedu.address.logic.commands;


import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ShowHelpRequestEvent;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private String usageMessage;

    /**
     * Creates a HelpCommand using one command .
     */

    public HelpCommand(String helpMessage) {
        super();
        usageMessage = helpMessage;
    }

    public HelpCommand() {
        super();
    }

    @Override
    public CommandResult execute() {
        if (usageMessage == null) {
            EventsCenter.getInstance().post(new ShowHelpRequestEvent());
            return new CommandResult(SHOWING_HELP_MESSAGE);
        } else {
            return new CommandResult(usageMessage);
        }
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
