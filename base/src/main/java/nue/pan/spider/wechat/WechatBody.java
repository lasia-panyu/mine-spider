package nue.pan.spider.wechat;

public class WechatBody {
    public static String sendMsgBody="{\n" +
            "   \"touser\" : \"@all\",\n" +
            "   \"toparty\" : \"2\",\n" +
            "   \"totag\" : \"\",\n" +
            "   \"msgtype\" : \"text\",\n" +
            "   \"agentid\" : 1000002,\n" +
            "   \"text\" : {\n" +
            "       \"content\" : \"%s\"\n" +
            "   },\n" +
            "   \"safe\":0,\n" +
            "   \"enable_id_trans\": 0,\n" +
            "   \"enable_duplicate_check\": 0,\n" +
            "   \"duplicate_check_interval\": 1800\n" +
            "}";

    public static String reciveMsgBody="<xml>\n" +
            "   <ToUserName><![CDATA[%s]]></ToUserName>\n" +
            "   <FromUserName><![CDATA[%s]]></FromUserName> \n" +
            "   <CreateTime>%s</CreateTime>\n" +
            "   <MsgType><![CDATA[%s]]></MsgType>\n" +
            "   <Content><![CDATA[%s]]></Content>\n" +
            "</xml>";

}
