import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;

public class GUI {
    Kontroll kontroll;
    JFrame vindu;
    JPanel panel, konsoll, taster, info, rutenett, startStop;
    JButton opp, ned, venstre, hoyre, stopp, start;
    JLabel status, lengde; //viser om man har tapt
    JLabel[][] ruter = new JLabel[12][12];;
    private static int hoyde = 150;
    private static int bredde = 200;
    int len = 0;

    GUI(Kontroll knt){
        kontroll = knt;
        
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } 
        catch (Exception e) { System.exit(1); 
        }

        //klasse for avslutt-knapp
        class Stopper implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.avslutt();
            }
        }

        //klasse for start-knapp
        class Starter implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                kontroll.startSpill();
            }
        }

        //klasse for pil-knapper
        class GaaOpp implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                kontroll.settRetningOpp();
            }
        }

        //klasse for pil-knapper
        class GaaNed implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                kontroll.settRetningNed();
            }
        }

        //klasse for pil-knapper
        class GaaHoyre implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                kontroll.settRetningHoyre();
            }
        }

        //klasse for pil-knapper
        class GaaVenstre implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                kontroll.settRetningVenstre();
            }
        }

        //vinduet
        vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //hovedpanel
        panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        vindu.add(panel);

        //konsoll, øverst på siden skal det være knapper og info
        konsoll = new JPanel();
        konsoll.setLayout(new BorderLayout());
        panel.add(konsoll, BorderLayout.NORTH);

        //øverst ti høyre skal det være knapper
        taster = new JPanel();
        opp = new JButton("^");
        opp.addActionListener(new GaaOpp());
        ned = new JButton("v");
        ned.addActionListener(new GaaNed());
        venstre = new JButton("<");
        venstre.addActionListener(new GaaVenstre());
        hoyre = new JButton(">");
        hoyre.addActionListener(new GaaHoyre());
        taster.setLayout(new GridLayout(3,3));
        taster.add(new JButton(" "));
        taster.add(opp);
        taster.add(new JButton(" "));
        taster.add(venstre);
        taster.add(new JButton(" "));
        taster.add(hoyre);
        taster.add(new JButton(" "));
        taster.add(ned);
        taster.add(new JButton(" "));
        taster.setPreferredSize(new Dimension(hoyde, hoyde));
        konsoll.add(taster, BorderLayout.EAST);
	    

        //øverst ti venstre skal det være oppdateringer, stopp- og startknapp
        info = new JPanel();
        info.setLayout(new GridLayout(3,1));
        lengde = new JLabel("Lengde: "+len);
        status = new JLabel("Status: lever");
        startStop = new JPanel();
        startStop.setLayout(new BorderLayout());
        stopp = new JButton("SLUTT");
        stopp.addActionListener(new Stopper());
        start = new JButton("START");
        start.addActionListener(new Starter());
        startStop.add(stopp, BorderLayout.EAST);
        startStop.add(start, BorderLayout.WEST);
        info.add(lengde);
        info.add(status);
        info.add(startStop);
        start.setPreferredSize(new Dimension(bredde/2, hoyde));
        stopp.setPreferredSize(new Dimension(bredde/2, hoyde));
        info.setPreferredSize(new Dimension(bredde, hoyde));
        konsoll.add(info, BorderLayout.WEST);


       //nederst på siden finnes spillbrettet
        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(12,12));
        int teller = 0;

        for (int r = 0; r < ruter.length; r++){
            for(int k = 0; k < ruter[0].length; k++){

                JLabel ny = new JLabel(""); //+i for å vise hvilken rute
                ny.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                if(r%2 == 0){
                    if(k%2 == 0){
                        ny.setOpaque(true);
                        ny.setBackground(Color.LIGHT_GRAY);
                    }
                    else{
                        ny.setOpaque(true);
                        ny.setBackground(Color.WHITE);
                    }
                }
                else{
                    if(k%2 == 0){
                        ny.setOpaque(true);
                        ny.setBackground(Color.WHITE);
                    }
                    else{
                        ny.setOpaque(true);
                        ny.setBackground(Color.LIGHT_GRAY);
                    }
                }
                ny.setHorizontalAlignment(JLabel.CENTER);
                ruter[r][k] = ny;
                rutenett.add(ruter[r][k]);
                teller++;
            }
        }
        rutenett.setPreferredSize(new Dimension(bredde, bredde+120));
        panel.add(rutenett);

        //pakker vindu
        vindu.pack();
        vindu.setVisible(true);
    }

    /////////////////////////////////////////////////////bevegelse/////////////////////////////////////////////////////////////////////////
    //ved start er startruten rød
    public void markerRute(int rad, int kol){
        JLabel startRute = ruter[rad][kol];
        //startRute.setText("O");
        startRute.setOpaque(true);
        startRute.setBackground(Color.RED);
    }

    //plasserer penger tilfeldig på brettet
    public void leggPenger(int rad, int kol){
        JLabel pengeRute = ruter[rad][kol];
        pengeRute.setText("$");
        pengeRute.setForeground(new ColorUIResource(0, 120, 0));
    }

    public void oppdaterLengde(int nyLengde){
        len = nyLengde;
        lengde.setText("Lengde: "+len);
    }

    public void oppdaterStatus(){
        status.setText("Status: dod");
    }

    public void flyttRute(int nyRad, int nyKol){
        //den nye ruta vil få fargen til hodet
        JLabel nesteRute = ruter[nyRad][nyKol];
        //nesteRute.setText("O");
        nesteRute.setOpaque(true);
        nesteRute.setBackground(Color.RED);
        nesteRute.setForeground(Color.BLACK);
    }

    public void leggBak(int rad, int kol){
        //forrige rute skal bli vanlig
        JLabel forrigeRute = ruter[rad][kol];
        forrigeRute.setText("");
        forrigeRute.setOpaque(true);
        if(rad%2 == 0){
            if(kol%2 == 0){
                forrigeRute.setBackground(Color.LIGHT_GRAY);
                forrigeRute.setForeground(Color.BLACK);
            }
            else{
                forrigeRute.setBackground(Color.WHITE);
                forrigeRute.setForeground(Color.BLACK);
            }
        }
        else{
            if(kol%2 == 0){
                forrigeRute.setBackground(Color.WHITE);
                forrigeRute.setForeground(Color.BLACK);
            }
            else{
                forrigeRute.setBackground(Color.LIGHT_GRAY);
                forrigeRute.setForeground(Color.BLACK);
            }
        }
    }

    public Boolean sjekkPenger(int rad, int kol){
        return ruter[rad][kol].getText().equals("$");
    }

    public Boolean sjekkKollisjon(int rad, int kol){
        return ruter[rad][kol].getBackground().equals(Color.RED);
    }
}
