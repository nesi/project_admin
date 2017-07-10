package nz.org.nesi.collaboration.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public final class Util {
	private static Logger log = Logger.getLogger(Util.class.getName());
	
	public static String formatDate(String strDate, String dateFormat){
		if(strDate == null || dateFormat == null){
			//log.warn("Please enter date and format!");
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String newStrDate = null;
		try {
			Date date = formatter.parse(strDate);
			newStrDate = formatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.warn("error!");
			e.printStackTrace();
		}
		return newStrDate;
	}
	
	public static Timestamp convertStringToTimestamp(String str_date) {
	    try {
	    	if(str_date != null && !"".equals(str_date)){
			    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = formatter.parse(str_date);
			    Timestamp timeStampDate = new Timestamp(date.getTime());
			    return timeStampDate;
	    	}
	    	return null;
	    } catch (ParseException e) {
	      System.out.println("Exception :" + e);
	      return null;
	    }
	  }
	
}