package com.example.sorting_listview_data_asc_desc_example;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity<ArrayAdapter, Context> extends Activity implements View.OnClickListener {

    private static final android.R.attr R =;
    private Button mAscButton;
    private Button mDescButton;
    private ListView mNameListView;

    private List<String> stringList;
    private StringAdapter stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_list);

        mAscButton = (Button) findViewById(R.id.asc_button);
        mDescButton = (Button) findViewById(R.id.desc_button);
        mNameListView = (ListView) findViewById(R.id.name_list);

        stringList = new ArrayList<String>();
        stringList.add("Rajesh");
        stringList.add("Ramanan");
        stringList.add("Kannan");
        stringList.add("Mahesh");
        stringList.add("Moses");
        stringList.add("Kuberan");

        stringAdapter = new StringAdapter(MainActivity.this, R.layout.sort_list_item, stringList);
        mNameListView.setAdapter((ListAdapter) stringAdapter);

        mAscButton.setOnClickListener(this);
        mDescButton.setOnClickListener(this);


    }
    // Comparator for Ascending Order
    public static Comparator<String> StringAscComparator = new Comparator<String>() {

        public int compare(String app1, String app2) {

            String stringName1 = app1;
            String stringName2 = app2;

            return stringName1.compareToIgnoreCase(stringName2);
        }
    };

    //Comparator for Descending Order
    public static Comparator<String> StringDescComparator = new Comparator<String>() {

        public int compare(String app1, String app2) {

            String stringName1 = app1;
            String stringName2 = app2;

            return stringName2.compareToIgnoreCase(stringName1);
        }
    };
    // Your Own Custom Adapter
    private class StringAdapter<ArrayAdapter> extends ArrayAdapter<String> {
        // Attributes
        private List<String> strModel;

        public <Context> (Context context, int textViewResourceId,
                                       List<String> strModel) {
            super(context, textViewResourceId, strModel);
            this.strModel = strModel;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Holder holder = null;

            if (view == null) {
                view = View.inflate(MainActivity.this,
                        R.layout.sort_list_item, null);

                holder = new Holder();
                holder.StringNameTextView = (TextView) view
                        .findViewById(R.id.name_text_view);

                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            String nameText=strModel.get(position);
            holder.StringNameTextView.setText(nameText);
            return view;
        }

        public void notifyDataSetChanged() {
        }
    }

    static class Holder
    {
        private TextView StringNameTextView;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.asc_button :
                Collections.sort(stringList, StringAscComparator);
                Toast.makeText(MainActivity.this, "Sorting in Ascending Order", Toast.LENGTH_LONG).show();
                break;
            case R.id.desc_button :
                Collections.sort(stringList, StringDescComparator);
                Toast.makeText(MainActivity.this, "Sorting in Descending Order", Toast.LENGTH_LONG).show();
                break;
        }
        stringAdapter.notifyDataSetChanged();

    }
}