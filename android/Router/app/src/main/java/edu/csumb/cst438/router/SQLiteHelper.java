package edu.csumb.cst438.router;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by pico on 10/15/16.
 */

public class SQLiteHelper {

    public SQLiteHelper() {}

    public static abstract class UserSettings implements BaseColumns {
        public static final String TABLE_NAME = "UserSettings";
        public static final String COLLUMN_NAME_USERNAME = "Username";
        public static final String COLLUMN_NAME_BIO = "Bio";
        public static final String COLLUM_NAME_PRIVACY = "Privacy";
        public static final String COLLUMN_NAME_EMAIL = "Email";
        public static final String COLLUMN_NAME_USERID = "UserId";
    }

    public static abstract class Routes implements BaseColumns {
        public static final String TABLE_NAME = "Routes";
        public static final String COLLUMN_NAME_ROUTEID = "RouteId";
        public static final String COLLUMN_NAME_ROUTE = "Route";
        public static final String COLLUMN_NAME_START_POINT_LAT = "StartPointLat";
        public static final String COLLUMN_NAME_START_POINT_LON = "StartPointLon";
        public static final String COLLUMN_NAME_ROUTE_NAME = "RouteName";
        public static final String COLLUMN_NAME_USER_ID = "UserId";
    }

    private static final String TEXT_STYPE = " TEXT";
    private static final String COMMA_SEP = " ,";

    private static final String CREATE_USER_SETTINGS =
            "CREATE TABLE " + UserSettings.TABLE_NAME + " (" +
                UserSettings._ID + " INTEGER PRIMARY KEY, " +
                UserSettings.COLLUMN_NAME_USERNAME + TEXT_STYPE + COMMA_SEP +
                UserSettings.COLLUMN_NAME_BIO + TEXT_STYPE + COMMA_SEP +
                UserSettings.COLLUM_NAME_PRIVACY + TEXT_STYPE + COMMA_SEP +
                UserSettings.COLLUMN_NAME_EMAIL + TEXT_STYPE + COMMA_SEP +
                    UserSettings.COLLUMN_NAME_USERID + TEXT_STYPE + COMMA_SEP + " )";

    private static final String DELETE_USER_SETTINGS =
            "DROP TABLE IF EXISTS " + UserSettings.TABLE_NAME;

    private static final String CREATE_ROUTES =
            "CREATE TABLE " + Routes.TABLE_NAME + " (" +
                Routes._ID + " INTEGER PRIMARY KEY, " +
                Routes.COLLUMN_NAME_ROUTEID + TEXT_STYPE + COMMA_SEP +
                Routes.COLLUMN_NAME_ROUTE + TEXT_STYPE + COMMA_SEP +
                Routes.COLLUMN_NAME_START_POINT_LAT + TEXT_STYPE + COMMA_SEP +
                Routes.COLLUMN_NAME_START_POINT_LON + TEXT_STYPE + COMMA_SEP +
                Routes.COLLUMN_NAME_ROUTE_NAME + TEXT_STYPE + COMMA_SEP +
                Routes.COLLUMN_NAME_USER_ID + TEXT_STYPE + COMMA_SEP + " )";

    private static final String DELTE_ROUTES =
            "DROP TABLE IF EXISTS " + Routes.TABLE_NAME;

    public static class DeBra extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "DeBra.db";
        private static DeBra me;

        public DeBra(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            me = this;
        }

        public static DeBra getInstance() {
            return me;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_SETTINGS);
            db.execSQL(CREATE_ROUTES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DELETE_USER_SETTINGS);
            db.execSQL(DELTE_ROUTES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}

