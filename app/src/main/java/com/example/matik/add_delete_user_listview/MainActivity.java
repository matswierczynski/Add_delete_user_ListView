package com.example.matik.add_delete_user_listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;



public class MainActivity extends AppCompatActivity {

    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String> lastNames = new ArrayList<>();
    private static ArrayList<Integer> ages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            int year, month, dayOfMonth;
            String name, lastName;
            year = getIntent().getIntExtra(AddUser.YEAR_EXTRA_MESSAGE, 2000);
            month = getIntent().getIntExtra(AddUser.MONTH_EXTRA_MESSAGE, 1);
            dayOfMonth = getIntent().getIntExtra(AddUser.DAY_OF_MONTH_EXTRA_MESSAGE, 1);
            name = getIntent().getStringExtra(AddUser.NAME_EXTRA_MESSAGE);
            lastName = getIntent().getStringExtra(AddUser.LASTNAME_EXTRA_MESSAGE);

            getIntent().removeExtra(AddUser.YEAR_EXTRA_MESSAGE);
            getIntent().removeExtra(AddUser.MONTH_EXTRA_MESSAGE);
            getIntent().removeExtra(AddUser.DAY_OF_MONTH_EXTRA_MESSAGE);
            getIntent().removeExtra(AddUser.NAME_EXTRA_MESSAGE);
            getIntent().removeExtra(AddUser.LASTNAME_EXTRA_MESSAGE);

            addNewPerson(name, lastName, calculateAge(dayOfMonth, month, year));
        }


        ListView listView = findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuItemAdd:
                add();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void add(){
        Intent intent = new Intent(MainActivity.this, AddUser.class);
        startActivity(intent);
    }

    private Integer calculateAge(Integer dayOfBirth, Integer monthOfBirth,
                                 Integer yearOfBirth) {

        Calendar now = Calendar.getInstance();
        int nowMonth = now.get(Calendar.MONTH)+1;
        int nowDayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        int nowYear = now.get(Calendar.YEAR);

        int result = nowYear - yearOfBirth;

        if (monthOfBirth > nowMonth)
            result--;
        else if (monthOfBirth == nowMonth) {
            if (dayOfBirth > nowDayOfMonth)
                result--;
        }

        return result;
    }

    private void addNewPerson(String name, String lastName, Integer age){
        names.add(name);
        lastNames.add(lastName);
        ages.add(age);
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public String getItem(int position) {
            return names.get(position)+" "+
                    lastNames.get(position)+" "+
                    ages.get(position).toString();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.list_element, null);

            TextView textView_nameLastName = convertView.findViewById(R.id.NameLastNameTextView);
            TextView textView_age          = convertView.findViewById(R.id.AgeTextView);
            ImageButton   btn_remove       = convertView.findViewById(R.id.removeImageButton);
            btn_remove.setTag(position);

            textView_nameLastName.setText(names.get(position)+" "+lastNames.get(position));
            textView_age.setText(ages.get(position).toString());
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int)view.getTag();
                    names.remove(pos);
                    lastNames.remove(pos);
                    ages.remove(pos);
                    CustomAdapter.this.notifyDataSetChanged();
                }
            });



            return convertView;
        }
    }

}