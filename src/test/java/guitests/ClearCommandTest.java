//@@author A0147996E
package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.ClearCommand.COMMAND_CLEAR;
import static seedu.address.logic.commands.ClearCommand.MESSAGE_SUCCESS;

import org.junit.Test;

import seedu.address.logic.commands.CommandFormatter;

public class ClearCommandTest extends TaskManagerGuiTest {

    @Test
    public void clear_otherCommandsWorkAfterClear_success() {
        assertTrue(taskListPanel.isListMatching(td.getTypicalTasks()));
        assertClearCommandSuccess();

        commandBox.runCommand(td.gym.getAddCommand());
        assertTrue(taskListPanel.isListMatching(td.gym));

        commandBox.runCommand("delete 1");
        assertListSize(0);
    }

    /**
     * Check if the clear command is correctly executed.
     */
    private void assertClearCommandSuccess() {
        commandBox.runCommand("clear");
        assertListSize(0);
        assertResultMessage(CommandFormatter.undoFormatter(MESSAGE_SUCCESS, COMMAND_CLEAR));
    }
}
