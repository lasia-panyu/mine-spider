package nue.pan.spider.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import nue.pan.spider.entity.*;
import nue.pan.spider.util.GeneralHandwritingOCR;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RequestMapping("/XWD")
@RestController
public class XWDController {
    String imageurl="C:\\Users\\Administrator\\Desktop\\";
    static String   imgUrl="/root/apache-tomcat-8.5.55/webapps/image/";
    static String   onlineUrl="http://pany.seaway.net.cn:8081/image/";
  //  @Autowired
  // CusImageRepository cusImageRepository;
    @Autowired
    CusRepository cusRepository;
    @Autowired
    CusIDCardRepository cusIDCardRepository;
    @Autowired
    CusImageRepository cusImageRepository;
    @Autowired
    CusLicenseRepository cusLicenseRepository;
    @Autowired
    CusBankCardRepository cusBankCardRepository;
    String wxLoginUrl="https://api.weixin.qq.com/sns/jscode2session?appid=wx644052bbfc1732a2&secret=a6156c87bb2a4562d8b397e32d85eab9&js_code=%s&grant_type=authorization_code";
    OkHttpClient client = new OkHttpClient();

  @RequestMapping(value = "/S",method = RequestMethod.POST)
    public String hello(@RequestParam("orcfile") MultipartFile orcfile,@RequestParam("type")String type,@RequestParam("cusid")String cusid,@RequestParam("filename")String filename) throws IOException {
//        System.out.println(type);
//        System.out.println(filename);
//        //File sb= new File("/home/"+orcfile.getOriginalFilename());
//
//        filename=cusid+filename.replace("wxfile://","");
//        System.out.println(filename);
//        File sb= new File("/root/apache-tomcat-8.5.55/webapps/image/"+filename);
          File sb= new File(imgUrl+orcfile.getName());
          orcfile.transferTo(sb);
          String s=GeneralHandwritingOCR.tencentOrcByGeneralHand(getImageStr(sb),type);
          sb.delete();
//        CusImage cusImage=new CusImage();
//        if(null == cusid||"".equals(cusid)||" ".equals(cusid)) {
//
//        }else{
//            cusImage.imageurl = "http://pany.seaway.net.cn:8081/image/"+filename;
//            System.out.println(getImageStr(sb));
//            cusImage.cusid = cusid;
//            cusImage.imagetype=type;
//            cusImageRepository.save(cusImage);
//        }
//        //pany.seaway.net.cn
//
       return  s;
//        //return "";
   }
    @RequestMapping(value = "/quickStep1",method = RequestMethod.POST)
    public Cus quickStep1(@RequestParam("orcfile") MultipartFile orcfile,@RequestParam("type") String type,@RequestParam("cus") String cus) throws IOException {
        System.out.println(cus);
        JSONObject user = JSONObject.parseObject(cus);
        Cus cusTmp= (Cus)JSONObject.parseObject(cus,Cus.class);
        System.out.println(cusTmp);
        String filename=UUID.randomUUID()+cusTmp.getCusNo()+orcfile.getName().replace("wxfile://","");
        File sb= new File(imgUrl+filename);
        orcfile.transferTo(sb);
        //cusRepository.save(cus);
        filename=onlineUrl+filename;
        cusTmp.setCusPhotoUrl(filename);
        cusTmp  = cusRepository.save(cusTmp);
        cusTmp.setCusName(GeneralHandwritingOCR.tencentOrcByGeneralHand(getImageStr(sb),type));
        System.out.println(cusTmp);
        return cusTmp;
    }
    @RequestMapping(value = "/quickStep2",method = RequestMethod.POST)
    public String quickStep2(@RequestBody Cus cus) throws IOException {
        System.out.println(cus);
        cus  = cusRepository.save(cus);
        System.out.println(cus);
        return JSONObject.toJSONString(cus);
    }
    @RequestMapping(value = "/quickStep4",method = RequestMethod.POST)
    public String quickStep4(@RequestBody Cus cus) throws IOException {
        System.out.println(cus);
        cusRepository.updateOne(cus.getCusName(),cus.getCusAddress(),cus.getCusPhone(),cus.getCusNo());
        System.out.println(cus);
        return JSONObject.toJSONString(cus);
    }
    @RequestMapping(value = "/quickStep3",method = RequestMethod.POST)
    public String quickStep3(@RequestParam("orcfile") MultipartFile orcfile,@RequestParam("imagetype") String imagetype,@RequestParam("imageurl") String imageurl,@RequestParam("cus") String cus) throws IOException {
        Cus cusTmp= (Cus)JSONObject.parseObject(cus,Cus.class);
        Cus cusNew=cusTmp;
        String filename=UUID.randomUUID()+cusTmp.getCusNo()+orcfile.getName().replace("wxfile://","");
        File sb= new File(imgUrl+filename);
        orcfile.transferTo(sb);
        String cusImageStr =GeneralHandwritingOCR.tencentOrcByGeneralHand(getImageStr(sb),imagetype);
        filename=onlineUrl+filename;
        //accountstype:["cusImage","cusIDcard","cusBankcard","cusLicense"],
        System.out.println("imagetype"+imagetype);
        if(imagetype.equals("通用文字")) {
            //System.out.println(imageurl+cusImageStr);
            CusImage cusImage = new CusImage();
            cusImage.setImageUrl(filename);
            cusImage.setContent(cusImageStr);
            cusImage.setCus(cusTmp);
            cusImageRepository.save(cusImage);
            //cusImage.setCusNo(cusTmp.getCusNo());
            //Set<CusImage> cusImageList = cusTmp.getCusImage();
            //for(CusImage cusI :cusImageList){
              //  if(cusImage.get)
            //}
            //cusImageList.add(cusImage);
            //for()
            //cusImage.setCus(cusNew);
            //System.out.println("cusImageList" + cusImageList);
            //cusTmp.setCusImage(cusImageList);
            //
            System.out.println("cusTmp" + cusTmp);
        }else if(imagetype.equals("身份证")){

            CusIDCard cusImage= (CusIDCard)JSONObject.parseObject(cusImageStr,CusIDCard.class);
            cusImage.setImageUrl(filename);
            if(cusTmp.getCusIDcard()!=null)
                cusImage.setId(cusTmp.getCusIDcard().getId());
            //Cus cus1=new Cus();
            //cus1.setCusNo(cusTmp.getCusNo());
            cusImage.setCus(cusTmp);
            //cusImage.setCusNo(cusTmp.getCusNo());
            //System.out.println("line108:"+cusTmp.getCusNo());
            //System.out.println("line108:"+cusImage);
            cusIDCardRepository.save(cusImage);
            //cusImage.setCus(cus1);
            //cusTmp.setCusIDcard(cusImage);
            //System.out.println("cusImage" + cusImage);
        }else if(imagetype.equals("银行卡")){
            CusBankCard cusImage= (CusBankCard)JSONObject.parseObject(cusImageStr,CusBankCard.class);
            cusImage.setImageUrl(filename);
            cusImage.setCus(cusTmp);
            cusBankCardRepository.save(cusImage);
            //cusImage.setCusNo(cusTmp.getCusNo());
            //cusImage.setCus(cusTmp);
            //Set<CusBankCard> cusImageList=cusTmp.getCusBankcard();
            //cusImageList.add(cusImage);
            //cusTmp.setCusBankcard(cusImageList);
            System.out.println("cusImage" + cusImage);
        }else if(imagetype.equals("营业执照")){
            CusLicense cusImage= (CusLicense)JSONObject.parseObject(cusImageStr,CusLicense.class);
            cusImage.setImageUrl(filename);
            cusImage.setCus(cusTmp);
            if(cusTmp.getCusLicense()!=null)
                cusImage.setId(cusTmp.getCusLicense().getId());
            cusLicenseRepository.save(cusImage);
            //cusImage.setCusNo(cusTmp.getCusNo());
            //cusTmp.setCusLicense(cusImage);
            //cusImage.setCus(cusTmp);
            //System.out.println("cusImage" + cusImage);
        }
        //System.out.println(cusTmp);

        cusTmp=cusRepository.findByCusNo(cusTmp.getCusNo());
        System.out.println("cysTmp"+cusTmp);
        return JSONObject.toJSONString(cusTmp);
    }
    @RequestMapping(value = "/wxLogin",method = RequestMethod.POST)
    public String wxLogin(@RequestParam("code") String code) throws IOException {
        System.out.println(code);
        Request request = new Request.Builder()
                .url(String.format(wxLoginUrl,code))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String wxResult=response.body().string();
            JSONObject wxResultJson = JSONObject.parseObject(wxResult);
            System.out.println(wxResultJson.get("openid").toString());
            return wxResultJson.get("openid").toString();
        }

      //  System.out.println(wxResult);
    }
    @RequestMapping(value = "/queryS",method = RequestMethod.POST)
    public String queryS(@RequestParam("cuscounter" )String cuscounter, @RequestParam("page")Integer page, @RequestParam("cusname")String cusname){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(cuscounter);
        System.out.println(page);
        System.out.println(cusname+"~~~~~~~~");
        page=(page==null || page<=0)?1:page ;
        Pageable pageable = PageRequest.of(page-1, 10);
        //Pageable pageable = PageRequest.of(page-1, 10, Sort.by("newsDate"));
        System.out.println(cusRepository.findByCusNameLikeAndCusCounterLike("%"+cusname+"%",cuscounter,pageable).getContent());
        if(" ".equals(cusname) || ("").equals(cusname)||null==cusname)
            return JSONObject.toJSONString(cusRepository.findByCusNameLikeAndCusCounterLike("%"+cusname+"%",cuscounter,pageable).getContent());
        else
            return JSONObject.toJSONString(cusRepository.findByCusNameLikeAndCusCounterLike("%"+cusname+"%",cuscounter,pageable).getContent());
    }
//    @RequestMapping(value = "/queryImage",method = RequestMethod.POST)
//    public List<CusImage> queryImage(@RequestParam("cusid" )String cusid){
//         System.out.println(cusid);
//         Pageable pageable = PageRequest.of(0, 100);
//         System.out.println(cusImageRepository.findByCusid(cusid));
//         return cusImageRepository.findByCusid(cusid);
//    }
//    @RequestMapping(value = "/saveImage",method = RequestMethod.POST)
//    public CusImage saveImage(@RequestParam("orcfile") MultipartFile orcfile, CusImage cusImage) throws IOException {
//        String filename=cusImage.cusid+cusImage.imageurl.replace("wxfile://","");
//        System.out.println(filename);
//        File sb= new File("/root/apache-tomcat-8.5.55/webapps/image/"+filename);
//        orcfile.transferTo(sb);
//        cusImage.imageurl="http://pany.seaway.net.cn:8081/image/"+filename;;
//        cusImageRepository.save(cusImage);
//        return cusImage;
//    }
//    @RequestMapping(value = "/saveCus",method = RequestMethod.POST)
//    public Cus saveCus(@RequestBody Cus cus){
//        System.out.println(cus.toString());
//        cusRepository.save(cus);
//        return cus;
//    }
    public static String getImageStr(File imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
    //    @RequestMapping(value = "/S",method = RequestMethod.POST)
}
