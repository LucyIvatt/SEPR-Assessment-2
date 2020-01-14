package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Kroy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Kroy.WIDTH;
		config.height = Kroy.HEIGHT;
		config.title= Kroy.TITLE;
		config.fullscreen = true;
		new LwjglApplication(new Kroy(), config);
	}
}
