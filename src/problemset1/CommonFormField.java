package problemset1;

public enum CommonFormField {

    /** The BO Type. */
    YOYO("YAYA"),
    /** The BO Status. */
    TOTO("TATA");

    /** The id. */
    private final String id;


    CommonFormField(String id) {
        this.id = id;
    }


    public String id() {
        return id;
    }


    public String filter() {
        return String.format("%s.filter", id);
    }


    public String sort() {
        return String.format("%s.sort", id);
    }

}