package sk.rain.men.abc.tracking.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

/**
 * Created by mhorvath on 14.02.2018.
 */
@MultiUnique("childId, behaviorId")
public class BehaviorForm extends SugarRecord {

    private Long childId;
    private Long behaviorId;

    public BehaviorForm() {

    }

    public BehaviorForm(Long childId, Long behaviorId, AbcType type) {
        this.childId = childId;
        this.behaviorId = behaviorId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(Long behaviorId) {
        this.behaviorId = behaviorId;
    }
}
