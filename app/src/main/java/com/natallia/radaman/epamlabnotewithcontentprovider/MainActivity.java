package com.natallia.radaman.epamlabnotewithcontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.natallia.radaman.epamlabnotewithcontentprovider.DataBaseSettings.ContractClass;

import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Natallia Radaman
 * @since 05-2018
 */
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    private Context context;
    private EditText editNameString;
    private EditText editNumberInt;
    private EditText editSearch;

    private Button addButton;
    private ImageButton searchButton;

    private ListView listView;

    private DataCursorAdapter adapter;

    private ArrayList<String[]> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        editNumberInt = findViewById(R.id.et_int);
        editSearch = findViewById(R.id.et_search_string);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString().toLowerCase(Locale.getDefault());
                new WorkWithDB().execute(text);

            }
        });

        editNameString = findViewById(R.id.et_string);
        addButton = findViewById(R.id.btn_add_row);
        addButton.setOnClickListener(this);

        searchButton = findViewById(R.id.button_search_string);
        searchButton.setOnClickListener(this);

        listView = findViewById(R.id.lv_rows);
        data = new ArrayList<>();
        data.add(new String[]{"1", "No Data", "Yet"});
        adapter = new DataCursorAdapter(context, data);
        listView.setAdapter(adapter);

        // fetch Data
        new WorkWithDB().execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_row:
                addRow();
                break;
            case R.id.button_search_string:
                break;
        }
    }

    private void addRow() {

        ContentValues values = new ContentValues();

        String intString = editNumberInt.getText().toString();
        String stringString = editNameString.getText().toString();

        data.add(new String[]{"5", stringString, intString.toString()});
        adapter.setData(data);

        editNumberInt.setText("");
        editNameString.setText("");

        values.put(ContractClass.ContactsTableEntry.COLUMN_NUMBER, Integer.parseInt(intString));
        values.put(ContractClass.ContactsTableEntry.COLUMN_NAME, stringString);

        Uri uri = getContentResolver().insert(ContractClass.ContactsTableEntry.CONTENT_URI, values);
        if (uri != null)
            Toast.makeText(context, uri.toString(), Toast.LENGTH_LONG).show();
    }

    public class WorkWithDB extends AsyncTask<String, Void, Cursor> {

        @Override
        protected Cursor doInBackground(String... strings) {

            ContentResolver resolver = getContentResolver();
            Cursor cursor;

            int stringsLength = strings.length;

            if (stringsLength > 0 && !strings[0].isEmpty()) {
                String searchString = "%" + strings[0] + "%";
                String selection = ContractClass.ContactsTableEntry.COLUMN_NAME + " LIKE ?";
                cursor = resolver.query(ContractClass.ContactsTableEntry.CONTENT_URI, null,
                        selection, new String[]{searchString}, null);
            } else {
                cursor = resolver.query(ContractClass.ContactsTableEntry.CONTENT_URI, null,
                        null, null, null);
            }
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            data = new ArrayList<String[]>();
            if (cursor != null) {
                // point to the first
                cursor.moveToFirst();
                if (cursor.getCount() >= 1) {
                    try {
                        do {
                            int i = cursor.getInt(cursor
                                    .getColumnIndex(ContractClass.ContactsTableEntry.COLUMN_NUMBER));
                            int id = cursor.getInt(cursor
                                    .getColumnIndex(ContractClass.ContactsTableEntry._ID));
                            String text = cursor.getString(cursor.getColumnIndex(ContractClass
                                    .ContactsTableEntry.COLUMN_NAME));
                            data.add(new String[]{id + "", text, i + ""});
                        } while (cursor.moveToNext());
                        adapter.setData(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        cursor.close();
                    }
                }
            }
        }
    }
}
