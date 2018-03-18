import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AffichageJeu extends JPanel {
    private  boolean komi;
    private  int handicap = 0;
    private int modeTemps = 0;
    private int size =0;
    private Jeu j;
    private int[][] goban;
    private Fenetre f;
    private Historique h;
    private JLabel score1;
    private JLabel score2;
    private JLabel tempsJ1;
    private JLabel tempsJ2;
    private JLabel tempsByo; 
    private JLabel nbReserve1;
    private JLabel nbReserve2;
    private JButton finPriseGM;
    
    public AffichageJeu(Jeu j, Fenetre f, int[][] goban, boolean komi, int modeTemps, int handicap, int size, Historique h){
	this.j=j;
	this.f=f;
	this.goban=goban;     	
	this.komi=komi;
	this.handicap=handicap;
	this.size=size;
	this.modeTemps=modeTemps;
	this.h=h;
    }
    
    public void afficherJeu(){
	
	JButton retour = new JButton("annuler");
	JButton desannuler = new JButton("désannuler");
	JButton passer = new JButton("passer");
	JButton abandonner = new JButton("abandonner");
	finPriseGM = new JButton("Groupes morts retirés");
	score1 = new JLabel();
	score2 = new JLabel();
	tempsJ1 = new JLabel();
	tempsJ2 = new JLabel();
	tempsByo = new JLabel();
	nbReserve1 = new JLabel();
	nbReserve2 = new JLabel();
	
	this.add(score1);
	this.add(score2);
	this.add(retour);
	this.add(desannuler);
	this.add(passer);
	this.add(abandonner);
	this.add(tempsJ1);
	this.add(tempsJ2);
	this.add(tempsByo);
	this.add(nbReserve1);
	this.add(nbReserve2);
	this.add(finPriseGM);
	
	BoutonListener b12= new BoutonListener(12,this,this.f);
	BoutonListener b13= new BoutonListener(13,this,this.f);
	BoutonListener b14= new BoutonListener(14,this,this.f);
	BoutonListener b15= new BoutonListener(15,this,this.f);
	BoutonListener b16 = new BoutonListener(16,this,this.f);
	b12.setAJ(this,this.h, this.j);
	b13.setAJ(this,this.h, this.j);
	b14.setAJ(this,this.h, this.j);
	b15.setAJ(this,this.h, this.j);
	b16.setAJ(this,this.h, this.j);
	
	
	retour.addActionListener(b12);
	desannuler.addActionListener(b13);
	passer.addActionListener(b14);
	abandonner.addActionListener(b15);
	finPriseGM.addActionListener(b16);
	
	retour.setBounds(380,20,150,30);
	desannuler.setBounds(550,20,150,30);
	score1.setBounds(100,100,100,30);
	score2.setBounds(100,200,100,30);
	passer.setBounds(900,100,150,30);
	abandonner.setBounds(900,200,150,30);
	tempsJ1.setBounds(850,450,200,30);
	tempsJ2.setBounds(850,500,200,30);
	tempsByo.setBounds(850,550,220,30);
	nbReserve1.setBounds(850,600,200,30);
	nbReserve2.setBounds(850,650,200,30);
	finPriseGM.setBounds(30,500,200,30);
	
	
	finPriseGM.setVisible(false);
	
    }
    
    
    @Override
	public void paintComponent(Graphics pinceau){
	
	super.paintComponent(pinceau);	
	afficherGoban(pinceau);
	afficherHoshi(pinceau);
	afficherPierrePose(pinceau);
	afficherScore(pinceau);
	if(modeTemps != 1){
	    afficherTemps(pinceau);
	}
	if(j.getGagnant()!= 0){
	    afficherGagnant(pinceau);
	}

    }
    
    public void afficherGoban(Graphics pinceau){
	pinceau.setColor(Color.BLACK);
	int i,k,ecart=360,l=this.size;
	if(size==9){
	    ecart = 420;
	}else if(size==13){
	    ecart = 360;
	}else if(size==19){
	    ecart = 270;
	}
	for(i=0;i<l;i++){
	    pinceau.drawLine(i*30+ecart,100,i*30+ecart,100+((l-1)*30));
	}
	for(k=0;k<l;k++){
	    pinceau.drawLine(ecart,k*30+100,ecart+((l-1)*30),k*30+100);
	}	
	
    }	
    
    
    public void afficherHoshi(Graphics pinceau){
	int ecart=0,nbe=0;
	if(size==9){
            ecart=416;
	    nbe=2;
	    int k,i,j = nbe;
	    int nby=nbe;
	    pinceau.fillOval(536,96+4*30,8,8);
	    for(k=0;k<2;k++){
		for(i=0;i<2;i++){
		    pinceau.fillOval(nbe*30+ecart,96+nby*30,8,8);
		    nbe=nbe+(2*j);
		    
		}
		
		nbe = j;
		nby=nby+(2*j);
		
	    }
	    
        }else if(size==13){
            ecart = 356;
	    nbe=3;
	    
	    int k,i,j = nbe;
	    int nby=nbe;
	    pinceau.fillOval(536,96+6*30,8,8);
	    for(k=0;k<2;k++){
		for(i=0;i<2;i++){
		    pinceau.fillOval(nbe*30+ecart,96+nby*30,8,8);
		    nbe=nbe+(2*j);
		    
		}
		
		nbe = j;
		nby=nby+(2*j);
		
	    }
	    
	}else if(size==19){
	    
	    ecart = 266;
	    nbe=3;
	    
	    int k,i,j = nbe;
	    int nby=nbe;
	    
	    for(k=0;k<3;k++){
		for(i=0;i<3;i++){
		    pinceau.fillOval(nbe*30+ecart,96+nby*30,10,10);
		    nbe=nbe+(2*j);
		    
		}
		nbe = j;
		nby=nby+(2*j);
	    }
	    
	    
    	}
	
    }
    
    public void afficherPierrePose(Graphics p){
	
	Image i1 = Toolkit.getDefaultToolkit().getImage("noir.png");
	Image i2 = Toolkit.getDefaultToolkit().getImage("blanc.png");
	int x=0,y=0;	
	if(size==9){
	    x=405;
	}else if(size==13){
	    x=345;
	}else if(size==19){
	    x=255;
	}
	int i=0,j=0;
	for(i=0;i<size;i++){
	    for(j=0;j<size;j++){
		if(this.goban[i][j] == 1){
		    p.drawImage(i1,i*30+x,j*30+85,this);
		}else if(this.goban[i][j] == 2){
		    p.drawImage(i2,i*30+x,j*30+85,this);
		}
		
	    }
	}
	
    }
    
    
    
    public void afficherScore(Graphics p){
	
	score1.setText("Score 1 :" + j.getScoreJ1());
	score2.setText("Score 2 :" + j.getScoreJ2());
    }
    
    public void afficherTemps(Graphics p){
	
	tempsJ1.setText("Temps Joueur 1 : "+j.getTempsActJ1()); 
	tempsJ2.setText("Temps Joueur 2 : "+j.getTempsActJ2()); 
	if(modeTemps == 3){
	    
	    tempsByo.setText("Temps Byo-Yomi Joueur "+j.getTour()+" : "+j.getTempsActB()); 
	    nbReserve1.setText(" Reserve Joueur 1: "+j.getNbR1()); 
	    nbReserve2.setText(" Reserve Joueur 2 : "+j.getNbR2()); 
	    
	}
	
    }
    
    public void afficherGagnant(Graphics p){
	this.setVisible(false);
	
	JPanel sortie = new JPanel();
	sortie.setBackground(Color.WHITE);
	sortie.setLocation(0,0);
	sortie.setLayout(null);
	this.f.add(sortie);
	JLabel phrase = new JLabel();
	sortie.add(phrase);
	int scoreGagnant=0;
	double score2=0;
	if(j.getGagnant() == 1){
	    scoreGagnant = j.getScoreJ1();
	}else if(j.getGagnant() == 2){
	    scoreGagnant = j.getScoreJ2();
	    if(this.komi == true){
		score2 = (double) scoreGagnant + 7.5;
	    }
	}
	

	if(j.getAbandonner() == true){
	    phrase.setText("Le joueur " + j.getTour()+" a gagné");
	}else{
	    
	    if((j.getGagnant() == 1) || ((j.getGagnant() == 2) && (j.getKomi() == false))){
		phrase.setText("Le joueur " + j.getTour()+" a gagné avec le score de "+ scoreGagnant );
	    }else if((j.getGagnant()== 2) && (j.getKomi() == true)){
		phrase.setText("Le joueur 2 a gagné avec le score de "+ score2 );
	    }else{
		phrase.setText(" Egalité ");
	    }
	}
	phrase.setBounds(400,360,350,50);
	
    }
    
    public void afficherBouton(){
	if(j.partieFinie()==true){
	    this.finPriseGM.setVisible(true);
	}
	
    }
    
}