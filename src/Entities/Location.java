package Entities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Location {
    private int locationID;
    private double latitude;
    private double longitude;
    private String name;
    private HashMap<Location, Edge> edgeMap;
    private LinkedList<Edge> sortedEdgeList;

    public Location(int locationID,String name ,double latitude, double longitude) {
        this.locationID = locationID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.edgeMap = new HashMap<>();
        this.sortedEdgeList = new LinkedList<>();
    }


    public void addEdge(Edge edge){
        edgeMap.put(edge.getTo(), edge);
        //Entities.Edge list moet altijd gesorteerd blijven op basis van afstand
        sortedEdgeList.add(edge);
        sortedEdgeList.sort(Comparator.comparing(Edge::getDistance));
    }

    public int distanceTo(Location l){
        Edge edge= edgeMap.get(l);
        return edge.getDistance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return locationID == location.locationID;
    }

    public int timeTo(Location l){
        return edgeMap.get(l).getTime();
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Location, Edge> getEdgeMap() {
        return edgeMap;
    }

    public void setEdgeMap(HashMap<Location, Edge> edgeMap) {
        this.edgeMap = edgeMap;
    }

    public LinkedList<Edge> getSortedEdgeList() {
        return sortedEdgeList;
    }

    public void setSortedEdgeList(LinkedList<Edge> sortedEdgeList) {
        this.sortedEdgeList = sortedEdgeList;
    }

    @Override
    public int hashCode() {
        return locationID;
    }
}
