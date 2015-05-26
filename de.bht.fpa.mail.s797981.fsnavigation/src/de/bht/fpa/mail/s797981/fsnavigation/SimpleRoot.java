package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;
import java.util.Observable;

/**
 * Default tree root
 * 
 * @author Novichkov Maxim
 *
 */
public class SimpleRoot extends Observable{
	
	private ITreeDirectory directory = new ITreeDirectory(System.getProperty("user.home"));
	
	final private static SimpleRoot root = new SimpleRoot();
	/**
	 * Returns settled ITreeDirectory root to default location (user.home).
	 * @return Default ITreeDirectory root
	 */
	public ITreeDirectory getRoot() {
		File a = new File("c:/test.txt");
		System.out.println(a.isFile());
		System.out.println("File: " + a.getAbsoluteFile().toString());
		System.out.println(directory.toString());
		System.out.println(a.exists());
		return directory;
	}
	
	public void setRoot(String path){
		File f = new File(System.getProperty("user.home") + "\\fpa_mailer_path.txt"); 
		File a = new File("C:\\Users\\Max\\fpa_mailer_path1.txt"); 
        if (f.exists() || a.exists()) {
            System.out.println("File exists");
//            f.delete();
        } else {
            System.out.println("File does not exist");
        }
        System.out.println("SimpleRoot: " + f.getAbsolutePath());
        directory = new ITreeDirectory(path); 
        setChanged();
        notifyObservers(directory);
	}
	/**
	 * Provide access to instance of SimpleRoot.
	 * @return Instance of SimpleRoot
	 */
	public static SimpleRoot getInstance(){
		return root;
	}
	
	
}
