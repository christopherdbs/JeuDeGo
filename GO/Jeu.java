import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.*;
import java.math.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Jeu{
    private boolean[][] stop;
    private  boolean komi;
    private  int handicap = 0;
    private int modeTemps = 0;
    private int size =0;
    private int temps = 0;
    private int[][] goban;
    private int tour=1;
    private int scorej1 = 0;
    private int scorej2 = 0;
    private int lib=0;
    private int compte=0;
    private boolean passer=false;
    private boolean abandonner;
    private boolean coupJouer;
    private int tempsActJ1=0;
    private int tempsActJ2=0;
    private int tempsActB=30;
    private int nbR1=5;
    private int nbR2=5;
    private Fenetre f;
    private int gagnant;
    private Timer timerAbs;
    private Timer timerByo;
    private boolean fini=false;
    private boolean tabT[][];
    private int ter=0;
    private boolean prise=false;
    
    public Jeu(boolean komi, int handicap, int modeTemps, int size,int temps, Fenetre f){
     	this.komi=komi;
	this.handicap=handicap;
	this.modeTemps=modeTemps;
	this.size=size;
	this.f=f;
	this.temps=temps;
    }
    
    public void jouer(){
	this.goban= new int[size][size];
        this.stop= new boolean[size][size];
	this.tabT = new boolean[size][size];
	if(this.handicap != 0){
	    setPierre();
	}
	Historique h = new Historique(this);
	h.initialiserHistorique();
	h.remplirHistorique();
	this.tempsActJ1 = this.temps;
	this.tempsActJ2 = this.temps;
	AffichageJeu affjeu = new AffichageJeu(this, this.f,goban,this.komi,this.modeTemps,this.handicap,this.size,h);
	choixTemps(affjeu);
	affjeu.setBackground(Color.WHITE);
	affjeu.setLocation(0,0);
	affjeu.setLayout(null);
	this.f.add(affjeu);
	affjeu.afficherJeu();
	MListener m1 = new MListener(this,affjeu,h);
	affjeu.addMouseListener(m1);
	
	
    }
    
    
    
    
    public void enleverPierre(int cx, int cy){
	this.goban[cx][cy]=0;
	
    }
    
    
    
    public void poserPierre(int cx, int cy){


	if(this.size==9){
	    if(cx>=0 && cy>=0 && cx <9 && cy<9){
		this.goban[cx][cy]=this.tour;
	    }
	}else if(this.size==13){
	    if(cx>=0 && cy>=0 && cx <13 && cy<13){
		this.goban[cx][cy]=this.tour;
	    }
	}else if(this.size==19){
	    if(cx>=0 && cy>=0 && cx <19 && cy<19){
		this.goban[cx][cy]=this.tour;
	    }
	    
	}
	
    }
    
    public int CaseX(int x){
	if(this.size==9){
	    x = x- 405;
	}else if(this.size==13){
	    x = x- 345;
	}else if(this.size==19){
	    x = x-255;
	}
	x = x/30;
	return x;
	
    }
    
    public int CaseY(int y){
	if(this.size==9){
	    
	    y = y - 85;
	}else if(this.size==13){
	    
	    y = y - 85;
	}else if(this.size==19){
	    
	    y = y - 85;
	}
	
	y = y/30;
	return y;
	
    }
    
    public boolean coupValide(int cx, int cy){
	
	if(this.goban[cx][cy]==0){
	    return true;
	}else{
	    return false;
	}
	
    }
    
    public void switchj(){
	if(this.tour==1){
	    this.tour = 2;
	}else if(this.tour==2){
	    this.tour=1;
	}
    }
    
    public void addScore(){
	
	if(this.tour==1){
	    scorej1 += 1;
	}else if(this.tour==2){ 
	    scorej2 += 1;
	}
    }	
    
    public void baisserScore(){
	if(this.tour==1){
	    if(this.scorej1 > 0){
		scorej1 -= 1;
	    }
	}else if(this.tour==2){ 
	    if(this.scorej2 > 0){
		scorej2 -= 1;
	    }
	}
    }
    
    public int getTour(){
	return this.tour;
    }
    
    public int[][] getGoban(){
	return this.goban;
    }
    
    public boolean getKomi(){
	return this.komi;
    }
    
    public int getSize(){
	return this.size;
    }
    
    public int getHandicap(){
	return this.handicap;
    }
    
    public int getModeTemps(){
	return this.modeTemps;
    }	
    
    public int getScoreJ1(){
	return this.scorej1;
    }
    
    public int getScoreJ2(){
	return this.scorej2;
    }
    
    public void setScoreJ1(int ScoreJ1){
	this.scorej1=ScoreJ1;
    }
    
    public void setScoreJ2(int ScoreJ2){
	this.scorej2=ScoreJ2;
    }
    
    
    
    public int getCompte(){
	return this.compte;
    }
    
    public void resetCompte(){
	this.compte=0;
    }
    
    public void setHandicap(int handicap){
	this.handicap = handicap;
    }
    
    public void setGoban(int[][] goban){
	int x,y;
	for(x=0;x<this.size;x++){
	    for(y=0;y<this.size;y++){
		
		this.goban[x][y]=goban[x][y];
	    }
	}
	
    }
    
    public boolean getCoupJouer(){
	return this.coupJouer;
    }
    
    public void setCoupJouer(boolean coupJouer){
	this.coupJouer = coupJouer;
    }
    
    public int getTempsActJ1(){
	return this.tempsActJ1;
    }
    
    public void setTempsActJ1(int tempsActJ1){
	this.tempsActJ1 = tempsActJ1;
    } 
    
    public int getTempsActJ2(){
	return this.tempsActJ2;
    }
    
    public void setTempsActJ2(int tempsActJ2){
	this.tempsActJ2 = tempsActJ2;
    } 
    
    public int getTempsActB(){
	return this.tempsActB;
    }
    
    public void setTempsActB(int tempsActB){
	this.tempsActB = tempsActB;
    } 
    
    public int getNbR1(){
	return this.nbR1;
    }
    
    public void setNbR1(int nbR1){
	this.nbR1= nbR1;
    } 
    
    public int getNbR2(){
	return this.nbR2;
    }
    
    public void setNbR2(int nbR2){
	this.nbR2= nbR2;
    } 
    
    public int getGagnant(){
	return this.gagnant;
    }
    
    public void setGagnant(int gagnant){
	this.gagnant = gagnant;
    }
    
    public void stopAbs(){
	this.timerAbs.stop();
    }
    public void stopByo(){
	this.timerByo.stop();
    }
    
    public void startByo(){
	this.timerByo.restart();
    }
    
    public void startAbs(){
	this.timerAbs.restart();
    }
    
    public void setFini(boolean fini){
	this.fini=fini;
    }
    
    public void setPrise(boolean f){
	this.prise=f;
    } 
    
    public void setAbandonner(boolean a){
	this.abandonner=a;
    }
    
    public boolean getAbandonner(){
	return this.abandonner;
    }
    
    public void Abs(){
	this.timerAbs.start();
    }
    
    public void prisePion(int x,int y){
	int i,j, tmp;
	int[][] tab=getGoban();
	boolean tmps;
	
	for(i=-1;i<2;i++){
	    for(j=-1;j<2;j++){
		if((i==j) ||(i==1 && j==-1)||(i==-1 && j==1)){
		    //rien
		}else{
		    
		    tmps=verifTab(x+i,y+j);
		    if(tmps==false){
			//rien                                                                  
		    }else{
			
			if (this.stop[x+i][y+j]==true){
			    //rien          
			}else{
			    
			    if(tab[x][y]!=tab[x+i][y+j] && tab[x+i][y+j]!=0){
				this.stop[x][y]=true;
				tmp=attaque(x+i,y+j);
				clearLib();
				if(tmp<=0){
				    this.stop[x][y]=false;
				    destruction();
				    
				}
			    }
			    clearStop();
			}
		    }
		}
	    }
	}
	
	
	
    }
    
    
    public int suicide(int x,int y){
	
	int i,j;
	int[][] tab = this.getGoban();
	for(i=-1;i<2;i++){
	    for(j=-1;j<2;j++){
		if((i==j) ||(i==1 && j==-1)||(i==-1 && j==1)){
		    //rien
		}else{
		    boolean tmp=verifTab(x+i,y+j);
		    if(tmp==false){
			//rien
		    }else{

			if (this.stop[x+i][y+j]==true){
			    this.stop[x][y]=true;
			    if(tab[x+i][y+j]==0){
				//this.lib++;
			    }else{
				//this.lib--;
			    }
			}else{

			    this.stop[x][y]=true;
			    if(tab[x+i][y+j]==0){
				this.lib++;
				this.stop[x+i][y+j]=true;
			    }else if(tab[x+i][y+j]==getTour()){
				this.lib = suicide(x+i,y+j);
			    }else if(tab[x+i][y+j]!=getTour()){
				//this.lib--;
			    }
			    
			}
		    }
		}
	    }
	}



	if(this.lib<=0){
	    clearLib();
	    return 0;
	}else{
	    return this.lib;
	}
    }
    
    public int  attaque(int x,int y){
        int i,j;
	int[][] tab=getGoban();
        for(i=-1;i<2;i++){
	    for(j=-1;j<2;j++){
                if((i==j) ||(i==1 && j==-1)||(i==-1 && j==1)){
		    
                }else{
                    boolean tmp=verifTab(x+i,y+j);
                    if(tmp==false){
			//rien                                                                             
                    }else{

                        if (this.stop[x+i][y+j]==true){
			    this.stop[x][y]=true;
                            if(tab[x+i][y+j]==0){
				//this.lib++;
			    }else{
				//this.lib--;
			    }                                                                        
			}else{

                            this.stop[x][y]=true;
			    if(tab[x+i][y+j]==0){
				this.stop[x+i][y+j]=true;
				this.lib++;
			    }else if(tab[x+i][y+j]!=getTour()){
				this.lib = attaque(x+i,y+j);
			    }else if(tab[x+i][y+j]==getTour()){
				//this.lib--;
			    }
			    
			}
		    }
                }
            }
	}
        if(this.lib<=0){ 
	    clearLib();
            return 0;
        }else{
            return this.lib;
        }
    }
    
    public void clearLib(){
	this.lib=0;
    }
    
    public boolean verifTab (int x,int y){
	int tmp=getSize();
	if(x<0 || y<0 || x>=tmp || y>=tmp){
	    return false;
	}else{
	    return true;
	}
    }
    
    
    public void clearStop(){
	int i,j;
	for(i=0;i<getSize();i++){
	    for(j=0;j<getSize();j++){
		this.stop[i][j]=false;
	    }
	}
    }
    
    public void destruction(){
	int i,j;
	for(i=0;i<getSize();i++){
	    for(j=0;j<getSize();j++){
		if(this.stop[i][j]==true){

		    enleverPierre(i,j);
		    addScore();
		    switchj();
		    baisserScore();
		    switchj();
		}
	    }
	}
	
    }
    
    public boolean partieFinie(){
	if(this.fini==true){
	    return true;
	}else{
	    return false;
	}
    }   
    
    
    
    public void groupeMort(int x, int y){
	this.clearStop();
	this.clearLib();

	if(this.goban[x][y]==1){
	    this.tour=1;
	}else if(this.goban[x][y]==2){
	    this.tour=2;
	}else{
	    this.tour=0;
	}

	if(this.getTour()!=0){
	    if(this.suicide(x,y) == 1){


		switchj();
		this.destruction();
		baisserScore();
		switchj();
		addScore();
	    }
	}
	this.clearStop();
	this.clearLib();
    	
    }
    
    public void verifPlein(AffichageJeu aff){
	
	int i,j;
	int[][] tab= getGoban();
	for(i=0;i<getSize();i++){
	    for(j=0;j<getSize();j++){
		if(tab[i][j] == 0){
		    return;
		}
	    }
	}
	this.finDePartie(aff);
    }
    
    public void setPasser(boolean x){
	this.passer = x;
    }
    
    public boolean getPasser(){
 	return this.passer;
    }
    
    
    public void choixTemps(AffichageJeu affjeu){
	
	if(this.modeTemps == 1){
	    
	}else if(this.modeTemps == 2){
	    timeAbsolu(affjeu);
	}else if(this.modeTemps == 3){
	    ByoYomi(affjeu);
	}
	
    }
    
    public void timeAbsolu(AffichageJeu affjeu){
	int tps=1000;
	
	TimeListener t = new TimeListener(1,this,affjeu);
	timerAbs = new Timer(tps,t); 
	
	
	timerAbs.start();
    }
    
    public void ByoYomi(AffichageJeu affjeu){
	int tps=1000;
	
	TimeListener t2 = new TimeListener(2,this,affjeu);
	timerByo = new Timer(tps,t2); 
	timeAbsolu(affjeu);
	
	
    }
    
    
    
    
    public void pointT(){
	int i,j,tmp=getTour();
	for(i=0;i<getSize();i++){
	    for(j=0;j<getSize();j++){
		if(this.stop[i][j]==true && this.ter!=3){
		    enleverPierre(i,j);
		    if(this.ter==1){
			this.tour=1;
			addScore();
			switchj();
			baisserScore();
		    }else if(this.ter==2){
			this.tour=2;
			addScore();
			switchj();
			baisserScore();
			switchj();
		    }

		}
	    }
	}
	clearStop();
	this.ter=0;
	this.tour=tmp;
    }
    
    
    public void verifT(){
	int i,j;	
	int[][]tab=getGoban();
	for(i=0;i<getSize();i++){
	    for(j=0;j<getSize();j++){
		if(tab[i][j]!=0){
		    this.tabT[i][j]=true;
		}else if(this.tabT[i][j]==false){
		    calculT(i,j);
		    pointT();
		}
		
	    }
	}
    }
    
    
    public void calculT(int x,int y){
	int[][] tab=getGoban();
	int i,j;
	if(this.ter==1){
	    if(tab[x][y]==2){
		this.ter+=2;
	    }
	}else if(this.ter==2){
	    if(tab[x][y]==1){	    
		this.ter+=1;
	    }
	}else if(this.ter==0){
	    if(tab[x][y]==1){
		this.ter=1;
	    }else if(tab[x][y]==2){
		this.ter=2;
	    }
	}else if(this.ter==3){
	    
	}
	

	this.tabT[x][y]=true;
	if(tab[x][y]==0){
	    for(i=-1;i<2;i++){
		for(j=-1;j<2;j++){
		    if((i==j) ||(i==1 && j==-1)||(i==-1 && j==1)){
		    }else{
			boolean tmp=verifTab(x+i,y+j);
			if(tmp==false){
			    //rien   
			}else{
			    if (this.tabT[x+i][y+j]==true){
				this.stop[x][y]=true;
				if(tab[x+i][y+j]==0){
				    //this.lib++;
				}else{
				    calculT(x+i,y+j);
				}
			    }else{
				this.stop[x][y]=true;
				calculT(x+i,y+j);	    
			    }
			}
		    }
		}
	    }
	}
	return;
    }
    
    
    
    
    public void setPierre(){
	int tmp=this.getSize();
	if(tmp==9){
	    if(this.handicap==1){
		poserPierre(5,5);
	    }else if(this.handicap==2){
		poserPierre(2,2);
		poserPierre(6,6);
	    }else if(this.handicap==3){
		poserPierre(2,2);
		poserPierre(6,6);
		poserPierre(4,4);
	    }else if(this.handicap==4){
		poserPierre(2,2);
		poserPierre(6,6);
		poserPierre(6,2);
		poserPierre(2,6);
	    }else if(this.handicap==5){
		poserPierre(2,2);
		poserPierre(6,6);
		poserPierre(6,2);
		poserPierre(2,6);
		poserPierre(4,4);
	    }
	    
	}else if(tmp==13){
	    if(this.handicap==1){
		poserPierre(6,6);
	    }else if(this.handicap==2){
		poserPierre(3,3);
		poserPierre(9,9);	
	    }else if(this.handicap==3){
		poserPierre(9,9);
		poserPierre(6,6);
		poserPierre(3,3);
	    }else if(this.handicap==4){
		poserPierre(3,3);
		poserPierre(9,9);
		poserPierre(9,3);
		poserPierre(3,9);
	    }else if(this.handicap==5){
		poserPierre(3,3);
		poserPierre(9,9);
		poserPierre(9,3);
		poserPierre(3,9);
		poserPierre(6,6);
	    }
	    
	}else if(tmp==19){
	    if(this.handicap==1){
		poserPierre(9,9);
	    }else if(this.handicap==2){
		poserPierre(3,3);
		poserPierre(15,15);
	    }else if(this.handicap==3){
		poserPierre(15,15);
		poserPierre(9,9);
		poserPierre(3,3);
	    }else if(this.handicap==4){
		poserPierre(3,3);
		poserPierre(15,15);
		poserPierre(15,3);
		poserPierre(3,15);
	    }else if(this.handicap==5){
		poserPierre(3,3);
		poserPierre(15,15);
		poserPierre(15,3);
		poserPierre(3,15);
		poserPierre(9,9);
	    }else if(this.handicap==6){
		poserPierre(3,3);
		poserPierre(3,9);
		poserPierre(3,15);
		poserPierre(15,3);
		poserPierre(15,9);
		poserPierre(15,15);
	    }else if(this.handicap==7){
		poserPierre(3,3);
		poserPierre(3,9);
		poserPierre(3,15);
		poserPierre(15,3);
		poserPierre(15,9);
		poserPierre(15,15);
		poserPierre(9,9);
	    }else if(this.handicap==8){
		poserPierre(3,3);
		poserPierre(3,9);
		poserPierre(3,15);
		poserPierre(15,3);
		poserPierre(15,9);
		poserPierre(15,15);
		poserPierre(9,3);
		poserPierre(9,15);
	    }else if(this.handicap==9){
		poserPierre(3,3);
		poserPierre(3,9);
		poserPierre(3,15);
		poserPierre(15,3);
		poserPierre(15,9);
		poserPierre(15,15);
		poserPierre(9,3);
		poserPierre(9,15);
		poserPierre(9,9);
	    }
	    
	    
	}
	
	for(int i = 0; i<this.handicap;i++){
	    addScore();
	}
	
    }
    
    
    public void finDePartie(AffichageJeu aff){
	
	setFini(true);
	if(this.modeTemps != 1){
	    stopAbs();
	}
	if(this.modeTemps == 3){
	    stopByo();
	}
	if(this.abandonner == false){
	    aff.afficherBouton();
	}
	
    }
    
    public void win(){
	
	int s1 = getScoreJ1();
	int s2 = getScoreJ2();
	double score1 = (double) s1;
	double score2 = (double) s2;

	if(this.komi==true){
	    score2 += 7.5;
	    
	}

	if(score1 > score2){
	    setGagnant(1);
	}else if (score1 < score2){
	    setGagnant(2);
	}else if (score1 == score2){
	    setGagnant(0);
	}
	
    }
    
}