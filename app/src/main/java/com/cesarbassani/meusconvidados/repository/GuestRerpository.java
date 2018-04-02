package com.cesarbassani.meusconvidados.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cesarbassani.meusconvidados.constants.DataBaseConstants;
import com.cesarbassani.meusconvidados.entities.GuestEntity;

import java.util.ArrayList;
import java.util.List;

public class GuestRerpository {

    private static GuestRerpository INSTANCE;
    private GuestDataBaseHelper mGuestDataBaseHelper;

    private GuestRerpository(Context context) {
        this.mGuestDataBaseHelper = new GuestDataBaseHelper(context);
    }

    public static synchronized GuestRerpository getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new GuestRerpository(context);
        }

        return INSTANCE;
    }

    public Boolean insert(GuestEntity guestEntity) {
        try {

            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guestEntity.getName());
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guestEntity.getConfirmed());
            sqLiteDatabase.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<GuestEntity> getGuestsByQuery(String query) {

        List<GuestEntity> list = new ArrayList<>();

        try {

            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    GuestEntity guestEntity = new GuestEntity();
                    guestEntity.setId(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)));
                    guestEntity.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)));
                    guestEntity.setConfirmed(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)));

                    list.add(guestEntity);
                }
            }

            if (cursor != null) {
                cursor.close();
            }

        } catch (Exception e) {
            return list;
        }

        return list;
    }

    public GuestEntity load(int id) {

        GuestEntity guestEntity = new GuestEntity();

        try {

            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getReadableDatabase();

            String[] projection = {
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            };

            String selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            Cursor cursor = sqLiteDatabase.query(DataBaseConstants.GUEST.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                guestEntity.setId(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)));
                guestEntity.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)));
                guestEntity.setConfirmed(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)));
            }

            if (cursor != null) {
                cursor.close();
            }

//            sqLiteDatabase.rawQuery("select * from guest where id = " + String.valueOf(id), null) ;

            return guestEntity;

        } catch (Exception e) {
            return guestEntity;
        }
    }
}