package radio.dao;

import radio.core.BankingInfo;
import radio.core.Database;
import radio.transfer.BankingInfoTransfer;

import java.util.Optional;

public class BankingDAO {
    private Database database;

    public BankingDAO(Database db) {
        this.database = db;
    }

    public Optional<BankingInfoTransfer> getInfo() {
        return Optional.ofNullable(database.getBankingInfo()).map(BankingInfoTransfer::new);
    }

    public void persistInfo(BankingInfoTransfer tr) {
        database.setBankingInfo(new BankingInfo(tr));
    }

    public void deleteInfo() {
        database.setBankingInfo(null);
    }
}
