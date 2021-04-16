
public class PolaGry {
    int wspolrzednaX; //wspolrzedna poczatkowa X danego pola
    int wspolrzednaY; //wspolrzedna poczatkowa Y danego pola
    boolean czyJestBomba; //zmienna ktora mowi nam o tym czy pod danym polem ukryta jest bomba
    boolean czyJestFlaga; //zmienna ktora mowi nam o tym czy dane pole oznaczylem flagą ==Czy kliknąłem na pole PPM
    boolean czyOdkryte; //zmienna ktora mowi nam o tym czy pole zostało kliknięte LPM
    int liczba;//tu bede przechowywal liczby ktore beda sie wyswietlac w polach

    public PolaGry(){
        wspolrzednaX=0;
        wspolrzednaY=0;
        czyJestBomba=false;
        czyJestFlaga=false;
        czyOdkryte=false;
    }
}
