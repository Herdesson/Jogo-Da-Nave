import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FLAVIO
 */
public class JogoDaNave extends Canvas implements Runnable, KeyListener {
    public int height = 600, widht = 500;
    public Nave[] nave = new Nave[1];
    public Nave[] heart = new Nave[3];
    public Nave tro = new Nave();
    Image fundo;
    public SpawnnerObjects spawn = new SpawnnerObjects();
    public int navePositionX = 300, navePositionY = 500,newPositionX,newPositionY;
    public boolean right = false, left = false, up = false, down = false,projetil = false;
    public JogoDaNave(){
        Dimension dimension = new Dimension(widht,height);
        this.setPreferredSize(dimension);
        this.addKeyListener(this);
    }
    public void tick(){
        spawn.tick();
        movimentoNave();
    }
    public void movimentoNave(){
        if(navePositionX >= widht - 70){
            right = false;
        }
        if(navePositionX <= -15){
            left = false;
        }
        if(navePositionY >= height -70){
            down = false;
        }
        if(navePositionY <= 200){
            up = false;
        }
        if(projetil){
            spawn.disparouProjetil = true;
            projetil = false;
            editCursor();
        }
        if(right){
            navePositionX = nave[0].getPositionX();        
            nave[0].setPositionX(navePositionX+=15);                      
            right = false;
        }if(left){
            navePositionX = nave[0].getPositionX();
            nave[0].setPositionX(navePositionX-=15);                      
            left = false;
        }if(up){
            navePositionY = nave[0].getPositionY();
            nave[0].setPositionY(navePositionY-=15);
            up = false;
        }if(down){
            navePositionY = nave[0].getPositionY();
            nave[0].setPositionY(navePositionY+=15);
            down = false;
        }
    }
    public void editCursor(){
        spawn.getNave.poInicialNaveX = 32+nave[0].getPositionX();
        spawn.getNave.poInicialNaveY = nave[0].getPositionY();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, widht, height);       
        URL url = JogoDaNave.class.getResource("PlanoDeFundo.gif");
        ImageIcon icon = new ImageIcon(url);
        fundo = icon.getImage();
        g.drawImage(fundo, 0, 0, null);
        
        for(int i = 0; i < nave.length;i++){
            nave[i] = new Nave(navePositionX, navePositionY);
            nave[i].dimension(g);
            nave[i].icon(g);
        }
        spawn.render(g);
        /*for(int r = 0; r < nave.length;r++){
            heart[r] = new Nave(heart[r].positionHeartX, heart[r].positionHeartY);
            heart[r].dimensionHeart(g);
            heart[r].iconHeart(g);
        }
        for(int o = 0; o < nave.length;o++){
            heart[o] = new Nave(heart[o].positionHeartX, heart[o].positionHeartY);
            heart[o].dimensionHeart(g);
            heart[o].iconHeart(g);
        }*/
        bs.show();
        
    }
    public static void main(String[] args) {
        JogoDaNave jogo = new JogoDaNave();
        JFrame frame = new JFrame("Jogo");
        frame.add(jogo);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();       
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        new Thread(jogo).start();
    }

    @Override
    public void run(){      
        while(true){           
            render();
            tick();
            try{
                Thread.sleep(1000/60);
            }catch(Exception e){
            };
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            projetil = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            up = true;
            right = false;
            left = false;
            down = false;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            up = false;
            right = false;
            left = false;
            down = true;
        }else if(e.getKeyCode() == KeyEvent.VK_A){
            up = false;
            right = false;
            left = true;
            down = false;
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            up = false;
            right = true;
            left = false;
            down = false;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

