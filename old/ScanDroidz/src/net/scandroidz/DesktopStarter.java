package net.scandroidz;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {

	public static void main(String[] args) {
		new JoglApplication(new ScanDroidz(),
				"ScanDroidz",
				960	, 540, false);
	}

}
