package de.bht.fpa.mail.s797981.fsnavigation;

public class IDirectory extends ITreeProvider{

	public IDirectory(String name) {
		super(name);
		this.imagePath = "icons/woman.png";
	}

	/**
	 * Concrete implementation of adding children for folder
	 */
	public void addChild(ITreeProvider p) {
		this.children.add(p);
	}
}
