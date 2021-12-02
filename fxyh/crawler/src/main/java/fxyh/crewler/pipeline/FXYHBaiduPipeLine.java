package fxyh.crewler.pipeline;

import com.geccocrawler.gecco.annotation.PipelineName;
import fxyh.crewler.bean.FXYHBaiduNewsList;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
@PipelineName("FXYHPipeline")
@Slf4j
@Data
@RestController
public class FXYHBaiduPipeLine {


    public void process(FXYHBaiduNewsList bean) {
        // log.debug(JSON.toJSONString(bean));
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd hhMMss");
//        for (FXYHBaiduNews baiduNews : bean.getListFXYHBaiduNews()) {
//            News news=new News();
//            JSONObject js=JSONObject.parseObject(baiduNews.getBaiduNewsSource());
//            news.setNewsDate(sf.format(new Date()));
//            /     news.setNewsTime(sf.format(new Date()));
//            news.setNewsDesc(baiduNews.getBaiduNewsContent());
//            news.setNewsHref(js.getString("url"));
//            news.setNewsMd5(DigestUtils.md5Hex(baiduNews.getBaiduNewsTitle()));
//            news.setNewsName(js.getString("title"));
//            news.setNewsTime(sf.format(new Date()));
//            news.setNewsTitle(baiduNews.getBaiduNewsTitle());
//            Tools.news.put(js.getString("url"),news);
//            log.info("NewsController.news.toString()"+Tools.news.toString());
//           // newsDao.insert(news);
//        }
        //log.info("~~~~~~~~~~~~~~~~~");
        //log.info(bean.toString());
        //log.info("1:"+.size());
        // log.info("1:"+bean.getFXYHBaiduNews());
    }
}
