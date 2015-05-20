package de.bht.fpa.mail.s797981.fsnavigation.handlers;

import de.bht.fpa.mail.s797981.fsnavigation.ITreeDirectory;
import de.bht.fpa.mail.s797981.fsnavigation.NavigationView;

public class SetDirectoryCommand implements ICommand{
	
	private ITreeDirectory directory;

	public SetDirectoryCommand(String path) {
		directory = new ITreeDirectory(path);
	}
	
	public void execute() {
		NavigationView.getInstance().setInput(directory);
	}

}
