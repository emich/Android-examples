package com.emakina.holderpattern;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HolderPatternActivity extends ListActivity {
    public static final String[] items = new String[]{
    	"lorem", "ipsum", "dolor", "sit", "amet",
		"consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis",
		"etiam", "vel", "erat", "placerat", "ante",
		"porttitor", "sodales", "pellentesque", "augue",
		"purus"};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new HolderExampleAdapter());
    }
    
    public class HolderExampleAdapter extends ArrayAdapter<String>{
    	public HolderExampleAdapter() {
			super(HolderPatternActivity.this,android.R.layout.simple_list_item_1,items);
		}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		LayoutInflater inflater = getLayoutInflater();
    		View v = convertView;
    		
    		if(v==null){
    			v=inflater.inflate(R.layout.row,parent,false);
    			v.setTag(R.id.imageView,v.findViewById(R.id.imageView));
    			v.setTag(R.id.textView,v.findViewById(R.id.textView));
    		}
    		
    		((TextView)v.getTag(R.id.textView)).setText(items[position]);
    		ImageView iv = ((ImageView)v.getTag(R.id.imageView));
    		if(items[position].length()>4){
    			iv.setImageResource(R.drawable.ok);
    		}else{
    			iv.setImageResource(R.drawable.nok);
    		}
    		
    		return v;
    	}
    }
}