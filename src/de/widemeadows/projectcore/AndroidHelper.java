package de.widemeadows.projectcore;

import android.os.Build;

/**
 * Helferfunktionalität für Android-Projekte
 */
public class AndroidHelper {

	private AndroidHelper() {}

	/**
	 * Ermittelt, ob dieser Code unter dem Android SDK läuft
	 * @see Build.VERSION#SDK
	 */
	public static final boolean IsAndroidSdk = Build.VERSION.SDK_INT != 0;
}
