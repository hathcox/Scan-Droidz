package net.scandroidz.states;

import net.scandroidz.core.State;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class GenerateSeedState implements State{

	private AndroidApplication application;
	private long phoneNumber;
	
	public GenerateSeedState(AndroidApplication application) {
		this.application = application;
	}
	
	@Override
	public void startUp() {
		TelephonyManager tMgr =(TelephonyManager)application.getSystemService(Context.TELEPHONY_SERVICE);
		application.log("Phone Number:", tMgr.getLine1Number());
		application.log("ISME:", tMgr.getDeviceId());

		//Grab the phone number
		phoneNumber = Long.parseLong(tMgr.getDeviceId(), 16);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

}
