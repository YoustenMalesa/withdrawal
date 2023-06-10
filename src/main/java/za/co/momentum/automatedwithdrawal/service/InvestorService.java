package za.co.momentum.automatedwithdrawal.service;

import za.co.momentum.automatedwithdrawal.model.Investor;

public interface InvestorService {
    Investor findById(Long id);
    void updateInvestor(Investor investor);
}
