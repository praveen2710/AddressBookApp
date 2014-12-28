package praveenbanthia.addressbookapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will show list of all existing contacts and also give option to add new contacts.
 */
public class MainActivity extends ListActivity {

    Intent intent;
    TextView contactId;

    DBTools dbtools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String,String>> contactList = dbtools.getAllContacts();
        // this will enable us to go into another view when user clicks on a contact
        if(contactList.size() != 0){

            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    contactId = (TextView) findViewById(R.id.contactId);

                    String contactIdValue = contactId.getText().toString();

                    Intent newIntent = new Intent(getApplicationContext(),EditContact.class);
                    newIntent.putExtra("contactId",contactIdValue);
                    startActivity(newIntent);
                }
            });
            //list all existing contacts in view.
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,contactList,
                    R.layout.contact_entry,new String[] {"contactId","lastName","firstName"}
                    ,new int[]{R.id.contactId,R.id.lastName,R.id.firstName});

            setListAdapter(adapter);
        }
    }

    /**
     * This will take us to a different view when user click add button.
     * @param view
     */
    public void showAddContact(View view){
        Intent addIntent = new Intent(getApplicationContext(),NewContact.class);

        startActivity(addIntent);
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
