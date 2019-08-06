package pl.javastart.sellegro.auction;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository <Auction, Long> {


//    @Query("SELECT a from Auction a " +
//            "ORDER BY a.%:sorter DESC")
//    List<Auction> sortUserByLastNameDescJPQL(@Param("sorter") String sort);

    List<Auction> findAllByOrderByPrice();
    List<Auction> findAllByOrderByCarModel();
    List<Auction> findAllByOrderByCarMake();
    List<Auction> findAllByOrderByColor();
    List<Auction> findAllByOrderByEndDate();
    List<Auction> findAllByOrderByTitle();
    List<Auction> findAll();

}
