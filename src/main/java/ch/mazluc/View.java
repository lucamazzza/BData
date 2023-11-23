package ch.mazluc;

public interface View {

    void print();

    void printf(char colDelimiter );

    void printf(boolean uppercaseTitle);

    void printf(boolean uppercaseTitle, boolean totalRow);

    void printf(char colDelimiter, boolean uppercaseTitle);

    void printf(char colDelimiter, boolean uppercaseTitle, boolean totalRow);
}
