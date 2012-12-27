/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author apoorvparijat
 */

public class DateController {
    
    public static String getDateToDisplay(String millisecs){
        String toDisp = "";
        if(millisecs == null){
            return "";
        }
        long l = Long.parseLong(millisecs);
        Calendar  c = Calendar.getInstance();
        c.setTimeInMillis(l);
        toDisp = Integer.toString(c.get(Calendar.DATE)) +" " +getMonthName(c.get(Calendar.MONTH));
        return toDisp;
    }
    
    public static String getMonthName(int i){
        String name = "";
        switch(i){
            case 0: name = "Jan";
                break;
            case 1: name = "Feb";
                break;
            case 2: name = "Mar";
                break;
            case 3: name = "Apr";
                break;
            case 4: name = "May";
                break;
            case 5: name = "Jun";
                break;
            case 6: name = "Jul";
                break;
            case 7: name = "Aug";
                break;
            case 8: name = "Sep";
                break;
            case 9: name = "Oct";
                break;
            case 10: name = "Nov";
                break;
            case 11: name = "Dec";
                break;
        }
        return name;
    }
    
    public static String getTimeForDB()
    {
        Calendar  c = Calendar.getInstance();
        return Long.toString(c.getTimeInMillis());
    }
    
}