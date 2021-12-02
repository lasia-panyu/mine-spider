package fxyh.crewler.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * news
 * @author 
 */
@Data
public class News implements Serializable {
    private Integer newId;

    private String newsMd5;

    private String newsDesc;

    private String newsHref;

    private String newsName;

    private String newsDate;

    private String newsTime;

    private String newsTitle;

    private static final long serialVersionUID = 1L;
}