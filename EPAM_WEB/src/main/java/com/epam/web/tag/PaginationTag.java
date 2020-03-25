package com.epam.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.stream.IntStream;

@SuppressWarnings("serial")
public class PaginationTag extends TagSupport {

    private static final int CARDS = 4;

    private int amount;
    private int page;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            int amountPages = pageCount();
            StringBuilder stringBuilder = new StringBuilder();

            IntStream.range(0, amountPages)
                    .forEach(number -> createButton(stringBuilder, number + 1));

            String buttons = stringBuilder.toString();
            JspWriter writer = pageContext.getOut();
            writer.write(buttons);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

    private int pageCount() {
        double value = (double) amount / CARDS;
        return (int) Math.ceil(value);
    }

    private void createButton(StringBuilder stringBuilder, int number) {
        stringBuilder
                .append("<a href=\"controller?command=menu&page=")
                .append(number)
                .append("\"");
        if (number == page) {
            stringBuilder.append(" class=\"active\">");
        } else {
            stringBuilder.append(">");
        }
        stringBuilder
                .append(number)
                .append("</a>");
    }
}
