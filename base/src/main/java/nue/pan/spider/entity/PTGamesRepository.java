package nue.pan.spider.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PTGamesRepository extends PagingAndSortingRepository<PTGames, String> {
    Page<PTGames> findByGamesType(String gameType, Pageable pageable);
}
