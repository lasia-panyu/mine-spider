package fxyh.crewler.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fxyh.crewler.bean.NewCategroy;
import fxyh.crewler.bean.News;
import fxyh.crewler.engine.MineEngine;
import fxyh.crewler.mapper.NewCategroyDao;
import fxyh.crewler.mapper.NewsDao;
import fxyh.wechat.tools.WechatTools;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Component
@Log
public class TaskController {
    @Autowired
    public NewsDao newsMapper;
    @Autowired
    public NewCategroyDao newCategroyMapper;

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void fxyhNews() throws ClassNotFoundException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
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
            if(title!=null&&newCategroy.getCategroyPushflag()==1)
                WechatTools.sendMsgMarkDown(title);
            Thread.sleep(30000);
        }
        log.info(newCategroyList.toString());



    }
}
