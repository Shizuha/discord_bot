package botfile;

import java.io.FileInputStream;
import java.util.Properties;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Run {

	public static void Main() {

		Calculator Calculator = new Calculator();
		
		try {
			// 프로퍼티 파일 위치
			String propFile = "src/main/resources/Token.properties";

			// 프로퍼티 객체 생성
            Properties props = new Properties();
             
            // 프로퍼티 파일 스트림에 담기
            FileInputStream fis = new FileInputStream(propFile);
             
            // 프로퍼티 파일 로딩
            props.load(new java.io.BufferedInputStream(fis));

			JDA bot = JDABuilder.createDefault(props.getProperty("token"))
					.setActivity(Activity.listening("!명령어")).build();
			bot.awaitReady();
			bot.addEventListener(Calculator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arguments) throws Exception {
		Main();
	}

}
