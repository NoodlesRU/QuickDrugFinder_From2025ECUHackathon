package com.example.myapplicationifejfe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;



public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "medicines.db";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    private static final String TABLE_MEDICINES = "medicines";

    // Columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // ✅ Implement onCreate() properly

    public String textname() {
        SQLiteDatabase db = this.getWritableDatabase();
        StringBuilder str = new StringBuilder();
        Cursor cur =  db.query("medicines", null, null, null, null, null, null, null);
        for (int x = 0; x < cur.getCount(); x++) {
            str.append(cur.getString(x));
        }
        return str.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MEDICINES + " (" +
                "DRUG_NAME" + "  TEXT, " +
                "TARGET_NAME" + " TEXT, " +
                "ACT_COMMENT" + " TEXT, " +
                "ORGANISM" + " TEXT )";
        db.execSQL(createTable);
        //SQLiteDatabase.openDatabase("C:\\Users\\zacka\\AndroidStudioProjects\\MyApplicationifejfe\\app\\assets\\drug.db", null, 0);


        // Attach the source database
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("DRUG_NAME", "azaribine");
        values.put("TARGET_NAME", "Orotidine 5'-phosphate decarboxylase");
        values.put("ACT_COMMENT", "Inhibition of yeast ODCase");
        values.put("ORGANISM", "Saccharomyces cerevisiae");

        // after adding all values we are passing
        // content values to our table.
        db.insert("medicines", null, values);

        // at last we are closing our
        // database after adding database.
        db.close();

    }

    // ✅ Implement onUpgrade() properly
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINES);
        onCreate(db);
    }

    // ✅ Insert Medicine Data
    public void insertMedicine(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_MEDICINES, null, values);
        db.close();
    }

    // ✅ Get All Medicines
    public Cursor getAllMedicines() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MEDICINES, null);
    }
}

