package be.emich.labs.shoppinglist;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CursorAdapter;
import android.widget.ListView;
import be.emich.labs.shoppinglist.adapter.ShoppingItemsAdapter;
import be.emich.labs.shoppinglist.database.ShoppingDatabaseHelper;

public class ShoppingListExample extends ListActivity {
    protected SQLiteDatabase db;
    protected Cursor c;
    protected CursorAdapter cursorAdapter;
    
    public static final int MENU_ADD=100;
    public static final int MENU_CLEAR=101;
    public static final int MENU_DELETE=102;
    public static final int MENU_EDIT=103;
    
    public static final int REQ_ITEMDETAIL=200;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        db = (new ShoppingDatabaseHelper(this)).getWritableDatabase();
        c=db.query(ShoppingDatabaseHelper.TABLE_ITEMS, null, null, null, null, null, null);
        
        logContents(c);
        
        cursorAdapter = new ShoppingItemsAdapter(this, c);
        setListAdapter(cursorAdapter);
        
        registerForContextMenu(getListView());
    }
    
    @Override
    protected void onDestroy() {
    	if(db!=null)db.close();
    	//We do not need to close the cursor as it will be closed by the cursor adapter.
    	super.onDestroy();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0,MENU_ADD,0,R.string.add).setIcon(android.R.drawable.ic_menu_add);
    	menu.add(0,MENU_CLEAR,0,R.string.clear).setIcon(android.R.drawable.ic_menu_delete);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case MENU_ADD:
    		Intent i = new Intent(this,ItemDetailActivity.class);
    		startActivityForResult(i, REQ_ITEMDETAIL);
    		return true;
    	case MENU_CLEAR:
    		db.delete(ShoppingDatabaseHelper.TABLE_ITEMS, null, null);
    		cursorAdapter.getCursor().requery();
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode==REQ_ITEMDETAIL && resultCode==RESULT_OK){
    		int id = data.getIntExtra(ShoppingDatabaseHelper.TableItems.ID, -1);
    		String item = data.getStringExtra(ShoppingDatabaseHelper.TableItems.ITEM);
    		save(id,item);
    	} else super.onActivityResult(requestCode, resultCode, data);
    }
    
    private void save(int id,String item){
    	ContentValues cv = new ContentValues();
    	cv.put(ShoppingDatabaseHelper.TableItems.ITEM, item);
    	if(id==-1){
    		db.insert(ShoppingDatabaseHelper.TABLE_ITEMS, "", cv);
    		Log.v(getClass().getName(),"Update");
    	}
    	else{
    		db.update(ShoppingDatabaseHelper.TABLE_ITEMS, cv, ShoppingDatabaseHelper.TableItems.ID+"="+id, null);
    		Log.v(getClass().getName(),"Insert");
    	}
    	c.requery();
    }
    
    private void logContents(Cursor c){
    	if(c==null)return;
    	
    	c.moveToFirst();
    	while(!c.isAfterLast()){
    		Log.v(getClass().getName(),String.format("Row with id %d : %s",
    				c.getInt(0),
    				c.getString(c.getColumnIndex(ShoppingDatabaseHelper.TableItems.ITEM))
    				));
    		c.moveToNext();
    	}
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Cursor c = cursorAdapter.getCursor();
    	c.moveToPosition(position);
    	
    	Intent i = new Intent(this,ItemDetailActivity.class);
    	i.putExtra(ShoppingDatabaseHelper.TableItems.ID, (int)id);
    	i.putExtra(ShoppingDatabaseHelper.TableItems.ITEM, 
    				c.getString(c.getColumnIndex(ShoppingDatabaseHelper.TableItems.ITEM))
    			);
    	startActivityForResult(i, REQ_ITEMDETAIL);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	menu.setHeaderTitle("Item");
    	menu.add(0,MENU_EDIT,0,R.string.edit);
    	menu.add(0,MENU_DELETE,0,R.string.delete);
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
    	switch(item.getItemId()){
    	case MENU_EDIT:
    		Cursor c = cursorAdapter.getCursor();
    		String itemName = c.getString(c.getColumnIndex(ShoppingDatabaseHelper.TableItems.ITEM));
    		
    		Intent i = new Intent(this,ItemDetailActivity.class);
    		i.putExtra(ShoppingDatabaseHelper.TableItems.ID, (int)info.id);
    		i.putExtra(ShoppingDatabaseHelper.TableItems.ITEM, itemName);
    		startActivityForResult(i, REQ_ITEMDETAIL);
    		
    		return true;
    	case MENU_DELETE:
    		db.delete(ShoppingDatabaseHelper.TABLE_ITEMS, "_id=?", new String[]{info.id+""});
    		cursorAdapter.getCursor().requery();
    		return true;
    	}
    	return super.onContextItemSelected(item);
    }
}