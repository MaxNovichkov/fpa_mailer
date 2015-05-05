package de.bht.fpa.mail.s797981.fsnavigation;

//TODO: to string and comments

public class ITreeFile extends IAbstractTree{
	
	private static final String FILE_PATH = "icons/file_sm.png";

	public ITreeFile(String absolutePath) {
		super(absolutePath);
		this.imagePath = FILE_PATH;
	}
}
