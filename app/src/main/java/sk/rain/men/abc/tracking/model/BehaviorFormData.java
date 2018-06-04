package sk.rain.men.abc.tracking.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by mhorvath on 14.02.2018.
 */

public class BehaviorFormData extends SugarRecord {

    private Long childId;
    private Long behaviorId;
    private Date startDate;
    private Date endDate;
    // In seconds
    private int duration;

    public BehaviorFormData() {

    }

    public BehaviorFormData(Long childId, Long behaviorId, Date startDate, Date endDate, int duration) {
        this.childId = childId;
        this.behaviorId = behaviorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
