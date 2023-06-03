package com.example.qrcodegeneratorproductview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Databasehelper extends SQLiteOpenHelper {
    QR_scanner Qs =new QR_scanner();

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "PR_DATABASE";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "PR_LIST";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our product name column
    private static final String PR_NAME = "pr_name";

    // below variable id for our product price column.
    private static final String PR_PRICE = "pr_price";

    // below variable for our product quantity column.
    private static final String PR_QUANTITY = "pr_quantity";
    int pr_quantity;

    // creating a constructor for our database handler.
    public Databasehelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PR_NAME + " TEXT,"
                + PR_PRICE + " INTEGER,"
                + PR_QUANTITY + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public Boolean insertuserdata(String pr_Name, int pr_Price, int pr_Quantity) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(PR_NAME, pr_Name);
        values.put(PR_PRICE, pr_Price);
        values.put(PR_QUANTITY, pr_Quantity);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    /*public Boolean updateuserdata(String pr_name, int pr_price)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Quantity",pr_quantity-1);
        Cursor cursor = DB.rawQuery("Select * from PR_LIST where pr_name = ?", new String[]{pr_name});
        if (cursor.getCount() > 0) {
            long result = DB.update(TABLE_NAME,contentValues,"pr_name=?", new String[]{pr_name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }*/
    public Boolean deletedata (String pr_name)
    {
        /*SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Quantity",pr_quantity-1);
        Cursor cursor = DB.rawQuery("Select * from PR_LIST where pr_name = ?", new String[]{pr_name});
        if (cursor.getCount() > 0) {
            long result = DB.update(TABLE_NAME,contentValues,"pr_name=?", new String[]{pr_name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }*/
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PR_LIST where pr_name = ?", new String[]{pr_name});
        if (cursor.getCount() > 0) {
            long result = DB.delete(TABLE_NAME, "pr_name=?", new String[]{pr_name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PR_LIST", null);
        return cursor;
    }
}