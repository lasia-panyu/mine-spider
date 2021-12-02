package nue.pan.spider.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pt_category")
public class PTCategory {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name="id",columnDefinition="varchar(32) COMMENT '菜单id'")
    public String id;
    @Column(name="category_name",columnDefinition="varchar(20) COMMENT '菜单名称'")
    public String categoryName;
    @Column(name="category_abbr",columnDefinition="varchar(20) COMMENT '菜单简称'")
    public String categoryAbbr;
}
