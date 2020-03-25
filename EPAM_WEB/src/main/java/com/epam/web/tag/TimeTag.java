package com.epam.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTag extends TagSupport {

    private static final int ONE_HOUR = 3600000;

    @Override
    public int doStartTag() throws JspException {
        Date date = new Date();
        long currentTime = date.getTime();
        date.setTime(currentTime + ONE_HOUR);
        SimpleDateFormat time = new SimpleDateFormat("kk:mm");
        try {
            String input =
                    "<input type=\"time\" name=\"time\" id=\"time\" min=\""
                    + time.format(date)
                    + "\" max=\"21:00\">";
            JspWriter writer = pageContext.getOut();
            writer.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
