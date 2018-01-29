package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.Setting;

/**
 * Created by PSI_DEV_07 on 1/29/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "iptv";
    private static final String TABLE_SETTING = "setting";
    private static final String KEY_ID = "id";
    private static final String KEY_SERVER_IP = "server_ip";
    private static final String KEY_SERVER_PORT = "server_port";
    private static final String KEY_LOCAL_IP = "local_ip";
    private static final String KEY_MAC = "mac_address";
    private static final String KEY_ENABLED_PASS = "enabled_password";
    private static final String KEY_PASS = "pass";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SETTING_TABLE = "CREATE TABLE " + TABLE_SETTING + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SERVER_IP + " TEXT,"
                + KEY_SERVER_PORT + " NUMBER,"
                + KEY_LOCAL_IP + " TEXT,"
                + KEY_MAC + " TEXT,"
                + KEY_ENABLED_PASS + " BOOLEAN,"
                + KEY_PASS + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_SETTING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        onCreate(sqLiteDatabase);
    }

    public void addSetting(Setting setting){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVER_IP, setting.getServerIp());
        values.put(KEY_SERVER_PORT, setting.getServerPort());
        values.put(KEY_LOCAL_IP, setting.getLocalIp());
        values.put(KEY_MAC, setting.getMac());
        values.put(KEY_ENABLED_PASS, setting.getEnablePassword());
        values.put(KEY_PASS, setting.getPassword());

        // Inserting Row
        db.insert(TABLE_SETTING, null, values);
        db.close(); // Closing database connection
    }

    public Setting getSetting(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resCount = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+TABLE_SETTING+" WHERE id=0 LIMIT 1);",null);
        resCount.moveToFirst();
        if(resCount.getInt(0) == 1){

        Cursor cursor = db.query(TABLE_SETTING, new String[] { KEY_ID,
                        KEY_SERVER_IP, KEY_SERVER_PORT, KEY_LOCAL_IP,
                KEY_MAC, KEY_ENABLED_PASS, KEY_PASS}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null) {
            cursor.moveToFirst();

            Setting setting = new Setting(cursor.getInt(0),
                    cursor.getString(1), cursor.getInt(2), cursor.getString(3),
                    cursor.getString(4), Boolean.getBoolean(cursor.getString(5)),
                    cursor.getString(6));
            return setting;
        }else{
            return  null;
        }

    }

    public int updateSetting(Setting setting) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVER_IP, setting.getServerIp());
        values.put(KEY_SERVER_PORT, setting.getServerPort());
        values.put(KEY_LOCAL_IP, setting.getLocalIp());
        values.put(KEY_MAC, setting.getMac());
        values.put(KEY_ENABLED_PASS, setting.getEnablePassword());
        values.put(KEY_PASS, setting.getPassword());

        return db.update(TABLE_SETTING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(setting.getId()) });
    }

    public int updateServerIp(String serverIp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVER_IP, serverIp);

        return db.update(TABLE_SETTING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(0) });
    }

    public int updateServerPort(String serverPort) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVER_PORT, serverPort);

        return db.update(TABLE_SETTING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(0) });
    }

    public int updateLocalIp(String localIp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOCAL_IP, localIp);

        return db.update(TABLE_SETTING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(0) });
    }

    public int updateEnabledPassword(Boolean enabledPass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ENABLED_PASS, enabledPass);

        return db.update(TABLE_SETTING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(0) });
    }

    public int updatePassword(String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASS, password);

        return db.update(TABLE_SETTING, values, KEY_ID + " = ?",
                new String[] { String.valueOf(0) });
    }
}
