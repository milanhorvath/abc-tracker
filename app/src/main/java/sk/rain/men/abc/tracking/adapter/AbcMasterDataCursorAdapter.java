package sk.rain.men.abc.tracking.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.listener.AbcClickerManager;
import sk.rain.men.abc.tracking.listener.AbcFormClickListener;
import sk.rain.men.abc.tracking.model.AbcType;

/**
 * Created by mhorvath on 15.02.2018.
 */

public class AbcMasterDataCursorAdapter extends CursorAdapter {

    private LayoutInflater cursorInflater;
    private int selectedPosition = -1;
    private AbcType type;

    // NULL - when no selection, TRUE - checkbox active, FALSE - radiobutton active
    private Boolean selectState;

    public AbcMasterDataCursorAdapter(Context context, Cursor c, Boolean selectState, AbcType type) {
        super(context, c, 0);
        this.selectState = selectState;
        this.type = type;
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        //return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
        return LayoutInflater.from(context).inflate(R.layout.abc_master_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if(cursor.getPosition() % 2 == 0) {
            view.setBackgroundResource(R.color.childEven);
        }else{
            view.setBackgroundResource(R.color.childOdd);
        }

        //CheckedTextView text1 = view.findViewById(android.R.id.text1);
        //text1.setText(cursor.getString(cursor.getColumnIndex("NAME")));
        if (selectState != null) {
            if (selectState) {
                CheckBox checkBox = view.findViewById(R.id.abcSelectCheck);
                checkBox.setVisibility(View.VISIBLE);
            } else {
                RadioButton radioButton = view.findViewById(R.id.abcSelectRadio);
                radioButton.setVisibility(View.VISIBLE);
                radioButton.setTag(cursor.getPosition());
                radioButton.setChecked(cursor.getPosition() == selectedPosition);
                AbcFormClickListener clickListener = null;
                switch (type) {
                    case Antecedent:
                        clickListener = AbcClickerManager.getInstance().getaFormListener();
                        break;
                    case Behavior:
                        clickListener = AbcClickerManager.getInstance().getbFormListener();
                        break;
                    case Consequence:
                        clickListener = AbcClickerManager.getInstance().getcFormListener();
                        break;
                }
                clickListener.registerCursorAdapter(this);
                radioButton.setOnClickListener(clickListener);
            }
        }

        TextView abcNameText = view.findViewById(R.id.abcNameListText);
        TextView abcDescText = view.findViewById(R.id.abcDescListText);

        abcNameText.setText(cursor.getString(cursor.getColumnIndex("NAME")));
        abcDescText.setText(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
    }

    public void setSelected(int index) {
        if (selectState != null) {
            if (selectState) {

            } else {
                selectedPosition = index;
            }
            notifyDataSetChanged();
        }
    }
}
