package ch.mazluc;

import java.util.Iterator;

public class Intestation {

    Tuple columns;

    public Intestation(String... columnTitles) {
        this.columns = new Tuple();
        for (String columnTitle : columnTitles) {
            if (columnTitle.isEmpty()) { continue; }
            this.columns.push(columnTitle);
        }
    }

    public int length() {
        return this.columns.length();
    }

    public void push(String... columnTitles) throws IllegalArgumentException {
        for (String columnTitle : columnTitles) {
            if (this.columns.contains(columnTitle)) { continue; } // check if column already exists
            this.columns.push(columnTitle);
        }
    }

}
