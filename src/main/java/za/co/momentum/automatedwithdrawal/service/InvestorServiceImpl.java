package za.co.momentum.automatedwithdrawal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.momentum.automatedwithdrawal.model.Address;
import za.co.momentum.automatedwithdrawal.model.Contact;
import za.co.momentum.automatedwithdrawal.model.Investor;
import za.co.momentum.automatedwithdrawal.model.Product;
import za.co.momentum.automatedwithdrawal.repository.InvestorRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class InvestorServiceImpl implements InvestorService {
    private InvestorRepository investorRepository;

    @Autowired
    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    public Investor findById(Long id) {
        return investorRepository.findById(id).orElse(null);
    }

    @Override
    public void updateInvestor(Investor investor) {
        investorRepository.saveAndFlush(investor);
    }
}
