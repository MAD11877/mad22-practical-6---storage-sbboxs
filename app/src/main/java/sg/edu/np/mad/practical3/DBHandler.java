package sg.edu.np.mad.practical3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.UserData;
import android.widget.Toast;

public class DBHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "accounts.db"; //DB Name
    public static String ACCOUNTS = "Accounts"; //Table Name
    public static String COLUMN_USERNAME = "Username";//Column 1 and data type
    public static String COLUMN_PASSWORD = "Password";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "ID";
    public static String COLUMN_FOLLOWED = "Followed";
    public static int DATABASE_VERSION = 1;
    public  DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + ACCOUNTS +" ( " + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT," +COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_ID + " TEXT," + COLUMN_FOLLOWED + " TEXT" + " ) ";
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase(); //Refer to DBHandler
        ContentValues values = new ContentValues(); //Store all our data in application
        //get data and store
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(String.valueOf(COLUMN_FOLLOWED), user.isFollowed());

        db.insert(ACCOUNTS, null, values);

        db.close(); //Always remember open and closeeeee
    }

    public User findUser(String userName){
        String query = "SELECT * FROM " + ACCOUNTS + " WHERE " + COLUMN_USERNAME + "=\"" + userName + "\"";
        //SELECT * FROM Accounts WHERE username = "???"
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null); //This will return what is found

        User queryData = new User();
        if (cursor.moveToFirst()){
            queryData.setName(cursor.getString(0));
            queryData.setPassword(cursor.getString(1));
            queryData.setDescription(cursor.getString(2));
            queryData.setId(Integer.parseInt(cursor.getString(3)));
            queryData.setFollowed(Boolean.parseBoolean(cursor.getString(4)));
        }
        else{
            queryData = null;
        }
        db.close();;
        return  queryData;
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + ACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return  cursor;
    }

    public Boolean updateUserData(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, user.getName());
        cv.put(COLUMN_PASSWORD, user.getPassword());
        cv.put(COLUMN_DESCRIPTION, user.getDescription());
        cv.put(COLUMN_ID, user.getId());
        cv.put(String.valueOf(COLUMN_FOLLOWED),user.isFollowed());
        db.update(ACCOUNTS,cv,"Username=?", new String[]{user.getName()});
        return true;
    }
}
