package nue.pan.spider.entity;

import lombok.Data;
import nue.pan.spider.util.UtilTools;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ophis")
public class Ophis {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="news_name")
    private String newsName;
    @Column(name="query_name")
    private String queryName;
    @Column(name="query_date")
    private String queryDate;
    public Ophis(){}
    public Ophis(String newsName, String queryName, String queryDate) {
        this.newsName=newsName;
        this.queryName=queryName;
        this.queryDate=queryDate;
    }
}
