package ObserverPattern;

public class SimpleObserver implements Observer {
    private int value;
    private Subject subject;

    public SimpleObserver(Subject subject) {
        subscribe(subject);
    }

    public void subscribe(Subject newSubject) {
        if (this.subject != null) {
            this.subject.removeObserver(this);
        }
        this.subject = newSubject;
        this.subject.registerObserver(this);
    }

    public void unsubscribe() {
        if (subject != null) {
            subject.removeObserver(this);
            subject = null;
        }
    }

    @Override
    public void update(int value) {
        this.value = value;
        display();
    }

    public void display() {
        System.out.println("Value: " + value);
    }
}
