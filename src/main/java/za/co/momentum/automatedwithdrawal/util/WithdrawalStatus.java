package za.co.momentum.automatedwithdrawal.util;

public enum WithdrawalStatus {
    STARTED("STARTED"),
    EXECUTING("EXECUTING"),
    DONE("DONE");

    private String status;

    WithdrawalStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
