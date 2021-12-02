package nue.pan.spider.entity;

import lombok.Data;
import nue.pan.spider.util.UtilTools;

import javax.persistence.*;

@Data
@Entity
@Table(name = "News")
public class News {

    @Column(name="news_name")
    public String name;
    @Column(name="news_href")
    private String href;
    @Column(name="news_desc")
    private String desc;
    @Column(name="news_time")
    private String time;
    @Column(name="news_title")
    private String title;
    @Id
    @Column(name="news_md5")
    private String newsMd5;
    @Column(name="news_date")
    private String newsDate;
    public News(){}
    public News(String md5,String date,String name,String text, String href, String replaceAll, String replaceAll1) {
        this.newsMd5=md5;
        this.newsDate=date;
        this.name=name;
        this.title=text;
        this.href=href;
        this.time=replaceAll;
        this.desc=replaceAll1;
    }
    public String getAtag(){
        System.out.println(title.replaceAll("\"","\'"));
        return String.format(UtilTools.atag,href,title.replaceAll("\"","\'"));
    }
}
