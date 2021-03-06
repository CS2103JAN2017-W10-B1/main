//@@author A0138474X
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FinishCommand;
import seedu.address.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates a new FinishCommand object
 */
public class FinishCommandParser {

    private static FinishCommandParser theOne;

    private FinishCommandParser() {
    }

    public static FinishCommandParser getInstance() {
        if (theOne == null) {
            theOne = new FinishCommandParser();
        }
        return theOne;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     */
    public Command parse(String args) {
        Optional<Integer> index = ParserUtil.parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FinishCommand.MESSAGE_USAGE));
        }

        return new FinishCommand(index.get());
    }

}
