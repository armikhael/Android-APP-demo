package com.example.demon_1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	
	Button btnSearch;
	Button btnOpenActivity;
	LinearLayout inputControls;
	public static final String TAG = MainActivity.class.toString();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
		
		ButtonListener listener = new ButtonListener();
		btnSearch.setOnClickListener(listener);
		btnOpenActivity.setOnClickListener(listener);
		
		Button btnlist = new Button(this);
		btnlist.setText(getString(R.string.btn_list));
		btnlist.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				                                 LayoutParams.WRAP_CONTENT));
		
		LinearLayout mainContent = (LinearLayout)findViewById(R.id.mainContent);
		
		inputControls = (LinearLayout) View.inflate(this, 
																 R.layout.input_controls_content, 
																 null);
		mainContent.addView(btnlist);
		setInputControls();
		mainContent.addView(inputControls);
	}
	
	public void setInputControls(){
		SeekBar seekbar = (SeekBar) inputControls.findViewById(R.id.seekBar1);
		seekbar.setMax(10);
		seekbar.setProgress(5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class ButtonListener implements OnClickListener {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Log.i(TAG, "hizo Click!");
			//Toast.makeText(getApplicationContext(),
					//"hizo Click!2",
					//Toast.LENGTH_SHORT).show();
			EditText searchQuery = (EditText)findViewById(R.id.editTextSearchQuery);
			String searchQueryText = searchQuery.getText().toString();
			String url = "https://www.google.com/?q=" + searchQueryText +
					     "#q=" + searchQueryText;
			Intent intent = null;
			
			if(v.getId() == btnOpenActivity.getId()){
				intent = new Intent(getApplicationContext(),
						ShowSearchQueryActivity.class);
				intent.putExtra(ShowSearchQueryActivity.QUERY, searchQueryText);	
			
			}else if (v.getId() == btnSearch.getId()){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
			}
			
			startActivity(intent);
			
			
		}
		
	}

}
