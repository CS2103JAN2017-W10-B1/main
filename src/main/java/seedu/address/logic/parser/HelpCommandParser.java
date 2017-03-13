package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.IncorrectCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectCommand;

public class HelpCommandParser {

    /**
     * Parses the given {String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public Command parse(String args) {
        // keywords delimited by whitespace
        final String commandWord = args.trim();
        final String helpMessage = parseCommand(commandWord);
        if (helpMessage.isEmpty()) {
        	return new HelpCommand();
        } else {
            return new HelpCommand(helpMessage);
        }
    }

	private String parseCommand(String commandWord) {
		switch (commandWord) {
            case AddCommand.COMMAND_WORD:
                return AddCommand.MESSAGE_USAGE;
        
            case EditCommand.COMMAND_WORD:
                return EditCommand.MESSAGE_USAGE;
        
            case SelectCommand.COMMAND_WORD:
                return SelectCommand.MESSAGE_USAGE;
        
            case DeleteCommand.COMMAND_WORD:
                return DeleteCommand.MESSAGE_USAGE;
        
            case ClearCommand.COMMAND_WORD:
                return ClearCommand.MESSAGE_USAGE;
        
            case FindCommand.COMMAND_WORD:
                return FindCommand.MESSAGE_USAGE;
        
            case ListCommand.COMMAND_WORD:
                return ListCommand.MESSAGE_USAGE;
        
            case ExitCommand.COMMAND_WORD:
                return ExitCommand.MESSAGE_USAGE;
        
            case HelpCommand.COMMAND_WORD:
                return HelpCommand.MESSAGE_USAGE;
        
            default:
                return null;
        }
	}
}