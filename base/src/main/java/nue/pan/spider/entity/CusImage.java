package nue.pan.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import nue.pan.spider.util.UtilTools;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "xwd_cusimage")
public class CusImage       {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '图片id'")
    public String id;
    //@Column(name="cusimage_cusno")
    //private  int cusNo;
    @Column(name="cusimage_imageurl",columnDefinition="varchar(255) COMMENT '图片地址'")
    public String imageUrl;
    @Column(name="cusimage_content",columnDefinition = "text COMMENT '识别内容'")
    private String content;


    @CreatedDate
    @Column(name="create_time")
    private String createDate;
    @Column(name="create_by")
    private String createBy;
    @LastModifiedDate
    @Column(name="update_time")
    private String cusModifyDate;
    @Column(name="update_by")
    private String updateBy;
    @Column(name="sys_org_code")
    private String sysOrgCode;
    @ManyToOne   //图片之于相册，多对一
    @JoinColumn //抑制ORM建造多余表
    @JsonBackReference  //设置少的一方管理集合，属性名于mappedBy相对应
    private Cus cus;
    public CusImage(){
        this.createDate= UtilTools.getNowDate();
    }
    public String toString(){
        return "";
    }
    /*
    @Id
    @Column(name="imageid")
    public int imageid;
    @Column(name="cusid")
    public String cusid;
    @Column(name="imagetype")
    public String imagetype;
    @Column(name="imageurl")
    public String imageurl;
    @Column(name="imagedate")
    public String imagedate;
     */

}
