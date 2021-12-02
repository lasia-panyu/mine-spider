package fxyh.crewler.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * new_categroy
 * @author 
 */
@Data
public class NewCategroy implements Serializable {
    @TableId
    private Integer categroyId;

    private String categroyName;

    private Integer categroyFlag;

    private String categroyType;

    private String categroyKeyword;

    private Integer categroyPushflag;

    private static final long serialVersionUID = 1L;
}