package co.yeadam.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
//    	MainMenu menus = new MainMenu();
//    	menus.run();
    	
    	Calendar cal = Calendar.getInstance();
		System.out.println("예약 시작 날짜를 입력하세요(yy-MM-dd)");
		String str = sc.nextLine();	
		SimpleDateFormat transFormat = new SimpleDateFormat("yy-MM-dd");
		Date startDate = null;
		try {
			startDate = transFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(startDate);
		System.out.println("숙박 시작날짜: " + transFormat.format(cal.getTime()));
		int lastDate = sc.nextInt(); sc.nextLine();
		cal.add(Calendar.DATE, lastDate);
		System.out.println("숙박 끝 날짜: " + transFormat.format(cal.getTime()));
    }
}
