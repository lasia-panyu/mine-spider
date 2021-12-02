package fxyh.crewler.bean;


import com.alibaba.fastjson.JSONObject;
import fxyh.crewler.annoation.Mine;
import fxyh.crewler.annoation.MineDo;
import fxyh.crewler.annoation.MineType;
import fxyh.crewler.annoation.Path;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Mine(crewlerUrl = "www.baidu.com")
@Log
public class FXYHBaiduNewsList     {

    @Path(cssPath = "#content_left > div.result")
    public List<FXYHBaiduNews> listFXYHBaiduNews;

    public  static FXYHBaiduNewsList fxyhBaiduNewsList;
    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd hhMMss");

    static String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        return conn.getHeaderField("Location");
    }

    @MineDo({MineType.after})
    public ArrayList<News> m1(FXYHBaiduNewsList fxyhBaiduNewsList) throws Exception {

        ArrayList newList=new ArrayList();
        log.info(fxyhBaiduNewsList.toString());
        for(FXYHBaiduNews baiduNews:fxyhBaiduNewsList.listFXYHBaiduNews){
            News news = new News();
            JSONObject js=JSONObject.parseObject(baiduNews.getBaiduNewsSource());
            log.info(baiduNews.toString());
            baiduNews.setNewsMd5(DigestUtils.md5Hex(baiduNews.getNewsName()));
            String url=getRedirectUrl(js.getString("url"));
            log.info(url);
            baiduNews.setNewsHref(url);
            baiduNews.setNewsDate(sf.format(new Date()));
            baiduNews.setNewsTime(sf.format(new Date()));
            BeanUtils.copyProperties(baiduNews, news);
           // newService.count(queryWrapper);
            newList.add(news);
           // newService.se
        }

//        newsDao.;
        System.out.println("已经调用m1");
        return newList;
    }

    @Override
    public String toString() {
        return "FXYHBaiduNewsList{" +
                "listFXYHBaiduNews=" + listFXYHBaiduNews +
                '}';
    }

    private static ApplicationContext applicationContext;
}
