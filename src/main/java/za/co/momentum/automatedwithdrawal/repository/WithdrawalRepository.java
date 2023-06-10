package za.co.momentum.automatedwithdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.momentum.automatedwithdrawal.model.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    Withdrawal findFirstByStatus(String status);
}
