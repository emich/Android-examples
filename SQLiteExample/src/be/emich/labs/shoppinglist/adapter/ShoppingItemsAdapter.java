package be.emich.labs.shoppinglist.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import be.emich.labs.shoppinglist.R;
import be.emich.labs.shoppinglist.database.ShoppingDatabaseHelper;

public class ShoppingItemsAdapter extends CursorAdapter {

	public ShoppingItemsAdapter(Context ctx,Cursor c) {
		super(ctx,c);
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView textView = (TextView)view.getTag(R.id.textViewItem);
		textView.setText(cursor.getString(cursor.getColumnIndex(ShoppingDatabaseHelper.TableItems.ITEM)));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.listitem, parent,false);
		Log.v(getClass().getName(),"textViewItem: "+v.findViewById(R.id.textViewItem));
		Log.v(getClass().getName(),"textViewItem class: "+v.getClass().getName());
		v.setTag(R.id.textViewItem,v.findViewById(R.id.textViewItem));
		return v;
	}

}
