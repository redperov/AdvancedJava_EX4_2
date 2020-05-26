import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ChooseDatePanel extends JPanel implements Subject {

    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final int DISPLAYED_YEARS_RANGE = 80;
    private List<Observer> displayObservers;

    private JComboBox<String> monthsComboBox;
    private JComboBox<String> yearsComboBox;

    public ChooseDatePanel() {
        setLayout(new FlowLayout());
        initializeFields();
        this.displayObservers = new ArrayList<>();
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

        displayButton.addActionListener(e -> {
            updateAll();
        });

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

    @Override
    public void addObserver(Observer observer) {
        this.displayObservers.add(observer);
    }

    @Override
    public void updateAll() {
        for (Observer observer : this.displayObservers) {
            observer.update(null);
        }
    }
}
