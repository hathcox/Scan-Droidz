package net.scandroidz;

import net.scandroidz.ScanDroidz;
import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import net.scandroidz.core.StateManager;
import net.scandroidz.states.Scanning;
import net.scandroidz.weblayer.WebLayerUtil;

public class ScanDroidsActivity extends AndroidApplication {

	private Scanning scan;
	public static ScanDroidz scanDroidz;
	private String session;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StateManager sm = StateManager.getInstance();
		// Register phone specific states
		scan = new Scanning(this);
		sm.registerState("Scanning", scan);
		scanDroidz = new ScanDroidz();
		initialize(scanDroidz, false);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				// Handle successful scan
				System.out.println(contents);
				WebLayerUtil.refreshSession();
				WebLayerUtil.scanBarcode(contents);
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

}