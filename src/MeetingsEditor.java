import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingsEditor extends JPanel implements Observer, Subject {

    private JTextArea editMeetingArea;
    private JButton saveButton;
    private String dayToPass;

    private List<Observer> saveButtonObservers;

    public MeetingsEditor() {
        setLayout(new FlowLayout());

        initializeFields();
    }

    private void initializeFields() {
        this.saveButtonObservers = new ArrayList<>();
        this.editMeetingArea = new JTextArea(12, 50);
        this.editMeetingArea.setEditable(false);

        this.saveButton = new JButton("save");
        this.saveButton.setEnabled(false);

        this.saveButton.addActionListener(e -> updateAll());

        // Add components
        JScrollPane scrollPane = new JScrollPane(this.editMeetingArea);
        this.add(scrollPane);
        this.add(this.saveButton);
    }

    @Override
    public void update(Object updateInfo) {
        if (!(updateInfo instanceof CalendarItemPanel)) {
            return;
        }
        CalendarItemPanel calendarItemPanel = (CalendarItemPanel) updateInfo;
        this.editMeetingArea.setText("");

        if (!calendarItemPanel.getActiveItem()) {
            this.editMeetingArea.setEditable(false);
            this.saveButton.setEnabled(false);
            return;
        }

        this.dayToPass = calendarItemPanel.getDayNumber();
        this.editMeetingArea.setEditable(true);
        this.editMeetingArea.setCaretPosition(0);
        this.editMeetingArea.setText(calendarItemPanel.getMeeting());
        this.saveButton.setEnabled(true);
    }

    public void clear() {
        this.editMeetingArea.setText("");
        this.editMeetingArea.setEditable(false);
        this.saveButton.setEnabled(false);
    }

    @Override
    public void addObserver(Observer observer) {
        this.saveButtonObservers.add(observer);
    }

    @Override
    public void updateAll() {
        for(Observer observer : this.saveButtonObservers) {
            observer.update(new String[] {this.dayToPass, this.editMeetingArea.getText()});
        }
    }
}
