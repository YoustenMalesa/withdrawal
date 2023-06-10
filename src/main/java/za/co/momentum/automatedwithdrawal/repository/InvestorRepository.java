package za.co.momentum.automatedwithdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.momentum.automatedwithdrawal.model.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
