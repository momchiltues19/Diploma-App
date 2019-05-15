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
        String queryRoles = "create table Roles (roleId Integer primary key autoincrement," +
                " roleName text)";
        db.execSQL(queryRoles);

        String queryAvatars = "create table Avatars (avatarId Integer primary key autoincrement," +
                " imageLink text)";
        db.execSQL(queryAvatars);

        String queryUsers = "create table Users (userId Integer primary key autoincrement, " +
                " username text, password text, avatarId Integer, roleId Integer, " +
                " experiancePoint Integer, distanceWalked Integer)";
        db.execSQL(queryUsers);

        String queryPlaceTypes = "create table PlaceTypes (placeTypeId Integer primary key autoincrement, " +
                " typeName text)";
        db.execSQL(queryPlaceTypes);

        String queryPlaces = "create table Places (placeId Integer primary key autoincrement, " +
                " placeName text, description text, imageLink text, level Integer, " +
                " typeId Intger, coordinates text)";
        db.execSQL(queryPlaces);

        String queryUserPlaces = "create table UserPlaces (userPlaceId Integer primary key autoincrement, " +
                " userId Integer, placeId Integer)";
        db.execSQL(queryUserPlaces);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            System.out.println("UPGRADE DB from " + oldVersion + " to " + newVersion);
            onCreate(db);
            if (oldVersion<10){
                String queryRoles = "create table Roles (roleId Integer primary key autoincrement," +
                        " roleName text)";
                db.execSQL(queryRoles);

                String queryAvatars = "create table Avatars (avatarId Integer primary key autoincrement," +
                        " imageLink text)";
                db.execSQL(queryAvatars);

                String queryUsers = "create table Users (userId Integer primary key autoincrement, " +
                        " username text, password text, avatarId Integer, roleId Integer, " +
                        " experiancePoint Integer, distanceWalked Integer)";
                db.execSQL(queryUsers);

                String queryPlaceTypes = "create table PlaceTypes (placeTypeId Integer primary key autoincrement, " +
                        " typeName text)";
                db.execSQL(queryPlaceTypes);

                String queryPlaces = "create table Places (placeId Integer primary key autoincrement, " +
                        " placeName text, description text, imageLink text, level Integer, " +
                        " typeId Intger, coordinates text)";
                db.execSQL(queryPlaces);

                String queryUserPlaces = "create table UserPlaces (userPlaceId Integer primary key autoincrement, " +
                        " userId Integer, placeId Integer)";
                db.execSQL(queryUserPlaces);
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
        //values.put("avatarId", queryValues.avatarId);
        //values.put("roleId", queryValues.roleId);
        //values.put("experiencePoints", queryValues.experiencePoints);
        //values.put("distanceWalked", queryValues.distanceWalked);
        queryValues.userId = database.insert("Users", null, values);
        database.close();
        return queryValues;
    }

    //TODO add updateUserPassword

    public User getUser (String username){
        String query = "Select userId, password from Users where username ='"+username+"'";
        User myUser = new User(0,username,"");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myUser.userId = cursor.getLong(0);
                myUser.password = cursor.getString(1);
                //myUser.avatarId = cursor.getLong(2);
                //myUser.roleId = cursor.getLong(3);
                //myUser.experiencePoints = cursor.getLong(4);
                //myUser.distanceWalked = cursor.getLong(5);
            } while (cursor.moveToNext());
        }
        return myUser;
    }

    public Avatar insertAvatar (Avatar queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imageLink", queryValues.imageLink);
        queryValues.avatarId = database.insert("Avatars", null, values);
        database.close();
        return queryValues;
    }

    public Avatar getAvatar (String username){
        String query = "Select Avatars.avatarId, imageLink from Avatars left join Users on Avatars.avatarId = Users.avatarId where username ='"+username+"'";
        Avatar myAvatar = new Avatar(0,"");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myAvatar.avatarId = cursor.getLong(0);
                myAvatar.imageLink = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return myAvatar;
    }

    public Place insertPlace (Place queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("placeName", queryValues.placeName);
        values.put("description", queryValues.description);
        values.put("imageLink", queryValues.imageLink);
        values.put("level", queryValues.level);
        values.put("typeId", queryValues.typeId);
        values.put("coordinates", queryValues.coordinates);
        queryValues.placeId = database.insert("Places", null, values);
        database.close();
        return queryValues;
    }
}
