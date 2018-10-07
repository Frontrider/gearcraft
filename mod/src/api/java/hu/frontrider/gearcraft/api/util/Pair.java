package hu.frontrider.gearcraft.api.util;

public class Pair<I,K> {

    private final I first;
    private final K second;

    public Pair(I first, K second){

        this.first = first;
        this.second = second;
    }

    public I getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }
}
