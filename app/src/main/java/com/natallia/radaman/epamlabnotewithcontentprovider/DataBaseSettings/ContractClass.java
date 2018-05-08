package com.natallia.radaman.epamlabnotewithcontentprovider.DataBaseSettings;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * The class stores all column names, projections for queries, and other values
 *
 * @author Natallia Radaman
 * @since 05-2018
 */
public class ContractClass {
    // authority for the Content Provider
    public static final String AUTHORITY = "com.natallia.radaman.epamlabnotewithcontentprovider";
    // base for all URIs
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // define paths for tables to be able to: BASE_CONTENT_URI/PATH_TO_TABLE
    public static final String PATH_TO_CONTACTS = "contacts";

    // define table and its content
    public static final class ContactsTableEntry implements BaseColumns {

        // build the content uri
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TO_CONTACTS).build();

        // table name
        public static final String TABLE_NAME = "table_contacts";

        // columns name
        public static final String COLUMN_NAME = "first_name";
        public static final String COLUMN_NUMBER = "telephone";
    }
}
