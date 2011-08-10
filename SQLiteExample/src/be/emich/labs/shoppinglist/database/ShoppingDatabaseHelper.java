package be.emich.labs.shoppinglist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShoppingDatabaseHelper extends SQLiteOpenHelper {

	public static final int DB_VERSION = 1;
	public static final String DB_NAME = "shopping.db";
	
	public static final String TABLE_ITEMS="items";
	public static class TableItems {
		public static final String ID = "_id";
		public static final String ITEM = "item";
	}
	
	public ShoppingDatabaseHelper(Context ctx) {
		super(ctx,DB_NAME,null,DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "+TABLE_ITEMS+"("+TableItems.ID+" integer primary key autoincrement,"+TableItems.ITEM+" text null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table items");
		onCreate(db);
	}

}
