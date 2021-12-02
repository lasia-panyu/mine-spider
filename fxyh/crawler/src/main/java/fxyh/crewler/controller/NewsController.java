package fxyh.crewler.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fxyh.crewler.bean.NewCategroy;
import fxyh.crewler.bean.News;
import fxyh.crewler.engine.MineEngine;
import fxyh.crewler.mapper.NewCategroyDao;
import fxyh.crewler.mapper.NewsDao;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequestMapping("/")
@RestController
@Data
@Slf4j

public class NewsController {
    @Autowired
    public NewsDao newsMapper;
    @Autowired
    public NewCategroyDao newCategroyMapper;

    private Lock lock = new ReentrantLock();
    public  void  crewler() throws InterruptedException {
//        lock.lock();
//        int nowTime=(int) (new Date().getTime()/1000);
//        int startTime=nowTime-86400;
//        log.info("nowTime"+nowTime);
//        log.info("startTime"+startTime);
//        String url=String.format("%s%s",String.valueOf(startTime),String.valueOf(nowTime));
//        url="https://www.baidu.com/s?wd=阜新银行&gpc=stf%"+startTime+"%"+nowTime;
//        log.info("url:"+url);
//        GeccoEngine.create()
//                //Gecco搜索的包路径
//                .classpath("fxyh.crewler")
//                .debug(false)
//                .loop(false)
//                //开启几个爬虫线程
//                .thread(1)
//                //开始抓取的页面地址
//                //tf%3D1608598780%2C1608685180%
//                .start(url)
//                .start();
//        lock.unlock();
//
//        while (Tools.news.isEmpty()){
//            Thread.sleep(15000);
//            log.info(Tools.news.toString());
//        }
//        log.info("news.toString()"+ Tools.news.toString());
//        String title="";
//        for(Map.Entry<String, News> entry:Tools.news.entrySet()){
//            log.info("entry.getValue().toString()"+entry.getValue().toString());
//            if(newsDao.selectById(entry.getValue().getNewsMd5())==null) {
//               // newsDao.
//                newsDao.insert(entry.getValue());
//              //  title+=String.format("<a href='%s'>%s</a>\n",entry.getValue().getNewsHref(),entry.getValue().getNewsTitle());
//               // title+="<hr/>\n";
//                title+=String.format(">[%s](%s)\n>\n",entry.getValue().getNewsName(),entry.getValue().getNewsHref());
//            }
//           // log.info("entry.getValue().toString()"+String.valueOf(result));
//        }
//        log.info("title:"+title);
//        WechatTools.sendMsgMarkDown(title);
    }
    @RequestMapping("/")
    public String hello() throws InterruptedException, ClassNotFoundException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        QueryWrapper<NewCategroy> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("categroy_type", "百度");
        List<NewCategroy> newCategroyList=newCategroyMapper.selectList(queryWrapper);
        int nowTime = (int) (new Date().getTime() / 1000);
        int startTime = nowTime - 86400;
//        String url = String.format("%s%s", String.valueOf(startTime), String.valueOf(nowTime));//阜新银行
        String url ="";//阜新银行
        for(NewCategroy newCategroy:newCategroyList){
            if(newCategroy.getCategroyFlag()!=1)
                continue;
            url = "https://www.baidu.com/s?wd="+newCategroy.getCategroyKeyword().trim()+"&gpc=stf%" + startTime + "%" + nowTime;
            log.info(url);
            MineEngine mineEngine=new MineEngine(url);
            log.info(newsMapper.toString());
            String title="";
            for(News news:(List<News>)mineEngine.load()){
                QueryWrapper<News> newSQueryWrapper = new QueryWrapper<News>();
                newSQueryWrapper.eq("news_md5",news.getNewsMd5());
//                News value=newsMapper.selectByNewsMd5(news.getNewsMd5());
                log.info(news.toString());
                News value=newsMapper.selectOne(newSQueryWrapper);

                if(value==null){
                    newsMapper.insert(news);
                    // title+=String.format("<a href='%s'>%s</a>\n",news.getNewsHref(),news.getNewsTitle());
                    title+=String.format(">[%s](%s)\n>\n",news.getNewsName(),news.getNewsHref());
                }
                log.info(String.valueOf(value));
            }
            if(title!=null&&newCategroy.getCategroyPushflag()==1) {
                WechatTools.sendMsgMarkDown(title);
            }
            Thread.sleep(30000);
        }
        log.info(newCategroyList.toString());

//        crewler();
        return "";
    }
    @RequestMapping("/newsList")
    public List<News> newsList() throws InterruptedException, ClassNotFoundException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        log.info(newCategroyMapper.toString());
//        QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
//        //queryWrapper.ge("categroy_type", "百度");
//        List<News> newList=newsMapper.selectList(queryWrapper);
////        crewler();
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        List<News> value=newsMapper.selectList(queryWrapper);
        log.info(value.toString());
        return value;
    }
    @RequestMapping("/keysList")
    public List<NewCategroy> keysList() throws InterruptedException, ClassNotFoundException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        QueryWrapper<NewCategroy> queryWrapper = new QueryWrapper<NewCategroy>();
        List<NewCategroy> value=newCategroyMapper.selectList(queryWrapper);
        log.info(value.toString());
        return value;
    }
    @RequestMapping("/updateKey")
    public NewCategroy keysUpdate(NewCategroy newsCategroy){
           log.info("newsCategroy.toString()"+newsCategroy.toString());
           int num=newCategroyMapper.updateById(newsCategroy);
           log.info("num"+num);
           NewCategroy result=newCategroyMapper.selectById(newsCategroy.getCategroyId());
           return result;
    }
}
