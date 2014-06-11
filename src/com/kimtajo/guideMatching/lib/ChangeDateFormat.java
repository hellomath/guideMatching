package com.kimtajo.guideMatching.lib;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Hellomath on 2014. 5. 1..
 */
public class ChangeDateFormat {
    public String formattedDate(String date, String fromFormatString, String toFormatString){
        SimpleDateFormat fromFormat = new SimpleDateFormat(fromFormatString);
        SimpleDateFormat toFormat = new SimpleDateFormat(toFormatString);
        Date fromDate = null;
        try{
            fromDate = fromFormat.parse(date);
        }catch(ParseException e){
            fromDate = new Date();
        }

        return toFormat.format(fromDate);

    }
    public String formattedDate(String date, String format){
        SimpleDateFormat toFormat = new SimpleDateFormat(format);
        return toFormat.format(date);
    }

}
