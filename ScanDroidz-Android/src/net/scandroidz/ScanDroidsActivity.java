package net.scandroidz;

import net.scandroidz.ScanDroidz;
import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import net.scandroidz.core.StateManager;
import net.scandroidz.states.GenerateSeedState;
import net.scandroidz.states.Scanning;

public class ScanDroidsActivity extends AndroidApplication {
	
	private Scanning scan;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StateManager sm = StateManager.getInstance();
        //Register phone specific states
        scan = new Scanning(this);
        sm.registerState("Scanning", scan);
        sm.registerState("GenerateSeedState", new GenerateSeedState(this));
        
        initialize(new ScanDroidz(), false);
    }
    
    @Override
    protected void onActivityResult(int result,int resultCode, Intent data)
    {
    	scan.onActivityResult(result,resultCode,data);
    	
    }
    
}