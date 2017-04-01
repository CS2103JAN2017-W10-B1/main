//@@author A0143409J-reused
package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyTaskManager;

/** Indicates the TaskManager in the model has changed*/
public class DueueChangedEvent extends BaseEvent {

    public final ReadOnlyTaskManager data;

    public DueueChangedEvent(ReadOnlyTaskManager data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of tasks " + data.getTaskList().size() + ", number of tags " + data.getTagList().size();
    }
}
