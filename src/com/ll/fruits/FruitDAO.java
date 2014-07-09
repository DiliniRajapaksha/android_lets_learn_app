package com.ll.fruits;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FruitDAO {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * @param dbHelper
     */
    public FruitDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public List<Fruit> findAllFruits() {
        db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM fruit";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<Fruit> fruits = new ArrayList<Fruit>();
        while (!cursor.isAfterLast()) {
            int imageId = cursor.getInt(cursor.getColumnIndex("imageID"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String voicePath = cursor.getString(cursor
                    .getColumnIndex("voicePath"));
            Fruit fruit = createFruit(imageId, name, voicePath);
            fruits.add(fruit);
            Log.d("Dilini", name + " fruit added to list from DB");
            cursor.moveToNext();
        }
        return fruits;

    }

    private Fruit createFruit(int imageId, String name, String voicePath) {
        Fruit fruit = new Fruit();
        fruit.setImgResId(imageId);
        fruit.setName(name);
        fruit.setVoicePath(voicePath);
        return fruit;
    }

}
