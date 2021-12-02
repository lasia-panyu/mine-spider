package nue.pan.spider.entity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PTRepository extends PagingAndSortingRepository<PT, String> {
    PT findByPtdateAndPttimeAndCreOpenIdAndCusidAndGameid(String ptdate, String pttime, String creOpenId, String cusid, String gameid);
    List<PT> findByCreOpenIdAndPtstateIn(String openId,     List<String> ptState);
    List<PT> findByCreOpenIdAndPtstate(String openId,String ptState);
}
