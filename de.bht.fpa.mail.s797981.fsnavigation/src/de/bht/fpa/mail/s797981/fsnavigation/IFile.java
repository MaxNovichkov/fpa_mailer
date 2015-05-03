package de.bht.fpa.mail.s797981.fsnavigation;

public class IFile extends ITreeProvider{

	public IFile(String name) {
		super(name);
		this.imagePath = "icons/man.png";
	}

	/**
	 * It is not possible to add children for an file.
	 */
	public void addChild(ITreeProvider p) {
		
	}

}
