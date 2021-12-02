package nue.pan.spider.util;

import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import nue.pan.spider.entity.News;
import nue.pan.spider.fetch.NewsRepoPageProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.w3c.dom.Element;
public class UtilTools {
    public static Map<String,String> resultMap=new HashMap<>();
    public static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    public static String daiLiUrl="http://api.xdaili.cn/xdaili-api/greatRecharge/getGreatIp?spiderId=a307ff7c748d4a9ca04233f9e1a84bbf&orderno=YZ2020413497f52fU1&returnType=2&count=1";
    public static String ip;
    public static int port;
    public static int changeDailiCount=10;
    public static List<News> newsList=null;
    public static String atag="<a href=\\\"%s\\\"> %s</a>\n";
    public static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");

    /**
     *
     * @param sMsg
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     *
     * <xml>
     *    <ToUserName><![CDATA[toUser]]></ToUserName>
     *    <FromUserName><![CDATA[fromUser]]></FromUserName>
     *    <CreateTime>1348831860</CreateTime>
     *    <MsgType><![CDATA[text]]></MsgType>
     *    <Content><![CDATA[this is a test]]></Content>
     *    <MsgId>1234567890123456</MsgId>
     *    <AgentID>1</AgentID>
     * </xml>
     *
     */

    public static Map<String,String> resultToMap(String sMsg) throws IOException, SAXException,ParserConfigurationException {
        DocumentBuilder db =dbf.newDocumentBuilder();
        Document document = db.parse(new InputSource(new StringReader(sMsg)));
        NodeList root =  document.getDocumentElement().getChildNodes();
        resultMap=new HashMap<>();
        for (int i=0;i<root.getLength();i++)
            resultMap.put(root.item(i).getNodeName(),root.item(i).getTextContent());
        return resultMap;
    }

    public static  String  getDaili() throws InterruptedException {
        String body = Unirest.get(daiLiUrl).asString().getBody();
        JSONObject json = new JSONObject(body);
        System.out.println(body);
        System.out.println(json.toString());
        while(json.get("ERRORCODE").toString() == "10055"){
            Thread.sleep(10000);
            body = Unirest.get(daiLiUrl).asString().getBody();
            json = new JSONObject(body);
        }
        json = new JSONObject(body);
        ip = json.getJSONArray("RESULT").getJSONObject(0).get("ip").toString();
        //json.getJSONObject("RESULT").get("port");
        port = Integer.valueOf( json.getJSONArray("RESULT").getJSONObject(0).get("port").toString());
        return ip+":"+port;
    }

    public static List<News> fetchData(String newsNameList) throws Exception {
        Thread.sleep(6000);
        newsList=new ArrayList<>();
        getDaili();
        int useCount=0;
        for(String newsName:newsNameList.split(" ")){
            System.out.println("newsName"+newsName);
            if(useCount>10) {
                getDaili();
                useCount=0;
            }else{
                useCount++;
            }

            NewsRepoPageProcessor.newsUrl = NewsRepoPageProcessor.newsUrlHead + newsName + NewsRepoPageProcessor.newsUrlTail;
            System.out.println("newsUrl"+NewsRepoPageProcessor.newsUrl);
            NewsRepoPageProcessor newsFetch = new NewsRepoPageProcessor("crawl", true, NewsRepoPageProcessor.newsUrl);
            newsFetch.setThreads(1);
            newsFetch.start(1);
            //for(News news:newsList)
            //    newsRepository.save(news);
        }
        return newsList;
    }
    public static List<News> saveData(String newsNameList) throws Exception {

        int useCount=0;
        for(String newsName:newsNameList.split(" ")){
            if(useCount>10) {
                getDaili();
                useCount=0;
            }else{
                useCount++;
            }

            NewsRepoPageProcessor.newsUrl = NewsRepoPageProcessor.newsUrlHead + newsName + NewsRepoPageProcessor.newsUrlTail;
            NewsRepoPageProcessor newsFetch = new NewsRepoPageProcessor("crawl", true, NewsRepoPageProcessor.newsUrl);
            newsFetch.setThreads(1);
            newsFetch.start(1);
            //for(News news:newsList)
            //    newsRepository.save(news);
            newsList=new ArrayList<>();
        }
        return newsList;
    }

    public static void main(String[] args)  throws ParserConfigurationException, IOException, SAXException {
        String sMsg="<xml>\n" +
                "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "   <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
                "   <CreateTime>1348831860</CreateTime>\n" +
                "   <MsgType><![CDATA[text]]></MsgType>\n" +
                "   <Content><![CDATA[this is a test]]></Content>\n" +
                "   <MsgId>1234567890123456</MsgId>\n" +
                "   <AgentID>1</AgentID>\n" +
                "</xml>";
        Map<String,String> resultMap=new HashMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db =dbf.newDocumentBuilder();

        StringReader sr = new StringReader(sMsg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        NodeList root =  document.getDocumentElement().getChildNodes();
        for (int i=0;i<root.getLength();i++){
            System.out.println(root.item(i).getNodeName());
            System.out.println(root.item(i).getTextContent());

        }
        for (String s :"阜新银行".split(" "))
             System.out.println(s);
    }
    public static String getNowDate(){

        return sf.format(new Date());
    }

}
