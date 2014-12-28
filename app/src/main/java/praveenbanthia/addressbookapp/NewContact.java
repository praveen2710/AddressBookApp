package praveenbanthia.addressbookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class is called when user wants to add new contacts.
 * Created by praveenbanthia on 12/26/14.
 */
public class NewContact extends Activity {

    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText homeAddress;

    DBTools dbtools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_contact);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        homeAddress = (EditText) findViewById(R.id.homeAddress);

    }

    public void addNewContact(View view){
        HashMap<String,String> queryValuesMap = new HashMap<String,String>();

        queryValuesMap.put("firstName",firstName.getText().toString());
        queryValuesMap.put("lastName",lastName.getText().toString());
        queryValuesMap.put("phoneNumber",phoneNumber.getText().toString());
        queryValuesMap.put("emailAddress",emailAddress.getText().toString());
        queryValuesMap.put("homeAddress",homeAddress.getText().toString());

        dbtools.insertContacts(queryValuesMap);

        this.callMainActivity(view);
    }

    /**
     * This method takes us back to main view.
     * @param view
     */
    public void callMainActivity(View view){
        Intent addIntent = new Intent(getApplicationContext(),MainActivity.class);

        startActivity(addIntent);
    }


}
