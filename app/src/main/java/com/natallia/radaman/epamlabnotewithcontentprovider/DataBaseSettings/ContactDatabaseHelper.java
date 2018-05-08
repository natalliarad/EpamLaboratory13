package com.natallia.radaman.epamlabnotewithcontentprovider.DataBaseSettings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Natallia Radaman
 * @since 05-2018
 */

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts";
    // increment the version if you change the DB
    private static final int DATABASE_VERSION = 1;

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_NAME_TABLE = "CREATE TABLE " + ContractClass.ContactsTableEntry.TABLE_NAME
                + " (" + ContractClass.ContactsTableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ContractClass.ContactsTableEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + ContractClass.ContactsTableEntry.COLUMN_NUMBER + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_NAME_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContractClass.ContactsTableEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
