package de.bht.fpa.mail.s797981.fsnavigation.handlers;

import de.bht.fpa.mail.s797981.fsnavigation.ITreeDirectory;
import de.bht.fpa.mail.s797981.fsnavigation.NavigationView;

/**
 * This class implement concrete command and set selected by user path to the navigation view.
 * 
 * @author Novichkov Maxim
 *
 */
public class SetDirectoryCommand implements ICommand{
	/**
	 * Created {@link ITreeDirectory} by provided path.
	 */
	private final ITreeDirectory directory;
	
	/**
	 * Construct new command by provided path
	 * @param path Provided path
	 */
	public SetDirectoryCommand(final String path) {
		directory = new ITreeDirectory(path);
	}
	/**
	 * This method will set wrapped in {@link ITreeDirectory} path in navigation view.
	 */
	public void execute() {
		NavigationView.getInstance().setInput(directory);
		
	}

}
