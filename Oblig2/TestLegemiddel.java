public class TestLegemiddel {

    public static void main(String[] args) {
        Narkotisk a = new Narkotisk("Fentanyl", 450, 4.2, 3);
        Vanedannende b = new Vanedannende("Oksygen", 0, 5.1, 1);
        Vanlig c = new Vanlig("c-vitamin", 20, 2.1);
        Vanlig d = new Vanlig("c-vitamin", 60, 2.1);

        // System.out.println(a);
        // System.out.println(b);
        // System.out.println(c);
        // System.out.println(d);

        //tester a,b og c
        System.out.println("Id test "+testId(a,1)+"\nNavn test: " +testNavn(a, "Fentanyl")+"\nPris test: "+testPris(a,450)+"\nVirkestoff test: "+testVirkestoff(a, 4.2)+"\nStryke test: "+testStyrke(a,3)+"\n\n");
        System.out.println("Id test "+testId(b,2)+"\nNavn test: " +testNavn(b, "Oksygen")+"\nPris test: "+testPris(b,0)+"\nVirkestoff test: "+testVirkestoff(b, 5.1)+"\nStryke test: "+testStyrke(b,1)+"\n\n");
        System.out.println("Id test "+testId(c,3)+"\nNavn test: " +testNavn(c, "c-vitamin")+"\nPris test: "+testPris(c,20)+"\nVirkestoff test: "+testVirkestoff(c, 2.1)+"\n\n");
        System.out.println("Id test "+testId(d,4)+"\nNavn test: " +testNavn(c, "c-vitamin")+"\nPris test: "+testPris(c,20)+"\nVirkestoff test: "+testVirkestoff(c, 2.1)+"\n\n");    
    }

    //tester id
    public static boolean testId(Legemiddel legemiddel, int forventetId){
        //caster for å hente frem metoder fra subklassene
        if (legemiddel instanceof Vanedannende){
            Vanedannende vanedannende = (Vanedannende)legemiddel;
            return vanedannende.hentId() == forventetId;
        }
        if (legemiddel instanceof Narkotisk){
            Narkotisk narkotisk= (Narkotisk)legemiddel;
            return narkotisk.hentId() == forventetId;
        }
        if (legemiddel instanceof Vanlig){
            Vanlig vanlig= (Vanlig)legemiddel;
            return vanlig.hentId() == forventetId;
        }
        return false;
    }

    //tester navnet
    public static boolean testNavn(Legemiddel legemiddel, String forventetNavn){
        return legemiddel.hentNavn() == forventetNavn;
    }

    //tester pris
    public static boolean testPris(Legemiddel legemiddel, int forventetPris){
        return legemiddel.hentPris() == forventetPris;
    }

    //tester virkestoff
    public static boolean testVirkestoff(Legemiddel legemiddel, double forventetVirkestoff){
        return legemiddel.hentVirkestoff() == forventetVirkestoff;
    }

    //tester styrke
    public static boolean testStyrke(Legemiddel legemiddel, int forventetStyrke){
        //caster for å hente frem metoder fra subklassene
        if (legemiddel instanceof Vanedannende){
            Vanedannende vanedannende = (Vanedannende)legemiddel;
            return vanedannende.hentStyrke() == forventetStyrke;
        }
        if (legemiddel instanceof Narkotisk){
            Narkotisk narkotisk= (Narkotisk)legemiddel;
            return narkotisk.hentStyrke() == forventetStyrke;
        }
        return false;
    }


}
