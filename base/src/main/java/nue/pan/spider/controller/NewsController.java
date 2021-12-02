package nue.pan.spider.controller;

import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import nue.pan.spider.aes.AesException;
import nue.pan.spider.aes.WXBizMsgCrypt;
import nue.pan.spider.entity.News;
import nue.pan.spider.entity.NewsRepository;
import nue.pan.spider.entity.Ophis;
import nue.pan.spider.entity.OphisRepository;
import nue.pan.spider.fetch.NewsRepoPageProcessor;
import nue.pan.spider.util.UtilTools;
import nue.pan.spider.wechat.WechatTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/")
@RestController
public class NewsController {
    @Autowired
    private NewsRepository newsRepository ;
    @Autowired
    private OphisRepository ophisRepository;
    public static List<News> newsList=new ArrayList<>();
    public static String ip;
    public static int port;
    public static String  name="";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date=new Date();
    public static String  corname="";
    public static String  ncorname="";
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    public  String  hostcheck() throws Exception {
        File file = new File("C:\\Users\\Administrator\\Desktop\\ISSUER.txt");

        BufferedReader bf  = new BufferedReader(new FileReader(file));
        String line=bf.readLine();

        int i=11;
        while((line=bf.readLine())!=null) {
            Thread.sleep(1000);
            name=line;
            if(i>10) {
                String body = Unirest.get("http://api.xdaili.cn/xdaili-api/greatRecharge/getGreatIp?spiderId=a307ff7c748d4a9ca04233f9e1a84bbf&orderno=YZ2020413497f52fU1&returnType=2&count=1").asString().getBody();
                System.out.println(body);
                JSONObject json = new JSONObject(body);
                if(json.get("ERRORCODE").toString() == "10055"){
                    Thread.sleep(10000);
                    body = Unirest.get("http://api.xdaili.cn/xdaili-api/greatRecharge/getGreatIp?spiderId=a307ff7c748d4a9ca04233f9e1a84bbf&orderno=YZ2020413497f52fU1&returnType=2&count=1").asString().getBody();
                    System.out.println("body:"+body);
                    json = new JSONObject(body);
                }
                json = new JSONObject(body);
                ip = json.getJSONArray("RESULT").getJSONObject(0).get("ip").toString();
                //json.getJSONObject("RESULT").get("port");
                port = Integer.valueOf( json.getJSONArray("RESULT").getJSONObject(0).get("port").toString());
                i=0;
            }else{
                i++;
            }

            System.out.println(line+ip+port);

            NewsRepoPageProcessor.newsUrl = NewsRepoPageProcessor.newsUrlHead + line + NewsRepoPageProcessor.newsUrlTail;
            System.out.println(NewsRepoPageProcessor.newsUrl);
            NewsRepoPageProcessor newsFetch = new NewsRepoPageProcessor("crawl", true, NewsRepoPageProcessor.newsUrl);
            newsFetch.setThreads(1);
            newsFetch.start(1);
            for(News news:newsList)
                newsRepository.save(news);
            newsList=new ArrayList<>();
       //String body="{\"ERRORCODE\":\"0\",\"RESULT\":[{\"port\":\"44415\",\"ip\":\"123.169.37.83\"}]}";

           // System.out.println(json.toString());

        }
        bf.close();
        return "ok!!!!!!!!!!!!!!!!!!!!1";
       // newsFetch.start(1);



//        while ((line = bf.readLine()) != null)
//        {
//               System.out.println(line);
//        }



    }


    public void singleTest(){

//        String body = Unirest.get("http://api.xdaili.cn/xdaili-api/greatRecharge/getGreatIp?spiderId=a307ff7c748d4a9ca04233f9e1a84bbf&orderno=YZ2020413497f52fU1&returnType=2&count=1").asString().getBody();
//        JSONObject json = new JSONObject(body);
//        ip = json.getJSONArray("RESULT").getJSONObject(0).get("ip").toString();
//        //json.getJSONObject("RESULT").get("port");
//        port = Integer.valueOf( json.getJSONArray("RESULT").getJSONObject(0).get("port").toString());
//
//
//        Unirest.config().reset();
//        Unirest.config().proxy(ip,port);
//        String page1 = Unirest.get("https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=江苏常熟农村商业银行股份有限公司&medium=0 (URL: https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=江苏常熟农村商业银行股份有限公司&medium=0").asString().getBody();
//        System.out.println(page1);
//        Page page=new Page(new CrawlDatum(),"1",page1.getBytes());
//        String desc="";
//        Elements elements=page.select("div[class=\"result\"]");
//        for(int i=0;i<elements.size();i++){
//            Element element=elements.get(i);
//            // News news =new News("大唐河南发电有限公司",element.select("h3>a").text(),element.select("h3>a").attr("href"),element.select("div>p").text().replaceAll("&nbsp;",""),element.select("div>div").text().replaceAll("百度快照",""));
//
//            System.out.println(element.select("h3>a").text());
//            System.out.println(element.select("h3>a").attr("href"));
//            System.out.println(element.select("div>p").text().replaceAll("&nbsp;",""));
//            System.out.println(element.select("div").text().replaceAll("百度快照",""));
//            Elements elements1=element.select("div>div");
//            for( int z=0;z<elements1.size();z++){
//                if(!elements1.get(z).toString().contains("c_photo"))
//                    desc=elements1.get(z).text();
//            }
//            News news = null;
//            try {
//                news = new News(MD5Utils.md5(element.select("h3>a").attr("href").getBytes()),simpleDateFormat.format(date),NewsController.name,element.select("h3>a").text(),element.select("h3>a").attr("href"),element.select("div>p").text().replaceAll("&nbsp;",""),desc);
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//            //  System.out.println(news.toString());
//            NewsController.newsList.add(news);
//            //System.out.println(news.toString()) ;
//
//        }
    }

    @RequestMapping(value="/",method= RequestMethod.GET)
    public String wechatCheck(@RequestParam("msg_signature") String sVerifyMsgSig,@RequestParam("timestamp") String sVerifyTimeStamp,@RequestParam("nonce") String sVerifyNonce,@RequestParam("echostr") String sVerifyEchoStr) throws AesException {
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WechatTools.token, WechatTools.encodingAESKey, WechatTools.corpid);
        System.out.println(sVerifyMsgSig+sVerifyTimeStamp+sVerifyNonce+sVerifyEchoStr);
        // 验证URL成功，将sEchoStr返回
        String sEchoStr= wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,sVerifyNonce, sVerifyEchoStr);
        return sEchoStr;
    }
    @RequestMapping(value="/",method= RequestMethod.POST)
    public String reciveMsg(@RequestParam("msg_signature") String sReqMsgSig, @RequestParam("timestamp") String sReqTimeStamp, @RequestParam("nonce") String sReqNonce, @RequestBody String sReqData, HttpServletResponse response) throws Exception {
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WechatTools.token, WechatTools.encodingAESKey, WechatTools.corpid);
        Map<String, String> sMsgMap =UtilTools.resultToMap(wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, sReqData));
        System.out.println(sMsgMap.toString());
        System.out.println(sMsgMap.get("MsgType"));
        String content=sMsgMap.get("Content");
        if(!sMsgMap.get("MsgType").equals("text"))
            return wxcpt.EncryptMsg(WechatTools.buildMsgResponse("请不要发送非消息类型",sMsgMap), sReqTimeStamp, sReqNonce);
        if(content.equals(ncorname)){
            System.out.println("ojbk"+content);
            return wxcpt.EncryptMsg(WechatTools.buildMsgResponse("请稍等！！！",sMsgMap), sReqTimeStamp, sReqNonce);
        }else {
            //if(!sMsgMap.get("MsgType").toString().equals("text"))
            // return wxcpt.EncryptMsg(WechatTools.buildMsgResponse("请不要发送非消息类型",sMsgMap), sReqTimeStamp, sReqNonce);
            //System.out.println(sMsg);
            //response.getWriter().write(wxcpt.EncryptMsg(WechatTools.buildMsgResponse("已收到，请等待推送！", sMsgMap), sReqTimeStamp, sReqNonce));
            //response.getWriter().close();

            ncorname=content;
            System.out.println("看看还出不出从"+content);
            sendMsg(content);
            ophisRepository.save(new Ophis(sMsgMap.get("FromUserName"),content,simpleDateFormat.format(new Date())));
            ncorname = content;
            // String sRespData = "<xml><ToUserName><![CDATA[PanYu]]></ToUserName><FromUserName><![CDATA[ww131a6ededa06d31a]]></FromUserName><CreateTime>"+Integer.valueOf((int) (new Date().getTime()/1000))+"</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+Content+"]]></Content></xml>";
            String sEncryptMsg = "";
            //sEncryptMsg = wxcpt.EncryptMsg(sRespData, sReqTimeStamp, sReqNonce);
            // System.out.println("after encrypt sEncrytMsg: " + sRespData);
            return wxcpt.EncryptMsg(WechatTools.buildMsgResponse("已收到，请等待推送",sMsgMap), sReqTimeStamp, sReqNonce);
        }

    }
    @RequestMapping(value="/queryOp/{pageNum}",method= RequestMethod.GET)
    public ModelAndView queryOp(@PathVariable  Integer pageNum, ModelAndView mv1, HttpServletRequest request){
        //System.out.println(mv1.getModel().get("pageNum"));
        pageNum=(pageNum==null || pageNum<=0)?1:pageNum ;
        System.out.println(pageNum);
        Pageable pageable = PageRequest.of(pageNum-1, 10,Sort.by("id"));
        Page<Ophis> ophisPage = ophisRepository.findAll(pageable);
        System.out.println(ophisPage.getContent());
        System.out.println(ophisPage.getTotalPages());
        ModelAndView mv = new ModelAndView();
        request.getSession().setAttribute("lastPage",(request.getSession().getAttribute("pageNum")==null||pageNum+1<ophisPage.getTotalPages())?pageNum+1:ophisPage.getTotalPages());
        if (ophisPage.getTotalPages()==1)
           request.getSession().setAttribute("lastPage",1);
        request.getSession().setAttribute("pageNum",pageNum);
        mv.addObject("ophisPage", ophisPage);
        mv.addObject("pageNum", pageNum);
        mv.setViewName("rate");
        return mv;
    }
    @RequestMapping(value="/queryNews/{pageNum}",method= RequestMethod.GET)
    public ModelAndView queryNews( @PathVariable Integer pageNum,@RequestParam(value = "content",required = false) String content,ModelAndView mv1, HttpServletRequest request){
        //System.out.println(mv1.getModel().get("pageNum"));
        //String content=request.getSession().getAttribute("content").toString();
        if(content==null)
            content=request.getSession().getAttribute("content")==null?null:request.getSession().getAttribute("content").toString();
        pageNum=(pageNum==null || pageNum<=0)?1:pageNum ;
        System.out.println(content);
        Pageable pageable = PageRequest.of(pageNum-1, 10,Sort.by("newsDate"));
        Page<News> newsPage = null;
        if (content==null||content.equals("init")||content.equals("")) {
            newsPage = newsRepository.findAll(pageable);
        }else{
            request.getSession().setAttribute("content",content);
            newsPage = newsRepository.findByNameLike("%"+content+"%",pageable);
        }
        System.out.println(newsPage.getContent());
        System.out.println(newsPage.getTotalPages());
        ModelAndView mv = new ModelAndView();
        List<Integer> pageList=new ArrayList<>();
        for(int i=0;i<5;i++){
            if((i + pageNum)<=newsPage.getTotalPages()){
                 pageList.add(i + pageNum);
            }else{
                if((i+pageNum-5)>1){
                    pageList.add(i + pageNum-5);
                }
            }
        }
        Collections.sort(pageList);
        mv.addObject("firstPage", 1);
        mv.addObject("newsPage", newsPage);
        mv.addObject("lastPage",newsPage.getTotalPages());
        request.getSession().setAttribute("pageNum",pageNum);
        mv.addObject("pageNum",pageNum);
        mv.addObject("pageList", pageList);

        mv.setViewName("querynews");
        return mv;
    }
    @Async
    public void sendMsg(String content) throws Exception {

            List<News> newsLIst = UtilTools.fetchData(content);
            for (News news:newsLIst)
                 news.name=content;
            newsRepository.saveAll(newsLIst);
            String result = "";
            for (News news : newsLIst) {
                System.out.println(news.toString());
                result += news.getAtag();
            }
            System.out.println(result);
            WechatTools.sendMsg(result);
            ncorname="";
    }
}
