package sk.rain.men.abc.tracking;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.SugarCursor;

import sk.rain.men.abc.tracking.adapter.AbcMasterDataCursorAdapter;
import sk.rain.men.abc.tracking.model.AbcMasterData;
import sk.rain.men.abc.tracking.model.AbcType;


public class BehaviorFragment extends Fragment implements AdapterView.OnItemClickListener {

    private AbcMasterDataCursorAdapter cursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_behavior, container, false);

        Cursor cursor = Select.from(AbcMasterData.class).where("type == '" + AbcType.Behavior.name() + "'").getCursor();
        cursorAdapter = new AbcMasterDataCursorAdapter(getActivity(), cursor, null, AbcType.Behavior);

        ListView dataListView = rootView.findViewById(R.id.behaviorListView);
        dataListView.setAdapter(cursorAdapter);
        dataListView.setOnItemClickListener(this);
        //dataListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SugarCursor cursor = (SugarCursor) adapterView.getItemAtPosition(i);
        Long abcMasterDataId = cursor.getLong(cursor.getColumnIndex("ID"));
        Intent intent = new Intent(getActivity(), AbcMasterDataActivity.class);
        intent.putExtra(MessageKey.ABC_MD_ID_KEY, abcMasterDataId);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        Cursor cursor = Select.from(AbcMasterData.class).where("type == '" + AbcType.Behavior.name() + "'").getCursor();
        if (cursorAdapter != null) {
            cursorAdapter.changeCursor(cursor);
        }

        super.onResume();
    }
}
