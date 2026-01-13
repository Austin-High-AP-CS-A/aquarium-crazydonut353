import java.util.*;

public class Aquarium{
    // The list of all tanks in the aquarium; guaranteed never to be null
    private ArrayList<Tank> tanks;

    public Aquarium(){
        this.tanks = new ArrayList<Tank>();
    }


    public ArrayList<Tank> getTanks() {return tanks;}

    /**
     * Returns a tank in this aquarium with a temperature fishy can tolerate and
     * that does not contain a fish that is not compatible with fishy. Returns
     * null if there is no such tank in this aquarium.
     * Postcondition: The state of this aquarium is unchanged.
     * @param fishy the fish to be checked
     * @return a suitable tank for fishy or null if no such tank exists

     * I changes the function for index so it works well.
     */
    private int findTank(Fish fishy){
        for(int i = 0; i < tanks.size(); i++) {
            ArrayList<Fish> tfih = tanks.get(i).getFish();
            boolean b = true;
            for(int j = 0; j < tfih.size(); j++) {// runs too long but whatever
                if(!fishy.isCompatible(tfih.get(j))) {
                    b = false;
                }
            }
            if(b&&tanks.get(i).temp()>fishy.minTemp() && tanks.get(i).temp()<fishy.maxTemp()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds each fish in fishes to a suitable tank in this aquarium if such a
     * tank exists. Each fish should be added to at most 1 tank.
     * @param fishes the list of fish to add
     * @return a list of the fish in fishes that could not be added
     */
    public ArrayList<Fish> addFish(ArrayList<Fish> fishes){
        for(int i = 0; i < fishes.size(); i++) {
            int indx = findTank(fishes.get(i));
            if(indx!=-1) {
                tanks.get(indx).addFish(fishes.get(i));
                fishes.remove(i);
                i--;
            }
        }

        return fishes;
    }

    /**
     * Adds fishTank to this aquarium if a suitable position can be found. The
     * temperature of fishTank can be no more than 5 degrees different (lower or
     * higher) than each of any adjacent tanks.
     * Postcondition: the order of the other tanks in the aquarium relative to each other is not changed
     * @param fishTank the tank to add
     * @return true if fishTank was added, false otherwise
     */
    public boolean addTank(Tank fishTank){
        //adding at all sides (including end)
        if(tanks.size()>0) {
            for(int i = 0; i <= tanks.size(); i++) {
                if(i == 0) {// bad ahh rushed code
                    if(Math.abs(fishTank.temp()-tanks.get(i).temp()) <= 5) {
                        tanks.add(i, fishTank);
                        return true;
                    }
                }else if(i == tanks.size()) {
                    if(Math.abs(fishTank.temp()-tanks.get(i-1).temp()) <= 5) {
                        tanks.add(i, fishTank);
                        return true;
                    }
                }else if(Math.abs(fishTank.temp()-tanks.get(i-1).temp()) <= 5 && Math.abs(fishTank.temp()-tanks.get(i).temp()) <= 5) {
                        tanks.add(i, fishTank);
                        return true;
                }
            }
        } else {
            tanks.add(0, fishTank);
            return true;
        }

        return false;
    }
}
