package de.bht.fpa.mail.s797981.fsnavigation;

/**
 * This wrapper class for java.io.Fil represents a file with corresponding icon.
 * 
 * @author Maxim Novichkov
 */
public class ITreeFile extends IAbstractTree{
	/**
	 * Path to the file icon
	 */
	private static final String FILE_PATH = "icons/file_sm.png";
	/**
	 * Construct a new ITreeFile with icon 
	 * @param path The specified path
	 */
	public ITreeFile(final String path) {
		super(path);
		this.imagePath = FILE_PATH;
	}
	
	@Override
	public String toString() {
		return "ITreeFile [imagePath=" + imagePath + ", path=" + path
				+ ", file=" + file + "]";
	}
}
