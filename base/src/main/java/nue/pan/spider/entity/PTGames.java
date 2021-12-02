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
@Table(name = "pt_games")
public class PTGames {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '游戏id'")
    public String id;
    @Column(name="games_name",columnDefinition="varchar(20) COMMENT '游戏名称'")
    public String gamesName;
    @Column(name="games_desc",columnDefinition="varchar(20) COMMENT '游戏简介'")
    public String gamesDesc;
    @Column(name="games_type",columnDefinition="varchar(10) COMMENT '游戏类型'")
    public String gamesType;
    @Column(name="games_num",columnDefinition="int(2) COMMENT '游戏人数'")
    public String gamesNum;

    @ManyToMany(targetEntity = PTCus.class, cascade = CascadeType.ALL)
    @JoinTable(name = "cus_games",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "games_id", referencedColumnName = "id")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "cus_id", referencedColumnName = "id")})
    @JsonBackReference
    private List<PTCus> ptCusList=new ArrayList<PTCus>();
    public String toString(){
        return "";
    }
}
