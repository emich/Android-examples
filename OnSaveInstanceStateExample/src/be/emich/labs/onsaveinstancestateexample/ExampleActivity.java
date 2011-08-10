package be.emich.labs.onsaveinstancestateexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ExampleActivity extends Activity implements OnClickListener {
    
	private Button buttonIncrement;
	private TextView textViewCounter;
	
	private int counter;
	
	public static final String COUNTER = "ExampleActivity.Counter";  
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textViewCounter = (TextView)findViewById(R.id.textViewCounter);
        buttonIncrement = (Button)findViewById(R.id.buttonIncrement);
        buttonIncrement.setOnClickListener(this);
        
        if(savedInstanceState!=null)
        	counter = savedInstanceState.getInt(COUNTER, 0);
        
        updateCounter();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	outState.putInt(COUNTER,counter);
    	super.onSaveInstanceState(outState);
    }

	public void onClick(View v) {
		counter++;
		updateCounter();
	}
	
	protected void updateCounter(){
		textViewCounter.setText(counter+"");
	}
}