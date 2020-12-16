package za.co.tmasethe.model;

public abstract class Aircraft {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;

    public static final String BALLOON = "balloon";
    public static final String JET_PLANE = "jet plane";
    public static final String HELICOPTER = "helicopter";

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private static long nextId() {
        return ++Aircraft.idCounter;
    }

}
