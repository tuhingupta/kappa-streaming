package io.vertx.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString {
	
	
	static String str = "\"id\":1,\"first_name\":\"Philip\",\"last_name\":\"Lewis\",\"email\":\"plewis0@npr.org\",\"gender\":\"Female\",\"ip_address\":\"204.91.26.63\"}{\"id\":2,\"first_name\":\"Donald\",\"last_name\":\"Nguyen\",\"email\":\"dnguyen1@weebly.com\",\"gender\":\"Female\",\"ip_address\":\"2.16.165.242\"}{\"id\":3,\"first_name\":\"Jason\",\"last_name\":\"Hunter\",\"email\":\"jhunter2@skype.com\",\"gender\":\"Female\",\"ip_address\":\"104.80.77.139\"}{\"id\":4,\"first_name\":\"Jeffrey\",\"last_name\":\"Bailey\",\"email\":\"jbailey3@go.com\",\"gender\":\"Male\",\"ip_address\":\"68.155.105.196\"}{\"id\":5,\"first_name\":\"Philip\",\"last_name\":\"Elliott\",\"email\":\"pelliott4@usda.gov\",\"gender\":\"Female\",\"ip_address\":\"183.197.233.117\"}{\"id\":6,\"first_name\":\"Samuel\",\"last_name\":\"Andrews\",\"email\":\"sandrews5@wikispaces.com\",\"gender\":\"Female\",\"ip_address\":\"211.170.102.28\"}{\"id\":7,\"first_name\":\"Clarence\",\"last_name\":\"Stevens\",\"email\":\"cstevens6@scribd.com\",\"gender\":\"Female\",\"ip_address\":\"151.26.89.212\"}{\"id\":8,\"first_name\":\"Wayne\",\"last_name\":\"Morris\",\"email\":\"wmorris7@cpanel.net\",\"gender\":\"Female\",\"ip_address\":\"42.210.19.40\"}{\"id\":9,\"first_name\":\"James\",\"last_name\":\"Powell\",\"email\":\"jpowell8@mapquest.com\",\"gender\":\"Male\",\"ip_address\":\"126.114.161.26\"}{\"id\":10,\"first_name\":\"Arthur\",\"last_name\":\"Kim\",\"email\":\"akim9@privacy.gov.au\",\"gender\":\"Female\",\"ip_address\":\"31.213.54.227\"}{\"id\":11,\"first_name\":\"Lawrence\",\"last_name\":\"Lewis\",\"email\":\"llewisa@themeforest.net\",\"gender\":\"Female\",\"ip_address\":\"166.187.237.113\"}{\"id\":12,\"first_name\":\"Keith\",\"last_name\":\"Frazier\",\"email\":\"kfrazierb@cbc.ca\",\"gender\":\"Female\",\"ip_address\":\"101.141.82.182\"}{\"id\":13,\"first_name\":\"Gerald\",\"last_name\":\"Ferguson\",\"email\":\"gfergusonc@google.nl\",\"gender\":\"Female\",\"ip_address\":\"177.55.211.38\"}{\"id\":14,\"first_name\":\"Chris\",\"last_name\":\"Mitchell\",\"email\":\"cmitchelld@hibu.com\",\"gender\":\"Male\",\"ip_address\":\"193.45.40.224\"}{\"id\":15,\"first_name\":\"James\",\"last_name\":\"Webb\",\"email\":\"jwebbe@foxnews.com\",\"gender\":\"Male\",\"ip_address\":\"56.198.194.94\"}{\"id\":16,\"first_name\":\"Donald\",\"last_name\":\"Burton\",\"email\":\"dburtonf@wikia.com\",\"gender\":\"Male\",\"ip_address\":\"172.96.27.20\"}{\"id\":17,\"first_name\":\"Scott\",\"last_name\":\"Arnold\",\"email\":\"sarnoldg@prweb.com\",\"gender\":\"Female\",\"ip_address\":\"";
	static String pattern1 = "{";
	static String pattern2 = "}";
	
	static Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Matcher m = p.matcher(str);
		
		
		int start = -1;
		int end = str.length() -1;
		
		System.out.println("length "+str.length());
		
		while (m.find()) {
			if(start < 0){
				if(m.start() > 0)
					start = m.start();
			}

			System.out.println(m.group());
			end = m.start() + m.group().length(); 

		}//while
		
		System.out.println("First wrong string - "+str.substring(0, start));
		System.out.println("Last wrong string - "+end+" "+str.substring(end, str.length()));
		

	}

}
