package be.labs.emich.webviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class WebViewActivity extends Activity implements OnClickListener {
    private WebView webView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        webView = (WebView)findViewById(R.id.webviewMain);
        Button buttonLoadFromUrl = (Button)findViewById(R.id.buttonLoadFromUrl);
        buttonLoadFromUrl.setOnClickListener(this);
        Button buttonLoadFromString = (Button)findViewById(R.id.buttonLoadFromString);
        buttonLoadFromString.setOnClickListener(this);
    }

	public void onClick(View v) {
		if(v.getId()==R.id.buttonLoadFromUrl){
			webView.loadUrl("http://labs.emich.be");
		}else if(v.getId()==R.id.buttonLoadFromString){
			webView.loadData("<html><body><h1>OHAI!</h1><p>I CAN HAZ WEBVIEW</p></body></html>",
					"text/html",
					"utf-8"
					);
		}
		
	}
}