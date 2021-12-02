package nue.pan.spider.entity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CusImageRepository extends PagingAndSortingRepository<CusImage, String> {
;
}
