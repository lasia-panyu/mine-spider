package nue.pan.spider.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CusRepository extends PagingAndSortingRepository<Cus, String> {
    Page<Cus> findByCusNameLikeAndCusCounterLike(String cusName,String cusCounter, Pageable pageable);
    Cus findByCusNo(String cusNo);
    Page<Cus> findByCusNameAndCusCounter(String name,String cusCounter,Pageable pageable);
    @Query(value = "update Cus set cusName=?1,cusAddress=?2,cusPhone=?3 where cusNo=?4 ", nativeQuery = false)
    @Modifying
    @Transactional
    public void updateOne(String cusName,String cusAddress,String cusPhone, String cusNo); ;
}
