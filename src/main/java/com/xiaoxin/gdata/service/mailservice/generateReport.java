package com.xiaoxin.gdata.service.mailservice;

import org.mortbay.xml.XmlParser;

import javax.lang.model.element.Element;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class generateReport {

    /**
     * 判断字符串中是否包含数字
     * @param content 查找的字符串
     * @return 是否包含
     */
    public static boolean hasDigit(String content) {

        boolean flag = false;

        Pattern p = Pattern.compile(".*\\d+.*");

        Matcher m = p.matcher(content);

        if (m.matches())

            flag = true;

        return flag;

    }

    /**
     * 获取到最新的目录
     * @return 返回目录的名称
     */
    private static String getLastesFolder() {
        File path=new File("./test-output/");
        //列出该目录下所有文件和文件夹
        File[] files = path.listFiles();
        //按照文件最后修改日期倒序排序
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return (int)(file2.lastModified()-file1.lastModified());
            }
        });
        //取出第一个(即最新修改的)文件，打印文件名
        for (File file : files) {
            System.out.println(file.getName());
            if (hasDigit(file.getName())) {
                return file.getName();
            }
        }
        return files[0].getName();
    }


    /**
     * 读取Suite.xml文件
     * @return 对应的html内容
     */
 /*   public static String readSuitsXml() {
        XmlParser xmlParser = new XmlParser("./test-output/"+getLastesFolder()+"/TEST-TestSuite.xml");
        List elements = xmlParser.getElementsByTag(xmlParser.getRootElement(), "testcase");
        int testCasePass = 0, testCaseFail = 0, testCaseSkip = 0;
        String baseHtml;
        for (Object element1 : elements) {
            Element element = (Element) element1;
            if (xmlParser.getElementByTag(element, "failure") == null) {
                baseHtml += "<li class=\"list-group-item list-group-item-success\"><strong>" + xmlParser.
                        getElementByAttr(element, "name") + "</strong></li>\n";
                testCasePass += 1;
            }else {
                baseHtml += "<li class=\"list-group-item list-group-item-danger\"><strong>" + xmlParser.
                        getElementByAttr(element, "name") + "</strong></li>\n";
                testCaseFail += 1;
            }
        }
        baseHtml +="</ul>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        baseHtml = baseHtml.replace("SUCCESSCOUNT", String.valueOf(testCasePass));
        baseHtml = baseHtml.replace("FAILCOUNT", String.valueOf(testCaseFail));
        baseHtml = baseHtml.replace("SKIPCOUNT", String.valueOf(testCaseSkip));
        return baseHtml;
    }*/
}
