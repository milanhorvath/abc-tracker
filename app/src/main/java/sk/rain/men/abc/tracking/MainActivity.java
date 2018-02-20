package sk.rain.men.abc.tracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sk.rain.men.abc.tracking.model.AbcMasterData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openChildren(View view) {
        Intent intent = new Intent(this, ChildrenActivity.class);
        startActivity(intent);
    }

    public void openABCData(View view) {
        Intent intent = new Intent(this, AbcMasterDataTabActivity.class);
        startActivity(intent);
    }
}
