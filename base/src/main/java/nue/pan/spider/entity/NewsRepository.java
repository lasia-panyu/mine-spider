package nue.pan.spider.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<News, String> {
    Page<News> findByNameLike(String name, Pageable pageable);
}
