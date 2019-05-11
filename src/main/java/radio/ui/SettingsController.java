package radio.ui;

import radio.transfer.BankingInfoTransfer;

import java.time.LocalDate;

public class SettingsController {

    private MainController controller;

    public SettingsController(MainController cont) {
        this.controller = cont;
    }

    public boolean checkBankingPermissions() {
        return this.controller.core.userHasBankingPermissions();
    }

    public void getContactInfo() {
        this.controller.core.getContactInfo();
    }

    public void getBankingInfo() {
        this.controller.core.getBankingInfo();
    }

    public void setBankingInfo(String name, String cardNumber, Integer cvv, LocalDate expiry) {
        BankingInfoTransfer tr = new BankingInfoTransfer(name, cardNumber, cvv, expiry);
        this.controller.core.setBankingInfo(tr);
    }
}
