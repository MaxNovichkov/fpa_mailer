package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * Implement null filter pattern. All filtered messages will be added to the result list.
 * 
 * @author Novichkov Maxim
 *
 */
public class NullFilter extends AFilter{

	@Override
	boolean match(Message message) {
		return true;
	}

}
