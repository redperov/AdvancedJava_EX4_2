import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CalendarPanel extends JPanel {

    // Day of week name labels
    private static final String[] DAYS_OF_WEEK = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static final int DAYS_OF_WEEK_X_COORDINATE = 30;
    private static final int DAYS_OF_WEEK_Y_COORDINATE = 30;
    private static final int DAYS_OF_WEEK_LABEL_WIDTH = 100;
    private static final int DAYS_OF_WEEK_LABEL_HEIGHT = 50;
    //private static final int DAYS_OF_WEEK_LABEL_PADDING = 30;
    private static final int DAYS_OF_WEEK_LABEL_FONT_SIZE = 18;

    private static final int NUM_OF_ROWS = 6;
    private static final int NUM_OF_COLUMNS = 7;

    private ChooseDatePanel chooseDatePanel;
    private LayoutManager mainLayout;
    private JPanel daysOfWeekPanel;
    private JPanel gridPanel;
    private Calendar calendar;

    public CalendarPanel(ChooseDatePanel chooseDatePanel) {
        this.chooseDatePanel = chooseDatePanel;
        this.calendar = Calendar.getInstance();

        this.mainLayout = new BorderLayout();
        setLayout(this.mainLayout);
        this.setSize(new Dimension(500, 500));
        initializeDaysOfWeek();
        initializeGrid();
        this.add(this.daysOfWeekPanel, BorderLayout.NORTH);
        this.add(this.gridPanel, BorderLayout.CENTER);
    }

    private void initializeDaysOfWeek() {
        this.daysOfWeekPanel = new JPanel();
        this.daysOfWeekPanel.setLayout(new FlowLayout());

        Font font = new Font("Serif", Font.PLAIN, DAYS_OF_WEEK_LABEL_FONT_SIZE);
        JLabel dayNameLabel;

        for (int dayIndex = 0; dayIndex < DAYS_OF_WEEK.length; dayIndex++) {
            dayNameLabel = new JLabel(DAYS_OF_WEEK[dayIndex]);
            dayNameLabel.setFont(font);
            dayNameLabel.setBounds( DAYS_OF_WEEK_X_COORDINATE, DAYS_OF_WEEK_Y_COORDINATE,
                    DAYS_OF_WEEK_LABEL_WIDTH, DAYS_OF_WEEK_LABEL_HEIGHT);
            this.daysOfWeekPanel.add(dayNameLabel);
        }
    }

    private void initializeGrid() {
        this.gridPanel = new JPanel();
        this.gridPanel.setLayout(new GridLayout(NUM_OF_ROWS, NUM_OF_COLUMNS));
        this.gridPanel.setSize(new Dimension(100, 100));

        CalendarItemPanel calendarItem;

        for (int i = 0; i < NUM_OF_ROWS * NUM_OF_COLUMNS; i++) {
            calendarItem = new CalendarItemPanel();
            calendarItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            this.gridPanel.add(calendarItem);
        }
    }

    private void displayCalendarData() {

    }
}
