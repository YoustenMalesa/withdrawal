package za.co.momentum.automatedwithdrawal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.momentum.automatedwithdrawal.dto.WithdrawalRequest;
import za.co.momentum.automatedwithdrawal.service.WithdrawalService;
import za.co.momentum.automatedwithdrawal.util.exception.InvalidWithdrawalException;

@RestController
@RequestMapping("/api/v1/withdrawal")
public class WithdrawalController {
    private WithdrawalService withdrawalService;

    @Autowired
    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestBody WithdrawalRequest request) throws InvalidWithdrawalException {
        withdrawalService.withDraw(request);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({InvalidWithdrawalException.class})
    public ResponseEntity handleWithdrawalErrors(InvalidWithdrawalException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
