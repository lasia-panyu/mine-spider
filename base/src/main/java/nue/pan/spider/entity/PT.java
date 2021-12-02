package nue.pan.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pt_pintuan")
public class PT {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="pt_id",columnDefinition="varchar(32) COMMENT '拼团id'")
    public String id;
    @Column(name="cre_openId",columnDefinition="varchar(100) COMMENT '用户openid'")
    public String creOpenId;
    @Column(name="cre_url",columnDefinition="varchar(255) COMMENT '用户头像'")
    public String creAvatarUrl;
    @Column(name="pt_date",columnDefinition="varchar(12) COMMENT '组队日期'")
    public String ptdate;
    @Column(name="pt_time",columnDefinition="varchar(8) COMMENT '组队时间'")
    public String pttime;
    @Column(name="pt_state",columnDefinition="varchar(1) COMMENT '组队状态 0 发起拼团 1 拼团成功 2 拼团失败'")
    public String ptstate;
    @Column(name="pt_num",columnDefinition="varchar(255) COMMENT '组队人数'")
    public String num;
    @Column(name="pt_toalnum",columnDefinition="varchar(10) COMMENT '游戏人数'")
    public String totalnum;
    @Column(name="pt_cusid",columnDefinition="varchar(32) COMMENT '商户id'")
    public String cusid;
    @Column(name="pt_gameid",columnDefinition="varchar(32) COMMENT '游戏id'")
    public String gameid;
    @Column(name="pt_ptdesc",columnDefinition="varchar(255) COMMENT '组队说明'")
    public String ptDesc;
    @ManyToMany(mappedBy = "ptList", fetch = FetchType.EAGER)
    public List<PTUser> userList;


}
