import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;

public class TimeListener implements ActionListener{
    
    private Jeu j;
    private int id=0;
    private AffichageJeu aff;
    
    public TimeListener(int id,Jeu j,AffichageJeu aff){
	this.j=j;
	this.id=id;
	this.aff=aff;
    }
    
    @Override
	public void actionPerformed(ActionEvent e){
	if(id == 1){
	    
	    
	    if (j.getTempsActJ1() != 0 && j.getCoupJouer() == false && j.getTour() == 1){

		j.setTempsActJ1(j.getTempsActJ1()-1);
	    }else if (j.getTempsActJ2() != 0 && j.getCoupJouer() == false && j.getTour() == 2){

		j.setTempsActJ2(j.getTempsActJ2()-1);
	    }else if(j.getTempsActJ1() !=0 && j.getCoupJouer() == true && j.getTour() == 2) { 
		if(j.getModeTemps() == 2){
		    
		}else if(j.getModeTemps() == 3){
		    if(j.getTempsActJ2() == 0){
			j.stopAbs();
			j.startByo();
		    }
		}
		j.setCoupJouer(false);
	    }else if(j.getTempsActJ2() !=0 && j.getCoupJouer() == true && j.getTour() == 1) { 
		if(j.getModeTemps() == 2){
		    
		}else if(j.getModeTemps() == 3){
		    if(j.getTempsActJ1() == 0){
			j.stopAbs();
			j.startByo();
		    }
		}
		
		j.setCoupJouer(false);
	    }else if(j.getTempsActJ1() == 0 && j.getTour() == 1){
		if(j.getModeTemps()==2){ 					
		    j.stopAbs();
		    j.finDePartie(aff);
		}else if(j.getModeTemps()==3){
		    j.stopAbs();
		    j.startByo();
		    
		}
		
	    }else if(j.getTempsActJ2() == 0 && j.getTour() == 2){
		if(j.getModeTemps()==2){ 					
		    j.stopAbs();
		    j.finDePartie(aff);
		}else if(j.getModeTemps()==3){
		    j.stopAbs();
		    j.startByo();
		}
		
	    }
	}else if (id == 2){
	    
	    
	    if(j.getTour() == 1){
		if(j.getTempsActB() != 0 && j.getCoupJouer() == false && j.getNbR1() != 0){
		    j.setTempsActB(j.getTempsActB()-1);
		}else if(j.getTempsActB()==0 && j.getNbR1() > 1){
		    j.setNbR1(j.getNbR1()-1);
		    j.setTempsActB(10);
		}else if(j.getTempsActB()==0 && j.getNbR1() == 1){
		    j.setNbR1(j.getNbR1()-1);
		    j.stopAbs();
		    j.stopByo();
		    j.finDePartie(aff);
		    
		}
	    }else if(j.getTour() == 2){

		if(j.getTempsActB() != 0 && j.getCoupJouer() == false && j.getNbR2() != 0){
		    j.setTempsActB(j.getTempsActB()-1);
		}else if(j.getTempsActB()==0 && j.getNbR2() > 1){
		    j.setNbR2(j.getNbR2()-1);
		    j.setTempsActB(10);
		}else if(j.getTempsActB()==0 && j.getNbR2() == 1){
		    j.setNbR2(j.getNbR2()-1);
		    j.stopAbs();
		    j.stopByo();
		    j.finDePartie(aff);
		    
		}
	    }	
	    
	}
	
	aff.repaint();			
    }
    
    
}