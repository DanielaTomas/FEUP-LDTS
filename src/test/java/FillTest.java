import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FillTest {

    public void setTypeH(Land land, int x1, int x2, int j, int type){
        for(int i = x1; i < x2; i++){
            land.setType(i,j,type);
        }
    }
    public void setTypeV(Land land, int y1, int y2, int i, int type){
        for(int j = y1; j < y2; j++){
            land.setType(i,j,type);
        }
    }
    public void setTypeRect(Land land, int x1, int x2, int y1, int y2, int type){
        setTypeH(land,x1,x2,y1,type);
        setTypeH(land,x1,x2,y2,type);
        setTypeV(land,y1,y2,x1,type);
        setTypeV(land,y1,y2,x2,type);
    }
    @Test
    public void testVerifyWolvesRec(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        wolves.add(new Wolf(10, 15));
        assertFalse(fill.verifyWolvesRec(10,15));
    }
    @Test
    public void testClearAnomalies(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        for(int i=0; i<60; i++){
            for(int j=0;j<30;j++){
                if((i==0 || i == 59) && (j == 0 || j == 29)){land.setType(i,j,3);}
                else if((i==1 || i == 58) && (j == 1 || j == 28)){land.setType(i,j,1);}
                else land.setType(i,j,4);
            }
        }
        fill.clearAnomalies(4,0);
        for(int i=2; i<58; i++){
            for(int j=2;j<28;j++){
                assertEquals(0,land.getType(i,j));
            }
        }
    }
    @Test
    public void testBoundaryFill(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeRect(land,4,20,3,10,1);
        fill.boundaryFill(5,4);
        for(int i = 4; i < 20; i++){
            for(int j = 3; j < 10; j++){
                assertEquals(1, land.getType(i,j));
            }
        }
    }
    @Test
    public void testInsideGrassL(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeV(land,0,29,5,1);
        assertFalse(fill.insideGrass(8,6));
    }
    @Test
    public void testInsideGrassR(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeV(land,0,29,5,1);
        assertFalse(fill.insideGrass(3,6));
    }
    @Test
    public void testInsideGrassLR(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeV(land,0,30,5,1);
        setTypeV(land,0,30,19,1);
        assertFalse(fill.insideGrass(8,6));
    }
    @Test
    public void testInsideGrassU(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,1,1);
        assertFalse(fill.insideGrass(3,6));
    }
    @Test
    public void testInsideGrassD(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,15,1);
        assertFalse(fill.insideGrass(3,6));
    }
    @Test
    public void testInsideGrassDU(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,1,1);
        setTypeH(land,0,59,10,1);
        assertFalse(fill.insideGrass(3,6));
    }
    @Test
    public void testInsideGrassDL(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,29,1);
        setTypeV(land,0,29,10,1);
        assertFalse(fill.insideGrass(15,6));
    }
    @Test
    public void testInsideGrassDR(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,29,1);
        setTypeV(land,0,29,10,1);
        assertFalse(fill.insideGrass(8,6));
    }
    @Test
    public void testInsideGrassUL(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,0,1);
        setTypeV(land,0,29,10,1);
        assertFalse(fill.insideGrass(15,6));
    }
    @Test
    public void testInsideGrassUR(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,0,1);
        setTypeV(land,0,29,10,1);
        assertFalse(fill.insideGrass(8,6));
    }
    @Test
    public void testInsideGrassUDL(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,0,1);
        setTypeH(land,0,59,29,1);
        setTypeV(land,0,29,10,1);
        assertFalse(fill.insideGrass(15,6));
    }
    @Test
    public void testInsideGrassUDR(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,0,1);
        setTypeH(land,0,59,29,1);
        setTypeV(land,0,29,10,1);
        assertFalse(fill.insideGrass(8,6));
    }
    @Test
    public void testInsideGrassLRU(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,0,1);
        setTypeV(land,0,29,10,1);
        setTypeV(land,0,29,5,1);
        assertFalse(fill.insideGrass(8,6));
    }
    @Test
    public void testInsideGrassLRD(){
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        Fill fill = new Fill(60,30, land,wolves);
        setTypeH(land,0,59,0,1);
        setTypeV(land,0,29,10,1);
        setTypeV(land,0,29,5,1);
        assertFalse(fill.insideGrass(8,6));
    }
    @Test
    public void testInsideGrassFill() {
        Land land = new Land(new int[60][30]);
        ArrayList<Wolf> wolves = new ArrayList<>();
        wolves.add(new Wolf(35, 20));
        Fill fill = new Fill(60, 30, land, wolves);
        setTypeV(land, 0, 30, 0, 3);
        setTypeV(land, 1, 29, 1, 1);

        setTypeV(land, 0, 30, 59, 3);
        setTypeV(land, 1, 29, 58, 1);

        setTypeH(land, 0, 60, 0, 3);
        setTypeH(land, 1, 59, 1, 1);

        setTypeH(land, 0, 60, 29, 3);
        setTypeH(land, 1, 59, 28, 1);

        setTypeRect(land, 2, 10, 3, 8, 1);
        setTypeRect(land, 10, 15, 3, 8, 1);

        fill.insideGrass();
        for (int i = 2; i < 15; i++) {
            for (int j = 3; j < 8; j++) {
                assertEquals(1, land.getType(i, j));
            }
        }
    }
    @Test
    public void verifyPosition() {
        Land land = new Land(new int[60][30]);
        land.setType(1,1,3);
        land.setType(1,2,1);
        land.setType(1,3,2);
        land.setType(1,4,3);
        List<Wolf> wolves = new ArrayList<>();
        wolves.add(new Wolf(1,1));

        Fill fill = new Fill(60,30,land,wolves);

        assertFalse(fill.verifyPosition(1,1));
        assertFalse(fill.verifyPosition(-13,-10));
        assertFalse(fill.verifyPosition(1,2));
        assertFalse(fill.verifyPosition(1,1));
        assertFalse(fill.verifyPosition(100,70));
        assertTrue(fill.verifyPosition(20,20));
    }
}

