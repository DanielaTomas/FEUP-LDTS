interface FillInterface {
    void clearAnomalies(int a, int b);
    void insideGrass();
    boolean insideGrass(int x, int y);
    boolean verifyWolves(int x, int y);
    boolean verifyWolvesRec(int x, int y);
    void boundaryFill(int x, int y);
    void boundaryFillRec(int x, int y);
    boolean verifyPosition(int x, int y);
}