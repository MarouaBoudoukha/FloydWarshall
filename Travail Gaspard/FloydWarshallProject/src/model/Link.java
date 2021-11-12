package model;

/**
 * Representation of the link value between two summits in a graph
 */
public class Link {
    private int value;
    private boolean hasLink;

    public Link(int value) {
        this.value = value;
        this.hasLink = true;
    }

    public Link(){
        this.hasLink = false;
    }

    public void setValue(int value) {
           this.value = value;
        this.hasLink = true;
    }

    public void deleteLink(){
        this.value = 0;
        this.hasLink = false;
    }

    public boolean exists() {
        return hasLink;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (!hasLink) return "-";
        return String.valueOf(value);
    }
}
