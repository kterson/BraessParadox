import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Launcher {
    public static void main(String[] args) throws InterruptedException {
        int highwayTime = 25;
        int localRoadTime = 10;
        int highwayLimit = 100;
        int localRoadLimit = 40;

        List<Road> roads = new ArrayList<>();
        roads.add(new Road('A', 'X', highwayTime, highwayLimit));
        roads.add(new Road('A', 'Y', localRoadTime, localRoadLimit));
        roads.add(new Road('Y', 'B', highwayTime, highwayLimit));
        roads.add(new Road('X', 'B', localRoadTime, localRoadLimit));

        roads.add(new Road('Y', 'X', highwayTime, highwayLimit));


        RoadRepo roadRepo = new RoadRepo(roads);

        List<Thread> cars = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            cars.add(new Thread(new Car(roadRepo, i + 1)));
        }

        long start = new Date().getTime();
        for (Thread car : cars) {
            car.start();
            //Thread.sleep(1);
        }
        for (Thread car : cars) {
            car.join();
        }
        long end = new Date().getTime();

        System.out.println();
        System.out.println(end - start);
        System.out.println();
        for(Road r : roadRepo.getRoads()){
            System.out.println(r);
        }

    }
}
