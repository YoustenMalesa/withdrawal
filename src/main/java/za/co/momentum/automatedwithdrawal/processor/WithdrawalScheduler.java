package za.co.momentum.automatedwithdrawal.processor;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.momentum.automatedwithdrawal.model.Investor;
import za.co.momentum.automatedwithdrawal.model.Product;
import za.co.momentum.automatedwithdrawal.model.Withdrawal;
import za.co.momentum.automatedwithdrawal.service.InvestorService;
import za.co.momentum.automatedwithdrawal.service.WithdrawalService;
import za.co.momentum.automatedwithdrawal.util.WithdrawalStatus;

import java.time.LocalDate;

@Component
@Log
public class WithdrawalScheduler {
    private WithdrawalService withdrawalService;
    private InvestorService investorService;

    @Autowired
    public WithdrawalScheduler(WithdrawalService withdrawalService, InvestorService investorService) {
        this.withdrawalService = withdrawalService;
        this.investorService = investorService;
    }


    @Scheduled(fixedRate = 60000, initialDelay = 200)
    public void processWithdrawal() {
        synchronized (this) {
            Withdrawal withdrawal = withdrawalService.findByStatus(WithdrawalStatus.STARTED.getStatus());
            if(withdrawal != null) {
                withdrawal.setStatus(WithdrawalStatus.EXECUTING.getStatus());
                withdrawal.setDateUpdated(LocalDate.now());
                withdrawalService.updateWithdrawal(withdrawal);

                log.info("WithdrawalScheduler found withdrawal pending processing. PROCESSING");
                Investor investor = investorService.findById(withdrawal.getInvestorId());
                if(investor != null) {
                    log.info("WithdrawalScheduler found investor, processing withdrawal");
                    Product product = null;
                    for(Product p : investor.getProducts()) {
                        if(p.getId() == withdrawal.getProductId()) {
                            product = p;
                            break;
                        }
                    }

                    if(product != null ) {
                        log.info("WithdrawalScheduler found product, processing withdrawal");
                        //update product's current balance
                        product.setCurrentBalance(product.getCurrentBalance() - withdrawal.getAmount());
                        withdrawal.setStatus(WithdrawalStatus.DONE.getStatus());

                        //update db
                        log.info("WithdrawalScheduler done. Updating records");
                        investorService.updateInvestor(investor);
                        withdrawalService.updateWithdrawal(withdrawal);
                    }
                }
            }
        }
    }
}
