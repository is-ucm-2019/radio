package radio.core;

import radio.transfer.BankingInfoTransfer;

import java.io.Serializable;
import java.time.LocalDate;

public class BankingInfo implements Serializable {
    private String holder;
    private String number;
    private int cvv;
    private LocalDate expireDate;

    public BankingInfo(BankingInfoTransfer tr) {
        this.holder = tr.holder;
        this.number = tr.number;
        this.cvv = tr.cvv;
        this.expireDate = tr.expireDate;
    }

    public String getHolder() {
        return holder;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }
}
