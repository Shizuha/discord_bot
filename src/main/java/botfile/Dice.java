package botfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern; 

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
        	String regExp = "(^[0-9]+$)"; // 숫자만 인식하는 정규식
        	
    		String[] array = content.split(" "); // 공백 잘라내기
    		
    		ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
    		
    		if(list.size() == 2) {
    			list.add(2, "1");
    		}
    		
    		if(!list.get(1).matches(regExp) || !list.get(2).matches(regExp)) { // 예외처리
    			event.getChannel().sendMessage("숫자만 넣을 수 있습니다. `!주사위 숫자 or !주사위 숫자 숫자` 처럼 입력해주세요.").queue();
    			return;
    		}
  
    		if(list.size() != 2 && list.size() != 3) { // 예외처리
    			event.getChannel().sendMessage("틀린 사용법입니다. `!주사위 숫자 or !주사위 숫자 숫자`").queue();
    			return;
        	}
    		
       		log.info(list.get(0)); // !주사위 
    		log.info(list.get(1)); // 첫번째 수
    		log.info(list.get(2)); // 두번째 수
    		
    		if(Integer.valueOf(list.get(1)) < Integer.valueOf(list.get(2))) {
	    		int random = (int) (Math.random()*(Integer.valueOf(list.get(2)) - Integer.valueOf(list.get(1)) + 1)
	    				+ Integer.valueOf(list.get(1))); // (끝수 - 시작수 + 1) + 시작수 = 끝수 ~ 시작수
	    		log.info(String.valueOf(random));
	    		event.getChannel().sendMessage("`" + event.getAuthor().getName() + "`님이 " + ":game_die:주사위를 굴려 __**" 
	    				+ random + "**__ 나왔습니다.").queue();
    		} else {
    			int random = (int) (Math.random()*(Integer.valueOf(list.get(1)) - Integer.valueOf(list.get(2)) + 1) 
    					+ Integer.valueOf(list.get(2))); // (시작수 - 끝수 + 1) + 끝수 = 시작수 ~ 끝수
        		log.info(String.valueOf(random));
        		event.getChannel().sendMessage("`" + event.getAuthor().getName() + "`님이 " + ":game_die:주사위를 굴려 __**" 
        				+ random + "**__ 나왔습니다.").queue();
    		}
        }
    }
}
