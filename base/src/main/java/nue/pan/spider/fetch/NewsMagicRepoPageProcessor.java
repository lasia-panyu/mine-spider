package nue.pan.spider.fetch;


import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.net.Proxies;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.util.MD5Utils;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import nue.pan.spider.controller.NewsController;
import nue.pan.spider.entity.News;
import okhttp3.OkHttpClient;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsMagicRepoPageProcessor  implements PageProcessor {

    private Site site = Site.me().setRetryTimes(1).setSleepTime(3000);
    @Override
    public void process(Page page) {//*[@class="result"]
        System.out.println(page.getHtml().toString());
        System.out.println(page.getHtml().$("div[class=\"result\"]").all());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(page.getHtml().xpath("//*[@class=\"result\"]").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        Spider.create(new NewsMagicRepoPageProcessor()).addUrl("https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=江苏常熟农村商业银行股份有限公司&medium=0 (URL: https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=江苏常熟农村商业银行股份有限公司&medium=0").thread(1).run();
    }
}
