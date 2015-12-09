package com.himani.parkingright.sqlite;

import java.util.ArrayList;
import java.util.HashSet;
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
    public static HashSet<CarInfo> getCahedList(Context context) {

    	HashSet<CarInfo> carInfo = new HashSet<CarInfo>();
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
        values.put(CarEntry.COLUMN_PLATE, c.getPlate());
        values.put(CarEntry.COLUMN_MAKE, c.getMake());
        values.put(CarEntry.COLUMN_MODEL, c.getModel());
        values.put(CarEntry.COLUMN_TIMESTAMP, c.getTimestamp());
        return values;
    }

}
