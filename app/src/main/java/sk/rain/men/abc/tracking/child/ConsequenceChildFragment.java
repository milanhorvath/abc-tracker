package sk.rain.men.abc.tracking.child;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.orm.query.Select;
import com.orm.util.SugarCursor;

import java.util.List;

import sk.rain.men.abc.tracking.AbcMasterDataActivity;
import sk.rain.men.abc.tracking.ChildrenActivity;
import sk.rain.men.abc.tracking.MessageKey;
import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.adapter.AbcMasterDataCursorAdapter;
import sk.rain.men.abc.tracking.listener.AbcClickerManager;
import sk.rain.men.abc.tracking.listener.AbcFormClickListener;
import sk.rain.men.abc.tracking.model.AbcForm;
import sk.rain.men.abc.tracking.model.AbcMasterData;
import sk.rain.men.abc.tracking.model.AbcType;


public class ConsequenceChildFragment extends Fragment implements AbcChildFragment {

    private Long childId;
    private AbcMasterDataCursorAdapter cursorAdapter;
    private ListView dataListView;
    private FloatingActionButton saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consequence, container, false);

        childId = getArguments().getLong(MessageKey.CHILD_ID_KEY);

        cursorAdapter = new AbcMasterDataCursorAdapter(getActivity(), createCursor(), Boolean.FALSE, AbcType.Consequence);

        dataListView = view.findViewById(R.id.consequenceListView);
        dataListView.setAdapter(cursorAdapter);
        AbcFormClickListener clickListener = AbcClickerManager.getInstance().getcFormListener();
        clickListener.registerFragment(this);
        dataListView.setOnItemClickListener(clickListener);

        FloatingActionButton addButton = view.findViewById(R.id.add_consequence_button);
        addButton.setVisibility(View.GONE);

        saveButton = view.findViewById(R.id.save_consequence_abc_form_button);
        saveButton.setVisibility(View.VISIBLE);
        saveButton.setEnabled(false);

        return view;
    }

    @Override
    public void onResume() {
        if (cursorAdapter != null) {
            cursorAdapter.changeCursor(createCursor());
        }

        super.onResume();
    }

    private Cursor createCursor() {
        List<AbcForm> abcForm = AbcForm.find(AbcForm.class, "child_id = " + childId);
        StringBuilder sb = new StringBuilder();
        for (AbcForm aForm : abcForm) {
            sb.append(aForm.getAbcId());
            sb.append(",");
        }
        if (sb.toString().isEmpty()) {
            sb.append("-1,");
        }

        String whereClause = "type == '" + AbcType.Consequence.name() + "'";
        if (abcForm != null && abcForm.size() > 0) {
            whereClause += " AND id IN (" + sb.substring(0, sb.length() - 1)  + ")";
        }

        return Select.from(AbcMasterData.class).where(whereClause).getCursor();
    }

    @Override
    public Long setSelectedIndex(int selectedIndex) {
        SugarCursor cursor = (SugarCursor) dataListView.getItemAtPosition(selectedIndex);
        Long abcMasterDataId = cursor.getLong(cursor.getColumnIndex("ID"));

        Toast.makeText(getActivity(), "Save the selected form to DB", Toast.LENGTH_SHORT).show();

        return abcMasterDataId;
    }

    /**
     * Enable the save button.
     */
    public void enableSaveButton() {
        saveButton.setEnabled(true);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButton.setEnabled(false);
                saveButton.setOnClickListener(null);
                AbcClickerManager.getInstance().saveForm(childId, getActivity());
            }
        });
    }

    public void disableSaveButton() {
        saveButton.setEnabled(false);
        saveButton.setOnClickListener(null);
    }

}
