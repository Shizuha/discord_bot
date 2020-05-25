package botfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Dice extends ListenerAdapter{
	
	static final Logger log = LoggerFactory.getLogger(Dice.class);
	
	@Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw(); 
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)

        if (content.startsWith("!주사위")) {
    		
    		String[] array = content.split(" ");
    		
    		int random = (int) (Math.random()*(Integer.valueOf(array[2]) - Integer.valueOf(array[1] + 1)) + Integer.valueOf(array[1]));
    		log.info(array[0]);
    		log.info(array[1]);
    		log.info(array[2]);
    		log.info(String.valueOf(random));
    		
        }
    }
}
