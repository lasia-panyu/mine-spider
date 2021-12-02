package nue.pan.spider.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OphisRepository extends PagingAndSortingRepository<Ophis, String> {

}
