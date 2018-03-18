import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;

public class Historique{
    
    private ArrayList<int[][]> historique;
    private ArrayList<Integer> historiqueScoreJ1;
    private ArrayList<Integer> historiqueScoreJ2;
    int pos;
    int posScoreJ1;
    int posScoreJ2;
    
    
    private Jeu j;
    
    
    public Historique(Jeu j){
	this.j=j;
    }
    
    public void initialiserHistorique(){
	
	this.historique = new ArrayList<int[][]>();
	pos = -1;
	
	this.historiqueScoreJ1 = new ArrayList<Integer>();
	this.historiqueScoreJ2 = new ArrayList<Integer>();
	
    }
    
    public void remplirHistorique(){	
	int[][] curg = j.getGoban();
	int[][] toAdd = new int[this.j.getSize()][this.j.getSize()];
	
	for (int x=0;x<j.getSize();x++){
	    for(int y=0;y<j.getSize();y++){
		toAdd[x][y]= curg[x][y];
	    }
	}
	
  
	while (this.historique.size() - 1 > pos){
            this.historique.remove(pos+1);
            this.historiqueScoreJ1.remove(pos+1);
            this.historiqueScoreJ2.remove(pos+1);
	}
	
	
	this.historique.add(toAdd);
	this.historiqueScoreJ1.add(j.getScoreJ1());
	this.historiqueScoreJ2.add(j.getScoreJ2());	
	
 	pos++;
		
	
    }
    
    public void annulerCoup(){
	if (pos > 0){
	    pos--;
	    j.switchj();
	    
	}
	
	

	j.setGoban(this.historique.get(pos));
	j.setScoreJ1(this.historiqueScoreJ1.get(pos));
	j.setScoreJ2(this.historiqueScoreJ2.get(pos));
	j.clearStop();
    }
    
    public void desannulerCoup(){
	if (pos < this.historique.size()-1){
	    pos++;
	    
	    j.switchj();
	}
	
	j.setGoban(this.historique.get(pos));
	j.setScoreJ1(this.historiqueScoreJ1.get(pos));
	j.setScoreJ2(this.historiqueScoreJ2.get(pos));
	j.clearStop();
    }  
    
    
    public boolean ko(){
	
	int compteur=0;
	int[][] gobact = j.getGoban();
	if(pos > 1){
	    int[][] gobPosM1 = this.historique.get(pos-1);
	    for (int x=0;x<j.getSize();x++){
		for(int y=0;y<j.getSize();y++){
		    if(gobact[x][y]== gobPosM1[x][y]){
			compteur++;
		    }
		}
	    }
	}else{
	    return false;
	}
	
	if(compteur == (j.getSize()*j.getSize())){
	    return true;
	}else{
	    return false;
	}
	
    }
    
    public boolean koInfini(){
	
	int compteur=0,compteur2=0;
	int[][] gobact = j.getGoban();
	if(pos > 10){
	    int[][] gobPosM1 = this.historique.get(pos-11);
	    int[][] gobPosM2 = this.historique.get(pos-5);
	    for (int x=0;x<j.getSize();x++){
		for(int y=0;y<j.getSize();y++){
		    if(gobact[x][y]== gobPosM1[x][y]){
			compteur++;
		    }
		    if(gobact[x][y] == gobPosM2[x][y]){
			compteur2++;
		    }
		}
	    }
	}else{
	    return false;
	}
	
	if((compteur == (j.getSize()*j.getSize())) && (compteur2 == (j.getSize()*j.getSize()))){
	    return true;
	}else{
	    return false;
	}
	
    }
    
    public void saveGoban(){

	j.setGoban(this.historique.get(pos));
	j.setScoreJ1(this.historiqueScoreJ1.get(pos));
	j.setScoreJ2(this.historiqueScoreJ2.get(pos));
    }
    
    
}