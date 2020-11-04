import java.util.*;
import java.awt.*;

public class TileManager{
    private ArrayList<Tile> list = new ArrayList<Tile>();

    public TileManager(){
    }

    public void addTile(Tile rect){ //adds tiles to list
        list.add(rect);
    }

    public void drawAll(Graphics g){ //draws all tiles
        for(Tile i : list){
            i.draw(g);
        }
    }

    private ArrayList<Tile> getClickedOn(int x, int y){ //returns a list of all clicked on tiles
        ArrayList<Tile> clickedTiles = new ArrayList<Tile>();
        for(Tile i : list){
            if(x >= i.getX() && x <= (i.getX() + i.getWidth()) && y >= i.getY() && y <= (i.getY() + i.getHeight())){
                clickedTiles.add(i);
            }
        }
        return clickedTiles;
    }

    public void raise(int x, int y){ //raises topmost clicked on tile to the top
        ArrayList<Tile> clickedTiles = getClickedOn(x, y);
        if(clickedTiles.size() != 0){
            int index = list.indexOf(clickedTiles.get(clickedTiles.size()-1));
            list.add(list.get(index));
            list.remove(index);
        }
    }

    public void lower(int x, int y){ //lowers topmost clicked on tile to the bottom
        ArrayList<Tile> clickedTiles = getClickedOn(x, y);
        ArrayList<Tile> tempArray = new ArrayList<Tile>();
        if(clickedTiles.size() != 0){
            int index = list.indexOf(clickedTiles.get(clickedTiles.size()-1));
            tempArray.add(list.get(index));
            for(Tile i : list){
                if(i != list.get(index)){
                    tempArray.add(i);
                }
            }
            for(int j = 0; j < list.size(); j++){
                list.set(j, tempArray.get(j));
            }
        }
    }

    public void delete(int x, int y){ //deletes topmost clicked on tiles
        ArrayList<Tile> clickedTiles = getClickedOn(x, y);
        if(clickedTiles.size() != 0){
            int index = list.indexOf(clickedTiles.get(clickedTiles.size()-1));
            list.remove(index);
        }
    }

    public void deleteAll(int x, int y){ //deletes all tiles clicked on in a stack
        ArrayList<Tile> clickedTiles = getClickedOn(x, y);
        if(clickedTiles.size() != 0){
            for(Tile i : clickedTiles){
                list.remove(i);
            }
        }
    }

    public void shuffle(int width, int height){ //shuffles the position and layer of every tile
        ArrayList<Tile> tempArray = new ArrayList<Tile>();
        int length = list.size();
        for(int i = 0; i < length; i++){
            int randIndex = (int) (Math.random() * (list.size()));
            tempArray.add(list.get(randIndex));
            list.remove(randIndex);
            System.out.println(list.size() + ", " + tempArray.size());
        }
        for(Tile j : tempArray){
            int xLimit = TileMain.WIDTH - j.getWidth();
            int yLimit = TileMain.HEIGHT - j.getHeight();
            int xRand = (int) (Math.random() * xLimit);
            int yRand = (int) (Math.random() * yLimit);
            j.setX(xRand);
            j.setY(yRand);
        }
        for(Tile q : tempArray){
            list.add(q);
        }
    }
}