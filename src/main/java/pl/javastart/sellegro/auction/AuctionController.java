package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuctionController {

//    private AuctionService auctionService;

//    public AuctionController(AuctionService auctionService) {
//        this.auctionService = auctionService;
//    }

    @Autowired
    AuctionRepository auctionRepository;

    public AuctionController(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public AuctionRepository getAuctionRepository() {
        return auctionRepository;
    }

    @GetMapping("/auctions")
    public String auctions(Model model,
                           @RequestParam(required = false) String sort,
                           AuctionFilters auctionFilters) {
        List<Auction> auctions = null;


        if(sort != null) {

            switch (sort) {
                case "title":
                    auctions = auctionRepository.findAllByOrderByTitle();
                    break;
                case "color":
                    auctions = auctionRepository.findAllByOrderByColor();
                    break;
                case "price":
                    auctions = auctionRepository.findAllByOrderByPrice();
                    break;
                case "endDate":
                    auctions = auctionRepository.findAllByOrderByEndDate();
                    break;
            }

        } else {
            auctions = auctionRepository.findAll();
        }

        model.addAttribute("cars", auctions);
        model.addAttribute("filters", auctionFilters );
        model.addAttribute("sort", sort );
        return "auctions";
    }



}
