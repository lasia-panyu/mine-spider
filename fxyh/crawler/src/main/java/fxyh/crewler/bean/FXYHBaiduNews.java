package fxyh.crewler.bean;


import fxyh.crewler.annoation.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FXYHBaiduNews {
    @Path(cssPath = "h3.t > a")
    String newsName;

    @Path(cssPath = "div.c-abstract")
    private String newsDesc;

    @Path(cssPath = "div.f13.c-gap-top-xsmall.se_st_footer.user-avatar > a.c-showurl.c-color-gray")
    private String newsHref;

    @Attr("data-tools")
    @Path(cssPath = "div.f13.c-gap-top-xsmall.se_st_footer.user-avatar > div.c-tools.c-gap-left")
    private String baiduNewsSource;

    private String newsMd5;
    private String newsDate;
    private String newsTime;
    private String newsTitle;
    //@Html
    //@HtmlField(cssPath="")
    //private String htmlContent;

    //@Href
    //String baiduNewsHref;
    //@Attr
    //String baiduNewsTime;

//    @MineDo({MineType.after})
//    public void m2(){
//        System.out.println("已经调用m1");
//    }
}
