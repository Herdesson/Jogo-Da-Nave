
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
public class Boss {
    private int x, y;
    Image boss;
    Image tiro;
    Image laser;
    int vidaBoss = 40;
    int damageProjetil = 10, damageLaser = 30;
    
    public Boss(){
    }
    public Boss(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setPositionX(int x){
        this.x = x;
    }
    public void setPositionY(int y){
        this.y = y;
    }
    public void setVidaBoss(int vida){
        this.vidaBoss = vida;
    }
    public int getPositionX(){
        return x;
    }
    public int getPositionY(){
        return y;
    }
    public int getDamageProjetil(){
        return damageProjetil;
    }
    public int getDamageLaser(){
        return damageLaser;
    }
    public int getVidaBoss(){
        return vidaBoss;
    }
    public void dimesionBoss(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void dimesionTiro(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void dimesionLaser(Graphics g){
        g.fillRect(x, y, 0, 0);
    }
    public void iconBoss(Graphics g){
        URL url = Boss.class.getResource("Boss.gif");
        ImageIcon icon = new ImageIcon(url);
        boss = icon.getImage();
        g.drawImage(boss, x, y, null);
    }
    public void iconTiro(Graphics g){
        URL url = Boss.class.getResource("MisselBoss.png");
        ImageIcon icon = new ImageIcon(url);
        tiro = icon.getImage();
        g.drawImage(tiro, x, y, null);
    }
    public void iconLaser(Graphics g){
        URL url = Boss.class.getResource("LaserTiro.gif");
        ImageIcon icon = new ImageIcon(url);
        laser = icon.getImage();
        g.drawImage(laser, x, y, null);
    }
    public void movimentoTiro(){
        y+=7;
    }public void movimentoBolaDeRaio(){
        y+=15;
    }
}
