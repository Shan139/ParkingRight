package com.himani.parkingright.sqlite;

import android.provider.BaseColumns;

public class CurrentCarsCacheSchema {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public CurrentCarsCacheSchema() {
    }

    public static abstract class CarEntry implements BaseColumns {
        public static final String TABLE_NAME = "CarEntry";

        public static final String COLUMN_PLATE = "plate";

        public static final String COLUMN_MAKE = "make";

        public static final String COLUMN_MODEL = "model";

        public static final String COLUMN_TIMESTAMP = "timestamp";

        public static final String COLUMN_USERID = "userid";  
        
    }

    public static final String BIGINT_TYPE = " BIGINT";

    public static final String TEXT_TYPE = " TEXT";

    public static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_CARS_TABLE = "CREATE TABLE " + CarEntry.TABLE_NAME
            + " (" + CarEntry._ID + " INTEGER PRIMARY KEY," + CarEntry.COLUMN_PLATE
            + TEXT_TYPE + COMMA_SEP + CarEntry.COLUMN_MAKE + TEXT_TYPE + COMMA_SEP
            + CarEntry.COLUMN_MODEL + TEXT_TYPE + COMMA_SEP
            + CarEntry.COLUMN_TIMESTAMP + BIGINT_TYPE + COMMA_SEP + CarEntry.COLUMN_USERID + BIGINT_TYPE
            + " )";
   

    public static final String SQL_DROP_CARS_TABLE = "DROP TABLE IF EXISTS "
            + CarEntry.TABLE_NAME;

}
