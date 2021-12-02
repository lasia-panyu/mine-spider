package nue.pan.spider.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import nue.pan.spider.entity.*;
import nue.pan.spider.util.GeneralHandwritingOCR;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RequestMapping("/PT")
@RestController
public class PTController {
    @Autowired
    PTRepository ptRepository;
    @Autowired
    PTUserRepository ptUserRepository;
    @Autowired
    PTCategoryRepository ptCategoryRepository;
    @Autowired
    PTCusRepository ptCusRepository;
    @Autowired
    PTGamesRepository ptGamesRepository;
    String wxLoginUrl="https://api.weixin.qq.com/sns/jscode2session?appid=wxf66a58768c92fc23&secret=7e72199bf565072437e33a70c2fcabd2&js_code=%s&grant_type=authorization_code";
    OkHttpClient client = new OkHttpClient();
    @RequestMapping(value = "/queryCategory",method = RequestMethod.POST)
    public List<PTCategory> hello() throws IOException {
        Pageable pageable = PageRequest.of(0, 20);
        return ptCategoryRepository.findAll(pageable).getContent();
  }
    @RequestMapping(value = "/queryGames",method = RequestMethod.POST)

    public List<PTCus> getCusGames() throws IOException {
        Pageable pageable = PageRequest.of(0, 200);
        //if(gameType.equals("")||gameType==null)
        List<PTCus> ptCus=ptCusRepository.findAll(pageable).getContent();
        //List<PTGames> ptGCus=ptGamesRepository.findAll(pageable).getContent();
        //System.out.println(ptCus);
        //System.out.println(JSONObject.toJSONString(ptCus));
        return ptCus;
    }
    @RequestMapping(value = "/queryTpGames",method = RequestMethod.POST)

    public List<PTGames> queryTpGames(@RequestParam("type") String type) throws IOException {
        Pageable pageable = PageRequest.of(0, 200);
        //if(gameType.equals("")||gameType==null)
        List<PTGames> ptGames=ptGamesRepository.findByGamesType(type,pageable).getContent();
        System.out.println(ptGames);
        //List<PTGames> ptGCus=ptGamesRepository.findAll(pageable).getContent();
        //System.out.println(ptCus);
        //System.out.println(JSONObject.toJSONString(ptCus));
        return ptGames;
    }
    @RequestMapping(value = "/crePt",method = RequestMethod.POST)
    public Map<String,PT> crePt(@RequestBody PT pt) throws IOException {

        System.out.println("pt.toString()"+pt.toString());
        Pageable pageable = PageRequest.of(0, 200);
        //if(gameType.equals("")||gameType==null
        Map result=new HashMap<String, PT>();
        System.out.println(ptRepository.findByPtdateAndPttimeAndCreOpenIdAndCusidAndGameid(pt.getPtdate(),pt.getPttime(),pt.getCreOpenId(),pt.getCusid(),pt.getGameid()));
        //(String ptdate, String pttime, String creOpenId, String cusid, String gameid)
        if(ptRepository.findByPtdateAndPttimeAndCreOpenIdAndCusidAndGameid(pt.getPtdate(),pt.getPttime(),pt.getCreOpenId(),pt.getCusid(),pt.getGameid())==null) {
            pt.setPtstate("0");
            PT ptResult = ptRepository.save(pt);
            PTUser ptUser=pt.getUserList().get(0);
            ptUser.setId(ptUser.getOpenId());
            PTUser dataPtUser=ptUserRepository.findByOpenId(ptUser.getOpenId());
            ptUser=dataPtUser==null?ptUser:dataPtUser;
            ptUser.getPtList().add(pt);
            ptUser=ptUserRepository.save(ptUser);
            pt=ptRepository.save(pt);
            System.out.println(ptResult);
            result.put("ok", ptResult);
            return result;
        }
        else {
            result.put("error",null);
            return result;
        }
    }

    @RequestMapping(value = "/queryGamesDetail",method = RequestMethod.POST)
    public Map<String,Object>  queryGamesDetail(@RequestParam("gameid")String gameid , @RequestParam("cusid")String cusid) throws IOException {
        PTGames ptGames=ptGamesRepository.findById(gameid).get();
        PTCus ptCus=ptCusRepository.findById(cusid).get();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("ptGames",ptGames);
        resultMap.put("ptCus",ptCus);
        return resultMap;

    }
    @RequestMapping(value = "/queryMyPt",method = RequestMethod.POST)
    public List<PT> queryMyPt(@RequestParam("openid")String openid,@RequestParam("type")String type) throws IOException {
        List<PT> ptList;
        List typeList=new ArrayList<String>();
        System.out.println(openid);
        System.out.println(type);
        if(type.equals("1")){
            ptList=ptRepository.findByCreOpenIdAndPtstate(openid,"0");
        }else{
            typeList.add("1");
            typeList.add("2");
            ptList=ptRepository.findByCreOpenIdAndPtstateIn(openid,typeList);
        }
        System.out.println("ptList.toString()"+ptList.toString());
        return ptList;

    }
    @RequestMapping(value = "/jionPt",method = RequestMethod.POST)
    public Map<String,Object>  jionpt(@RequestParam("pt")String pt,@RequestParam("user")String user,@RequestParam("phone")String phone,@RequestParam("formId")String formId){
        System.out.println("coming~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        PT ptTmp= (PT)JSONObject.parseObject(pt,PT.class);
        PTUser ptUserTmp= (PTUser)JSONObject.parseObject(user,PTUser.class);
        Map<String,Object> result=new HashMap<>();

        ptTmp=ptRepository.findById(ptTmp.getId()).get();
        System.out.println(ptTmp);
        if(!ptTmp.getPtstate().equals("0")){
            result.put("result","orderState");
            return result;
        }
        System.out.print(ptTmp.toString());
        System.out.print(user.toString());
        System.out.print(phone.toString());
        System.out.print(formId.toString());

        PTUser dataPtUser=ptUserRepository.findByOpenId(ptUserTmp.getOpenId());
        ptUserTmp=dataPtUser==null?ptUserTmp:dataPtUser;
        ptUserTmp.setUserPhone(phone);
        ptUserTmp.getPtList().add(ptTmp);

        String resultTmp="suc";
        ptTmp.setNum(ptTmp.getNum()+1);

        if(Integer.valueOf(ptTmp.getTotalnum())-Integer.valueOf(ptTmp.getNum())==0){
            ptTmp.setPtstate("1");
            resultTmp="full";


        }else{


        }
        ptUserTmp=ptUserRepository.save(ptUserTmp);
        ptTmp=ptRepository.save(ptTmp);
        result.put("result",resultTmp);
        result.put("ptUser", ptUserTmp);
        result.put("pt", ptTmp);
        return result;
    }

    @RequestMapping(value = "/wxLogin",method = RequestMethod.POST)
    public String wxLogin(@RequestParam("code") String code) throws IOException {
        System.out.println(code);
        Request request = new Request.Builder()
                .url(String.format(wxLoginUrl,code))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String wxResult=response.body().string();
            System.out.println(wxResult);
            JSONObject wxResultJson = JSONObject.parseObject(wxResult);
            System.out.println(wxResultJson.toString());
            return wxResultJson.get("openid").toString();
        }

        //  System.out.println(wxResult);
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add() throws IOException {
        String addData="寒江愁~12+1桌游吧\n" +
                "雪人~12+1桌游吧\n" +
                "浮图孽世~12+1桌游吧\n" +
                "爱幼妇产医院~12+1桌游吧\n" +
                "作揖~剧有意思推理馆\n" +
                "杀人回忆~剧有意思推理馆\n" +
                "来日方长~剧有意思推理馆\n" +
                "朝夕村~剧有意思推理馆\n" +
                "恶渊百物语~剧有意思推理馆\n" +
                "何似在人间~剧有意思推理馆\n" +
                "老废物俱乐部~剧有意思推理馆\n" +
                "奉天1928~剧有意思推理馆\n" +
                "肆零贰~当剧者迷\n" +
                "窗边的女人~当剧者迷\n" +
                "朝夕村~当剧者迷\n" +
                "奉天1928~当剧者迷\n" +
                "何似在人间~当剧者迷\n" +
                "雾夜将尽~当剧者迷\n" +
                "22条校规~当剧者迷\n" +
                "禁果~当剧者迷\n" +
                "羽生夜谈~当剧者迷\n" +
                "孽岛疑云~当剧者迷\n" +
                "鸢飞戾天~当剧者迷\n" +
                "I DO~谜团剧本杀桌游俱乐部\n" +
                "禁果~谜团剧本杀桌游俱乐部\n" +
                "蒙轻纱的处女~谜团剧本杀桌游俱乐部\n" +
                "年轮~谜团剧本杀桌游俱乐部\n" +
                "花子~谜团剧本杀桌游俱乐部\n" +
                "面具~谜团剧本杀桌游俱乐部\n" +
                "失格21克~谜团剧本杀桌游俱乐部\n" +
                "精神病院~谜团剧本杀桌游俱乐部\n" +
                "精神病院2~谜团剧本杀桌游俱乐部\n" +
                "精神病院3~谜团剧本杀桌游俱乐部\n" +
                "肆零贰~谜团剧本杀桌游俱乐部\n" +
                "肆零贰~大理肆探案馆\n" +
                "年轮~大理肆探案馆\n" +
                "孤城~大理肆探案馆\n" +
                "赶尸人~大理肆探案馆\n" +
                "葬爱出征，寸草不生~大理肆探案馆\n" +
                "家宴~大理肆探案馆\n" +
                "野樱花事件~Cbb推理社\n" +
                "三国新演义~Cbb推理社\n" +
                "蛹~Cbb推理社\n" +
                "一百零一~Cbb推理社\n" +
                "局~Cbb推理社\n" +
                "人事~啄木鸟推理社\n" +
                "二十二条校规~啄木鸟推理社\n" +
                "麋鹿神社~啄木鸟推理社\n" +
                "人事~夜幕\n" +
                "二十二条校规~夜幕\n" +
                "麋鹿神社~夜幕";
        String[] data=addData.split("\n");
        Pageable pageable = PageRequest.of(0, 200);
        List<PTGames> ptGames=ptGamesRepository.findAll(pageable).getContent();
        List<PTCus> ptCus=ptCusRepository.findAll(pageable).getContent();
        for(PTGames ptGames1:ptGames){
            List<PTCus> ptcus1=new ArrayList<>();
            for(int i=0;i<data.length;i++){
                if(data[i].split("~")[0].equals(ptGames1.getGamesName())){
                    for(PTCus cus:ptCus){
                        if(cus.getCusName().equals(data[i].split("~")[1])){
                           // if (cus.getPtGamesList().size()==0)
                             //   cus.setPtGamesList(new ArrayList<PTGames>());
                            //cus.getPtGamesList().add(ptGames1);
                            System.out.println("match"+cus.getCusName());
                            System.out.println("match"+ptGames1.getGamesName());
                            ptcus1.add(cus);
                        }
                    }
                }
            }
            System.out.println("ptCus~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ptcus1);
            ptGames1.setPtCusList(ptcus1);
            System.out.println(ptGames1);
            //ptCusRepository.saveAll(ptCus);
            ptGamesRepository.save(ptGames1);
        }
//        for(int i=0;i<ptGames.length;i++){
//            PTGames ptGames=new PTGames();
//               ptGames.setGamesName(data[i].split("~")[0]);
//            ptGames.setGamesNum(data[i].split("~")[1]);
//            ptGames.setGamesType("jbs");
//            ptGamesRepository.save(ptGames);
//        }
        return "ojbk";
    }

}
