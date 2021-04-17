
public class PlayingFields {
    int coordinateX; //wspolrzedna X danego pola
    int coordinateY; //wspolrzedna Y danego pola
    boolean isBomb; //zmienna ktora mowi nam o tym czy pod danym polem ukryta jest bomba
    boolean isFlag; //zmienna ktora mowi nam o tym czy dane pole oznaczylem flagą ==Czy kliknąłem na pole PPM
    boolean isDiscovered; //zmienna ktora mowi nam o tym czy pole zostało kliknięte LPM
    int number;//tu bede przechowywal liczby ktore beda sie wyswietlac w polach

    public PlayingFields(){
        coordinateX=0;
        coordinateY=0;
        isBomb=false;
        isFlag=false;
        isDiscovered=false;
    }
}
