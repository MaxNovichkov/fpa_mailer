package de.bht.fpa.mail.s797981.fsnavigation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Scanner;

/**
 * This class provide ability to remember last used location chose by user or in
 * case of first start/file damage will be used default location (user.home).
 * New path (location) settled by user will be saved in a file. This file will
 * be every time overwritten when user set another location.
 * 
 * @author Novichkov Maxim
 *
 */
public class SimpleRoot extends Observable {

	/**
	 * Default location
	 */
	private ITreeDirectory directory = new ITreeDirectory(
			System.getProperty("user.home"));
	/**
	 * File to save location
	 */
	private final File history = new File(System.getProperty("user.home") + "\\fpa_mailer_path");
	/**
	 * Instance of this class
	 */
	final private static SimpleRoot root = new SimpleRoot();

	/**
	 * Construct new SimpleRoot, if history file exist and valid, in this case will be
	 * used saved path.
	 */
	public SimpleRoot() {
		if (history.exists()) {
			directory = readRoot();
		}
	}

	/**
	 * Returns settled ITreeDirectory root.
	 * 
	 * @return ITreeDirectory root
	 */
	public ITreeDirectory getRoot() {
		return directory;
	}

	/**
	 * This method set root path.
	 * 
	 * @param path
	 *            The path to set
	 */
	public void setRoot(final String path) {
		saveRoot(path);
		directory = new ITreeDirectory(path);
		setChanged();
		notifyObservers(directory);
	}

	/**
	 * Provide ability to save path (location) to file.
	 * 
	 * @param path
	 *            The path to save
	 */
	private void saveRoot(final String path) {
		PrintWriter out = null;
		try {
			history.createNewFile();
			out = new PrintWriter(new FileWriter(history));
			out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Write error");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Here we read saved location (path) from file. In case of file damage or
	 * location (path) not exist, will be set default location (user.home).
	 * 
	 * @return Settled location
	 */
	private ITreeDirectory readRoot() {
		Scanner fin = null;
		ITreeDirectory result = null;
		try {
			fin = new Scanner(history);
			while (fin.hasNextLine()) {
				result = new ITreeDirectory(fin.nextLine().replace("\\", "/"));
				if (!result.exists()) {
					return directory;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fin.close();
		}
		return result;
	}

	/**
	 * Provide access to instance of SimpleRoot.
	 * 
	 * @return Instance of SimpleRoot
	 */
	public static SimpleRoot getInstance() {
		return root;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((directory == null) ? 0 : directory.hashCode());
		result = prime * result + ((history == null) ? 0 : history.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleRoot other = (SimpleRoot) obj;
		if (directory == null) {
			if (other.directory != null)
				return false;
		} else if (!directory.equals(other.directory))
			return false;
		if (history == null) {
			if (other.history != null)
				return false;
		} else if (!history.equals(other.history))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleRoot [directory=" + directory + ", history=" + history
				+ "]";
	}

}
