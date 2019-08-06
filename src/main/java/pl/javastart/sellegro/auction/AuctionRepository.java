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
    List<Auction> findAllByOrderByColor();
    List<Auction> findAllByOrderByEndDate();
    List<Auction> findAllByCarMakeContaining(String carMake);
    List<Auction> findAllByCarModelContaining(String carModel);
    List<Auction> findAllByColorContaining(String color);
    List<Auction> findAllByTitleContaining(String title);


    @Query(value= "SELECT * from auction a order by a.car_Make, a.car_Model ", nativeQuery= true)
    List<Auction> findAllByOrderByTitle();


    @Query(value= "SELECT * from auction a order by a.price DESC LIMIT 0,4", nativeQuery= true)
    List<Auction> getFirst4expensive();
}
