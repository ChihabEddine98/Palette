
/**
 *                                                                 Université Paris Diderot 2018/2019
 * Ce Tp est réalisé en binome entre : Benamara Abdelkader   2-MI-1
 *                                     Elhoufi  Othman       2-MI-2
 *
 * Remarques :
 *              Toutes les questions (les étapes de 1 à 9 sont réalisées ).*/



public class Palette{

    private Vue vue;
    private Modele modele;
    private Controleur controleur;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Palette palette = new Palette();
            }
        });
    }

    public Palette(){
        modele=new Modele();
        vue=new Vue();
        controleur=new Controleur(vue);
        vue.addControleur(controleur);
        vue.pack();
        vue.setVisible(true);
    }

}