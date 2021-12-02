package nue.pan.spider.util;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.ocr.v20181119.OcrClient;

import com.tencentcloudapi.ocr.v20181119.models.*;
import nue.pan.spider.entity.Cus;

public class GeneralHandwritingOCR
{
    public static String  tencentOrcByGeneralHand(String  imagesBase64,String type) {
        try{

            Credential cred = new Credential("AKIDIRJze5yQHbjaFaIYAWeXXHSO2RZSON4q", "gCUdV6RDKBnBNJ0TpEOJyOYQ4U3Tr0ve");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);

            String params = "{\"ImageBase64\":\""+imagesBase64+"\"}";
            switch (type){
                //通用文字", "身份证","银行卡", "营业执照","增值税发票"
                case "通用文字":
                    System.out.println("通用文字1");
                    GeneralHandwritingOCRRequest req = GeneralHandwritingOCRRequest.fromJsonString(params, GeneralHandwritingOCRRequest.class);
                    GeneralHandwritingOCRResponse resp = client.GeneralHandwritingOCR(req);
                    System.out.println(GeneralHandwritingOCRRequest.toJsonString(resp));
                    System.out.println("通用文字2");
                    String orcTmp=GeneralHandwritingOCRRequest.toJsonString(resp);
                    JSONObject ocrObj = JSONObject.parseObject(orcTmp);
                    String orcResult="";
                    if(ocrObj.containsKey("TextDetections")) {
                        JSONArray orcArray=ocrObj.getJSONArray("TextDetections");

                        for (int ocrNum=0;ocrNum<orcArray.size();ocrNum++){
                              orcResult+=orcArray.getJSONObject(ocrNum).getString("DetectedText");
                        }
                    }else{

                    }
                    return orcResult;
                case "身份证":
                    IDCardOCRRequest idcardreq = IDCardOCRRequest.fromJsonString(params, IDCardOCRRequest.class);
                    IDCardOCRResponse idcardresp = client.IDCardOCR(idcardreq);
                    System.out.println(IDCardOCRResponse.toJsonString(idcardresp));
                    return IDCardOCRResponse.toJsonString(idcardresp);
                case "银行卡":
                    BankCardOCRRequest bankreq = BankCardOCRRequest.fromJsonString(params, BankCardOCRRequest.class);
                    BankCardOCRResponse bankresp = client.BankCardOCR(bankreq);
                    System.out.println(BankCardOCRRequest.toJsonString(bankresp));
                    return BankCardOCRRequest.toJsonString(bankresp);
                case "营业执照":
                    BizLicenseOCRRequest licensereq = BizLicenseOCRRequest.fromJsonString(params, BizLicenseOCRRequest.class);
                    BizLicenseOCRResponse licenseresp = client.BizLicenseOCR(licensereq);
                    System.out.println(BizLicenseOCRResponse.toJsonString(licenseresp));
                    return BizLicenseOCRResponse.toJsonString(licenseresp);
                case "增值税发票":
                    VatInvoiceOCRRequest invoicereq = VatInvoiceOCRRequest.fromJsonString(params, VatInvoiceOCRRequest.class);
                    VatInvoiceOCRResponse invoiceresp = client.VatInvoiceOCR(invoicereq);
                    System.out.println(VatInvoiceOCRResponse.toJsonString(invoiceresp));
                    return VatInvoiceOCRResponse.toJsonString(invoiceresp);

            }
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
     return "";
    }

}
