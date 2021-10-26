
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
public class Nave {
    private int x, y;
    Image iconNave;
    Image heart;
    Image tiro;
    public int poInicialNaveX = 300, poInicialNaveY = 500;
    public int poInicialTiroX = 300, poInicialTiroY = 490;
    public int positionHeartX = 380, positionHeartY = 0;
    
    public Nave(){}
    public Nave(int x, int y){
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
    public void dimension(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void dimensionHeart(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void dimensionTiro(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void icon(Graphics g){
        URL url = Nave.class.getResource("NaveProjeto.png");
        ImageIcon icon = new ImageIcon(url);
        iconNave = icon.getImage();
        g.drawImage(iconNave, x, y, null);
        
    }
    public void iconHeart(Graphics g){
        URL url = Nave.class.getResource("vidaProjeto.png");
        ImageIcon icon = new ImageIcon(url);
        heart = icon.getImage();
        g.drawImage(heart, x, y, null);
    }
    public void iconTiro(Graphics g){
        URL url = Nave.class.getResource("Missel.png");
        ImageIcon icon = new ImageIcon(url);
        tiro = icon.getImage();
        g.drawImage(tiro, x, y, null);
    }
    public void movimentoTiro(){
        y-=7;
    }
}
