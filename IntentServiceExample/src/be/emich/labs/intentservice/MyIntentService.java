package be.emich.labs.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
	public static final String ACTION_DOWNLOADFEED = "be.emich.labs.action.downloadfeed";
	public static final String ACTION_UPDATESTATUS = "be.emich.labs.action.updatestatus";
	public static final String EXTRA_STATUS = "be.emich.labs.extra.status"; 

	public MyIntentService() {
		super("MyIntentService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		String userAction = intent.getAction();
		if(userAction.equals(ACTION_DOWNLOADFEED))downloadFeed();
		else updateStatus(intent);
	}

	public void downloadFeed(){
		Log.i("MyIntentService","Downloading feed!");
	}
	
	public void updateStatus(Intent i){
		Log.i("MyIntentService","Updating status to "+i.getStringExtra(EXTRA_STATUS));
	}

}
