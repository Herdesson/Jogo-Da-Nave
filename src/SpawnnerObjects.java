
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
    public ArrayList<ObjectGame[]> obj = new ArrayList<ObjectGame[]>();
    public ArrayList<ObjectGame> objAstPeq = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objAstMed = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objAstGra = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objBomba = new ArrayList<ObjectGame>();
    public ArrayList<ObjectGame> objDisco = new ArrayList<ObjectGame>();       
    Boss[] boss = new Boss[50];
    public ArrayList<Boss> bossProjetil = new ArrayList<Boss>();
    public ArrayList<Boss> bossLaser = new ArrayList<Boss>();
    public ArrayList<Nave> projetil = new ArrayList<Nave>();
    public Nave getNave = new Nave();
    public boolean disparouProjetil = false;
    boolean teste = true,teste2 = true;
    boolean sec,sec2;
    private Timer timer;
    int timmer,timmer2;
    int limit, limit2 = 0, limit3;
    int height = 600, widht = 500;
    public int projetNaveX, projetNaveY,newProjetX,newProjetY;
    boolean verf1 = false, verf2 = false, verf3 = false,verf4 = false, verf5 = false;
    int bossX = 40,bossY = -150,astPeqX = 30, astPeqY = -40,astPeqX2 = 30, astPeqY2 = -40, laserX = -159, laserY  = -120
            , astMedX = 70, astMedY = -70, astGraX = 120, astGraY = -120, bombX = 200, bombY = -190
            , discX = -50, discY = 170;  
    public void tick(){
        timmer++;
        if(timmer % 40 == 0){            
            limit = (int)(Math.random()*4);
            System.out.println(limit);
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
            }if(timmer % 80 == 0){
                //4220
                limit3 = 1;
            }if(limit3 == 1){
                verf5 = true;
            }if(verf5){
                bossX = 40;
                bossY = -30;
                boss[0].setPositionX(bossX);
                boss[0].setPositionY(bossY);               
                if(timmer % 30 == 0){                  
                    bossProjetil.add(new Boss(105+boss[0].getPositionX(), 100+boss[0].getPositionY()));
                    bossProjetil.add(new Boss(265+boss[0].getPositionX(), 100+boss[0].getPositionY()));
                }if(timmer % 180 == 0){
                    bossLaser.add(new Boss(120+bossX, 150+bossY)); 
                    sec = true;
                }
                //if(sec){
                    //timmer2++;
                    //System.out.println("::"+timmer2++);
                //}
                bossAtirarProjetil();
                BossAtirarLaser();
            }if(disparouProjetil){
                newProjetX = projetNaveX;
                newProjetY = projetNaveY;
                projetil.add(new Nave(getNave.poInicialNaveX,getNave.poInicialNaveY));
                System.out.println(getNave.poInicialNaveX);
                disparouProjetil = false; 
            }atirarProjetil();
    }
    public void BossAtirarLaser(){
        for(int i = 0;i < bossLaser.size();i++){            
            Boss laserBoss = bossLaser.get(i);
            if(sec){
            timer = new Timer();
            final long tempo = (2000*2);
            TimerTask remove = new TimerTask(){                      
                @Override
                public void run() {
                    bossLaser.remove(laserBoss);
                    System.out.println("excluiu");
                }
            };
            timer.schedule(remove, tempo);
            sec = false;
            }
                
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
        for(int a = 0; a < projetil.size();a++){
            Nave rec = projetil.get(a);
            rec.dimensionTiro(g);
            rec.iconTiro(g);
        }                        
    }
    
}
