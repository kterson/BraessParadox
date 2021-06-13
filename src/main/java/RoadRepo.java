import java.util.ArrayList;
import java.util.List;

public class RoadRepo {

    private final List<Road> roads;

    public RoadRepo(List<Road> r) {
        roads = r;
    }

    public Road bestRoad(char loc) {

        ArrayList<Road> compatibleRoads = new ArrayList<>();
        for (Road r : roads) {
            if (r.getStart() == loc) {
                compatibleRoads.add(r);
            }
        }
        Road ret = compatibleRoads.get(0);
        for (Road r : compatibleRoads) {
            //System.out.println(r.getAvgTime());
            if (r.getAvgTime() < ret.getAvgTime()) {
                ret = r;
            }
        }
        return ret;
    }

    public List<Road> getRoads(){
        return roads;
    }
}
