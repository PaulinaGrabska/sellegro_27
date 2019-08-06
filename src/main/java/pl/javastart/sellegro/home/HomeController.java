package pl.javastart.sellegro.home;

import org.springframework.beans.factory.annotation.Autowired;
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

        List<Auction> auctions = auctionRepository.findAll();

        model.addAttribute("cars", auctions);
        return "home";
    }
}
