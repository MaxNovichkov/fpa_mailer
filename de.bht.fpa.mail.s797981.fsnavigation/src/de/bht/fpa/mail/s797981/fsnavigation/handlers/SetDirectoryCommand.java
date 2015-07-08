package de.bht.fpa.mail.s797981.fsnavigation.handlers;

import de.bht.fpa.mail.s797981.fsnavigation.SimpleRoot;
import de.bht.fpa.mail.s797981.fsnavigation.items.TreeFolder;

/**
 * This class implement concrete command and set selected by user path 
 * trough SimpleRoot to the navigation view.
 * 
 * @author Novichkov Maxim
 *
 */
public class SetDirectoryCommand implements ICommand{
	/**
	 * Path variable
	 */
	private final String root;
	
	/**
	 * Construct new command by provided path
	 * @param path Provided path
	 */
	public SetDirectoryCommand(final String path) {
		root = path;
	}
	/**
	 * This method will set wrapped in {@link TreeFolder} path in navigation view.
	 */
	public void execute() {
		SimpleRoot.getInstance().setRoot(root);
	}

}
