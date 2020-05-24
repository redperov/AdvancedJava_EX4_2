import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CalendarPanel extends JPanel implements Observer {

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
    private Map<Date, String> datesMap;
    private CalendarItemPanel[] calendarItemPanels;
    private int currentMonth;
    private int currentYear;

    public CalendarPanel(ChooseDatePanel chooseDatePanel) {
        this.chooseDatePanel = chooseDatePanel;

        this.calendar = Calendar.getInstance();
        this.datesMap = new HashMap<>();
        this.calendarItemPanels = new CalendarItemPanel[NUM_OF_ROWS * NUM_OF_COLUMNS];

        this.mainLayout = new BorderLayout();
        setLayout(this.mainLayout);
        this.setSize(new Dimension(500, 500));
        initializeDaysOfWeek();
        initializeGrid();
        this.add(this.daysOfWeekPanel, BorderLayout.NORTH);
        this.add(this.gridPanel, BorderLayout.CENTER);
        displayCalendarData();
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
            this.calendarItemPanels[i]= calendarItem;
            this.gridPanel.add(calendarItem);
        }
    }

    private void displayCalendarData() {
        this.currentMonth = this.chooseDatePanel.getCurrentMonth();
        this.currentYear = this.chooseDatePanel.getCurrentYear();

        this.calendar.set(this.currentYear, this.currentMonth, 1);
        int firstDayOfMonth = this.calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int numOfDaysInMonth = this.calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < NUM_OF_ROWS * NUM_OF_COLUMNS; i++) {
            this.calendarItemPanels[i].clear();
        }

        for (int i = firstDayOfMonth; i < firstDayOfMonth + numOfDaysInMonth; i++) {
           this.calendarItemPanels[i].setDayNumber(String.valueOf(i - firstDayOfMonth + 1));
        }
    }

    @Override
    public void update() {
        displayCalendarData();
    }
}
