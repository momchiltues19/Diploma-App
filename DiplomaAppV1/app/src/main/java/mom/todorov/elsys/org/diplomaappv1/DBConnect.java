package mom.todorov.elsys.org.diplomaappv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnect extends SQLiteOpenHelper {

    //use to detect upgrade/downgrade of the DB schema (source: https://stackoverflow.com/questions/22209046/example-and-explanation-android-studio-login-activity-template-generated-acti)
    private final static int DB_VERSION = 10;

    public DBConnect(Context context) {
        super(context, "testDb.db", null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table Roles (roleId Intger primary key autoincrement," +
                " rname text)";
        db.execSQL(query1);

        String query2 = "create table Avatars (avatarId Intger primary key autoincrement," +
                " image text)";
        db.execSQL(query2);

        String query3 = "create table Users (userId Integer primary key autoincrement, " +
                " username text, password text, avatarId Integer, roleId Integer, " +
                " experiancePoint Integer, distanceWalked Integer)";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            System.out.println("UPGRADE DB from " + oldVersion + " to " + newVersion);
            onCreate(db);
            if (oldVersion<10){
                String query1 = "create table Roles (roleId Intger primary key autoincrement," +
                        " rName text)";
                db.execSQL(query1);

                String query2 = "create table Avatars (avatarId Intger primary key autoincrement," +
                        " image text)";
                db.execSQL(query2);

                String query3 = "create table Users (userId Integer primary key autoincrement, " +
                        " username text, password text, avatarId Integer, roleId Integer, " +
                        " experiencePoints Integer, distanceWalked Integer)";
                db.execSQL(query3);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // super.onDowngrade(db, oldVersion, newVersion);
        System.out.println("DOWNGRADE DB from " + oldVersion + " to " + newVersion);
    }

    public User insertUser (User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        values.put("avatarId", queryValues.avatarId);
        values.put("roleId", queryValues.roleId);
        values.put("experiencePoints", queryValues.experiencePoints);
        values.put("distanceWalked", queryValues.distanceWalked);
        queryValues.userId = database.insert("Users", null, values);
        database.close();
        return queryValues;
    }

    //TODO add updateUserPassword

    public User getUser (String username){
        String query = "Select userId, password, avatarId, roleId, experiencePoints, distanceWalked from Users where username ='"+username+"'";
        User myUser = new User(0,username,"");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myUser.userId = cursor.getLong(0);
                myUser.password = cursor.getString(1);
                myUser.avatarId = cursor.getLong(2);
                myUser.roleId = cursor.getLong(3);
                myUser.experiencePoints = cursor.getLong(4);
                myUser.distanceWalked = cursor.getLong(5);
            } while (cursor.moveToNext());
        }
        return myUser;
    }
}
