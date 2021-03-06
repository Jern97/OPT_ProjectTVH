package TVH.Entities.Job;

import TVH.Entities.Machine.MachineType;
import TVH.Entities.Node.Edge;
import TVH.Entities.Node.Location;
import TVH.Entities.Machine.Machine;
import TVH.Entities.Node.Node;
import TVH.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//De klasse CollectJob bevat collect opdrachten die de trucks moeten uitvoeren.
//Statische klasse

public class CollectJob implements Job {
    private final Location collect;
    private final List<Location> drop;
    private final Machine machine;

    public CollectJob(Location collect, List<Location> drop, Machine machine) {
        this.collect = collect;
        this.drop = drop;
        this.machine = machine;
    }

    public boolean notDone(){ // nog niet uitgevoerd, (volledig)
        HashMap<Location,Node> nodesMap = Problem.getInstance().nodesMap; // daar zitten zowel client en depot in
        Node collectNode = nodesMap.get(collect);
        return collectNode.getAvailableMachines().contains(machine); // done als de machine niet meer in de node zit

    }

    public Location getFixedLocation(){
        return collect;
    }

    public Location getCollect() {
        return collect;
    }

    public List<Location> getDrop() {
        return drop;
    }

    public Machine getMachine() {
        return machine;
    }

    public int getRemoteFactor(){
        int distanceToAllNodes = 0;
        for(Edge e: collect.getEdgeMap().values()){
            distanceToAllNodes += e.getDistance();
        }
        return distanceToAllNodes;
    }
    public MachineType getMachineType(){
        return machine.getType();
    }

    public ArrayList<Move> generatePossibleMoves(){
        HashMap<Location, Node> nodesMap = Problem.getInstance().nodesMap;
        ArrayList<Move> moves = new ArrayList<>();
        //Eerst alle drop opties verwijderen waar we de machine niet meer kunnen zetten hebben
        for(Location l: drop){
            Node node = nodesMap.get(l);
            if(node.canPutMachineType(machine.getType())){
                moves.add(new Move(machine, collect, l));
            }
        }
        return moves;
    }

    public String toString(){
        return getFixedLocation().toString()+ " ("+getMachineType()+")";
    }
}
