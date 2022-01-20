import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
    public ArrayList<Nave> heart = new ArrayList<Nave>();
    public Nave tro = new Nave();
    Image fundo;
    public SpawnnerObjects spawn = new SpawnnerObjects();
    public int navePositionX = 300, navePositionY = 500,newPositionX,newPositionY;
    public boolean right = false, left = false, up = false, down = false,projetil = false;
    boolean createHeart = true;
    int vidaNave = 30,laserX,laserY;
    boolean gameOver = true;
    public Timer timer;
    int timmer;
    boolean dano;
    public JogoDaNave(){
        Dimension dimension = new Dimension(widht,height);
        this.setPreferredSize(dimension);
        this.addKeyListener(this);
    }
    public void tick(){
        spawn.tick();
        timmer++;
        movimentoNave();
        colision();
        if(spawn.sec2){
            laserY = 0;
        }
        if(createHeart){
            heart();
        }
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
    public void colision(){
        for(int i = 0; i < spawn.objAstPeq.size();i++){
            ObjectGame obj = spawn.objAstPeq.get(i);
            if(obj.getPositionX() >= nave[0].getPositionX() -20 && obj.getPositionX() <= nave[0].getPositionX() + 60){               
                if(obj.getPositionY() >= nave[0].getPositionY() && obj.getPositionY() <= nave[0].getPositionY() + 78){
                    vidaNave -= 10;
                    spawn.objAstPeq.remove(obj);                   
                    if(vidaNave == 20){
                        heart.remove(0);
                    }else if(vidaNave == 10){
                        heart.remove(0);
                    }else if(vidaNave <= 0){
                        heart.remove(0);
                        spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-60));
                        explosao();
                        gameOver();                        
                    }
                }
            }
        }for(int i = 0; i < spawn.objAstMed.size();i++){
            ObjectGame obj = spawn.objAstMed.get(i);
            if(obj.getPositionX() >= nave[0].getPositionX() -30 && obj.getPositionX() <= nave[0].getPositionX() + 60){
                if(obj.getPositionY() >= nave[0].getPositionY() && obj.getPositionY() <= nave[0].getPositionY() + 78){
                    vidaNave -= 10;
                    spawn.objAstMed.remove(obj);                   
                    if(vidaNave == 20){
                        heart.remove(0);
                    }else if(vidaNave == 10){
                        heart.remove(0);
                    }else if(vidaNave <= 0){
                        heart.remove(0);
                        spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-20));
                        explosao();
                        gameOver();
                    }
                }
            }
        }for(int i = 0; i < spawn.objAstGra.size();i++){
            ObjectGame obj = spawn.objAstGra.get(i);
            if(obj.getPositionX() >= nave[0].getPositionX() -60 && obj.getPositionX() <= nave[0].getPositionX() + 60){
                if(obj.getPositionY() >= nave[0].getPositionY() && obj.getPositionY() <= nave[0].getPositionY() + 78){
                    vidaNave -= 10;
                    spawn.objAstGra.remove(obj);                   
                    if(vidaNave == 20){
                        heart.remove(0);
                    }else if(vidaNave == 10){
                        heart.remove(0);
                    }else if(vidaNave <= 0){
                        heart.remove(0);
                        spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-20));
                        explosao();
                        gameOver();
                    }
                }
            }
        }for(int i = 0; i < spawn.objBomba.size();i++){
            ObjectGame obj = spawn.objBomba.get(i);
            if(obj.getPositionX() >= nave[0].getPositionX() -20 && obj.getPositionX() <= nave[0].getPositionX() + 60){
                if(obj.getPositionY() >= nave[0].getPositionY() && obj.getPositionY() <= nave[0].getPositionY() + 78){
                    vidaNave -= 10;
                    spawn.objBomba.remove(obj);
                    spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-40));
                    explosao();
                    if(vidaNave == 20){
                        heart.remove(0);
                    }else if(vidaNave == 10){
                        heart.remove(0);
                    }else if(vidaNave <= 0){
                        heart.remove(0);
                        spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-20));
                        explosao();
                        gameOver();
                    }
                }
            }
        }for(int i = 0; i < spawn.bossProjetil.size();i++){
            Boss obj = spawn.bossProjetil.get(i);
            if(obj.getPositionX() >= nave[0].getPositionX() -20 && obj.getPositionX() <= nave[0].getPositionX() + 60){
                if(obj.getPositionY() >= nave[0].getPositionY() && obj.getPositionY() <= nave[0].getPositionY() + 78){
                    vidaNave -= 10;
                    spawn.bossProjetil.remove(obj);
                    spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-40));
                    explosao();
                    if(vidaNave == 20){
                        heart.remove(0);
                    }else if(vidaNave == 10){
                        heart.remove(0);
                    }else if(vidaNave <= 0){
                        heart.remove(0);
                        spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-20));
                        explosao();
                        gameOver();
                    }
                }
            }
        }for(int i = 0; i < spawn.bossLaser.size();i++){
            Boss obj = spawn.bossLaser.get(i);
            laserColision(obj);
            if(laserX >= nave[0].getPositionX() -40 && laserX <= nave[0].getPositionX() + 60){
                if(laserY >= nave[0].getPositionY() && laserY <= nave[0].getPositionY() + 78 || dano == true){
                    dano = false;
                    vidaNave = vidaNave - 10;
                    if(vidaNave == 20){
                        heart.remove(0);
                    }if(vidaNave == 10){
                        heart.remove(0);
                    }if(vidaNave <= 0){
                        heart.remove(0);
                        spawn.objExplosao.add(new ObjectGame(nave[0].getPositionX()-20,nave[0].getPositionY()-20));
                        explosao();
                        gameOver();
                    }
                }
            }
        }
    }
    public void laserColision(Boss obj){
        laserX = obj.getPositionX();
        if(spawn.sec){
            laserY = obj.getPositionY();}
        laserX = laserX + 40;
        
        timer = new Timer();
            final long tempo = (100);
            TimerTask remove = new TimerTask(){                      
                @Override
                public void run() {
                    
                    laserY +=15;
                    if(laserY >= 600){
                        laserY = 480;
                        if(nave[0].getPositionY() <= laserY){
                            dano = true;
                        }
                    }
                }
            };
            timer.schedule(remove, tempo); 
    }
    public void explosao(){
        for(int i = 0;i < spawn.objExplosao.size();i++){            
            ObjectGame explosao = spawn.objExplosao.get(i);           
            timer = new Timer();
            final long tempo = (400);
            TimerTask remove = new TimerTask(){                      
                @Override
                public void run() {
                    spawn.objExplosao.remove(explosao);
                }
            };
            timer.schedule(remove, tempo);                                        
        }
    }
    public void gameOver(){
        gameOver = false;
        spawn.timmer = 0;
        navePositionY = 800;
        for(int d = 0;0 < spawn.objAstPeq.size();d++){
            spawn.objAstPeq.remove(0);
        }for(int c = 0;0 < spawn.objAstMed.size();c++){
            spawn.objAstMed.remove(0);
        }for(int k = 0;0 < spawn.objAstGra.size();k++){
            spawn.objAstGra.remove(0);
        }for(int z = 0;0 < spawn.objBomba.size();z++){
            spawn.objBomba.remove(0);
        }for(int t = 0;0 < spawn.objDisco.size();t++){
            spawn.objDisco.remove(0);
        }for(int x = 0;0 < spawn.projetil.size();x++){
            spawn.projetil.remove(0);
        }for(int w = 0;0 < spawn.bossProjetil.size();w++){
            spawn.bossProjetil.remove(0);
        }for(int y = 0;0 < spawn.bossLaser.size();y++){
            spawn.bossLaser.remove(0);
        }for(int q = 0;0 < spawn.cenMeteoro.size();q++){
            spawn.cenMeteoro.remove(0);
        }for(int u = 0;0 < spawn.cenFoguete.size();u++){
            spawn.cenFoguete.remove(0);
        }
    }
    public void heart(){       
        heart.add(new Nave(380,3));
        heart.add(new Nave(410,3));
        heart.add(new Nave(440,3));
        createHeart = false;
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
              
        spawn.renderCenario(g);
        
        for(int i = 0; i < nave.length;i++){
            nave[i] = new Nave(navePositionX, navePositionY);
            nave[i].dimension(g);
            nave[i].icon(g);
        }
        spawn.render(g);
        if(spawn.victory == false){
            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,60));
            g.drawString("Victory", widht/2 -100, height/2);
        }
        
        for(int b = 0; b < heart.size();b++){
            Nave rec = heart.get(b);
            rec.dimensionHeart(g);
            rec.iconHeart(g);
        }if(gameOver == false){
            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,60));
            g.drawString("GameOver", widht/2 -160, height/2);
            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,20));
            g.drawString("Press ENTER to try again", widht/2 -130, height/2 +30);
        }
        
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
            if(gameOver){
                tick();}
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
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameOver = true;
            createHeart = true;
            vidaNave = 30;
            spawn.vidaBoss = 2250;
            navePositionX = 300;
            navePositionY = 500;
            spawn.bossY = -150;
            spawn.movieBoss = true;
            spawn.goBoss = false;
            spawn.limit3 = 0;
            spawn.verf5 = false;
        }if(gameOver){                
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            projetil = true;
        }
    }
}

