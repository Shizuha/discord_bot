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
    		
    		String[] array = content.split(" "); // 공백 잘라내기
    		
    		log.info(array[0]); // !주사위 
    		log.info(array[1]); // 첫번째 수
    		log.info(array[2]); // 두번째 수
    		
    		if(Integer.valueOf(array[1]) < Integer.valueOf(array[2])) {
	    		int random = (int) (Math.random()*(Integer.valueOf(array[2]) - Integer.valueOf(array[1]) + 1) + Integer.valueOf(array[1]));
	    		log.info(String.valueOf(random));
	    		event.getChannel().sendMessage("`" + event.getAuthor().getName() + "`님이 " + ":game_die:주사위를 굴려 __**" + random + "**__ 나왔습니다.").queue();
    		} else {
    			int random = (int) (Math.random()*(Integer.valueOf(array[1]) - Integer.valueOf(array[2]) + 1) + Integer.valueOf(array[2]));
        		log.info(String.valueOf(random));
        		event.getChannel().sendMessage("`" + event.getAuthor().getName() + "`님이 " + ":game_die:주사위를 굴려 __**" + random + "**__ 나왔습니다.").queue();
    		}
    		
        }
    }
}
