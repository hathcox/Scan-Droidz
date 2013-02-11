package net.scandroidz;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * This is the service that is required for scandroids to work, this will hold all sustaining things
 * @author haddaway
 *
 */
public class ScanDroidsService  extends Service{
	
	private String session;
	private static ScanDroidsService scanDroidsService;
	
	public ScanDroidsService() {
		scanDroidsService = this;
	}
	
	//Returns the singleton instance
	public static ScanDroidsService getInstance() {
		if(scanDroidsService == null) {
			scanDroidsService = new ScanDroidsService();
		}
		return scanDroidsService;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	public String getSession() {
		return session;
	}
	
	public void setSession(String session) {
		this.session = session;
	}

}
