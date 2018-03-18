import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;

public class BoutonListener implements ActionListener{
    
    private JPanel m;
    private AffichageMenu AM;
    private Menu menu;
    private int id;
    private Fenetre f = new Fenetre();
    private Historique h;
    private AffichageJeu aff;
    private Jeu j;
    public BoutonListener(int id, JPanel m, Fenetre f){
	this.id= id;
	this.m = m;
	this.f=f;
    }
    
    @Override
	public void actionPerformed(ActionEvent evenement){
	
	if( this.id == 1 ){ 
 

	    menu.setKomi(true);
	    menu.setHandicap(0);
	    JTextField nb = new JTextField(5);
	    nb = AM.getNb();
	    nb.setVisible(false);
	    JButton valider = new JButton("Valider");
	    valider = AM.getValider();
	    valider.setVisible(false);
	    AM.afficherParametreR(menu);
	    
	}else if( this.id == 2 ){
	    
	    JTextField nb = new JTextField(5);
	    nb = AM.getNb();
	    nb.setVisible(true);
	    JButton valider = new JButton("Valider");
	    valider = AM.getValider();
	    valider.setVisible(true);
	    menu.setKomi(false);	

	    AM.afficherParametreR(this.menu); 
	    
	    
	}else if(this.id == 3){
	    
	    int val = Integer.parseInt(AM.nb.getText());
	    if(val > 0 && val < 10){
		menu.setHandicap(val);
		AM.nb.setBackground(new Color(131,166,111));
	    }else{
		AM.nb.setBackground(new Color(255,100,100));
	    }
	    
	}else if(this.id == 4){
	   
	    menu.setModeTemps(1);
	    
	    JTextField minute = new JTextField(5);
	    JTextField seconde = new JTextField(5);
	    minute = AM.getMinute();
	    minute.setVisible(false);
	    seconde= AM.getSeconde();
	    seconde.setVisible(false);
	    JButton validerMS = new JButton("Valider");
	    validerMS = AM.getValiderMS();
	    validerMS.setVisible(false);
	    
	    AM.afficherParametreT(this.menu); 
	}else if(this.id == 5){
	    
	    menu.setModeTemps(2);
	    
	    JTextField minute = new JTextField(5);
	    JTextField seconde = new JTextField(5);
	    minute = AM.getMinute();
	    minute.setVisible(true);
	    seconde= AM.getSeconde();
	    seconde.setVisible(true);
	    JButton validerMS = new JButton("Valider");
	    validerMS = AM.getValiderMS();
	    validerMS.setVisible(true);
	    
	    AM.afficherParametreT(this.menu); 
	}else if(this.id == 6){
	   
	    menu.setModeTemps(3);
	    
	    JTextField minute = new JTextField(5);
	    JTextField seconde = new JTextField(5);
	    minute = AM.getMinute();
	    minute.setVisible(true);
	    seconde= AM.getSeconde();
	    seconde.setVisible(true);
	    JButton validerMS = new JButton("Valider");
	    validerMS = AM.getValiderMS();
	    validerMS.setVisible(true);
	    
	    AM.afficherParametreT(this.menu); 
	}else if(this.id == 7){
	    
	    menu.setTaille(9);
	    AM.afficherParametreS(this.menu); 
	}else if(this.id == 8){
	    menu.setTaille(13);
	    AM.afficherParametreS(this.menu); 
	}else if(this.id == 9){
	    menu.setTaille(19);
	    AM.afficherParametreS(this.menu); 
	}else if(this.id == 10){
	    
	    if(menu.getSize() > 8){
	    }else{
		menu.setTaille(13);
	    }	
	    
	    if(menu.getKomi() == true){
	    }else if(menu.getHandicap() > 0){
	    }else{
		menu.setKomi(true);
	    }		
	    
	    if(menu.getModeTemps() != 0){
	    }else{
		menu.setModeTemps(1);
	    }
	    
	    if(menu.getTemps() != 0){
	    }else{
		menu.setTemps(300);
	    }	
	    
	    if(menu.getSize() > 8 && menu.getModeTemps() != 0 && ((menu.getKomi() == true) || (menu.getHandicap() > 0))){
		m.setVisible(false);
		menu.start(f);
	    }
	}else if(this.id == 11){
	    f.dispose();
	}else if(this.id == 12){
	    
	    h.annulerCoup();
	    aff.repaint();
	    
	    
	}else if(this.id == 13){
	    
	    h.desannulerCoup();
	    aff.repaint();
	}else if(this.id == 14){
	    
	    if(j.getPasser() == false){
		j.switchj();	
		j.setPasser(true);
	    }else if(j.getPasser() == true){
		j.finDePartie(aff);
	    }
	    
	}else if(this.id==15){
	  
	    j.setAbandonner(true);
	    j.finDePartie(aff);
	    j.switchj();
	    j.setGagnant(j.getTour());
	    aff.repaint();
	}else if(this.id==16){
	   
	    j.verifT();
	    j.win();
	    aff.repaint();
	}else if(this.id==17){
	   
	    int minute = Integer.parseInt(AM.minute.getText());
	    int seconde = Integer.parseInt(AM.seconde.getText());
	    
	    if(minute >=0){
		AM.minute.setBackground(new Color(131,166,111));
	    }else{
		AM.minute.setBackground(new Color(255,100,100));
	    }
	    if(seconde >= 0){	
		AM.seconde.setBackground(new Color(131,166,111));
	    }else{
		AM.seconde.setBackground(new Color(255,100,100));
	    }
	    
	    if(seconde>=0 && minute >=0){
		seconde = seconde + minute*60;
	    }
	    
	    if(seconde > 0){
		menu.setTemps(seconde);
	    }else if(seconde <= 0){
		menu.setTemps(300); 
	    }else{
		menu.setTemps(300);
	    }
	    
	}
	
    }
    
    public void setAM(AffichageMenu AM, Menu menu){
	this.AM=AM;
	this.menu=menu;
    }
    
    public void setAJ(AffichageJeu aff, Historique h, Jeu j){
	this.h=h;
	this.aff=aff;
	this.j=j;
    }
    
    
}