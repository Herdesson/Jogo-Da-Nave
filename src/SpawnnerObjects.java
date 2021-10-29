
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FLAVIO
 */
public class SpawnnerObjects{
    public ArrayList<ObjectGame[]> objects = new ArrayList<ObjectGame[]>();
    public ArrayList<Nave[]> projeteis = new ArrayList<Nave[]>();
    public ArrayList<Boss[]> enemy = new ArrayList<Boss[]>();
    public ArrayList<ObjectGame> objAstPeq = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objAstMed = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objAstGra = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objBomba = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objDisco = new ArrayList<ObjectGame>();  
    public ArrayList<ObjectGame> objExplosao = new ArrayList<ObjectGame>();
    public ObjectGame[] objEx = new ObjectGame[4];
    Boss[] boss = new Boss[50];
    public ArrayList<Boss> bossProjetil = new ArrayList<Boss>();
    public ArrayList<Boss> bossLaser = new ArrayList<Boss>();
    public ArrayList<Nave> projetil = new ArrayList<Nave>();
    public Nave getNave = new Nave();
    public boolean disparouProjetil = false;
    boolean respawnBoss = true,movieBoss = true, rightBoss = false, leftBoss = false, goBoss = false,atirarLaser = false;
    boolean sec,sec2;
    private Timer timer;
    int timmer,timmer2;
    int limit, limit2 = 0, limit3;
    int height = 600, widht = 500;
    public int projetNaveX, projetNaveY,newProjetX,newProjetY,vidaBoss = 2250;
    boolean verf1 = false, verf2 = false, verf3 = false,verf4 = false, verf5 = false, victory = true;
    int bossX = 40,bossY = -150,astPeqX = 30, astPeqY = -40,astPeqX2 = 30, astPeqY2 = -40, laserX = -159, laserY  = -120
            , astMedX = 70, astMedY = -70, astGraX = 120, astGraY = -120, bombX = 200, bombY = -190
            , discX = -50, discY = 170;
    boolean morteBoss = false,colisionBoss = false;
    
    public void tick(){
        if(victory){
            timmer++;}
        colision();
        if(timmer % 1140 == 0){            
            limit = (int)(Math.random()*4);
            objAstPeq.add(new ObjectGame(new Random().nextInt(490-10),astPeqY));
            objAstMed.add(new ObjectGame(new Random().nextInt(480-10),astMedY));
            objAstGra.add(new ObjectGame(new Random().nextInt(470-20),astGraY));
            if(limit == 0){
                limit = 1;
            }}
            if(limit == 1){
                verf1 = true;
            }else if(limit == 2){
                verf2 = true;
            }else if(limit == 3){
                verf3 = true;
            }if(verf1){
                for(int i = 0;i < objAstPeq.size();i++){
                    ObjectGame astPequeno = objAstPeq.get(i);
                    objAstPeq.get(i).movimentoAstPeq();
                    if(astPequeno.getPositionY() > height){
                        objAstPeq.remove(astPequeno);
                    }
                }                            
            }if(verf2){
                for(int i = 0;i < objAstMed.size();i++){
                    ObjectGame astMedio = objAstMed.get(i);
                    objAstMed.get(i).movimentoAstMed();
                    if(astMedio.getPositionY() > height){
                        objAstMed.remove(astMedio);
                    }
                } 
            }if(verf3){
                for(int i = 0;i < objAstGra.size();i++){
                    ObjectGame astGrande = objAstGra.get(i);
                    objAstGra.get(i).movimentoAstGra();
                    if(astGrande.getPositionY() > height){
                        objAstGra.remove(astGrande);
                    }
                } 
            }if(timmer % 340 == 0){
                objDisco.add(new ObjectGame(discX,discY));
                limit2 = 1;
            }if(limit2 == 1){
                verf4 = true;
            }if(verf4){
                for(int i = 0;i < objDisco.size();i++){
                    ObjectGame disco = objDisco.get(i);
                    objDisco.get(i).movimentoDisco();                   
                    if(timmer %60 == 0){
                    objBomba.add(new ObjectGame(objDisco.get(i).getPositionX(),objDisco.get(i).getPositionY()));
                    }
                    
                    if(disco.getPositionX() > widht){
                        objDisco.remove(disco);
                    }
                }bombPlant(); 
            }if(timmer % 220 == 0){
                //4220
                limit3 = 1;
            }if(limit3 == 1){
                verf5 = true;
            }if(verf5){
                bossX = 40;
                if(movieBoss){
                    boss[0].setPositionY(bossY++);
                    if(boss[0].getPositionY() >= -30){
                        bossY = -30;
                        movieBoss = false;
                        goBoss = true;
                    }                    
                }
                if(respawnBoss){
                   respawnBoss();
                   respawnBoss = false;
                   rightBoss = true;
                }if(goBoss){
                    colisionBoss = true;
                if(rightBoss){
                    bossX = boss[0].getPositionX();
                    boss[0].setPositionX(bossX++);
                    if(boss[0].getPositionX() >= 100){
                        leftBoss = true;
                        rightBoss = false;
                    }
                }if(leftBoss){
                    bossX = boss[0].getPositionX();
                    boss[0].setPositionX(bossX--);
                    if(boss[0].getPositionX() <= -10){
                        leftBoss = false;
                        rightBoss = true;
                    }
                }
                if(timmer % 1140 == 0){                  
                    bossProjetil.add(new Boss(105+boss[0].getPositionX(), 100+boss[0].getPositionY()));
                    bossProjetil.add(new Boss(265+boss[0].getPositionX(), 100+boss[0].getPositionY()));

                }if(timmer % 1160 == 0){                  
                    bossProjetil.add(new Boss(60+boss[0].getPositionX(), 105+boss[0].getPositionY()));
                    bossProjetil.add(new Boss(310+boss[0].getPositionX(), 105+boss[0].getPositionY()));
                }if(timmer % 220 == 0){
                    bossLaser.add(new Boss(500+boss[0].getPositionX(), 150+boss[0].getPositionY()));
                    //atirarLaser = true;                                       
                    //sec = true;
                }                              
                }}if(disparouProjetil){
                newProjetX = projetNaveX;
                newProjetY = projetNaveY;
                projetil.add(new Nave(getNave.poInicialNaveX,getNave.poInicialNaveY));
                disparouProjetil = false; 
            }atirarProjetil();
            BossAtirarLaser();
            explosao();
            bossAtirarProjetil();
            if(morteBoss){
                objExplosao.add(new ObjectGame(new Random().nextInt(400-40),new Random().nextInt(20-1)));
                timer = new Timer();
                final long tempo = (1000)*4;
                TimerTask remove = new TimerTask(){                      
                @Override
                    public void run() {
                        bossY = -180;
                        boss[0].setPositionY(bossY);
                        morteBoss = false;
                    }
                 };
                 timer.schedule(remove, tempo); 
                
            }
    }
    public void colision(){
        for(int i = 0;i < projetil.size();i++){
            Nave coliProjetil = projetil.get(i);
            for(int c = 0;c < objAstGra.size();c++){
                ObjectGame coliObj = objAstGra.get(c);
                if(coliProjetil.getPositionX() >= coliObj.getPositionX() && coliProjetil.getPositionX() <= coliObj.getPositionX() + 80){
                    if(coliProjetil.getPositionY() >= coliObj.getPositionY() && coliProjetil.getPositionY() <= coliObj.getPositionY() + 65){                                             
                        int vida = coliObj.getVidaAstGra();
                        coliObj.setVidaAstGra(vida - coliProjetil.getDamage());
                        projetil.remove(coliProjetil);
                        if(coliObj.getVidaAstGra() <= 0){
                            objAstGra.remove(coliObj);
                            objExplosao.add(new ObjectGame(coliObj.getPositionX(),coliObj.getPositionY()));
                        }                                             
                    }                   
                }
            }for(int d = 0;d < objAstMed.size();d++){
                ObjectGame coliObj = objAstMed.get(d);
                if(coliProjetil.getPositionX() >= coliObj.getPositionX() && coliProjetil.getPositionX() <= coliObj.getPositionX() + 60){
                    if(coliProjetil.getPositionY() >= coliObj.getPositionY() && coliProjetil.getPositionY() <= coliObj.getPositionY() + 49){
                        int vida = coliObj.getVidaAstMed();
                        coliObj.setVidaAstMed(vida - coliProjetil.getDamage());
                        projetil.remove(coliProjetil);
                        if(coliObj.getVidaAstMed() <= 0){
                            objAstMed.remove(coliObj);
                            objExplosao.add(new ObjectGame(coliObj.getPositionX(),coliObj.getPositionY()));
                        }                       
                    }                   
                }
            }for(int t = 0;t < objAstPeq.size();t++){
                ObjectGame coliObj = objAstPeq.get(t);
                if(coliProjetil.getPositionX() >= coliObj.getPositionX() && coliProjetil.getPositionX() <= coliObj.getPositionX() + 40){
                    if(coliProjetil.getPositionY() >= coliObj.getPositionY() && coliProjetil.getPositionY() <= coliObj.getPositionY() + 33){
                        int vida = coliObj.getVidaAstPeq();
                        coliObj.setVidaAstPeq(vida - coliProjetil.getDamage());
                        projetil.remove(coliProjetil);
                        if(coliObj.getVidaAstPeq() <= 0){
                            objAstPeq.remove(coliObj);
                            objExplosao.add(new ObjectGame(coliObj.getPositionX(),coliObj.getPositionY()));
                        }                       
                    }                   
                }
            }for(int s = 0;s < objDisco.size();s++){
                ObjectGame coliObj = objDisco.get(s);
                if(coliProjetil.getPositionX() >= coliObj.getPositionX() && coliProjetil.getPositionX() <= coliObj.getPositionX() + 70){
                    if(coliProjetil.getPositionY() >= coliObj.getPositionY() && coliProjetil.getPositionY() <= coliObj.getPositionY() + 55){
                        int vida = coliObj.getVidaDisco();
                        coliObj.setVidaDisco(vida - coliProjetil.getDamage());
                        projetil.remove(coliProjetil);
                        if(coliObj.getVidaDisco() <= 0){
                            objDisco.remove(coliObj);
                            objExplosao.add(new ObjectGame(coliObj.getPositionX(),coliObj.getPositionY()));
                        }                       
                    }                   
                }
            }if(colisionBoss){                
                if(coliProjetil.getPositionX() >= boss[0].getPositionX() && coliProjetil.getPositionX() <= boss[0].getPositionX() + 400){
                    if(coliProjetil.getPositionY() >= boss[0].getPositionY() && coliProjetil.getPositionY() <= boss[0].getPositionY() + 149){
                        vidaBoss -= 10;
                        projetil.remove(coliProjetil);
                        if(vidaBoss <= 0){
                            morteBoss = true;
                            goBoss = false;
                            victory = false;
                        }                       
                    }                   
                }
            }
        }
    }
    public void respawnBoss(){
        boss[0].setPositionX(bossX);
        boss[0].setPositionY(bossY);       
    }
    public void BossAtirarLaser(){
        for(int i = 0;i < bossLaser.size();i++){            
            Boss laserBoss = bossLaser.get(i);
            bossLaser.get(i).setPositionX(120+boss[i].getPositionX());
            if(sec){
            timer = new Timer();
            final long tempo = (2600);
            TimerTask remove = new TimerTask(){                      
                @Override
                public void run() {
                    bossLaser.remove(laserBoss);
                }
            };
            timer.schedule(remove, tempo);
            sec = false;
            }
                
        }
    }    
    public void explosao(){
        for(int i = 0;i < objExplosao.size();i++){            
            ObjectGame explosao = objExplosao.get(i);           
            timer = new Timer();
            final long tempo = (400);
            TimerTask remove = new TimerTask(){                      
                @Override
                public void run() {
                    objExplosao.remove(explosao);
                }
            };
            timer.schedule(remove, tempo);                                        
        }
    }    
    public void bossAtirarProjetil(){
        for(int i = 0;i < bossProjetil.size();i++){
            Boss projetilBoss = bossProjetil.get(i);
            bossProjetil.get(i).movimentoTiro();
            if(projetilBoss.getPositionY() > height){
                bossProjetil.remove(projetilBoss);
            }
        }
    }
    public void atirarProjetil(){
        for(int i = 0;i < projetil.size();i++){
            Nave naveProjetil = projetil.get(i);
            projetil.get(i).movimentoTiro();
            if(naveProjetil.getPositionY() <= 0){
                projetil.remove(naveProjetil);
            }else if(naveProjetil.getPositionY() <= 0){
                projetil.remove(naveProjetil);
            }
        }
    }
    public void bombPlant(){       
        for(int i = 0;i < objBomba.size();i++){
                    ObjectGame bomba = objBomba.get(i);
                    objBomba.get(i).movimentoBomba();
                    if(bomba.getPositionY() > height){
                        objBomba.remove(bomba);
                    }
                }
    }
    public void render(Graphics g){ 
        for(int z = 0; z < objAstPeq.size();z++){
            ObjectGame rec = objAstPeq.get(z);
            rec.asteroidPequenoDimension(g);
            rec.iconAsteroidPequeno(g);
        }
        for(int d = 0; d < objAstMed.size();d++){
            ObjectGame rec = objAstMed.get(d);
            rec.asteroidMedioDimension(g);
            rec.iconAsteroideMedio(g);
        }
        //Asteroide Grande
        for(int q = 0; q < objAstGra.size();q++){
            ObjectGame rec = objAstGra.get(q);
            rec.asteroidGrandeDimension(g);
            rec.iconAsteroideGrande(g);
        }
        for(int j = 0; j < objBomba.size();j++){
            ObjectGame rec = objBomba.get(j);
            rec.bombaDimension(g);
            rec.iconBomba(g);
        }
        for(int a = 0; a < objDisco.size();a++){
            ObjectGame rec = objDisco.get(a);
            rec.discoDimension(g);
            rec.iconDisco(g);
        }
        for(int a = 0; a < projetil.size();a++){
            Nave rec = projetil.get(a);
            rec.dimensionTiro(g);
            rec.iconTiro(g);
        } 
        
            for(int q = 0;q < boss.length;q++){
                boss[q] = new Boss(bossX, bossY);
                boss[q].dimesionBoss(g);
                boss[q].iconBoss(g);
            }
            for(int c = 0; c < bossProjetil.size();c++){
                Boss rec = bossProjetil.get(c);
                rec.dimesionTiro(g);
                rec.iconTiro(g);
            }
            
            for(int v = 0; v < bossLaser.size();v++){
                Boss rec = bossLaser.get(v);
                rec.dimesionLaser(g);
                rec.iconLaser(g);            
            }
        for(int b = 0; b < objExplosao.size();b++){
            ObjectGame rec = objExplosao.get(b);
            if(rec == null){
                objExplosao.remove(rec);
            }
            rec.explosaoDimension(g);            
            rec.iconExplosao(g);
            
        }
    }       
}
