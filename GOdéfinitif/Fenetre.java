import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.*;
import java.util.*;
import java.awt.event.*;

public class Fenetre extends JFrame{
    
    public static Fenetre f = new Fenetre();
    
    
    public Fenetre(){
     	this.setSize(1080,720);
	this.setLocation(20,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    }
    
    
    public static void main(String[] args){
	
	
	JPanel MainPanel = new JPanel();
	
	AffichageMenu m = new AffichageMenu();
	Menu menu = new Menu();
	f.add(MainPanel,BorderLayout.CENTER);
	
	m.Afficher(f,MainPanel,menu);
	f.setVisible(true);
	
    }
    
    
    
}