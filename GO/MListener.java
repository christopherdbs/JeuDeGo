import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;


public class MListener implements MouseListener{
    
    private Jeu j;   
    private AffichageJeu aff;
    private Historique h;	
    
    MListener(Jeu j, AffichageJeu aff, Historique h){
	this.j=j;
	this.aff=aff;
	this.h=h;
    }
    
    @Override
	
	public void mouseClicked(MouseEvent e) {
	int x= e.getX();
	int y= e.getY();
	int cx= j.CaseX(x);
	int cy=j.CaseY(y);
	int size = j.getSize();
	int handicap = j.getHandicap();
	if(j.partieFinie()==false){
	    if(cx>=0 && cy>=0 && cx <size && cy<size && j.coupValide(cx,cy)==true){
		
		
		j.poserPierre(cx,cy);
		j.clearLib();
		j.clearStop();
		j.prisePion(cx,cy);

		if(h.ko()== true || h.koInfini() == true ){
		    h.saveGoban();
		}else{
		    if (j.suicide(cx,cy)<=0){
			j.clearStop();

			j.enleverPierre(cx,cy);
			j.clearLib();
			
		    }else{
			
			j.addScore();
			h.remplirHistorique();
			j.setCoupJouer(true);
			j.verifPlein(aff);
			if(j.getTempsActJ2() > 0 && j.getTempsActJ1() == 0 && j.getTour() == 1){
			    j.stopByo();
			    j.timeAbsolu(aff);
			    j.setTempsActB(10);
			    j.setCoupJouer(false);
			}else if(j.getTempsActJ1() != 0&& j.getTempsActJ2() == 0 && j.getTour() == 2){
			    j.stopByo();
			    j.timeAbsolu(aff);
			    j.setTempsActB(10);
			    j.setCoupJouer(false);
			}else if(j.getTempsActJ1() == 0 && j.getTempsActJ2() == 0){
			    j.setCoupJouer(false);
			    j.setTempsActB(10);
			}
			j.setPasser(false);
			j.switchj();
		    }
		}
	    }
	    aff.repaint();	
	}else if(j.partieFinie()==true){
	    if(cx>=0 && cy>=0 && cx <size && cy<size){
		j.groupeMort(cx,cy);
	    }
	    aff.repaint();
	}
    }
    
    public void mouseExited(MouseEvent e){
	
    }
    
    public void mouseEntered(MouseEvent e){
	
    }
    
    public void mousePressed(MouseEvent e){
	
    }
    
    public void mouseReleased(MouseEvent e){
	
    }
    
    
}