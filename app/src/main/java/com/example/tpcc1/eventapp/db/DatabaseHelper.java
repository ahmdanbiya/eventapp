package com.example.tpcc1.eventapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tpcc1.eventapp.model.EventResult;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = DatabaseHelper.class.getSimpleName();
    // Database Info
    private static final String DATABASE_NAME = "eventDb";
    private static final int DATABASE_VERSION = 4;

    // Table Names
    private static final String TABLE_EVENTS = "events";

    // Post Table Columns
    private static final String KEY_ID = "id";
    private static final String KEY_EVENT_ID = "eventid";
    private static final String KEY_EVENT_NAME = "eventname";
    private static final String KEY_EVENT_TYPE_NAME = "eventtypename";
    private static final String KEY_EVENT_LOCATION = "eventlocation";
    private static final String KEY_EVENT_DESC = "eventdesc";
    private static final String KEY_EVENT_STARTTIME = "starttime";
    private static final String KEY_EVENT_ENDTIME = "endtime";
    private static final String KEY_EVENT_LATITUDE = "eventlatitude";
    private static final String KEY_EVENT_LONGITUDE = "eventlongitude";
    private static final String KEY_EVENT_PICTURE = "eventpicture";

    private static DatabaseHelper sInstance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_EVENT_ID + " TEXT," +
                KEY_EVENT_NAME + " TEXT," +
                KEY_EVENT_TYPE_NAME + " TEXT," +
                KEY_EVENT_LOCATION + " TEXT," +
                KEY_EVENT_DESC + " TEXT," +
                KEY_EVENT_STARTTIME + " TEXT," +
                KEY_EVENT_ENDTIME + " TEXT," +
                KEY_EVENT_LATITUDE + " TEXT," +
                KEY_EVENT_LONGITUDE + " TEXT," +
                KEY_EVENT_PICTURE + " TEXT" +
                ")";

        db.execSQL(CREATE_EVENTS_TABLE);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
            onCreate(db);
        }
    }

    // Insert a post into the database
    public void addEvent(EventResult event) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_EVENT_ID, event.getId());
            values.put(KEY_EVENT_NAME, event.getEventname());
            values.put(KEY_EVENT_TYPE_NAME, event.getTypename());
            values.put(KEY_EVENT_LOCATION, event.getEventlocation());
            values.put(KEY_EVENT_DESC, event.getEventdesc());
            values.put(KEY_EVENT_STARTTIME, event.getStarttime());
            values.put(KEY_EVENT_ENDTIME, event.getEndtime());
            values.put(KEY_EVENT_LATITUDE, event.getEventlatitude());
            values.put(KEY_EVENT_LONGITUDE, event.getEventlongitude());
            values.put(KEY_EVENT_PICTURE, event.getEventpicture());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.delete(TABLE_EVENTS,KEY_EVENT_ID +" = ?",new String[] {event.getId()});
            db.insertOrThrow(TABLE_EVENTS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database" + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }
    // Get all posts in the database
    public List<EventResult> getAllEvents() {
        List<EventResult> events = new ArrayList<>();
        String EVENTS_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        TABLE_EVENTS);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(EVENTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    EventResult newEvent = new EventResult();
                    newEvent.setId(cursor.getString(cursor.getColumnIndex(KEY_EVENT_ID)));
                    newEvent.setEventname(cursor.getString(cursor.getColumnIndex(KEY_EVENT_NAME)));
                    newEvent.setTypename(cursor.getString(cursor.getColumnIndex(KEY_EVENT_TYPE_NAME)));
                    newEvent.setEventlocation(cursor.getString(cursor.getColumnIndex(KEY_EVENT_LOCATION)));
                    newEvent.setEventdesc(cursor.getString(cursor.getColumnIndex(KEY_EVENT_DESC)));
                    newEvent.setStarttime(cursor.getString(cursor.getColumnIndex(KEY_EVENT_STARTTIME)));
                    newEvent.setEndtime(cursor.getString(cursor.getColumnIndex(KEY_EVENT_ENDTIME)));
                    newEvent.setEventlatitude(cursor.getString(cursor.getColumnIndex(KEY_EVENT_LATITUDE)));
                    newEvent.setEventlongitude(cursor.getString(cursor.getColumnIndex(KEY_EVENT_LONGITUDE)));
                    newEvent.setEventpicture(cursor.getString(cursor.getColumnIndex(KEY_EVENT_PICTURE)));
                    events.add(newEvent);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return events;
    }

}

