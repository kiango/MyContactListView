package com.example.torsh.mycontactlistview;

import android.app.ListActivity;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ListActivity {
    // ToDo: arrange it as fragment

    // basic list view: array of info -> array adapter -> ListView
    // custom ListView is created by new View xml-file: list_layout.xml

    ListView listView;
    Cursor cursor;
    String[] column;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        cursor = getBaseContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        // define the data / array of info
        String[] column = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone._ID};
        this.column = column;
        int[] to = {R.id.textView1, R.id.textView2};

        // create and set the adaptor
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, cursor, column, to);
        listView = (ListView) findViewById(R.id.listview1);
        setListAdapter(simpleCursorAdapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        super.onListItemClick(listView, v, position, id);

        // when we don't know the columns name at clicked list position we get it like below
        String name = cursor.getString(cursor.getColumnIndex(column[0]));
        String number = cursor.getString(cursor.getColumnIndex(column[1]));
        Toast.makeText(getBaseContext(), name +"\n"+ number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
