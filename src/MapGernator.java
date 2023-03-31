import java.awt.*;

public class MapGernator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public MapGernator(int row, int col){
        map = new int[row][col];
        if(row == 5 && col == 10){
            for(int i=0;i<map.length;i++){
                for(int j=i;j < map[0].length-i;j++){
                    //This means brick should be display shown on panel &
                    //It's not intersected with ball
                    map[i][j] =1;
                }
            }
            brickWidth = 540/col;
            brickHeight = 150/row;
        }
        else{
            for(int i=0;i<map.length;i++){
                for(int j=0;j < map[0].length;j++){
                    //This means brick should be display shown on panel &
                    //It's not intersected with ball
                    map[i][j] =1;
                }
            }
            brickWidth = 540/col;
            brickHeight = 150/row;
        }
    }

    public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j < map[0].length;j++){
                if(map[i][j] > 0){
                    if(i == 0)
                        g.setColor(new Color(0x603C00));
                    else if(i == 1)
                        g.setColor(new Color(0x7E5000));
                    else if(i == 2)
                        g.setColor(new Color(0x8F5800));
                    else if(i == 3)
                        g.setColor(new Color(0xCC7E00));
                    else
                        g.setColor(new Color(0xEE9000));

                    g.fillRect(j* brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j* brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);

                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
