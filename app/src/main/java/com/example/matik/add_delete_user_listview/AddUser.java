package com.example.matik.add_delete_user_listview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class AddUser extends AppCompatActivity {
    public static String NAME_EXTRA_MESSAGE =
                                "com.example.matik.add_delete_user_listview.NAME_MESSAGE";
    public static String LASTNAME_EXTRA_MESSAGE =
                                "com.example.matik.add_delete_user_listview.LAST_NAME_MESSAGE";
    public static String YEAR_EXTRA_MESSAGE =
                                "com.example.matik.add_delete_user_listview.YEAR_MESSAGE";
    public static String MONTH_EXTRA_MESSAGE =
                                "com.example.matik.add_delete_user_listview.MONTH_MESSAGE";
    public static String DAY_OF_MONTH_EXTRA_MESSAGE =
                                "com.example.matik.add_delete_user_listview.DAY_OF_MONTH_MESSAGE";

    private Button btn;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private String name, lastName;
    private int year=0, month=0, dayOfMonth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        btn = findViewById(R.id.datePickerButton);

            btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                                                    AddUser.this,
                                                    android.R.style.Theme_Holo_Light_Dialog,
                                                    onDateSetListener,
                                                    year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(
                                                new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                AddUser.this.year = year;
                AddUser.this.month = month;
                AddUser.this.dayOfMonth = dayOfMonth;
                String pickedDate = dayOfMonth+" - "+month+" - "+year;
                ((TextView)findViewById(R.id.dateTextView)).setText(pickedDate);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuItemSave:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void save(){
        name = ((TextView)findViewById(R.id.nameEditText)).getText().toString();
        lastName = ((TextView)findViewById(R.id.lastNameEditText)).getText().toString();
        if (name.length() >=2) {
            if (lastName.length() >= 2) {
                /*date can be changed only by date picker dialog, if year is correct then month and
                day of month are also chosen*/
                if (year > 1900 && year < Calendar.getInstance().get(Calendar.YEAR))
                    passArgumentsToMainActivity();
                else Toast.makeText(
                        this,
                        "Enter correct date of birth",
                        Toast.LENGTH_SHORT).show();
            } else Toast.makeText(
                    this,
                    "Enter correct name",
                    Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(
                            this,
                            "Enter correct surname",
                             Toast.LENGTH_SHORT).show();

    }

    private void passArgumentsToMainActivity(){
        Intent intent = new Intent(AddUser.this, MainActivity.class);
        intent.putExtra(NAME_EXTRA_MESSAGE, name);
        intent.putExtra(LASTNAME_EXTRA_MESSAGE, lastName);
        intent.putExtra(YEAR_EXTRA_MESSAGE, year);
        intent.putExtra(MONTH_EXTRA_MESSAGE, month);
        intent.putExtra(DAY_OF_MONTH_EXTRA_MESSAGE, dayOfMonth);
        startActivity(intent);

    }

}
