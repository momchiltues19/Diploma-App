package mom.todorov.elsys.org.diplomaappv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnect extends SQLiteOpenHelper {

    private final static int DB_VERSION = 10;

    public DBConnect(Context context) {
        super(context, "testDb.db", null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table logins (userId Integer primary key autoincrement, "+
                " username text, password text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            System.out.println("UPGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
            onCreate(db);
            if (oldVersion<10){
                String query = "create table logins (userId Integer primary key autoincrement, "+
                        " username text, password text)";
                db.execSQL(query);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // super.onDowngrade(db, oldVersion, newVersion);
        System.out.println("DOWNGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
    }

    public User insertUser (User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userId=database.insert("logins", null, values);
        database.close();
        return queryValues;
    }

    public User getUser (String username){
        String query = "Select userId, password from logins where username ='"+username+"'";
        User myUser = new User(0,username,"");
        Award myAward = new Award(1, "as", "as", ".com");
        UserAward myUserAward = new UserAward(1,1,1);
        Mission myMission = new Mission(1, "as", "as", ".com");
        UserMission myUserMisson = new UserMission(1,1,1);
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myUser.userId=cursor.getLong(0);
                myUser.password=cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return myUser;
    }
}
