//@@author A0138474X
package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Event;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.ReadOnlyEvent;
import seedu.address.model.task.ReadOnlyRecurringTask;
import seedu.address.model.task.ReadOnlyRecurringTask.RecurringMode;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.ReadOnlyTask.FinishProperty;
import seedu.address.model.task.RecurringEvent;
import seedu.address.model.task.RecurringTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Venue;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedTask {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = false)
    protected String date;
    @XmlElement(required = false)
    protected String time;
    @XmlElement(required = false)
    protected String tag;
    @XmlElement(required = false)
    protected String description;
    @XmlElement(required = false)
    protected String venue;
    @XmlElement(required = false)
    protected String priority;
    @XmlElement(required = true)
    protected boolean isFavourite;
    @XmlElement(required = true)
    protected FinishProperty isFinished;
    @XmlElement(required = true)
    protected boolean isEvent;
    @XmlElement(required = true)
    protected String startDate;
    @XmlElement(required = false)
    protected String startTime;
    @XmlElement(required = true)
    protected boolean isRecurring;
    @XmlElement(required = false)
    protected String recurringMode;

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedTask() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        name = source.getName().fullName;
        date = source.getDate().getValue();
        time = source.getTime().getValue();
        tag = source.getTag().tagName;
        description = source.getDescription().getValue();
        venue = source.getVenue().getValue();
        priority = source.getPriority().getValue();
        isFavourite = source.isFavorite();
        isFinished = source.getFinished();
        isEvent = source.isEvent();
        isRecurring = source.isRecurring();
        if (isEvent) {
            startDate = ((ReadOnlyEvent) source).getStartDate().getValue();
            startTime = ((ReadOnlyEvent) source).getStartTime().getValue();
        }
        if (isRecurring) {
            recurringMode = ((ReadOnlyRecurringTask) source).getRecurringPeriod();
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Task toModelType() throws IllegalValueException {
        final Name name = new Name(this.name);
        final TaskDate date = new TaskDate(this.date);
        final TaskTime time = new TaskTime(this.time);
        final Description description = new Description(this.description);
        final Tag tag = new Tag(this.tag);
        final Venue venue = new Venue(this.venue);
        final Priority priority = new Priority(this.priority);
        if (this.isEvent) {
            final TaskDate startDate = new TaskDate(this.startDate);
            final TaskTime startTime = new TaskTime(this.startTime);
            if (isRecurring) {
                final RecurringMode recurring = getRecurringMode(this.recurringMode);
                return new RecurringEvent(name, startDate, startTime, date, time, description, tag, venue, priority,
                        isFavourite, isFinished, recurring);
            } else {
                return new Event(name, startDate, startTime, date, time, description, tag, venue, priority,
                        isFavourite, isFinished);
            }
        } else {
            if (isRecurring) {
                final RecurringMode recurring = getRecurringMode(this.recurringMode);
                return new RecurringTask(name, date, time, description, tag, venue, priority,
                        isFavourite, isFinished, recurring);
            } else {
                return new Task(name, date, time, description, tag,
                        venue, priority, isFavourite, isFinished);
            }
        }
    }

    private RecurringMode getRecurringMode(String recurringMode) {
        if (recurringMode.contains("day")) {
            return RecurringMode.DAY;
        } else if (recurringMode.contains("week")) {
            return RecurringMode.WEEK;
        } else if (recurringMode.contains("month")) {
            return RecurringMode.MONTH;
        } else {
            return null;
        }
    }

    public String getTagName() {
        return this.tag;
    }
}
