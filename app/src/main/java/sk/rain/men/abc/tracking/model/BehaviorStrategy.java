package sk.rain.men.abc.tracking.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by mhorvath on 14.02.2018.
 */

public class BehaviorStrategy extends SugarRecord {

    private Long childId;
    private Long behaviorId;
    private Date creationDate;
    private StrategyType type;
    private String description;

    public BehaviorStrategy() {

    }

    public BehaviorStrategy(Long childId, Long behaviorId, Date creationDate, StrategyType type, String description) {
        this.childId = childId;
        this.behaviorId = behaviorId;
        this.creationDate = creationDate;
        this.type = type;
        this.description = description;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public StrategyType getType() {
        return type;
    }

    public void setType(StrategyType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
