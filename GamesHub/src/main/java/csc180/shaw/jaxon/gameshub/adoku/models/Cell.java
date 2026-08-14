package csc180.shaw.jaxon.gameshub.adoku.models;

public class Cell {
    protected int value;
    protected boolean fixed;

    public Cell(int value, boolean fixed) {
        this.fixed = false;
        setValue(value);
        setFixed(fixed);
    }

    public Cell(){
        this(0, false);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (!fixed) {
            this.value = value;
        }
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isFixed() {
        return fixed;
    }
}
