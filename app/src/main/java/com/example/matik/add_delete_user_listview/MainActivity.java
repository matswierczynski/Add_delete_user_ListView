package com.example.matik.add_delete_user_listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, AddUser.class);
        startActivity(intent);
        int  year, month, dayOfMonth;
        String name, lastname;
        year = getIntent().getIntExtra(AddUser.YEAR_EXTRA_MESSAGE, 2000);
        month = getIntent().getIntExtra(AddUser.MONTH_EXTRA_MESSAGE, 10);
        dayOfMonth = getIntent().getIntExtra(AddUser.DAY_OF_MONTH_EXTRA_MESSAGE, 10);
        String date = dayOfMonth+" - "+month+" - "+year;
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

}