import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Displays an calendar with meetings.
 */
public class CalendarPanel extends JPanel implements Observer {

    private static final int NUM_OF_ROWS = 6;
    private static final int NUM_OF_COLUMNS = 7;

    // Holds a mapping between a date and its meetings
    private Map<Date, String> datesMap;

    private ChooseDatePanel chooseDatePanel;
    private MeetingsEditor meetingsEditor;
    private LayoutManager mainLayout;
    private JPanel gridPanel;

    private Calendar calendar;
    private CalendarItemPanel[] calendarItemPanels;
    private int currentMonth;
    private int currentYear;

    /**
     * Constructor.
     *
     * @param chooseDatePanel panel for choosing dates
     * @param meetingsEditor  panel for editing meetings
     */
    public CalendarPanel(ChooseDatePanel chooseDatePanel, MeetingsEditor meetingsEditor) {
        this.chooseDatePanel = chooseDatePanel;
        this.meetingsEditor = meetingsEditor;

        this.calendar = Calendar.getInstance();
        this.datesMap = new HashMap<>();
        this.calendarItemPanels = new CalendarItemPanel[NUM_OF_ROWS * NUM_OF_COLUMNS];

        this.mainLayout = new BorderLayout();
        setLayout(this.mainLayout);
        initializeGrid();
        this.add(this.gridPanel, BorderLayout.CENTER);
        displayCalendarData();
    }

    @Override
    public void update(Object updateInfo) {
        if (updateInfo instanceof String[]) {
            String[] meetingInfo = (String[]) updateInfo;
            this.calendar.set(this.currentYear, this.currentMonth, Integer.parseInt(meetingInfo[0]), 0, 0);
            this.saveMeeting(this.calendar.getTime(), meetingInfo[1]);
        } else {
            displayCalendarData();
            this.meetingsEditor.clear();
        }
    }

    /**
     * Saves a given meeting.
     * @param date meeting's date
     * @param meeting meeting content
     */
    public void saveMeeting(Date date, String meeting) {
        this.datesMap.put(date, meeting);

        for (CalendarItemPanel calendarItemPanel : this.calendarItemPanels) {
            if (date.equals(calendarItemPanel.getDate())) {
                calendarItemPanel.setMeeting(meeting);
            }
        }
    }

    /**
     * Initializes the calendar grid.
     */
    private void initializeGrid() {
        this.gridPanel = new JPanel();
        this.gridPanel.setLayout(new GridLayout(NUM_OF_ROWS, NUM_OF_COLUMNS));

        CalendarItemPanel calendarItem;

        for (int i = 0; i < NUM_OF_ROWS * NUM_OF_COLUMNS; i++) {
            calendarItem = new CalendarItemPanel();
            this.calendarItemPanels[i] = calendarItem;
            this.gridPanel.add(calendarItem);
            calendarItem.addObserver(this.meetingsEditor);
        }
    }

    /**
     * Displays data on the calendar.
     */
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
