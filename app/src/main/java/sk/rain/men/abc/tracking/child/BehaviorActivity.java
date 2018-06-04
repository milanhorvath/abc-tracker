package sk.rain.men.abc.tracking.child;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

import com.orm.query.Select;
import com.orm.util.SugarCursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sk.rain.men.abc.tracking.MessageKey;
import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.adapter.BehaviorDataCursorAdapter;
import sk.rain.men.abc.tracking.adapter.BehaviorDataListAdapter;
import sk.rain.men.abc.tracking.adapter.ChildCursorAdapter;
import sk.rain.men.abc.tracking.child.ChildMainActivity;
import sk.rain.men.abc.tracking.model.AbcForm;
import sk.rain.men.abc.tracking.model.AbcMasterData;
import sk.rain.men.abc.tracking.model.AbcType;
import sk.rain.men.abc.tracking.model.BehaviorForm;
import sk.rain.men.abc.tracking.model.BehaviorFormData;
import sk.rain.men.abc.tracking.model.Child;

public class BehaviorActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Long childId;
    private BehaviorDataCursorAdapter cursorAdapter;
    private BehaviorDataListAdapter listAdapter;
    private boolean timerActive = false;
    private int lastSelectedIndex = -1;
    private long lastBehaviorId = -1;
    private Chronometer behaviorChronometer;
    private TextView actualBehaviorText;
    private Date startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);

        childId = getIntent().getLongExtra(MessageKey.CHILD_ID_KEY, -1);
        Child child = Child.findById(Child.class, childId);

        Cursor cursor = createCursor();
        cursorAdapter = new BehaviorDataCursorAdapter(this, cursor, childId);

        List<AbcMasterData> masterList = getMasterDataList();

        listAdapter = new BehaviorDataListAdapter(this, masterList, childId);

        ListView behaviorListView = findViewById(R.id.behaviorListView);
        behaviorListView.setAdapter(listAdapter);
        behaviorListView.setOnItemClickListener(this);


        behaviorChronometer = findViewById(R.id.behaviorChronometer);
        actualBehaviorText = findViewById(R.id.actualBehaviorText);
        actualBehaviorText.setText("NONE");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //SugarCursor cursor = (SugarCursor) adapterView.getItemAtPosition(i);
        //Long behaviorId = cursor.getLong(cursor.getColumnIndex("ID"));
        //String behaviorName = cursor.getString(cursor.getColumnIndex("NAME"));
        AbcMasterData masterData = (AbcMasterData) adapterView.getItemAtPosition(i);
        Long behaviorId = masterData.getId();
        String behaviorName = masterData.getName();

        if (timerActive && lastSelectedIndex == i) {
            // stop of the current running item
            //cursorAdapter.setSelectedPosition(-1);

            Date endDate = new Date();
            int durationInSeconds = (int) (endDate.getTime() - startDate.getTime()) / 1000;
            behaviorChronometer.stop();
            BehaviorFormData newBehaviorData = new BehaviorFormData(childId, lastBehaviorId, startDate, endDate,durationInSeconds);
            newBehaviorData.save();
            listAdapter.setNewestBehaviorDataChanged(behaviorId, newBehaviorData);
            listAdapter.setSelectedPosition(-1);
            timerActive = false;
        } else if (timerActive && lastSelectedIndex != i) {
            // stop of the current running item and start another one
            Date endDate = new Date();
            int durationInSeconds = (int) (endDate.getTime() - startDate.getTime()) / 1000;
            behaviorChronometer.stop();
            BehaviorFormData newBehaviorData = new BehaviorFormData(childId, lastBehaviorId, startDate, endDate,durationInSeconds);
            newBehaviorData.save();

            //cursorAdapter.setSelectedPosition(i);
            startDate = new Date();
            behaviorChronometer.setBase(SystemClock.elapsedRealtime());
            actualBehaviorText.setText(behaviorName);
            behaviorChronometer.start();
            lastBehaviorId = behaviorId;
            lastSelectedIndex = i;

            listAdapter.setNewestBehaviorDataChanged(behaviorId, newBehaviorData);
            listAdapter.setSelectedPosition(i);
        } else {
            // start chronometer
            //cursorAdapter.setSelectedPosition(i);
            startDate = new Date();
            behaviorChronometer.setBase(SystemClock.elapsedRealtime());
            actualBehaviorText.setText(behaviorName);
            behaviorChronometer.start();
            lastBehaviorId = behaviorId;
            lastSelectedIndex = i;
            timerActive = true;
            listAdapter.setSelectedPosition(i);
        }

    }

    private Cursor createCursor() {
        List<BehaviorForm> behaviorForms = BehaviorForm.find(BehaviorForm.class, "child_id = " + childId);
        StringBuilder sb = new StringBuilder();
        for (BehaviorForm bForm : behaviorForms) {
            sb.append(bForm.getBehaviorId());
            sb.append(",");
        }
        if (sb.toString().isEmpty()) {
            sb.append("-1,");
        }

        String whereClause = "type == '" + AbcType.Behavior.name() + "'";
        if (behaviorForms != null && behaviorForms.size() > 0) {
            whereClause += " AND id IN (" + sb.substring(0, sb.length() - 1)  + ")";
        }

        return Select.from(AbcMasterData.class).where(whereClause).getCursor();
    }

    private List<AbcMasterData> getMasterDataList() {
        List<BehaviorForm> behaviorForms = BehaviorForm.find(BehaviorForm.class, "child_id = " + childId);
        StringBuilder sb = new StringBuilder();
        for (BehaviorForm bForm : behaviorForms) {
            sb.append(bForm.getBehaviorId());
            sb.append(",");
        }
        if (sb.toString().isEmpty()) {
            sb.append("-1,");
        }

        String whereClause = "type == '" + AbcType.Behavior.name() + "'";
        if (behaviorForms != null && behaviorForms.size() > 0) {
            whereClause += " AND id IN (" + sb.substring(0, sb.length() - 1)  + ")";
        }

        return Select.from(AbcMasterData.class).where(whereClause).list();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
