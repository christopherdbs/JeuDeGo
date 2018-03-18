import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.*;
import java.util.*;

public class Menu{
    
    private  boolean komi;
    private  int handicap = 0;
    private int modeTemps = 0;
    private int size =0;
    private int temps=0;
    
    public Menu(){
    }
    
    public boolean getKomi(){
	return this.komi;
    }
    
    public int getHandicap(){
	return this.handicap;
    }
    
    public int getSize(){
	return this.size;
    }
    
    public int getModeTemps(){
	return this.modeTemps;
    }
    
    public int getTemps(){
	return this.temps;
    }
    
    public void setKomi(boolean komi){
	this.komi=komi;
    }
    
    public void setHandicap(int nbh){
	this.handicap = nbh;
    }
    
    
    public void setModeTemps(int nb){
	this.modeTemps = nb;
    }
    
    public void setTaille(int nb){
	this.size = nb;
    }
    
    public void setTemps(int nb){
	this.temps = nb;
    }
    
    public void start(Fenetre f){

	Jeu j = new Jeu(this.komi, this.handicap, this.modeTemps, this.size,this.temps,f);
	j.jouer();
    }
    
    
    
}