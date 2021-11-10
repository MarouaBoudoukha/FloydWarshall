public class Link {
    private int value;
    private boolean hasLink;

    public Link(int value){
        this.value=value;
        this.hasLink=true;
    }
    public Link(){
        this.hasLink=false;
    }

    public int getValue() {
        return value;
    }

    public boolean exists() {
        return hasLink;
    }

    public void setValue(int value) {
        this.value = value;
        this.hasLink=true;
    }
    @Override
    public String toString(){
        if (hasLink==true){
            return String.valueOf(value);
        }
        else
            return "-";
    }
}
