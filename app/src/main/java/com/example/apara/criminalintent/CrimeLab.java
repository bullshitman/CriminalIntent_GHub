package com.example.apara.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.apara.criminalintent.database.CrimeBaseHelper;
import com.example.apara.criminalintent.database.CrimeDbScheme;

import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeDbScheme.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeDbScheme.CrimeTable.Cols.TITLE, crime.getId().toString());
        values.put(CrimeDbScheme.CrimeTable.Cols.DATE, crime.getId().toString());
        values.put(CrimeDbScheme.CrimeTable.Cols.SOLVED, crime.getId().toString());
        return values;
    }

    public List<Crime> getCrimes() {
        return null;
    }

    public Crime getCrime(UUID id) {

        return null;
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(CrimeDbScheme.CrimeTable.NAME, values,
                CrimeDbScheme.CrimeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeDbScheme.CrimeTable.NAME, null, values);
    }

    public void removeCrime(Crime c) {

    }
}
