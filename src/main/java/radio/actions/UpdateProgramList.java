package radio.actions;

import radio.transfer.ProgramTransfer;

import java.util.List;

public final class UpdateProgramList {
    public final List<ProgramTransfer> list;

    public UpdateProgramList(List<ProgramTransfer> l) {
        this.list = l;
    }
}
