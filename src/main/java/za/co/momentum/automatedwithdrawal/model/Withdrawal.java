package za.co.momentum.automatedwithdrawal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WITHDRAWAL")
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "investor_id")
    private Long investorId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreatedDate
    private LocalDate dateCreated;

    @Column(name = "date_updated")
    @LastModifiedDate
    private LocalDate dateUpdated;
}
