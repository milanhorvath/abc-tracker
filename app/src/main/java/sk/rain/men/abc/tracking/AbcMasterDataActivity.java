package sk.rain.men.abc.tracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sk.rain.men.abc.tracking.model.AbcFormData;
import sk.rain.men.abc.tracking.model.AbcMasterData;
import sk.rain.men.abc.tracking.model.AbcType;

public class AbcMasterDataActivity extends AppCompatActivity {

    private AbcMasterData abcData;
    private AbcType newType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_master_data);

        Button deleteAbcDataButton = findViewById(R.id.delete_abcMdData_button);
        Long abcMdId = getIntent().getLongExtra(MessageKey.ABC_MD_ID_KEY, -1);
        newType = (AbcType) getIntent().getSerializableExtra(MessageKey.ABC_MD_TYPE_KEY);
        
        TextView typeText = findViewById(R.id.abcMdTypeText);
        if (abcMdId != -1) {
            abcData = AbcMasterData.findById(AbcMasterData.class, abcMdId);
            newType = abcData.getType();
            deleteAbcDataButton.setText(R.string.delete_abcMdData_button);
            EditText nameText = findViewById(R.id.abcMdNameText);
            EditText descText = findViewById(R.id.abcMdDescText);
            nameText.setText(abcData.getName());
            descText.setText(abcData.getDescription());
            typeText.setText(abcData.getType().name());
        } else {
            typeText.setText(newType.name());
            deleteAbcDataButton.setText(R.string.cancel_abcMdData_button);
        }
    }

    public void saveAbcData(View view) {
        if (saveAbcData()) {
            finish();
        }
    }

    private boolean saveAbcData() {
        if (abcData == null) {
            abcData = new AbcMasterData();
            abcData.setType(newType);
        }
        EditText nameText = findViewById(R.id.abcMdNameText);
        EditText descText = findViewById(R.id.abcMdDescText);
        if (!nameText.getText().toString().isEmpty()) {
            abcData.setName(nameText.getText().toString());
        }
        if (!descText.getText().toString().isEmpty()) {
            abcData.setDescription(descText.getText().toString());
        }
        if (abcData.getName() == null && abcData.getDescription() == null) {
            Toast.makeText(this, R.string.abcMdName_empty_fail, Toast.LENGTH_LONG).show();
            // can not be not filled
            return false;
        }

        abcData.save();

        return true;
    }

    public void deleteAbcData(View view) {
        Button deleteAbcDataButton = findViewById(R.id.delete_abcMdData_button);
        if (deleteAbcDataButton.getText().equals(R.string.cancel_abcMdData_button)) {
            finish();
            return;
        }

        if (abcData != null) {
            String[] params = new String[]{abcData.getId() + "",abcData.getId() + "",abcData.getId() + ""};
            long count = AbcFormData.count(AbcFormData.class, "A_ID = ? OR B_ID = ? OR C_ID = ?", params);
            if (count > 0) {
                Toast.makeText(this, R.string.abcMdData_used_fail, Toast.LENGTH_LONG).show();
                deleteAbcDataButton.setText(R.string.cancel_abcMdData_button);
            } else {
                abcData.delete();
            }
        }
        finish();
    }

}
