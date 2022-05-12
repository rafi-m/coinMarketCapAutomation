package helpers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class TimeHelper {
	
public long waitTill(String time) throws ParseException
{
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	Date date2 = formatter.parse(time);
		Date  currentTime=new Date();
		long difference =  date2.getTime() - currentTime.getTime();
	long difference_In_Seconds = (difference/ 1000) ; 
	System.out.println("current Time: " +currentTime+"\r\nReceived Time: "+date2+"\r\nDifference:"+difference_In_Seconds);
	//driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
	return difference_In_Seconds;
	
	}
	public long waitTill(String time, String operation,int timeOps,String unit) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date2 = formatter.parse(time);
		System.out.println("Received Time: " + date2);
		date2 = this.dateOperations(date2,operation,timeOps,unit);
		Date  currentTime=new Date();
		long difference =  date2.getTime() - currentTime.getTime();
		long difference_In_Seconds = (difference/ 1000) ;
		System.out.println("Corrected Time:"+date2+"\r\ncurrent Time: " +currentTime+"\r\nDifference:"+difference_In_Seconds);
		//driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
		return difference_In_Seconds;

	}

	public Date dateOperations(Date date,String Operation,int timeOps,String unitString){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int unit = 0;
		if(unitString.equals("minutes")){
			unit = Calendar.MINUTE;
		}
		else if(unitString.equals("seconds")){
			unit = Calendar.SECOND;
		}
		else if(unitString.equals("hours")){
			unit = Calendar.HOUR;
		}

		switch (Operation){
			case "plus":{
				cal.add(unit,timeOps);
				break;
			}
			case "minus":{
				cal.add(unit,-timeOps);
				break;
			}
		}
		return cal.getTime();
	}

public static Date parseDate(String date,String format) throws ParseException {
	//SimpleDateFormat formatter = new SimpleDateFormat("dd MMM','hh:mm aa");
	SimpleDateFormat formatter = new SimpleDateFormat(format);
	Date date2 = formatter.parse(date);
	return date2;
}

public static String toString(Date date,String format) throws ParseException {
	//SimpleDateFormat formatter = new SimpleDateFormat("dd MMM','hh:mm aa");
	SimpleDateFormat formatter = new SimpleDateFormat(format);
	return formatter.format(date);
}

public  static String toString(Date date,String format,String tz) throws ParseException {
	SimpleDateFormat formatter = new SimpleDateFormat(format);
	formatter.setTimeZone(TimeZone.getTimeZone(tz));
	return formatter.format(date);
}

public static void main(String[] args) throws ParseException
{
	long diff = new TimeHelper().waitTill("2022-01-24T10:40:00","minus",5,"minutes");
	System.out.println(diff);
}
}
