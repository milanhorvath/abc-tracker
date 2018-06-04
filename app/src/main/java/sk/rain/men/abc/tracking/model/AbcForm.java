package sk.rain.men.abc.tracking.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

/**
 * Created by mhorvath on 14.02.2018.
 */
@MultiUnique("childId,abcId")
public class AbcForm extends SugarRecord {

    private Long childId;
    private Long abcId;

    public AbcForm() {

    }

    public AbcForm(Long childId, Long abcId) {
        this.childId = childId;
        this.abcId = abcId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getAbcId() {
        return abcId;
    }

    public void setAbcId(Long abcId) {
        this.abcId = abcId;
    }
}
