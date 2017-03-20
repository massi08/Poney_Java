package Controller;


public interface Observer {
    public void update(int i);

    public void updatePoney(int i, double x, double y);

    public void updateObstacle(int i, double x, double y);

    public void updatePoney(boolean bool, int i, String str, double x, double y);

}