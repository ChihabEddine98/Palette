
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Vue extends JFrame{

    private JPanel panneauChoix;
    private JPanel panneauColore;
    private Modele modele;
    private JSlider[] jSlider;
    private JLabel codeCouleur;
    private JLabel labelImg;

    public Vue(){
        super();
        super.setLayout(new GridLayout(1,2));

        panneauChoix=new JPanel();
        panneauChoix.setBackground(new Color(0.65f,0.63f,0.45f));
        panneauColore=new JPanel();
        panneauColore.setBackground(new Color(0.5f,0.5f,0.5f));
        codeCouleur=new JLabel("808080");
        Font  f1  = new Font(Font.SERIF, Font.BOLD,  24);
        codeCouleur.setFont(f1);
        panneauColore.setLayout(new GridBagLayout());
        panneauColore.add(codeCouleur);
        panneauColore.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        super.getContentPane().add(panneauChoix);
        super.getContentPane().add(panneauColore);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(1000,400);

        modele=new Modele(50,50,50);
        panneauChoix.setLayout(new GridLayout(4,1));

        String[] tmp={"Rouge","Vert","Bleu"};
        jSlider=new JSlider[3];
        for (int i=0;i<3;i++){
            JSlider t=new JSlider(0,100,50);
            t.setMajorTickSpacing(25);
            t.setMinorTickSpacing(0);
            t.setPaintTicks(true);
            t.setPaintLabels(true);
            jSlider[i]=t;
            panneauChoix.add(t);
            t.setBorder(BorderFactory.createTitledBorder(tmp[i]));
        }

        JPanel boutons=new JPanel();
        boutons.setLayout(new GridLayout(1,5));
        panneauChoix.add(boutons);

        JButton setPreferee=new JButton("Mémoriser");
        setPreferee.setBackground(new Color(0.101f, 0.917f, 0.607f));
        setPreferee.addActionListener(event -> {
            modele.setPreferee(getValues());
        });
        JButton affPreferee=new JButton("S'en Rappeler");
        affPreferee.setBackground(new Color(0.850f, 0.411f, 0.886f));

        affPreferee.addActionListener(event -> {
            int[] t=modele.getPreferee();
            for (int i=0;i<3;i++) jSlider[i].setValue(t[i]);
        });
        JButton complementaire=new JButton("Complementaire");
        complementaire.setBackground(new Color(0.411f, 0.682f, 0.886f));
        complementaire.addActionListener(event -> {
            int[] t=getValues();
            for (int i=0;i<3;i++) jSlider[i].setValue(100-t[i]);
        });

        JButton image=new JButton("Image");
        image.setBackground(new Color(0.917f, 0.913f, 0.462f));
        image.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        ImageIcon imgIcn = new ImageIcon(ImageIO.read(file));
                        labelImg = new JLabel("", imgIcn, JLabel.CENTER);
                        panneauColore.remove(codeCouleur);
                        panneauColore.add(labelImg);
                        panneauColore.revalidate();
                        panneauColore.repaint();
                        panneauColore.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        //image.setIcon(new ImageIcon(ImageIO.read(file)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });






        JButton couleur=new JButton("Couleur");
        couleur.setBackground(new Color(0.921f, 0.360f, 0.450f));
        couleur.addActionListener(event -> {
            panneauColore.remove(labelImg);
            panneauColore.add( codeCouleur);
            panneauColore.revalidate();
            panneauColore.repaint();
            panneauColore.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        });

        boutons.add(setPreferee);
        boutons.add(affPreferee);
        boutons.add(complementaire);
        boutons.add(image);
        boutons.add(couleur);



        panneauColore.setVisible(true);
        panneauChoix.setVisible(true);
    }

    public int[] getValues(){
        int[] t=new int[3];
        for (int i=0;i<3;i++){
            t[i]=jSlider[i].getValue();
        }
        return t;
    }

    public void addControleur(Controleur c){
        for (int i=0;i<3;i++)
            jSlider[i].addChangeListener(c);
    }

    public void setValueModele(int i, int j){ modele.setValue(i,j); }

    public int getJSliderValueRouge(){ return jSlider[0].getValue(); }
    public int getJSliderValueVert(){ return jSlider[1].getValue(); }
    public int getJSliderValueBleu(){ return jSlider[2].getValue(); }

    public int getJSliderValue(int i){
        if (i==0) return getJSliderValueRouge();
        else if (i==1) return getJSliderValueVert();
        else if (i==2) return getJSliderValueBleu();
        else return -1;
    }

    public void miseAJour(){
        int[] t=modele.getValues();
        panneauColore.setBackground(new Color(((float)t[0])/101f,((float)t[1])/101f,((float)t[2])/101f));
        codeCouleur.setText(getHexa(t));
        if((t[0]>=50 || t[1]>=50 && t[2]>=50))
        {
            codeCouleur.setForeground(new Color((float)0,(float)0,(float)0));
        }
        else
        {
        codeCouleur.setForeground(new Color(((float)100-t[0])/101f,((float)100-t[1])/101f,((float)100-t[2])/101f));
        }
    }

    private String getHexa(int[] t){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<t.length;i++){
            int nb=t[i]*255;
            sb.append(hexa(nb/100));
        }
        return sb.toString();
    }

    private String hexa(int i){
        StringBuilder sb=new StringBuilder();
        if (i==0) return "00";
        while (i!=0){
            char c;
            if (i%16<10) c=(char)(48+i%16);
            else c=(char)(55+i%16);
            sb.insert(0,c);
            i/=16;
        }
        if (sb.length()==1) sb.insert(0,'0');
        return sb.toString();
    }



}