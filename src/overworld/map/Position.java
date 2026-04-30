package overworld.map;

public class Position {
    private int row;
    private int column;
    public Position(int row, int column){
        this.column = column;
        this.row = row;
    }
    public int getColumn() {return this.column; }
    public int getRow() {return this.row; }

    public void set(int row, int column){
        this.row = row;
        this.column = column;
    }
    public boolean equals(Position other){
        return this.column == other.column && this.row == other.row;
    }
}
