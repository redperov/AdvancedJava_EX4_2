import javax.swing.*;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A panel that allows the user to choose the month that will be displayed on the calendar.
 */
public class ChooseDatePanel extends JPanel implements Subject {

    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final int DISPLAYED_YEARS_RANGE = 80;
    private List<Observer> displayObservers;
    private JComboBox<String> monthsComboBox;
    private JComboBox<String> yearsComboBox;
    private JLabel displayedDateLabel;

    /**
     * Constructor.
     */
    public ChooseDatePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initializeFields();
        this.displayObservers = new ArrayList<>();
    }

    /**
     * Initializes the layout components.
     */
    private void initializeFields() {
        String[] months = new DateFormatSymbols(Locale.US).getMonths();
        String[] years = new String[DISPLAYED_YEARS_RANGE];

        // Fill the array with the desired years range
        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(i + CURRENT_YEAR);
        }

        monthsComboBox = new JComboBox<>(months);
        yearsComboBox = new JComboBox<>(years);
        displayedDateLabel = new JLabel();
        JButton displayButton = new JButton("Display");

        monthsComboBox.setSelectedIndex(0);
        yearsComboBox.setSelectedIndex(0);
        displayedDateLabel.setFont(new Font("Serif", Font.BOLD, 20));
        displayedDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayedDateLabel.setText(String.format("Meetings for %s %s",
                monthsComboBox.getSelectedItem().toString(), yearsComboBox.getSelectedItem().toString()));

        displayButton.addActionListener(e -> {
            displayedDateLabel.setText(String.format("Meetings for %s %s",
                    monthsComboBox.getSelectedItem().toString(), yearsComboBox.getSelectedItem().toString()));
            updateAll();
        });

        // Add the components
        this.add(displayedDateLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        optionsPanel.add(new JLabel("Choose Month:"));
        optionsPanel.add(monthsComboBox);
        optionsPanel.add(new JLabel("Choose Year:"));
        optionsPanel.add(yearsComboBox);
        optionsPanel.add(displayButton);
        this.add(optionsPanel);
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
