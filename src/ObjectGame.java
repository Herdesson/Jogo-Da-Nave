
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FLAVIO
 */
public class ObjectGame{
    int x,y;
    Image object;
    public int PoInicialAPX;
    public ObjectGame(){
    }  
    public ObjectGame(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setPositionX(int x){
        this.x = x;
    }
    public void setPositionY(int y){
        this.y = y;
    }
    public int getPositionX(){
        return x;
    }
    public int getPositionY(){
        return y;
    }
    public void asteroidPequenoDimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void asteroidMedioDimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void asteroidGrandeDimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void discoDimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void bombaDimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }    
    public void iconAsteroidPequeno(Graphics g){
        URL url = ObjectGame.class.getResource("AsteroidePequenoProjeto.png");
        ImageIcon icon = new ImageIcon(url);
        object = icon.getImage();
        g.drawImage(object, x, y, null);
    }
    public void iconAsteroideMedio(Graphics g){
        URL url = ObjectGame.class.getResource("AsteroideMedioProjeto.png");
        ImageIcon icon = new ImageIcon(url);
        object = icon.getImage();
        g.drawImage(object, x, y, null);
    }
    public void iconAsteroideGrande(Graphics g){
        URL url = ObjectGame.class.getResource("AsteroideGrandeProjeto.png");
        ImageIcon icon = new ImageIcon(url);
        object = icon.getImage();
        g.drawImage(object, x, y, null);
    }
    public void iconDisco(Graphics g){
        URL url = ObjectGame.class.getResource("discoProjeto.gif");
        ImageIcon icon = new ImageIcon(url);
        object = icon.getImage();
        g.drawImage(object, x, y, null);
    }
    public void iconBomba(Graphics g){
        URL url = ObjectGame.class.getResource("bombaProjeto.png");
        ImageIcon icon = new ImageIcon(url);
        object = icon.getImage();
        g.drawImage(object, x, y, null);
    }
    public void movimentoAstPeq(){
        y+=10;        
    }
    public void movimentoAstMed(){
        y+=7;        
    }
    public void movimentoAstGra(){
        y+=5;        
    }
    public void movimentoDisco(){
        x+=5;
    }
    public void movimentoBomba(){
        y+=3;
    }
}
