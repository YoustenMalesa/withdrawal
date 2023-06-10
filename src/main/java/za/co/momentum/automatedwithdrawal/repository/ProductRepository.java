package za.co.momentum.automatedwithdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.momentum.automatedwithdrawal.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
