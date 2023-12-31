package com.br.einstein;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.br.einstein.screen.ScreenManager;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.useVsync(true);
		config.setResizable(false);
		config.setForegroundFPS(60);
		config.setTitle("StreetFortal");
		config.setWindowIcon("icon.png");
		new Lwjgl3Application(new ScreenManager(), config);
	}
}
