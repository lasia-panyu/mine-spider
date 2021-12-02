package nue.pan.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import nue.pan.spider.util.UtilTools;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "xwd_cus")
public class Cus {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '商户id'")
    public String cusNo;
    @Column(name="cus_counter",columnDefinition="varchar(30) COMMENT '客户经理openid'")
    public String cusCounter;
    @Column(name="cus_phone",columnDefinition="varchar(11) COMMENT '商户电话'")
    public String cusPhone;
    @Column(name="cus_address",columnDefinition="varchar(50) COMMENT '商户地址'")
    private String cusAddress;
    @Column(name="cus_name",columnDefinition="varchar(50) COMMENT '商户名称'")
    private String cusName;
    @Column(name="cus_latitude",columnDefinition="double(20,8) COMMENT '商户维度'")
    private Double cusLatitude;
    @Column(name="cus_longitude",columnDefinition="double(20,8) COMMENT '商户经度'")
    private Double cusLongitude;
    @Column(name="cus_photo",columnDefinition="varchar(255) COMMENT '商户店面'")
    private String cusPhotoUrl;

    @CreatedDate
    @Column(name="create_time")
    private String cusCreateDate;
    @Column(name="create_by")
    private String createBy;
    @LastModifiedDate
    @Column(name="update_time")
    private String cusModifyDate;
    @Column(name="update_by")
    private String updateBy;
    @Column(name="sys_org_code")
    private String sysOrgCode;

    public Cus(){
        this.cusCreateDate=UtilTools.getNowDate();
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cus")
    @JsonBackReference(value = "cusBankcard")
    private List<CusBankCard> cusBankcard=new ArrayList<CusBankCard>();
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "cus")
    @JsonBackReference(value = "cusIDcard")
    private CusIDCard  cusIDcard;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "cus")
    @JsonBackReference(value = "cusLicense")
    private CusLicense  cusLicense;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cus")
    @JsonBackReference(value = "cusImage")
    private List<CusImage> cusImage=new ArrayList<>();
    /**
     * 身份证
     * {"Name":"马明","Sex":"男","Nation":"汉","Birth":"1969/11/30","Address":"辽宁省阜新市海州区新渠路31-2-107","IdNum":"210902196911300257","Authority":"","ValidDate":"","AdvancedInfo":"{}","RequestId":"e9e2cc8e-6145-4298-a1f5-9a8ff7453088"}
     */


    /**
     * {"RegNum":"92210902MA0X99131X","Name":"阜新市海州区容裕名酒行","Capital":"","Person":"马明","Address":"辽宁省阜新市海州区滨河路22号1网","Business":"烟、酒、预包装食品零售(依法须经批准的项目,经相关部门批准后后方可开展经营活动)","Type":"个体工商户","Period":"2013年09月24日至长期","ComposingForm":"个人经营","SetDate":"2013年09月24日","RequestId":"c775e50c-87d5-47df-b8a6-e5319ba0eb22"}
     */
}
