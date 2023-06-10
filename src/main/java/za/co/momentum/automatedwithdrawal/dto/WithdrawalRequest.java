package za.co.momentum.automatedwithdrawal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequest {
    private Long productId;
    private Long investorId;
    private Double amount;
}
