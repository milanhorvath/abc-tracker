package sk.rain.men.abc.tracking.listener;

import android.view.View;
import android.widget.AdapterView;

import sk.rain.men.abc.tracking.adapter.AbcMasterDataCursorAdapter;
import sk.rain.men.abc.tracking.child.AbcChildFragment;

/**
 * Created by mhorvath on 10.03.2018.
 */

public class AbcFormClickListener implements View.OnClickListener, AdapterView.OnItemClickListener {

    private int selectedIndex;
    private AbcMasterDataCursorAdapter abcDataAdapter;
    private AbcChildFragment childFragment;
    private Long abcDataId;

    @Override
    public void onClick(View view) {
        changeSelectedIndex((Integer)view.getTag());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        changeSelectedIndex(i);
    }

    public void registerCursorAdapter(AbcMasterDataCursorAdapter adapter) {
        abcDataAdapter = adapter;
    }

    public void registerFragment(AbcChildFragment fragment) {
        childFragment = fragment;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public Long getAbcDataId() {
        return abcDataId;
    }

    public void changeSelectedIndex(int index) {
        selectedIndex = index;
        abcDataAdapter.setSelected(selectedIndex);
        abcDataId = childFragment.setSelectedIndex(selectedIndex);

        AbcClickerManager.getInstance().checkDataFilled();
    }

    public void enableSaveButton() {
        childFragment.enableSaveButton();
    }

    public void disableSaveButton() {
        childFragment.disableSaveButton();
    }

    public AbcMasterDataCursorAdapter getAbcDataAdapter() {
        return abcDataAdapter;
    }

    public AbcChildFragment getChildFragment() {
        return childFragment;
    }
}
