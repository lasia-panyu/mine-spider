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
@Table(name = "xwd_cusbankcard")
public class CusBankCard     {


    /*
    {"CardNo":"623166001022732835","BankInfo":"阜新银行(04672299)",
    "ValidDate":"11/2029","RequestId":"83bccbfa-f108-4e20-9e3a-86c2d5de420a"}

     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '银行卡id'")
    public String id;
    //@Column(name="cus_cus_no")
    //private int cusNo;
    @Column(name="cusbankcard_cardno",columnDefinition="varchar(18) COMMENT '银行卡号'")
    public String cardNo;
    @Column(name="cusbankcard_bankinfo",columnDefinition="varchar(18) COMMENT '银行名称'")
    public String bankInfo;
    @Column(name="cusbankcard_validdate",columnDefinition="varchar(18) COMMENT '有效期'")
    public String validDate;
    @Column(name="cusbankcard_imageurl",columnDefinition="varchar(255) COMMENT '银行卡图片'")
    public String imageUrl;

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


    @ManyToOne  //图片之于相册，多对一
    @JoinColumn  //抑制ORM建造多余表
    @JsonBackReference  //设置少的一方管理集合，属性名于mappedBy相对应
    private Cus cus;
    public CusBankCard(){
        this.createDate= UtilTools.getNowDate();
    }
    public String toString(){
          return "";
    }
}
