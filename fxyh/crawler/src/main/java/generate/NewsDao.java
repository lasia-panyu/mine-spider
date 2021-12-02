package generate;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NewsDao {
    int deleteByPrimaryKey(Integer new_id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer new_id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}