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
        //mock();
        return investorRepository.findById(id).orElse(null);
    }

    @Override
    public void updateInvestor(Investor investor) {
        investorRepository.saveAndFlush(investor);
    }

    void mock() {
        Investor investor = new Investor();
        investor.setFirstName("Yousten");
        investor.setLastName("Malesa");
        investor.setDateOfBirth(LocalDate.of(1992, 8, 7));

        Address address = new Address();
        address.setCity("Midrand");
        address.setSuburb("Clayville");
        address.setStreetDetails("4093 Krypton street");

        Contact contact = new Contact();
        contact.setEmail("youstenmalesa@gmail.com");
        contact.setMobileNumber("0840351356");

        investor.setAddress(address);
        investor.setContactDetails(contact);


        Product p1 = new Product();
        p1.setName("Insurance Policy");
        p1.setType("RETIREMENT");
        p1.setCurrentBalance(500000D);

        Product p2 = new Product();
        p2.setName("Investment plus");
        p2.setType("SAVINGS");
        p2.setCurrentBalance(250000D);

        Set<Product> products = new HashSet<>();
        products.add(p1);
        products.add(p2);

        investor.setProducts(products);

        investorRepository.save(investor);
    }
}
