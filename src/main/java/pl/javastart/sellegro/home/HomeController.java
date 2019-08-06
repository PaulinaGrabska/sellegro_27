package pl.javastart.sellegro.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.sellegro.auction.Auction;
import pl.javastart.sellegro.auction.AuctionRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AuctionRepository auctionRepository;

    public HomeController(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Auction> auctions = auctionRepository.getFirst4expensive();

        auctions.get(0).setTitle("Niesamowity " + auctions.get(0).getCarMake()+ " " + auctions.get(0).getCarModel());
        auctions.get(1).setTitle( "Jedyny taki " + auctions.get(1).getCarMake()+ " " + auctions.get(1).getCarModel());
        auctions.get(2).setTitle("HIT " +  auctions.get(2).getCarMake()+ " " + auctions.get(2).getCarModel());
        auctions.get(3).setTitle("Wyjatkowy "  + auctions.get(3).getCarMake()+ " " + auctions.get(3).getCarModel());


        model.addAttribute("cars", auctions);
        return "home";
    }
}
