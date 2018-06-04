package sk.rain.men.abc.tracking.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sk.rain.men.abc.tracking.MessageKey;
import sk.rain.men.abc.tracking.R;
import sk.rain.men.abc.tracking.model.AbcFormData;
import sk.rain.men.abc.tracking.model.BehaviorFormData;
import sk.rain.men.abc.tracking.model.Child;

public class ChildMainActivity extends AppCompatActivity {

    private Long childId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_main);

        childId = getIntent().getLongExtra(MessageKey.CHILD_ID_KEY, -1);
        if (childId == -1) {
            childId = savedInstanceState.getLong(MessageKey.CHILD_ID_KEY);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (childId != -1) {
            outState.putLong(MessageKey.CHILD_ID_KEY, childId);
            Log.i("ChildMainActivity","Saving instance state childid: " + childId);
        }
    }

   @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private void updateData() {
        Child child = Child.findById(Child.class, childId);

        TextView tw = findViewById(R.id.child_main_name_text);
        tw.setText(child.getName() + " " + child.getSurname());

        long abcFormCount = AbcFormData.count(AbcFormData.class, "child_id = ?", new String[]{childId + ""});
        tw = findViewById(R.id.child_main_abc_count_text);
        tw.setText(abcFormCount + " ");

        long behaviorCount = BehaviorFormData.count(BehaviorFormData.class, "child_id = ?", new String[]{childId + ""});
        tw = findViewById(R.id.child_main_behavior_count_text);
        tw.setText(behaviorCount + " ");
    }

    public void openABCFormConfig(View view) {

        Toast.makeText(this, "ABC form config", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, AbcChildDataTabActivity.class);
        //intent.putExtra(MessageKey.CHILD_ID_KEY, childId);
        //startActivity(intent);
    }

    public void openABCForm(View view) {
        //Toast.makeText(this, "Abc form", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, AbcMasterDataTabActivity.class);
        //startActivity(intent);
        Intent intent = new Intent(this, AbcChildDataTabActivity.class);
        intent.putExtra(MessageKey.CHILD_ID_KEY, childId);
        startActivity(intent);
    }

    public void openBehaviorForm(View view) {
        //Toast.makeText(this, "Behavior config", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BehaviorActivity.class);
        intent.putExtra(MessageKey.CHILD_ID_KEY, childId);
        startActivity(intent);
    }

    public void openBehaviorFormConfig(View view) {

        Toast.makeText(this, "Behavior form config", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, AbcChildDataTabActivity.class);
        //intent.putExtra(MessageKey.CHILD_ID_KEY, childId);
        //startActivity(intent);
    }

    public void openABCFormData(View view) {
        Toast.makeText(this, "ABC form data", Toast.LENGTH_SHORT).show();
    }

    public void openBehaviorFormData(View view) {
        Toast.makeText(this, "Behavior form data", Toast.LENGTH_SHORT).show();
    }

}
