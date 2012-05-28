package net.scandroidz.states;

import net.scandroidz.core.State;
import android.content.Intent;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class Scanning implements State {

	private static final String TAG = "Scanning State";
	private AndroidApplication app;

	public Scanning(AndroidApplication app) {
		this.app = app;
	}

	@Override
	public void startUp() {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "ONE_D_MODE");
		app.startActivityForResult(intent, 0);
	}

	@Override
	public void render() {}

	@Override
	public void update(float delta) {}

	@Override
	public void load() {}

	@Override
	public void cleanUp() {}
	
}
