public class Car implements Runnable {

    private char location = 'A';
    private final RoadRepo repo;
    private final int id;

    public Car(RoadRepo rr, int id) {
        repo = rr;
        this.id = id;
    }

    @Override
    public void run() {
        while (location != 'B') {
            Road road = repo.bestRoad(location);
            try {
                road.travel();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            location = road.getEnd();
        }
        //System.out.println(id);
    }

    @Override
    public String toString() {
        return "Car " + id;
    }
}
