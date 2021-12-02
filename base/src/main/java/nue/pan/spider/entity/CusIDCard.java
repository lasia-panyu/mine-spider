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
@Table(name = "xwd_cusidcard")
public class CusIDCard   {

    /**
     * 身份证
     * {"Name":"马明","Sex":"男","Nation":"汉","Birth":"1969/11/30",
     * "Address":"辽宁省阜新市海州区新渠路31-2-107",
     * "IdNum":"210902196911300257",
     * "Authority":"",
     * "ValidDate":"",
     * "AdvancedInfo":"{}",
     * "RequestId":"e9e2cc8e-6145-4298-a1f5-9a8ff7453088"}
     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '身份证id'")
    public String id;
    //@Column(name="cus_no")
    //private  int cusNo;
    @Column(name="cusidcard_name",columnDefinition="varchar(10) COMMENT '姓名'")
    private String name;
    @Column(name="cusidcard_sex",columnDefinition="varchar(2) COMMENT '性别'")
    private String sex;
    @Column(name="cusidcard_nation",columnDefinition="varchar(15) COMMENT '民族'")
    private String nation;
    @Column(name="cusidcard_birth",columnDefinition="varchar(10) COMMENT '出生日期'")
    private String birth;
    @Column(name="cusidcard_address",columnDefinition="varchar(50) COMMENT '居住地址'")
    private String address;
    @Column(name="cusidcard_idnum",columnDefinition="varchar(18) COMMENT '身份证号'")
    private String idNum;
    @Column(name="cusidcard_authority",columnDefinition="varchar(20) COMMENT '发证机关'")
    private String authority;
    @Column(name="cusidcard_validDate",columnDefinition="varchar(20) COMMENT '有效日期'")
    private String validDate;
    @Column(name="cusidcard_imageurl",columnDefinition="varchar(255) COMMENT '身份证号'")
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

    @OneToOne  //图片之于相册，多对一
    @JoinColumn()  //抑制ORM建造多余表
    @JsonBackReference  //设置少的一方管理集合，属性名于mappedBy相对应
    private Cus cus;
    public CusIDCard(){
        this.createDate= UtilTools.getNowDate();
    }
    public String toString(){
        return "";
    }
}
