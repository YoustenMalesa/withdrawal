package za.co.momentum.automatedwithdrawal.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.momentum.automatedwithdrawal.dto.WithdrawalRequest;
import za.co.momentum.automatedwithdrawal.model.Investor;
import za.co.momentum.automatedwithdrawal.model.Product;
import za.co.momentum.automatedwithdrawal.model.Withdrawal;
import za.co.momentum.automatedwithdrawal.repository.WithdrawalRepository;
import za.co.momentum.automatedwithdrawal.util.WithdrawalStatus;
import za.co.momentum.automatedwithdrawal.util.exception.InvalidWithdrawalException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Log
public class WithdrawalServiceImpl implements WithdrawalService {
    private WithdrawalRepository withdrawalRepository;
    private InvestorService investorService;

    @Autowired
    public WithdrawalServiceImpl(WithdrawalRepository withdrawalRepository, InvestorService investorService) {
        this.withdrawalRepository = withdrawalRepository;
        this.investorService = investorService;
    }

    @Override
    public void withDraw(WithdrawalRequest request) throws InvalidWithdrawalException {
        Investor investor = investorService.findById(request.getInvestorId());
        if(investor == null) {
            throw new InvalidWithdrawalException(String.format("Investor with id %s does not exist", request.getInvestorId()));
        }

        Product product = investor.getProducts().stream()
                .filter(p -> p.getId() == request.getProductId()).findFirst().orElse(null);

        if(product == null) {
            throw new InvalidWithdrawalException(String.format("Product with id %s does not exist for the investor", request.getProductId()));
        }

        if(request.getAmount() > product.getCurrentBalance()) {
            throw new InvalidWithdrawalException("Withdrawal amount exceeds the current balance.");
        }

        double maxWithdrawalAmount = 0.9 * product.getCurrentBalance();
        if(request.getAmount() > maxWithdrawalAmount) {
            throw new InvalidWithdrawalException("Withdrawal amount cannot exceed 90% of the current balance.");
        }

        if (product.getType().equals("RETIREMENT")) {
            int investorAge = LocalDateTime.now().getYear() - investor.getDateOfBirth().getYear();
            log.info("Investor Age:: " + investorAge);

            if(investorAge < 66) {
                throw new InvalidWithdrawalException("Investor must be older than 65 to make a withdrawal for a retirement product.");
            }
        }

        //save withdrawal request for processor to pick up and perform the actual withdrawal
        Withdrawal withdrawal = new Withdrawal();

        withdrawal.setStatus(WithdrawalStatus.STARTED.getStatus());
        withdrawal.setAmount(request.getAmount());
        withdrawal.setInvestorId(request.getInvestorId());
        withdrawal.setProductId(request.getProductId());
        withdrawal.setDateCreated(LocalDate.now());

        withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal findByStatus(String status) {
        return withdrawalRepository.findFirstByStatus(status);
    }

    @Override
    public void updateWithdrawal(Withdrawal withdrawal) {
        withdrawalRepository.saveAndFlush(withdrawal);
    }
}
