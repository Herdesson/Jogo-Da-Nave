
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
    int vidaAstPeq = 10,vidaAstMed = 20,vidaAstGra = 30, vidaDisco = 10;
    int damage = 10;
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
    public void setVidaAstPeq(int vida){
        this.vidaAstPeq = vida;
    }
    public void setVidaAstMed(int vida){
        this.vidaAstMed = vida;
    }
    public void setVidaAstGra(int vida){
        this.vidaAstGra = vida;
    }
    public void setVidaDisco(int vida){
        this.vidaDisco = vida;
    }
    public int getPositionX(){
        return x;
    }
    public int getPositionY(){
        return y;
    }
    public int getDamage(){
        return damage;
    }
    public int getVidaAstPeq(){
        return vidaAstPeq;
    }
    public int getVidaAstMed(){
        return vidaAstMed;
    }
    public int getVidaAstGra(){
        return vidaAstGra;
    }
    public int getVidaDisco(){
        return vidaDisco;
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
    public void explosaoDimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void fogueteDimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void meteoroDimension(Graphics g){
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
    public void iconExplosao(Graphics g){
        URL url = ObjectGame.class.getResource("explosaoProjeto2.gif");
        ImageIcon icon = new ImageIcon(url);
        object = icon.getImage();
        g.drawImage(object, x, y, null);
    }
    public void iconFoguete(Graphics g){
        URL url = ObjectGame.class.getResource("foguetePaisagem2.gif");
        ImageIcon icon = new ImageIcon(url);
        object = icon.getImage();
        g.drawImage(object, x, y, null);
    }
    public void iconMeteoro(Graphics g){
        URL url = ObjectGame.class.getResource("Meteoro.gif");
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
    public void movimentoExplosao(){
        y++;
    }
    public void movimentoMeteoro(){
        x--;
        y++;
    }
    public void movimentoFoguete(){
        x++;
        y--;
    }
}
