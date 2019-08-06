package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/auctions")
    public String auctions(Model model,
                           @RequestParam(required = false) String sort,
                           @RequestParam(required = false) String filterValue,
                           @RequestParam(required = false) String filter,
                           AuctionFilters auctionFilters) {
        List<Auction> auctions = null;
        List<Auction> filteredAuctions = null;

        for (Auction a:auctionRepository.findAll()) {
            a.setTitle(a.getCarMake()+" " + a.getCarModel());
        }


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


        if(filter != null) {

            if (filter.equals(auctionFilters.getTitle())) {

                filteredAuctions = auctionRepository.findAllByTitleContaining(filterValue);
            } else if (filter.equals(auctionFilters.getColor())) {

                filteredAuctions = auctionRepository.findAllByColorContaining(filterValue);
            } else if (filter.equals(auctionFilters.getCarMaker())) {

                filteredAuctions = auctionRepository.findAllByCarMakeContaining(filterValue);
            } else if (filter.equals(auctionFilters.getCarModel())) {

                filteredAuctions = auctionRepository.findAllByCarModelContaining(filterValue);
            }
        }
         else {
            filteredAuctions = auctionRepository.findAll();
        }



        model.addAttribute("cars", auctions);
        model.addAttribute("filters", auctionFilters);
        model.addAttribute("sort", sort );
        return "auctions";
    }

//    @PostMapping("/filters")
//    public String edit(@RequestParam(required = false) String toFilter) {
//
//        return "/auctions?filterValue="+toFilter;
//    }
}
