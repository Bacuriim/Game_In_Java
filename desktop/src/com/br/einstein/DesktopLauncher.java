package com.br.einstein;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.br.einstein.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setMaximized(true);
		config.useVsync(true);
		config.setResizable(true);
		config.setForegroundFPS(75);
		config.setTitle("Game");
		config.setWindowIcon("icon.png");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
