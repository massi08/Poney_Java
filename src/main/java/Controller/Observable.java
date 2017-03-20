package Controller;

public interface Observable {
    public void addObserver(Observer obs);

    public void notifyObserver(int i);

    public void notifyObserver();
}