package sk.rain.men.abc.tracking.listener;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.util.Date;

import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.model.AbcFormData;

/**
 * Created by mhorvath on 10.03.2018.
 */

public class AbcClickerManager {

    private AbcFormClickListener aFormListener;
    private AbcFormClickListener bFormListener;
    private AbcFormClickListener cFormListener;
    private static AbcClickerManager instance;

    private AbcClickerManager() {
        aFormListener = new AbcFormClickListener();
        bFormListener = new AbcFormClickListener();
        cFormListener = new AbcFormClickListener();
    }

    public static AbcClickerManager getInstance() {
        if (instance == null) {
            instance = new AbcClickerManager();
        }

        return instance;
    }

    public void saveForm(Long childId, FragmentActivity activity) {
        Long aId = aFormListener.getAbcDataId();
        Long bId = bFormListener.getAbcDataId();
        Long cId = cFormListener.getAbcDataId();

        new AbcFormData(childId, aId, bId,cId, new Date(), null).save();

        Toast.makeText(activity, "Data saved", Toast.LENGTH_SHORT).show();

        aFormListener.getAbcDataAdapter().setSelected(-1);
        bFormListener.getAbcDataAdapter().setSelected(-1);
        cFormListener.getAbcDataAdapter().setSelected(-1);

        TabLayout tabLayout = activity.findViewById(R.id.abc_form_tabs);
        tabLayout.getTabAt(0).select();

        aFormListener.disableSaveButton();
        bFormListener.disableSaveButton();
        cFormListener.disableSaveButton();
    }

    public AbcFormClickListener getaFormListener() {
        return aFormListener;
    }

    public AbcFormClickListener getbFormListener() {
        return bFormListener;
    }

    public AbcFormClickListener getcFormListener() {
        return cFormListener;
    }

    /**
     * Check if all the data are filled.
     */
    public void checkDataFilled() {
        if (aFormListener.getAbcDataId() != null && bFormListener.getAbcDataId() != null && cFormListener.getAbcDataId() != null) {
            // inform all that all data filled.
            aFormListener.enableSaveButton();
            bFormListener.enableSaveButton();
            cFormListener.enableSaveButton();
        }
    }

}
