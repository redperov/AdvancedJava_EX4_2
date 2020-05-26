import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private MeetingsEditor meetingsEditor;
    private LayoutManager mainLayout;
    private JPanel daysOfWeekPanel;
    private JPanel gridPanel;

    private Calendar calendar;
    private Map<Date, String> datesMap;
    private CalendarItemPanel[] calendarItemPanels;
    private int currentMonth;
    private int currentYear;

    public CalendarPanel(ChooseDatePanel chooseDatePanel, MeetingsEditor meetingsEditor) {
        this.chooseDatePanel = chooseDatePanel;
        this.meetingsEditor = meetingsEditor;

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

    @Override
    public void update(Object updateInfo) {
        if (updateInfo instanceof String[]) {
            String[] meetingInfo = (String[]) updateInfo;
            this.calendar.set(this.currentYear, this.currentMonth, Integer.valueOf(meetingInfo[0]), 0, 0);
            this.saveMeeting(this.calendar.getTime(), meetingInfo[1]);
        }
        else {
        displayCalendarData();
        this.meetingsEditor.clear();
        }
    }

    public void saveMeeting(Date date, String meeting) {
        this.datesMap.put(date, meeting);

        for (CalendarItemPanel calendarItemPanel : this.calendarItemPanels) {
            if (date.equals(calendarItemPanel.getDate())) {
                calendarItemPanel.setMeeting(meeting);
            }
        }
    }

    private void initializeDaysOfWeek() {
        this.daysOfWeekPanel = new JPanel();
        this.daysOfWeekPanel.setLayout(new FlowLayout());

        Font font = new Font("Serif", Font.PLAIN, DAYS_OF_WEEK_LABEL_FONT_SIZE);
        JLabel dayNameLabel;

        for (int dayIndex = 0; dayIndex < DAYS_OF_WEEK.length; dayIndex++) {
            dayNameLabel = new JLabel(DAYS_OF_WEEK[dayIndex]);
            dayNameLabel.setFont(font);
            dayNameLabel.setBounds(DAYS_OF_WEEK_X_COORDINATE, DAYS_OF_WEEK_Y_COORDINATE,
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
            this.calendarItemPanels[i] = calendarItem;
            this.gridPanel.add(calendarItem);
            calendarItem.addObserver(this.meetingsEditor);
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

        Date currentDate;
        CalendarItemPanel currentCalendarItemPanel;

        for (int i = firstDayOfMonth; i < firstDayOfMonth + numOfDaysInMonth; i++) {
            currentCalendarItemPanel = this.calendarItemPanels[i];
            this.calendar.set(this.currentYear, this.currentMonth, i - firstDayOfMonth + 1, 0, 0);
            currentCalendarItemPanel.setDate(this.calendar.getTime());
            currentCalendarItemPanel.setDayNumber(String.valueOf(i - firstDayOfMonth + 1));
            currentDate = this.calendar.getTime();
            currentCalendarItemPanel.setMeeting(this.datesMap.get(currentDate));
            currentCalendarItemPanel.setActiveItem(true);
        }
    }
}
