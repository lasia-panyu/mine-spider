package nue.pan.spider.wechat;

import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import nue.pan.spider.base.BaseApplication;
import nue.pan.spider.entity.News;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static nue.pan.spider.wechat.WechatBody.sendMsgBody;

public class WechatTools {
    public static String corpid="ww131a6ededa06d31a";
    public static String corpsecret="c7CqJcJimq7vf9UIGesEy-pVq7lRr08PpZDN2IYmS84";
    public static long tokenDate=(Long)1585790498131L;
    public static String wechatUrl="https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    public static long expireDate=7200000L;
    public static String accessToken="LB7gHRw-RAbfOKP_l2MzEuc6SRb41jwklk_w2wGLRe__R6r7JewlN7AWL6qaTppkNL3Iqpi-Oz4bCOdjDFQ49wLfDICOLU45QyY9DIAJ3Z19ADJfm2Jnd3DX7EVkQ9NmqoN1PzH25Xk7--iZNBWyBf85ZFVoF84LK9okGIxsmM3FSVkTrDxz4rQIcH1o5lO9lnrM3sDIP13sdeKB1q5H4w";
    public static String wechatMsgUrl="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s";

    public static String token="l68Li4o8";
    public static String encodingAESKey="9yyD42fUyE1SAdLXFmWlLpSmIGjP4kCuKMoS1sSK8Ya";


    public static void main(String[] args) throws InterruptedException {
        tokenDate=new Date().getTime();
        String body=Unirest.post(String.format(wechatMsgUrl,getToken())).body(String.format(sendMsgBody,"hahahahaha")).asJson().getBody().toString();
        System.out.println("body~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:"+body);
    }

    public static void sendMsg(String msg) throws InterruptedException {
       String body=Unirest.post(String.format(wechatMsgUrl,getToken())).body(String.format(sendMsgBody,msg)).asJson().getBody().toString();
       System.out.println("body~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:"+body);
    }

    public static String getToken() throws InterruptedException {
        if(new Date().getTime()-tokenDate<=expireDate)
            return accessToken;
        else{
            String body = Unirest.get(wechatUrl).queryString("corpid",corpid).queryString("corpsecret",corpsecret).asString().getBody();
            System.out.println("body~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:"+body);
            JSONObject json = new JSONObject(body);
            accessToken=json.get("access_token").toString();
            tokenDate=new Date().getTime();
            System.out.println(accessToken);
            System.out.println(tokenDate);
            return accessToken;
        }
    }
    public static String reciveMsgBody="<xml>\n" +
            "   <ToUserName><![CDATA[%s]]></ToUserName>\n" +
            "   <FromUserName><![CDATA[%s]]></FromUserName> \n" +
            "   <CreateTime>%s</CreateTime>\n" +
            "   <MsgType><![CDATA[%s]]></MsgType>\n" +
            "   <Content><![CDATA[%s]]></Content>\n" +
            "</xml>";
    public static String buildMsgResponse(String sMsg, Map<String,String>sMsgMap){
        return String.format(WechatBody.reciveMsgBody,sMsgMap.get("FromUserName"),sMsgMap.get("ToUserName"),sMsgMap.get("CreateTime"),sMsgMap.get("MsgType"),sMsg);
    }

}
