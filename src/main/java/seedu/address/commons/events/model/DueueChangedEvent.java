package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyTaskManager;

/** Indicates the AddressBook in the model has changed*/
public class DueueChangedEvent extends BaseEvent {

    public final ReadOnlyTaskManager data;

    public DueueChangedEvent(ReadOnlyTaskManager data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of persons " + data.getTaskList().size() + ", number of tags " + data.getTagList().size();
    }
}