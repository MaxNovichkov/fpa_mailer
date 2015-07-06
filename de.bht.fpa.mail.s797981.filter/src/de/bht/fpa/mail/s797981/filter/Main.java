package de.bht.fpa.mail.s797981.filter;

import java.util.Collection;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

/**
 * Simply test class for some filters. It is useful to start at the same time printAllMessages method and
 * another one to control result. See more information in corresponding method.
 * 
 * @author Novichkov Maxim
 *
 */
public class Main {
	/**
	 * Configurated filters
	 */
	final private static ReadFilter READ_FILTER = new ReadFilter(false);
	final private static SenderFilter SENDER_FILTER = new SenderFilter("stulle_heidi@", FilterOperator.CONTAINS);
	final private static RecipientsFilter RECIPIENT_FILTER = new RecipientsFilter("heidi stulle", FilterOperator.IS);
	final private static UnionFilter UNION_FILTER = new UnionFilter(SENDER_FILTER, RECIPIENT_FILTER);
	final private static IntersectionFilter INTERSECTION_FILTER = new IntersectionFilter(SENDER_FILTER, RECIPIENT_FILTER);
	final private static int number_of_messages = 30;
	
	/** All filter configuration for tests should be changed in constants above. 
	 *  This constants are used in all test methods.   
	 */
	public static void main(String[] args) {
		Collection<Message> messages = new RandomTestDataProvider(number_of_messages).getMessages();
		
		printAllMessages(messages);
//		readTest(messages);
//		senderTest(messages);
//		recipientTest(messages);
		unionTest(messages);
//		intersectionTest(messages);
	}

	/**
	 * Test case: Read("see final variable").
	 */
	private static void readTest(final Collection<Message> messages) {
		final Set<Message> read = READ_FILTER.filter(messages);
		
		System.out.println("Test. Configuration is: " + READ_FILTER.toString());
		System.out.println("==================Founded messages: " + read.size()	+ " =====================");

		for (Message mes : read) {
			System.out.println("Read status is: " + mes.isRead() + " in message:");
			System.out.println(mes.toShortString());
		}
	}

	/**
	 * Test case: Sender("see configuration in final variable SENDER_FILTER")
	 */
	private static void senderTest(final Collection<Message> messages) {
		final Set<Message> senderMessages = SENDER_FILTER.filter(messages);
		
		System.out.println("Test. Configuration is: " +  SENDER_FILTER.toString());
		System.out.println("==================Founded messages with senderFilter: " + senderMessages.size() + " =====================");
		
		for (Message mes : senderMessages) {
			System.out.println("Sender is: " + mes.getSender() + " in message:");
			System.out.println(mes.toShortString());
		}
	}

	/**
	 * Test case: Recipient("see configuration in final variable RECIPIENT_FILTER"))
	 */
	private static void recipientTest(final Collection<Message> messages) {
		final Set<Message> recipientMessages = RECIPIENT_FILTER.filter(messages);
		
		System.out.println("Test. Configuration is: " +  RECIPIENT_FILTER.toString());
		System.out.println("==================Founded messages with recipientFilter: " + recipientMessages.size() + " =====================");
		
		for (Message mes : recipientMessages) {
			System.out.println("Recipient is "  + RECIPIENT_FILTER.searchedString + " in message(s):");
			System.out.println(mes.toShortString());
		}
	}

	/**
	 * Test case: Union("see configuration above in constants = separate filters").
	 * Note, as this method use senderTest() and recipientTest() methods, number of messages founded in
	 * this separate methods can be higher as number of messages founded in union test, because of some 
	 * messages are equal (just compare sender ID) and as return value we have Set<Message>, so duplicate messages will 
	 * be avoided.
	 */
	private static void unionTest(final Collection<Message> messages) {
		// first - filter messages with union filter
		final Set<Message> union = UNION_FILTER.filter(messages);
		
		System.out.println("Test. Configuration is: " + UNION_FILTER.toString());
		System.out.println("==================Founded messages with unionFilter: " + union.size() + " =====================");

		// filter from union filter with sender filter for checking result
		senderTest(union);

		// filter from union filter with recipient filter for checking result
		recipientTest(union);
	}

	/**
	 * Test case: Intersection("see configuration above in constants = separate filters").
	 * Note, as this method use senderTest() and recipientTest() methods, number of messages founded in
	 * this separate methods can be higher as number of messages founded in intersection test, because of some 
	 * messages are equal (just compare sender ID) and as return value we have Set<Message>, so duplicate messages will 
	 * be avoided.
	 */
	private static void intersectionTest(final Collection<Message> messages) {
		// first - filter messages with intersection filter
		final Set<Message> intersection = INTERSECTION_FILTER.filter(messages);
				
		System.out.println("Test. Configuration is: " + INTERSECTION_FILTER.toString());
		System.out.println("==================Founded messages with intersectionFilter: " + intersection.size() + " =====================");

		// filter from intersection filter with sender filter for checking result
		senderTest(intersection);

		// filter from intersection filter with recipient filter for checking result
		recipientTest(intersection);
	}
	
	/**
	 * Print initially generated messages. It's very useful to use this method (don't comment it out) 
	 * in combination with another tests. 
	 */
	private static void printAllMessages(final Collection<Message> messages) {
		System.out.println("==================Generated messages: " + messages.size()	+ " =====================");
		for(Message mes : messages){
			System.out.println(mes.toShortString());
		}
	}
}
