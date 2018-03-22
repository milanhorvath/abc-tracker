package sk.rain.men.abc.tracking.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import sk.rain.men.abc.tracking.AbcMasterDataTabActivity;
import sk.rain.men.abc.tracking.ChildrenActivity;
import sk.rain.men.abc.tracking.MessageKey;
import sk.rain.men.abc.tracking.R;

public class ChildMainActivity extends AppCompatActivity {

    private Long childId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_main);

        childId = getIntent().getLongExtra(MessageKey.CHILD_ID_KEY, -1);
    }

    public void openABCFormConfig(View view) {

        //Toast.makeText(this, "Form config", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AbcChildDataTabActivity.class);
        intent.putExtra(MessageKey.CHILD_ID_KEY, childId);
        startActivity(intent);
    }

    public void openABCForm(View view) {
        Toast.makeText(this, "Abc form", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, AbcMasterDataTabActivity.class);
        //startActivity(intent);
    }

    public void openBehaviorForm(View view) {
        Toast.makeText(this, "Behavior config", Toast.LENGTH_SHORT).show();
    }
}
