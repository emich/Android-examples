package be.emich.labs.intentservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent1 = new Intent(this,MyIntentService.class);
        intent1.setAction(MyIntentService.ACTION_DOWNLOADFEED);
        startService(intent1);
        
        Intent intent2 = new Intent(this,MyIntentService.class);
        intent2.setAction(MyIntentService.ACTION_UPDATESTATUS);
        intent2.putExtra(MyIntentService.EXTRA_STATUS, "Hello world !");
        startService(intent2);
    }
}