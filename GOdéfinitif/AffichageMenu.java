import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

public class AffichageMenu {
    public JButton valider = new JButton("Valider");
    public JButton validerMS = new JButton("Valider");
    public JTextField nb = new JTextField(5) ;
    public JTextField taille = new JTextField(5) ;
    public JTextField seconde = new JTextField(5) ;
    public JTextField minute = new JTextField(5) ;
    public JLabel choixR= new JLabel(" ") ;
    public JLabel choixT= new JLabel(" ") ;
    public JLabel choixS= new JLabel(" ") ;	
    
    
    public AffichageMenu(){
    }
    public void Afficher(Fenetre f,JPanel MainPanel, Menu menu){
	
	JButton komi = new JButton("Komi");
	JButton handicap = new JButton("Handicap");
	JButton ha = new JButton("Horloge Absolue");
	JButton notime = new JButton("Pas de temps");
	JButton by = new JButton("Byo-Yomi");
	JButton t1 = new JButton("9x9");
	JButton t2 = new JButton("13x13");
	JButton t3 = new JButton("19x19");
	JButton start = new JButton("Commencer la partie");
	JButton quitter = new JButton("Quitter le jeu");
	JLabel tg = new JLabel("Taille Goban");
	JLabel regle = new JLabel("Regle de Jeu");
	JLabel modeTemps = new JLabel("Mode Temps");
	
	
	MainPanel.add(komi);
	MainPanel.add(handicap);
	MainPanel.add(ha);
	MainPanel.add(notime);
	MainPanel.add(by);
	MainPanel.add(tg);
	MainPanel.add(t1);
	MainPanel.add(t2);
	MainPanel.add(t3);
	MainPanel.add(start);
	MainPanel.add(quitter);
	MainPanel.add(regle);
	MainPanel.add(modeTemps);
	MainPanel.setLayout(null);
	MainPanel.add(valider);
	MainPanel.add(nb);
	MainPanel.add(choixR);
	MainPanel.add(choixT);
	MainPanel.add(choixS);
	MainPanel.add(minute);
	MainPanel.add(seconde);
	MainPanel.add(validerMS);
	
	regle.setBounds(490,270,200,30);
	komi.setBounds(340,300,200,30);
	handicap.setBounds(540,300,200,30);
	modeTemps.setBounds(490,340,200,30);
	notime.setBounds(240,370,200,30);
	ha.setBounds(440,370,200,30);
	by.setBounds(640,370,200,30);
	tg.setBounds(490,410,200,30);
	t1.setBounds(240,440,200,30);
	t2.setBounds(440,440,200,30);
	t3.setBounds(640,440,200,30);
	start.setBounds(440,510,200,30);
	quitter.setBounds(440,580,200,30);
	nb.setBounds(750,300,50,30);
	valider.setBounds(810,300,100,30);
	minute.setBounds(850,370,50,30);
	validerMS.setBounds(970,370,100,30);
	seconde.setBounds(910,370,50,30);
	choixR.setBounds(590,270,200,30);
	choixT.setBounds(590,340,200,30);
	choixS.setBounds(590,410,200,30);
	nb.setVisible(false);
	valider.setVisible(false);
	minute.setVisible(false);
	seconde.setVisible(false);
	validerMS.setVisible(false);
	
	
	
	
	BoutonListener b = new BoutonListener(1,MainPanel,f);
	BoutonListener b2 = new BoutonListener(2,MainPanel,f);
	BoutonListener b3 = new BoutonListener(3,MainPanel,f);
	BoutonListener b4 = new BoutonListener(4,MainPanel,f);
	BoutonListener b5 = new BoutonListener(5,MainPanel,f);
	BoutonListener b6 = new BoutonListener(6,MainPanel,f);
	BoutonListener b7 = new BoutonListener(7,MainPanel,f);
	BoutonListener b8 = new BoutonListener(8,MainPanel,f);
	BoutonListener b9 = new BoutonListener(9,MainPanel,f);
	BoutonListener b10 = new BoutonListener(10,MainPanel,f);
	BoutonListener b11 = new BoutonListener(11,MainPanel,f);
	BoutonListener b17 = new BoutonListener(17,MainPanel,f);
	
	b.setAM(this,menu);
	b2.setAM(this,menu);
	b3.setAM(this,menu);
	b4.setAM(this,menu);
	b5.setAM(this,menu);
	b6.setAM(this,menu);
	b7.setAM(this,menu);
	b8.setAM(this,menu);
	b9.setAM(this,menu);
	b10.setAM(this,menu);
	b11.setAM(this,menu);
	b17.setAM(this,menu);
	
	
	komi.addActionListener(b);
	handicap.addActionListener(b2);
	valider.addActionListener(b3);
	notime.addActionListener(b4);
	ha.addActionListener(b5);
	by.addActionListener(b6);
	t1.addActionListener(b7);
	t2.addActionListener(b8);
	t3.addActionListener(b9);
	start.addActionListener(b10);
	quitter.addActionListener(b11);
	validerMS.addActionListener(b17);
	
	
    }
    
    public void afficherParametreR(Menu menu){
	
	if(menu.getKomi() == true){
	    this.choixR.setText(": Komi");
	}else if(menu.getKomi() == false){
	    this.choixR.setText(": Handicap");
	}
	
	
    }
    
    public void afficherParametreT(Menu menu){
	
	
	
	if(menu.getModeTemps() == 1){
	    choixT.setText(": Pas de temps");
	}else if(menu.getModeTemps() == 2){
	    choixT.setText(": Horloge absolue");
	}else if(menu.getModeTemps() == 3){
	    choixT.setText(": Byo-Yomi"); 
	}
	
	
	
    }
    
    public void afficherParametreS(Menu menu){
	
	choixS.setText(""+ menu.getSize() +" x "+ menu.getSize());
	
    }
    
    
    
    
    
    
    public JTextField getNb(){
	return this.nb;
    }
    
    public JButton getValider(){
	return this.valider;
    }
    
    
    public JTextField getMinute(){
	return this.minute;
    }
    
    public JTextField getSeconde(){
	return this.seconde;
    }
    
    public JButton getValiderMS(){
	return this.validerMS;
    }
    
}