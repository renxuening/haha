package com.wo.utils;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioEncoder;
import android.media.MediaRecorder.AudioSource;
import android.os.Environment;
import android.view.SurfaceView;

public class MovieRecorder {
	private MediaRecorder mediarecorder;
	boolean isRecording;
	private Context context;
	public String newPath;
	Timer timer;
	int timeSize = 0;
	private MediaPlayer mPlayer;

	// ��ʼ¼�Ƶķ���
	public void startRecording(SurfaceView surfaceView) {
		newPath = newFileName();
		mediarecorder = new MediaRecorder();// ����mediarecorder����
		// ����¼����ƵԴΪCamera(���)
		mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		mediarecorder.setAudioSource(AudioSource.MIC);// ��ƵԴ
		// ����¼����ɺ���Ƶ�ķ�װ��ʽTHREE_GPPΪ3gp.MPEG_4Ϊmp4
		mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediarecorder.setAudioEncoder(AudioEncoder.AMR_NB);// ��Ƶ��ʽ
		// ����¼�Ƶ���Ƶ����h263 h264
		mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		// ������Ƶ¼�Ƶķֱ��ʡ�����������ñ���͸�ʽ�ĺ��棬���򱨴�
		mediarecorder.setVideoSize(176, 144);
		// ����¼�Ƶ���Ƶ֡�ʡ�����������ñ���͸�ʽ�ĺ��棬���򱨴�
		mediarecorder.setVideoFrameRate(20);
		mediarecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
		// ������Ƶ�ļ������·��

		mediarecorder.setOutputFile(newPath);
		try {
			// ׼��¼��
			mediarecorder.prepare();
			// ��ʼ¼��
			mediarecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ֹͣ����
	public void stopRecording() {
		if (mediarecorder != null) {
			// ֹͣ
			mediarecorder.stop();
			mediarecorder.release();
			mediarecorder = null;

		}
	}

	public String newFileName() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().getPath()
					+ "/my.mp4";
		} else {
			File f = context.getCacheDir();
			if (!f.exists()) {
				f.mkdir();
			}
			return f.getPath() + "/my.mp4";
		}
	}

	// �ͷ���Դ
	public void release() {
		if (mediarecorder != null) {
			// ֹͣ
			mediarecorder.stop();
			mediarecorder.release();
			mediarecorder = null;
		}
	}

	public void play(String fileName, SurfaceView view) {
		mPlayer = new MediaPlayer();
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mPlayer.setDisplay(view.getHolder()); // ����һ��SurfaceView������
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				stop();

			}
		});
		try {
			mPlayer.setDataSource(fileName);
			mPlayer.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mPlayer.start();
	}

	// ��������ʱ��
	public void stop() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

}
