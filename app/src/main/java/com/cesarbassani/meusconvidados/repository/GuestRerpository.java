package com.cesarbassani.meusconvidados.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cesarbassani.meusconvidados.constants.DataBaseConstants;
import com.cesarbassani.meusconvidados.entities.GuestEntity;

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
}