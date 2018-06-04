package sk.rain.men.abc.tracking.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.model.AbcMasterData;
import sk.rain.men.abc.tracking.model.AbcType;
import sk.rain.men.abc.tracking.model.BehaviorForm;
import sk.rain.men.abc.tracking.model.BehaviorFormData;

/**
 * Created by mhorvath on 15.02.2018.
 */

public class BehaviorDataListAdapter extends ArrayAdapter<AbcMasterData> {

    private LayoutInflater cursorInflater;
    private int selectedPosition = -1;
    private Long childId;
    private List<AbcMasterData> behaviorMasterList;
    private Map<Long, BehaviorFormData> behaviorDataMap;
    private SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");

    public BehaviorDataListAdapter(@NonNull Context context, @NonNull List<AbcMasterData> behaviorMasterList, @NonNull Long childId) {
        super(context, R.layout.behavior_item, behaviorMasterList);
        this.childId = childId;
        this.behaviorMasterList = behaviorMasterList;

        behaviorDataMap = new HashMap<>();

        for (AbcMasterData behaviorMaster : behaviorMasterList) {
            BehaviorFormData newestBehaviorData = Select.from(BehaviorFormData.class).where(Condition.prop("child_id").eq(childId), Condition.prop("behavior_id").eq(behaviorMaster.getId())).orderBy("start_date DESC").first();
            if (newestBehaviorData != null) {
                behaviorDataMap.put(behaviorMaster.getId(), newestBehaviorData);
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.behavior_item, parent, false);

        if(position == selectedPosition) {
            rowView.setBackgroundResource(R.color.activeBehavior);
        } else {
            rowView.setBackgroundResource(R.color.notActiveBehavior);
        }

        AbcMasterData behaviorMaster = getItem(position);
        if (behaviorMaster != null) {
            TextView bItemText = rowView.findViewById(R.id.behaviorItemText);
            bItemText.setText(behaviorMaster.getName());
        }

        BehaviorFormData behaviorData = behaviorDataMap.get(behaviorMaster.getId());
        if (behaviorData != null) {
            TextView lastHappend = rowView.findViewById(R.id.lastHappendTimestampText);
            lastHappend.setText(SDF.format(behaviorData.getStartDate()));
            TextView lastDuraiton = rowView.findViewById(R.id.lastHappendDurationText);
            lastDuraiton.setText(behaviorData.getDuration());
        }

        return rowView;
    }

    public int getSelectedPosition() {
        return this.selectedPosition;
    }

    public void setSelectedPosition(int pos) {
        this.selectedPosition = pos;
        notifyDataSetChanged();
    }

    public void setNewestBehaviorDataChanged(Long behaviorId, BehaviorFormData data) {
        behaviorDataMap.put(behaviorId, data);
    }
}
