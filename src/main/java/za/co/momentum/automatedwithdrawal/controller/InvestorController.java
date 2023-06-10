package za.co.momentum.automatedwithdrawal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.momentum.automatedwithdrawal.model.Investor;
import za.co.momentum.automatedwithdrawal.service.InvestorService;

@RestController
@RequestMapping("/api/v1/investor")
public class InvestorController {
    private InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findInvestor(@PathVariable("id") Long id) {
        Investor investor = investorService.findById(id);
        if(investor == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(investor);
    }
}
