package nue.pan.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pt_user")
public class PTUser  implements Serializable {
    @Id
    @Column(name="user_id",columnDefinition="varchar(30) COMMENT '客户id'")
    public String id;
    @Column(name="user_openid",columnDefinition="varchar(30) COMMENT '用户openid'")
    public String openId;
    @Column(name="user_url",columnDefinition="varchar(255) COMMENT '用户头像'")
    public String avatarUrl;
    @Column(name="user_city",columnDefinition="varchar(20) COMMENT '用户城市'")
    public String city;
    @Column(name="user_gender",columnDefinition="varchar(1) COMMENT '用户性别'")
    public String gender;
    @Column(name="user_language",columnDefinition="varchar(11) COMMENT '用户语言'")
    public String language;
    @Column(name="user_nickName",columnDefinition="varchar(11) COMMENT '用户名称'")
    public String nickName;
    @Column(name="user_province",columnDefinition="varchar(11) COMMENT '用户省份'")
    public String province;
    @Column(name="user_phone",columnDefinition="varchar(11) COMMENT '用户电话'")
    public String userPhone;
    @ManyToMany(targetEntity = PT.class, cascade = CascadeType.ALL)
    @JoinTable(name = "user_pt",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "pt_id", referencedColumnName = "pt_id")})
    @JsonIgnore
    private List<PT> ptList=new ArrayList<PT>();
    public String toString(){
        return "";
    }

}
