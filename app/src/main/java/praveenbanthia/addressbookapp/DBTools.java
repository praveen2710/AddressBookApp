package praveenbanthia.addressbookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class will handle all transactions related to database
 * namely CRUD operations.
 */
public class DBTools extends SQLiteOpenHelper {

    public DBTools(Context context) {
        super(context,"contactbook.db", null, 1);
    }

    /**
     *Create the database to store the data
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE contacts ( contactId INTEGER PRIMARY KEY, firstName TEXT, " +
                "lastName TEXT, phoneNumber TEXT, emailAddress TEXT, homeAddress TEXT)";

        db.execSQL(query);
    }

    /**
     * This method is used to recreate the table whenever application is updated
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS contacts";

        db.execSQL(query);
        onCreate(db);
    }

    /**
     *
     * This is used to create new data in table
     * @param queryValues
     *          this is the data from user
     *
     */
    public void insertContacts(HashMap<String,String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstName",queryValues.get("firstName"));
        values.put("lastName",queryValues.get("lastName"));
        values.put("phoneNumber",queryValues.get("phoneNumber"));
        values.put("emailAddress",queryValues.get("emailAddress"));
        values.put("homeAddress",queryValues.get("homeAddress"));

        database.insert("contacts", null, values);
        database.close();
    }

    /**
     *
     * This is used to update existing data in table
     * @param queryValues
     *          this is the data updated by the user that
     */
    public void updateContact(HashMap<String,String> queryValues){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstName",queryValues.get("firstName"));
        values.put("lastName",queryValues.get("lastName"));
        values.put("phoneNumber",queryValues.get("phoneNumber"));
        values.put("emailAddress",queryValues.get("emailAddress"));
        values.put("homeAddress",queryValues.get("homeAddress"));

        database.update("contacts",values,"contactId"+"=?" ,new String[] {queryValues.get("contactId")} );
    }

    /**
     *
     * Deletes data from the database
     * @param id
     *          the id of the contact the needs to be deleted.
     */
    public void deleteContact(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM contacts where contactId='"+id+"'";

        database.execSQL(deleteQuery);

    }

    /**
     *
     * @return list all rows from the database
     */
    public ArrayList<HashMap<String,String>> getAllContacts(){
        ArrayList<HashMap<String,String>> contactArrayList = new ArrayList<HashMap<String,String>>();

        String selectQuery = "SELECT * FROM contacts";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> contactMap = new  HashMap<String,String>();

                contactMap.put("contactId",cursor.getString(0));
                contactMap.put("firstName",cursor.getString(1));
                contactMap.put("lastName",cursor.getString(2));
                contactMap.put("phoneNumber",cursor.getString(3));
                contactMap.put("emailAddress",cursor.getString(4));
                contactMap.put("homeAddress",cursor.getString(5));

               contactArrayList.add(contactMap);
            }while(cursor.moveToNext());
        }
        return  contactArrayList;
    }

    /**
     *
     * @param id
     *          the id in table that needs to be retrieved
     * @return the relevant data returned from database.
     */
    public HashMap<String,String> getContactInfo(String id){

        HashMap<String,String> contactMap = new  HashMap<String,String>();
        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM CONTACTS WHERE contactId = '"+id+"'";

        Cursor cursor = database.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                contactMap.put("contactId",cursor.getString(0));
                contactMap.put("firstName",cursor.getString(1));
                contactMap.put("lastName",cursor.getString(2));
                contactMap.put("phoneNumber",cursor.getString(3));
                contactMap.put("emailAddress",cursor.getString(4));
                contactMap.put("homeAddress",cursor.getString(5));
            }while(cursor.moveToNext());
        }
        return  contactMap;
    }


}
