package sk.rain.men.abc.tracking.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.SimpleDateFormat;

import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.listener.AbcClickerManager;
import sk.rain.men.abc.tracking.listener.AbcFormClickListener;
import sk.rain.men.abc.tracking.model.AbcType;
import sk.rain.men.abc.tracking.model.BehaviorForm;
import sk.rain.men.abc.tracking.model.BehaviorFormData;

/**
 * Created by mhorvath on 15.02.2018.
 */

public class BehaviorDataCursorAdapter extends CursorAdapter {

    private LayoutInflater cursorInflater;
    private int selectedPosition = -1;
    private Long childId;
    private SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");

    public BehaviorDataCursorAdapter(Context context, Cursor c, Long childId) {
        super(context, c, 0);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.childId = childId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.behavior_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if(cursor.getPosition() == selectedPosition) {
            view.setBackgroundResource(R.color.activeBehavior);
        } else {
            view.setBackgroundResource(R.color.notActiveBehavior);
        }

        Long behaviorId = cursor.getLong(cursor.getColumnIndex("ID"));
        String behaviorName = cursor.getString(cursor.getColumnIndex("NAME"));

        BehaviorFormData newestBehaviorData = Select.from(BehaviorFormData.class).where(Condition.prop("child_id").eq(childId), Condition.prop("behavior_id").eq(behaviorId)).orderBy("start_date DESC").first();

        TextView bItemText = view.findViewById(R.id.behaviorItemText);
        bItemText.setText(behaviorName);
        if (newestBehaviorData != null) {
            TextView lastHappend = view.findViewById(R.id.lastHappendTimestampText);
            lastHappend.setText(SDF.format(newestBehaviorData.getStartDate()));
            TextView lastDuraiton = view.findViewById(R.id.lastHappendDurationText);
            lastDuraiton.setText(newestBehaviorData.getDuration() + " " + behaviorName);
        }

    }

    public int getSelectedPosition() {
        return this.selectedPosition;
    }

    public void setSelectedPosition(int pos) {
        this.selectedPosition = pos;
        notifyDataSetChanged();
    }
}
