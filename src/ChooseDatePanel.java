import javax.swing.*;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class ChooseDatePanel extends JPanel {

    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final int DISPLAYED_YEARS_RANGE = 80;

    private JComboBox<String> monthsComboBox;
    private JComboBox<String> yearsComboBox;

    public ChooseDatePanel() {
        setLayout(new FlowLayout());
        initializeFields();
    }

    private void initializeFields() {
        String[] months = new DateFormatSymbols(Locale.US).getMonths();
        String[] years = new String[DISPLAYED_YEARS_RANGE];

        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(i + CURRENT_YEAR);
        }

        monthsComboBox = new JComboBox<>(months);
        yearsComboBox = new JComboBox<>(years);
        JButton displayButton = new JButton("Display");

        monthsComboBox.setSelectedIndex(0);
        yearsComboBox.setSelectedIndex(0);
        // TODO implement listener
        //displayButton.addActionListener(());

        this.add(new JLabel("Choose Month:"));
        this.add(monthsComboBox);
        this.add(new JLabel("Choose Year:"));
        this.add(yearsComboBox);
        this.add(displayButton);
    }

    public int getCurrentMonth() {
        return this.monthsComboBox.getSelectedIndex();
    }

    public int getCurrentYear() {
        return this.yearsComboBox.getSelectedIndex() + CURRENT_YEAR;
    }
}
