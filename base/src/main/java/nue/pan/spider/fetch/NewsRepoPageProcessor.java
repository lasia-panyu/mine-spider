package nue.pan.spider.fetch;


import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.util.MD5Utils;
import cn.edu.hfut.dmic.webcollector.net.*;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import nue.pan.spider.base.BaseApplication;
import nue.pan.spider.controller.NewsController;
import nue.pan.spider.entity.News;
import nue.pan.spider.entity.NewsRepository;
import nue.pan.spider.util.UtilTools;
import okhttp3.OkHttpClient;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static nue.pan.spider.controller.NewsController.newsList;

public class NewsRepoPageProcessor extends BreadthCrawler {
    public static String newsUrlHead="https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=";
    public static String newsUrlTail="&medium=0";
    public static String newsUrl="";

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date=new Date();
    Proxies proxies;
    public static class MyRequester extends OkHttpRequester {

        Proxies proxies;

        public MyRequester() {
            proxies = new Proxies();
            // add a socks proxy
            proxies.addHttpProxy(UtilTools.ip, UtilTools.port);
            // null means direct connection without proxy
        }

        @Override
        public OkHttpClient.Builder createOkHttpClientBuilder() {
            return super.createOkHttpClientBuilder()
                    // 设置一个代理选择器
                    .proxySelector(new ProxySelector() {
                        @Override
                        public List<Proxy> select(URI uri) {
                            // 随机选择1个代理
                            Proxy randomProxy = proxies.get(0);
                            // 返回值类型需要是List
                            List<Proxy> randomProxies = new ArrayList<Proxy>();
                            //如果随机到null，即不需要代理，返回空的List即可
                            //  if(randomProxy != null) {
                            randomProxies.add(randomProxy);
                            //}
                            System.out.println("Random Proxies:" + randomProxies);
                            return randomProxies;
                        }

                        @Override
                        public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                            proxies = new Proxies();
                            String body = Unirest.get("http://api.xdaili.cn/xdaili-api/greatRecharge/getGreatIp?spiderId=a307ff7c748d4a9ca04233f9e1a84bbf&orderno=YZ2020413497f52fU1&returnType=2&count=1").asString().getBody();
                            JSONObject json = new JSONObject(body);
                            if(json.get("ERRORCODE").toString() == "10055"){
                                try {
                                    Thread.sleep(10000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                body = Unirest.get("http://api.xdaili.cn/xdaili-api/greatRecharge/getGreatIp?spiderId=a307ff7c748d4a9ca04233f9e1a84bbf&orderno=YZ2020413497f52fU1&returnType=2&count=1").asString().getBody();
                            }
                            json = new JSONObject(body);
                            UtilTools.ip= json.getJSONArray("RESULT").getJSONObject(0).get("ip").toString();
                            //json.getJSONObject("RESULT").get("port");
                            UtilTools.port = Integer.valueOf( json.getJSONArray("RESULT").getJSONObject(0).get("port").toString());
                            proxies.addSocksProxy(NewsController.ip, NewsController.port);
                            System.out.println("垃圾代理ip");
                        }

                    });
        }
    }
    public NewsRepoPageProcessor(String crawlPath, boolean autoParse,String url) {
        super(crawlPath, autoParse);
        setRequester(new MyRequester());
        addSeed(url);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        Elements elements=page.select("div[class=\"result\"]");
        String desc="";
        for(int i=0;i<elements.size();i++){
            Element element=elements.get(i);
           // News news =new News("大唐河南发电有限公司",element.select("h3>a").text(),element.select("h3>a").attr("href"),element.select("div>p").text().replaceAll("&nbsp;",""),element.select("div>div").text().replaceAll("百度快照",""));

            System.out.println(element.select("h3>a").text());
            System.out.println(element.select("h3>a").attr("href"));
            System.out.println(element.select("div>p").text().replaceAll("&nbsp;",""));
            System.out.println(element.select("div").text().replaceAll("百度快照",""));
            Elements elements1=element.select("div>div");
            for( int z=0;z<elements1.size();z++){
                if(!elements1.get(z).toString().contains("c_photo"))
                 desc=elements1.get(z).text();
            }
            News news = null;
            try {
                news = new News(MD5Utils.md5(element.select("h3>a").attr("href").getBytes()),simpleDateFormat.format(date),NewsController.name,element.select("h3>a").text(),element.select("h3>a").attr("href"),element.select("div>p").text().replaceAll("&nbsp;",""),desc);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
          //  System.out.println(news.toString());
            UtilTools.newsList.add(news);
            //System.out.println(news.toString()) ;

        }
        System.out.println("===================================完成==========================================");
    }


}
