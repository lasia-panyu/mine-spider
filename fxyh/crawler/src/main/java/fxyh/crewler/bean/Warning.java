package fxyh.crewler.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("WARNING")
public class Warning {
    private String id;
    private String warning;
    private String BitcoinAddress;
    private String Email;
}
