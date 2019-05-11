package radio.transfer;

import radio.core.BankingInfo;

import java.time.LocalDate;

public class BankingInfoTransfer {
    public final String holder;
    public final String number;
    public final int cvv;
    public final LocalDate expireDate;

    public BankingInfoTransfer(String holder, String number, int cvv, LocalDate expireDate) {
        this.holder = holder;
        this.number = number;
        this.cvv = cvv;
        this.expireDate = expireDate;
    }

    public BankingInfoTransfer(BankingInfo info) {
        this.holder = info.getHolder();
        this.number = info.getNumber();
        this.cvv = info.getCvv();
        this.expireDate = info.getExpireDate();
    }
}
