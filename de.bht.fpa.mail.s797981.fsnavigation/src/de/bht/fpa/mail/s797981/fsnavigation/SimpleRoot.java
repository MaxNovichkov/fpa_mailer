package de.bht.fpa.mail.s797981.fsnavigation;

/**
 * Default tree root
 * 
 * @author Novichkov Maxim
 *
 */
public class SimpleRoot {

	/**
	 * Set tree root to default location (user.dir)
	 * 
	 * @return Default ITreeDirectory root
	 */
	public ITreeDirectory getRoot() {
		return new ITreeDirectory(System.getProperty("user.dir"));
	}
}
