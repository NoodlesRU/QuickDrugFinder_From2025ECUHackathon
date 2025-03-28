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
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MEDICINES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(createTable);
        SQLiteDatabase.openDatabase("./app/assets/drug.db", null, 0);

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
