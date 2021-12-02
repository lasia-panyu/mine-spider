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
@Table(name = "xwd_cuslicense")
public class CusLicense   {

    /**
     * {"RegNum":"92210902MA0X99131X","Name":"阜新市海州区容裕名酒行",
     * "Capital":"","Person":"马明","Address":"辽宁省阜新市海州区滨河路22号1网",
     * "Business":"烟、酒、预包装食品零售(依法须经批准的项目,经相关部门批准后后方可开展经营活动)",
     * "Type":"个体工商户","Period":"2013年09月24日至长期","ComposingForm":"个人经营",
     * "SetDate":"2013年09月24日","RequestId":"c775e50c-87d5-47df-b8a6-e5319ba0eb22"}
     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '营业执照id'")
    public String id;
    //@Column(name="cuslicense_cusno")
    //private int cusNo;
    @Column(name="cuslicense_regnum",columnDefinition="varchar(30) COMMENT '营业执照号码'")
    private String regNum;
    @Column(name="cuslicense_name",columnDefinition="varchar(30) COMMENT '营业执照名称'")
    private String name;
    @Column(name="cuslicense_capital",columnDefinition="varchar(30) COMMENT '注册资本'")
    private String capital;
    @Column(name="cuslicense_person",columnDefinition="varchar(30) COMMENT '法定代表人姓名'")
    private String person;
    @Column(name="cuslicense_address",columnDefinition="varchar(100) COMMENT '地址'")
    private String address;
    @Column(name="cuslicense_business",columnDefinition="varchar(50) COMMENT '营业范围'")
    private String business;
    @Column(name="cuslicense_type",columnDefinition="varchar(10) COMMENT '商户类型'")
    private String type;
    @Column(name="cuslicense_period",columnDefinition="varchar(20) COMMENT '有效期'")
    private String period;
    @Column(name="cuslicense_composingform",columnDefinition="varchar(20) COMMENT '组成形式'")
    private String composingForm;
    @Column(name="cuslicense_setDate",columnDefinition="varchar(50) COMMENT '发证日期'")
    private String setDate;
    @Column(name="cuslicense_imageurl",columnDefinition="varchar(255) COMMENT '营业执照照片'")
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
    @OneToOne   //图片之于相册，多对一
    @JoinColumn  //抑制ORM建造多余表
    @JsonBackReference  //设置少的一方管理集合，属性名于mappedBy相对应
    private Cus cus;
    public CusLicense(){
        this.createDate= UtilTools.getNowDate();
    }
    public String toString(){
        return "";
    }
}
