package com.example.myviewdo;

import com.wo.utils.MovieRecorder;
import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button mstart, mstop, mplay, stopplay;
	private SurfaceView sur;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mstart = (Button) findViewById(R.id.start);
		mstop = (Button) findViewById(R.id.stop);
		mplay = (Button) findViewById(R.id.play);
		sur = (SurfaceView) findViewById(R.id.sur);
		stopplay = (Button) findViewById(R.id.stopplay);
		stopplay.setOnClickListener(this);
		mstart.setOnClickListener(this);
		mstop.setOnClickListener(this);
		mplay.setOnClickListener(this);
	}

	MovieRecorder mov = new MovieRecorder();

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.start:
			mov.startRecording(sur);
			break;
		case R.id.stop:
			mov.stopRecording();
			break;
		case R.id.play:
			mov.play(mov.newFileName(), sur);
			break;
		case R.id.stopplay:
			mov.stop();
			break;
		default:
			break;
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		mov.stop();
	}
}
