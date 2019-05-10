package radio.ui;


import radio.transfer.BroadcastTransfer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// Cell renderer for the calendar view.
// Depending on the container, it will return a new rendering (broadcasts or events)
class CalendarCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel component = new JLabel();
        component.setOpaque(true);
        if (value == null) {
            return component;
        }

        if (value instanceof BroadcastTransfer) {
            Color c = ((BroadcastTransfer) value).parent.color;
            component.setBackground(c == null ? table.getBackground() : c);
        } else {
            component.setBackground(table.getBackground());
        }

        component.setText(value.toString());

        return component;

    }
}
