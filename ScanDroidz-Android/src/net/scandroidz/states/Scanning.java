package net.scandroidz.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;

import android.content.Intent;
import net.scandroidz.core.State;

public class Scanning implements State{
	
	static int CAMERA_PIC_REQUEST = 1;
	boolean test = false;
	private AndroidApplication ap;
	Intent cameraIntent;
	public Scanning(AndroidApplication ap)
	{
		this.ap = ap;
	}
	
	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if(test == false)
		{
            cameraIntent = new Intent("com.google.zxing.client.android.SCAN");
            cameraIntent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            ap.startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            test = true;
		}
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
	
	public void onActivityResult(int result,int resultCode, Intent data)
	{
		if (resultCode == 1) {
			Gdx.app.log("ACTIBITYFDj", data.getStringExtra("SCAN_RESULT"));
		}
	}

}
