package be.emich.labs.shoppinglist;

import be.emich.labs.shoppinglist.database.ShoppingDatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ItemDetailActivity extends Activity implements OnClickListener, TextWatcher {
	private EditText editTextItem;
	private Button buttonSave;
	private Button buttonCancel;
	
	private int id;
	private String item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		id = getIntent().getIntExtra(ShoppingDatabaseHelper.TableItems.ID, -1);
		item = getIntent().getStringExtra(ShoppingDatabaseHelper.TableItems.ITEM);

		buttonSave = (Button)findViewById(R.id.buttonSave);
		buttonSave.setOnClickListener(this);

		buttonCancel = (Button)findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(this);
		
		editTextItem = (EditText)findViewById(R.id.editTextItem);
		editTextItem.addTextChangedListener(this);
		editTextItem.setText(item);
	}

	public void onClick(View v) {
		if(v.getId()==R.id.buttonSave){
			Intent data = new Intent();
			data.putExtra(ShoppingDatabaseHelper.TableItems.ID, id);
			data.putExtra(ShoppingDatabaseHelper.TableItems.ITEM, editTextItem.getText().toString());
			setResult(RESULT_OK, data);
		}else if(v.getId()==R.id.buttonCancel){
			setResult(RESULT_CANCELED);
		}
		finish();
	}

	public void afterTextChanged(Editable s) {
		;
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		;
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if(s!=null && s.length()>0)buttonSave.setEnabled(true);
		else buttonSave.setEnabled(false);
	}
}
