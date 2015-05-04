package de.bht.fpa.mail.s797981.fsnavigation;

public class IFile extends ITreeProvider{

	public IFile(String absolutePath) {
		super(absolutePath);
		this.imagePath = "icons/man.png";
	}

	/**
	 * It is not possible to add children for an file.
	 */
	public void addChild(ITreeProvider p) {
	}
	
	/**
	 * Returns an empty array for file to avoid exception
	 */
	public ITreeProvider[] getChildren() {
		return new ITreeProvider[0];
	}

}
