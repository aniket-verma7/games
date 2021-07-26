import com.application.model.Game;
import com.application.model.Grid;
import com.application.model.Player;
import javafx.scene.paint.Color;
import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class Testing {
    @Test
    public void testGameClass()
    {
        assertFalse(Game.isLegal(6,6,0,0,10));
        assertTrue(Game.isLegal(2,2,2,3,10));
        assertTrue(Game.move(1,1,1,2,10));
        assertFalse(Game.move(7,5,1,2,10));

        Grid[][] cell = new Grid[10][10];
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                cell[i][j] = new Grid(i,j,30,30);

        assertFalse(Game.isWinner(cell,6,5));
        assertFalse(Game.isWinner(cell,1,1));


        cell[1][0].getRectangle().setFill(Color.BLUE);
        cell[0][1].getRectangle().setFill(Color.BLUE);
        cell[1][2].getRectangle().setFill(Color.BLUE);
        cell[2][1].getRectangle().setFill(Color.BLUE);

        assertTrue(Game.isWinner(cell,1,1));

    }

    @Test
    public void testGridClass()
    {
        Grid grid = new Grid(3,4,30,30);
        assertEquals(3,grid.getI());
        assertEquals(4,grid.getJ());
        assertEquals(30,grid.getW());
        assertEquals(30,grid.getH());

        assertFalse(grid.isClicked());
        assertEquals(grid.getRectangle().getFill(), Color.LIGHTYELLOW);

        grid.setI(5);
        grid.setJ(1);
        grid.setW(20);
        grid.setH(50);
        grid.setClicked(true);

        assertEquals(5,grid.getI());
        assertEquals(1,grid.getJ());
        assertEquals(20,grid.getW());
        assertEquals(50,grid.getH());
        assertTrue(grid.isClicked());
    }

    @Test
    public void testPlayerClass()
    {
        Player player = new Player("Zack",false,Color.BLUE);

        TestCase.assertEquals("Zack",player.getName());
        TestCase.assertEquals(Color.BLUE,player.getColor());

        assertFalse(player.isChance());

        player.setChance(true);
        assertTrue(player.isChance());

        player.setColor(Color.BLUE);
        TestCase.assertEquals(Color.BLUE,player.getColor());
    }
}
