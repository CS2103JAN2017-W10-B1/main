//@@author A0138474X
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Event;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.ReadOnlyRecurringTask.RecurringMode;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.RecurringEvent;
import seedu.address.model.task.RecurringTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.task.Venue;

/**
 * Adds a task/event/recurring task to Dueue.
 */
public class AddCommand extends AbleUndoCommand {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_ADD = COMMAND_WORD + COMMAND_SUFFIX;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to Dueue. \n"
            + "Parameters: NAME [due/DUE_DATE] [dueT/DUE_TIME] [start/START_DATE] [startT/START_TIME]\n"
            + "[#LISTNAME] [d/DESCRIPTION] [@VENUE] [p/PRIORITY_LEVEL] [*f] [f/REPEAT_PERIOD] \n"
            + "Examples: " + COMMAND_WORD
            + " \nTrip to Taiwan due/24/6/2017 start/tmr startT/16:00"
            + " dueT/18:00 p/3 *f\n"
            + COMMAND_WORD
            + " CS2103T Tutorial #CS2103 d/Finish asap p/important\n"
            + COMMAND_WORD
            + " CS2103 Demo f/weekly #CS2103 d/Exhausting module @SoC p/3\n";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the Dueue";

    private final Task toAdd;
    private boolean isSuccess;
    private boolean isDeleteAllOcurrence = true;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */

    public AddCommand(String name, String date, String startDate, String time, String startTime,
            String tag, String description, String venue, String priority, String frequency,
            boolean isFavourite, boolean isEvent, boolean isRecurring)
            throws IllegalValueException {
        //TODO: avoid long parameter list
        if (isEvent) {
            if (isRecurring) {
                RecurringMode mode = getRecurringMode(frequency);
                this.toAdd = buildEvent(name, date, startDate, time, startTime,
                        tag, description, venue, priority, isFavourite, mode);
            } else {
                this.toAdd = buildEvent(name, date, startDate, time, startTime,
                    tag, description, venue, priority, isFavourite);
            }
        } else {
            if (isRecurring) {
                RecurringMode mode = getRecurringMode(frequency);
                this.toAdd = buildTask(name, date, time, tag, description,
                        venue, priority, isFavourite, mode);
            } else {
                this.toAdd = buildTask(name, date, time, tag, description,
                        venue, priority, isFavourite);
            }
        }
        this.isSuccess = false;
    }

    private Event buildEvent(String name, String date, String startDate, String time,
            String startTime, String tag, String description, String venue,
            String priority, boolean isFavourite)
                    throws IllegalValueException {
        if (!startDate.isEmpty()) {
            return new Event(
                    new Name(name),
                    new TaskDate(startDate),
                    new TaskTime (startTime),
                    new TaskDate(date),
                    new TaskTime(time),
                    new Description(description),
                    new Tag(tag),
                    new Venue(venue),
                    new Priority(priority),
                    isFavourite
            );
        } else {
            return new Event(
                    new Name(name),
                    new TaskDate(date),
                    new TaskTime (startTime),
                    new TaskTime(time),
                    new Description(description),
                    new Tag(tag),
                    new Venue(venue),
                    new Priority(priority),
                    isFavourite
                );
        }
    }

    private Event buildEvent(String name, String date, String startDate, String time,
            String startTime, String tag, String description, String venue,
            String priority, boolean isFavourite, RecurringMode mode)
                    throws IllegalValueException {
        if (mode == null) {
            return buildEvent(name, date, startDate, time, startTime, tag,
                    description, venue, priority, isFavourite);
        } else {
            if (!startDate.isEmpty()) {
                return new RecurringEvent(
                        new Name(name),
                        new TaskDate(startDate),
                        new TaskTime (startTime),
                        new TaskDate(date),
                        new TaskTime(time),
                        new Description(description),
                        new Tag(tag),
                        new Venue(venue),
                        new Priority(priority),
                        isFavourite,
                        mode
                );
            } else {
                return new RecurringEvent(
                        new Name(name),
                        new TaskDate(date),
                        new TaskTime (startTime),
                        new TaskTime(time),
                        new Description(description),
                        new Tag(tag),
                        new Venue(venue),
                        new Priority(priority),
                        isFavourite,
                        mode
                    );
            }
        }
    }

    private Task buildTask(String name, String date, String time,
            String tag, String description, String venue,
            String priority, boolean isFavourite)
                    throws IllegalValueException {
        return new Task(
                new Name(name),
                new TaskDate(date),
                new TaskTime(time),
                new Description(description),
                new Tag(tag),
                new Venue(venue),
                new Priority(priority),
                isFavourite
                );
    }

    private Task buildTask(String name, String date, String time,
            String tag, String description, String venue,
            String priority, boolean isFavourite, RecurringMode mode)
                    throws IllegalValueException {
        if (mode == null) {
            return buildTask(name, date, time, tag,
                    description, venue, priority, isFavourite);
        } else {
            return new RecurringTask(
                    new Name(name),
                    new TaskDate(date),
                    new TaskTime(time),
                    new Description(description),
                    new Tag(tag),
                    new Venue(venue),
                    new Priority(priority),
                    isFavourite,
                    mode
                    );
            }
    }

    private RecurringMode getRecurringMode(String ocurring) {
        if (ocurring.matches(RecurringTask.PERIOD_DAY_REGEX)) {
            return RecurringMode.DAY;
        } else if (ocurring.matches(RecurringTask.PERIOD_WEEK_REGEX)) {
            return RecurringMode.WEEK;
        } else if (ocurring.matches(RecurringTask.PERIOD_MONTH_REGEX)) {
            return RecurringMode.MONTH;
        }
        return null;
    }

    public AddCommand(ReadOnlyTask task, boolean isDeleteAllOcurrence) {
        this.toAdd = (Task) task;
        this.isDeleteAllOcurrence = isDeleteAllOcurrence;
    }

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            this.isSuccess = true;
            int taskIndex = model.getFilteredTaskList().indexOf(toAdd);
            EventsCenter.getInstance().post(new JumpToListRequestEvent(taskIndex));
            return new CommandResult(
                    CommandFormatter.undoFormatter(
                            String.format(MESSAGE_SUCCESS, toAdd.getName()), COMMAND_ADD));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            this.isSuccess = false;
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
    }

    public Task getTask() {
        return toAdd;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public CommandResult executeUndo(String message) throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            this.isSuccess = true;
            return new CommandResult(CommandFormatter.undoMessageFormatter(message, COMMAND_ADD));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
    }

    @Override
    public Command getUndoCommand() {
        if (isSuccess) {
            return new DeleteCommand(getTask(), isDeleteAllOcurrence);
        } else {
            return new IncorrectCommand(null);
        }
    }

    @Override
    public String getUndoCommandWord() {
        return DeleteCommand.COMMAND_WORD + COMMAND_SUFFIX;
    }

}
