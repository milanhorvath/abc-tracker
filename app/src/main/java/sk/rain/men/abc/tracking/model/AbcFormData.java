package sk.rain.men.abc.tracking.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by mhorvath on 14.02.2018.
 */

public class AbcFormData extends SugarRecord {

    private Long childId;
    private Long aId;
    private Long bId;
    private Long cId;
    private Date creationDate;
    private String comment;

    public AbcFormData() {

    }

    public AbcFormData(Long childId, Long aId, Long bId, Long cId, Date creationDate, String comment) {
        this.childId = childId;
        this.aId = aId;
        this.bId = bId;
        this.cId = cId;
        this.creationDate = creationDate;
        this.comment = comment;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
