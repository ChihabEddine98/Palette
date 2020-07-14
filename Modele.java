
import javax.swing.*;
import java.awt.*;

public class Modele{
    private int rouge;
    private int vert;
    private int bleu;

    private int[] preferee=null;

    public void setPreferee(int a, int b, int c){ if (preferee==null) preferee=new int[3]; preferee[0]=a; preferee[1]=b; preferee[2]=c; }
    public void setPreferee(int[] t){ preferee=t; }
    public void setPreferee(Color c){ setPreferee(c.getRed(),c.getGreen(),c.getBlue()); }
    public int[] getPreferee(){
        if (preferee==null) throw new PrefereeNotSetException();
        else return preferee;
    }

    class PrefereeNotSetException extends RuntimeException{
        PrefereeNotSetException(){ super("La couleur préférée n'a pas été définie !"); }
    }

    public int getRouge(){ return rouge; }
    public int getVert(){ return vert; }
    public int getBleu(){ return bleu; }
    public int[] getValues(){ int[] t={rouge,vert,bleu}; return t; }

    public void setRouge(int x){ rouge=x; }
    public void setVert(int x){ vert=x; }
    public void setBleu(int x){ bleu=x; }

    public int getValue(int i){
        if (i==0) return getRouge();
        else if (i==1) return getVert();
        else if (i==2) return getBleu();
        else return -1;
    }

    public void setValue(int i, int j){
        if (i==0) setRouge(j);
        else if (i==1) setVert(j);
        else if (i==2) setBleu(j);
    }

    public Modele(){ this(0,0,0); }
    public Modele(int r, int v, int b){ rouge=r; vert=v; bleu=b; }

}