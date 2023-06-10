package za.co.momentum.automatedwithdrawal.service;

import za.co.momentum.automatedwithdrawal.dto.WithdrawalRequest;
import za.co.momentum.automatedwithdrawal.util.exception.InvalidWithdrawalException;

public interface WithdrawalService {
    void withDraw(WithdrawalRequest request) throws InvalidWithdrawalException;
}
