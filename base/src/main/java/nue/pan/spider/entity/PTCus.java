package nue.pan.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pt_cus")
public class PTCus {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '商户id'")
    public String id;
    @Column(name="cus_name",columnDefinition="varchar(20) COMMENT '商户名称'")
    public String cusName;
    @Column(name="cus_addr",columnDefinition="varchar(20) COMMENT '商户地址'")
    public String cusAddr;
    @Column(name="cus_tel",columnDefinition="varchar(11) COMMENT '商户联系方式'")
    public String cusTel;
    @ManyToMany(mappedBy = "ptCusList", fetch = FetchType.EAGER)

    @JsonManagedReference
    private List<PTGames> ptGamesList=new ArrayList<PTGames>();

}
