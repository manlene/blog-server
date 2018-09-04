package com.mm.blog.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: mm
 * @Date: 2018/9/1 19:03
 * @Description:
 */

public class MarkdownEntity {


    // 最外网的div标签， 可以用来设置样式，宽高，字体等
    private Map<String, String> divStyle = new ConcurrentHashMap<String, String>();

    // 转换后的html文档
    private String html;

    public MarkdownEntity() {
    }

    public MarkdownEntity(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "\n<div " + parseDiv() + ">\n" + html + "\n</div>";
    }


    private String parseDiv() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : divStyle.entrySet()) {
            builder.append(entry.getKey()).append("=\"")
                    .append(entry.getValue()).append("\" ");
        }
        return builder.toString();
    }


    public void addDivStyle(String attrKey, String value) {
        if (divStyle.containsKey(attrKey)) {
            divStyle.put(attrKey, divStyle.get(attrKey) + " " + value);
        } else {
            divStyle.put(attrKey, value);
        }
    }


    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}

