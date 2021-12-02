package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * news
 * @author 
 */
@Data
public class News implements Serializable {
    private Integer new_id;

    private String news_md5;

    private String news_desc;

    private String news_href;

    private String news_name;

    private String news_date;

    private String news_time;

    private String news_title;

    private static final long serialVersionUID = 1L;
}