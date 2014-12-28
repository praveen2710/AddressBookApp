package praveenbanthia.addressbookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

/**
 * This class is used to get the updated data from the user.
 * Created by praveenbanthia on 12/26/14.
 */
public class EditContact extends Activity {

    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText homeAddress;

    // this is to initialize the database object
    DBTools dbtools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);


        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        homeAddress = (EditText) findViewById(R.id.homeAddress);

        Intent newIntent = getIntent();
        //get existing data on the user
        String contactId = newIntent.getStringExtra("contactId");

        HashMap<String,String> contactList = dbtools.getContactInfo(contactId);

        if(contactList.size() != 0){
            //set existing data for user to see
            firstName.setText(contactList.get("firstName"));
            lastName.setText(contactList.get("lastName"));
            phoneNumber.setText(contactList.get("phoneNumber"));
            emailAddress.setText(contactList.get("emailAddress"));
            homeAddress.setText(contactList.get("homeAddress"));
        }

    }

    /**
     * This method is called when user clicks on edit button after making his changes.
     * This will update the database with updated values
     * @param view
     */
    public void editContact(View view){
        HashMap<String,String> queryValuesMap = new HashMap<String,String>();

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        homeAddress = (EditText) findViewById(R.id.homeAddress);

        Intent newIntent = getIntent();

        String contactId = newIntent.getStringExtra("contactId");

        queryValuesMap.put("contactId",contactId);
        queryValuesMap.put("firstName",firstName.getText().toString());
        queryValuesMap.put("lastName",lastName.getText().toString());
        queryValuesMap.put("phoneNumber",phoneNumber.getText().toString());
        queryValuesMap.put("emailAddress",emailAddress.getText().toString());
        queryValuesMap.put("homeAddress",homeAddress.getText().toString());
        queryValuesMap.put("homeAddress",homeAddress.getText().toString());

        dbtools.updateContact(queryValuesMap);

        this.callMainActivity(view);
    }

    /**
     * This will return us back to main view
     * @param view
     */
    public void callMainActivity(View view){
        Intent addIntent = new Intent(getApplication(),MainActivity.class);

        startActivity(addIntent);
    }

    /**
     * This will be executed when user wants to delete the contact.
     * It is called after user clicks on delete button
     * @param view
     */
    public void removeContact(View view){

        Intent newIntent = getIntent();

        String contactId = newIntent.getStringExtra("contactId");

        dbtools.deleteContact(contactId);

        this.callMainActivity(view);

    }

}
