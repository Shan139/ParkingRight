package com.himani.parkingright.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.himani.parkingright.CarInfo;
import com.himani.parkingright.sqlite.CurrentCarsCacheSchema.CarEntry;

public class CacheCurrentCarsHelper {
    private static final String[] allColumns = {
        CarEntry.COLUMN_PLATE, CarEntry.COLUMN_MAKE,
        CarEntry.COLUMN_MODEL, CarEntry.COLUMN_TIMESTAMP,
        CarEntry.COLUMN_USERID
    };

    /**
     * Gets the cached cars
     *
     * @param context
     * @return account
     */
    public static List<CarInfo> getCahedList(Context context) {

    	List<CarInfo> carInfo = new ArrayList<CarInfo>();
        CacheDbHelper dbHelper = new CacheDbHelper(context);
        SQLiteDatabase dbReader = dbHelper.getReadableDatabase();
        if (dbReader == null) {
            return null;
        }

        Cursor cursor = dbReader.query(CarEntry.TABLE_NAME, allColumns, null,
                null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
        	CarInfo c = new CarInfo();
            while (cursor.moveToNext()) {
            	c.setPlate(cursor.getString(cursor
                        .getColumnIndex(CarEntry.COLUMN_PLATE)));
            	c.setMake(cursor.getString(cursor
                        .getColumnIndex(CarEntry.COLUMN_MAKE)));
            	c.setModel(cursor.getString(cursor
                                .getColumnIndex(CarEntry.COLUMN_MODEL)));
            	c.setTimestamp(cursor.getLong(cursor
                                        .getColumnIndex(CarEntry.COLUMN_TIMESTAMP)));
            	c.setUserid(cursor.getLong(cursor
                                                .getColumnIndex(CarEntry.COLUMN_USERID)));
            	carInfo.add(c);
            }
        }
        dbHelper.close();
        return carInfo;
    }

    /**
     * Write a car detail
     *
     * @param context
     * @param
     * @return
     */
    public static boolean addCar(Context context, CarInfo c) {
        CacheDbHelper dbHelper = new CacheDbHelper(context);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        if (dbWriter == null) {
            return false;
        }

        long result =  dbWriter.insert(CarEntry.TABLE_NAME, null, getAllContentValuesCarInfo(c));
        
        dbHelper.close();
        return result != -1;
    }

    private static ContentValues getAllContentValuesCarInfo(CarInfo c) {
        ContentValues values = new ContentValues();
        values.put(AccountEntry.COLUMN_ACCESS_TOKEN, account.getAccessToken());
        values.put(AccountEntry.COLUMN_ACCOUNT_TYPE, account.getAccountType());
        values.put(AccountEntry.COLUMN_FULL_NAME, account.getFullName());
        values.put(AccountEntry.COLUMN_USERNAME, account.getUsername());
        return values;
    }

    private static ContentValues getAllContentValuesLocation(Location location) {
        ContentValues values = new ContentValues();
        values.put(LocationEntry.COLUMN_CURRENT_LAT, location.getLatitude());
        values.put(LocationEntry.COLUMN_CURRENT_LNG, location.getLongitude());
        return values;
    }

}
