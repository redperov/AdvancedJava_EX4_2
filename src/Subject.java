/**
 * Represents a subject which notifies its observers.
 */
public interface Subject {
    void addObserver(Observer observer);
    void updateAll();
}
