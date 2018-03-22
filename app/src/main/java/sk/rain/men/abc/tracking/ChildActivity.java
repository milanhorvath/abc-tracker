package sk.rain.men.abc.tracking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sk.rain.men.abc.tracking.model.Child;

public class ChildActivity extends AppCompatActivity {

    private Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Button deleteChildButtton = findViewById(R.id.delete_child_button);
        Long childId = getIntent().getLongExtra(MessageKey.CHILD_ID_KEY, -1);
        if (childId != -1) {
            child = Child.findById(Child.class, childId);
            deleteChildButtton.setText(R.string.delete_child_button);
            EditText nameText = findViewById(R.id.nameText);
            EditText surnameText = findViewById(R.id.surnameText);
            EditText ageText = findViewById(R.id.ageText);
            nameText.setText(child.getName());
            surnameText.setText(child.getSurname());
            ageText.setText(child.getAge() + "");
        } else {
            deleteChildButtton.setText(R.string.cancel_child_button);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!saveChild()) {
            return false;
        }
        return super.onSupportNavigateUp();
    }

    public void saveChild(View view) {
        if (saveChild()) {
            NavUtils.navigateUpFromSameTask(this);
        }
    }

    private boolean saveChild() {
        if (child == null) {
            child = new Child();
        }
        EditText nameText = findViewById(R.id.nameText);
        EditText surnameText = findViewById(R.id.surnameText);
        EditText ageText = findViewById(R.id.ageText);
        if (!nameText.getText().toString().isEmpty()) {
            child.setName(nameText.getText().toString());
        }
        if (!surnameText.getText().toString().isEmpty()) {
            child.setSurname(surnameText.getText().toString());
        }
        if (!ageText.getText().toString().isEmpty()) {
            child.setAge(Integer.valueOf(ageText.getText().toString()));
        }
        if (child.getName() == null && child.getSurname() == null) {
            Toast.makeText(this, R.string.name_surname_empty_fail, Toast.LENGTH_LONG).show();
            //confirmDialog();
            // can not be not filled
            return false;
        }

        child.save();

        return true;
    }

    public void deleteChild(View view) {
        if (child != null) {
            child.delete();
        }

        NavUtils.navigateUpFromSameTask(this);
    }

}
