package be.labs.emich.cpuinfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CPUInfoActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView textView = (TextView)findViewById(R.id.cpuinfo);
		textView.setText(readCPUInfo());
		
		Toast.makeText(this,"BogoMIPS: "+getBogoMips(),Toast.LENGTH_LONG).show();
	}

	private String readCPUInfo() {
		ProcessBuilder cmd;
		String result = "";

		try {
			String[] args = { "/system/bin/cat", "/proc/cpuinfo" };
			cmd = new ProcessBuilder(args);

			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[1024];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	private float getBogoMips(){
		Pattern pattern = Pattern.compile(".*BogoMIPS\\s*:\\s*([0-9]*\\.[0-9]*).*",Pattern.UNIX_LINES|Pattern.MULTILINE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(readCPUInfo());
		
		if(matcher.matches())
		{
		    return Float.valueOf(matcher.group(1));
		}
		else return -1.0f;
	}
}