
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
public class Controleur implements ChangeListener{

    private Vue vue;

    public Controleur(Vue v){ vue=v; }

    public void stateChanged(ChangeEvent event){
        for (int i=0;i<3;i++)
            vue.setValueModele(i,vue.getJSliderValue(i));
        vue.miseAJour();
    }

}

