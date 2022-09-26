import java.util.Objects;
import java.util.Scanner;

public class tresenralla {
    static int matriu[][] = new int[3][3];
    static int torn;
    static int numero2;
    static int finalitzar;
    static char numlletra;


    private static void Tauler() {//Aquest es el tauler on es fara la partida
        //Creem el array amb les lletres que estara amb vertical al tauler per poder ficar les fitxes de manera mes facil per elegir la filera
        String[] lletres = new String[]{"A ", "B ", "C "};
        //Els nmereos seguents sera els que van situats a d'alt on indicaran cada columna
        System.out.println("  1 2 3");
        //Creem la matriu amb el for, primer fem aquest que sera per indicar les files
        for (int files = 0; files < matriu.length; files++) {
            //Fiquem les lletres del array creat anteriorment
            System.out.print(lletres[files]);
            //Creem l'altre array que ens indicara les columens
            for (int columnes = 0; columnes < matriu.length; columnes++) {
                //Li deiem que si el numero es un 0 que es com es crea, tingui forma de cercle, di te un 1 que sera el 1r jugador tingui una x i si es el 2n jugador amb un 2 tingui un triangle
                if (matriu[files][columnes] == 0) {
                    System.out.print("⚪ ");
                }else if (matriu[files][columnes] == 1) {
                    System.out.print("x ");
                } else if (matriu[files][columnes] == 2) {
                    System.out.print("△ ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    private static void TriarJugador() {//Aquest indicarem el jugador que li toca

        Scanner sc = new Scanner(System.in);
        //Amb el següent if li indiquem que si el residu de jugada amb 2 es 0 li toque al jugar 1 en cas contrari jugador 2 d'aquesta manera cadascu tindra el seu torn.
        if (torn % 2 == 0) {System.out.println("Jugador1 on vols ficar la fitxa? Exemple (A1/a1)");}
        else {System.out.println("Jugador2 on vols ficar la fitxa? Exemple (A1/a1)");}
        //Indiquem la posicio que volem la fitxa i la indiquem com llegir-ho
        String posicio = sc.nextLine();
        numlletra = posicio.toUpperCase().charAt(0);
        char numero1 = posicio.charAt(1);
        numero2 = Integer.parseInt(String.valueOf(numero1)) - 1;
        if (numlletra == 'A') {
            numlletra = 0;
        } else if (numlletra == 'B') {
            numlletra = 1;
        } else if (numlletra == 'C') {
            numlletra = 2;
        }
        //Li indiquem d'entrar a tauler en joc que es on esta el joc ficat
        Taulerenjoc();

    }

    public static void Taulerenjoc() {//Fem la part del joc i la partida
        int fitxa;
        //Aqui calculem el torn: com torn va aumentan cada vegada al fer aquest if ens assegurem de que cada torn li toca un diferent.
        if (torn % 2 == 0) {
            fitxa = 1;
        } else fitxa = 2;
        try {
            //Si la posicio on esta indicada es 0 aquesta vol dir que la posicio sera buida i entrara a la buidaExeption
            if (matriu[numlletra][numero2] == 0) {
                throw new buidaExeption();
            }
            //Si la posicio te un 1 o un 2 aquesta voldra dir que esta ocupada per un dels 2 jugadors, llavors entrara a ocupadaExeption
            if (matriu[numlletra][numero2] == 1 || matriu[numlletra][numero2] == 2) {
                throw new ocupadaExeption();
            }

        } catch (buidaExeption e) {
            //Al estar buidar ficarem la fitxa a la posicio indicada i el torn seguira, aumentan torn per a que li toque al seguent
            System.out.println("Aquesta posicio es correcta");
            matriu[numlletra][numero2] = fitxa;
            Tauler();
            torn++;

        } catch (ocupadaExeption e) {
            //La fitxar al entrar a aquest exeption voldra dir que esta ocupada i tindra que repetir la posicio fican una buida
            System.out.println("Aquesta posició esta OCUPADA! Mira bé on la fiques. ");
            TriarJugador();
        } catch (Exception e) {
            //Al entrar a aquest voldra dir que esta incorrecte el que estar ficat
            System.out.println("No existeix aquesta posicio! Mira bé com ho fiques.");
        }
        //Entrarem a dins de victoriapartida aquesta ens dira qui ha guanyat o si hi ha enpat
        victoriapartida();
    }

    public static void victoriapartida() {//Aqui veurem si hi ha empat o ha guanyat algú
        //Fem un if amb totes les possibilitats de guanyar, amb victoria de jugardor 1
        if (matriu[0][0] == 1 && matriu[0][1] == 1 && matriu[0][2] == 1 /*a1-a2-a3*/ ||
                matriu[1][0] == 1 && matriu[1][1] == 1 && matriu[1][2] == 1 /*b1-b2-b3*/ ||
                matriu[2][0] == 1 && matriu[2][1] == 1 && matriu[2][2] == 1 /*c1-c2-c3*/ ||
                matriu[0][0] == 1 && matriu[1][1] == 1 && matriu[2][2] == 1 /*a1-b2-c3*/ ||
                matriu[0][0] == 1 && matriu[1][0] == 1 && matriu[2][0] == 1 /*a1-b1-c1*/ ||
                matriu[0][1] == 1 && matriu[1][1] == 1 && matriu[2][1] == 1 /*a2-b2-c2*/ ||
                matriu[0][2] == 1 && matriu[1][2] == 1 && matriu[2][2] == 1 /*a3-b3-c3*/ ||
                matriu[0][2] == 1 && matriu[1][1] == 1 && matriu[2][0] == 1 /*a3-b2-c1*/) {
            System.out.println("VICTORIA DEL JUGADOR1!!");
            //Fem un if amb totes les possibilitats de guanyar, amb victoria de jugardor 2
        } else if (matriu[0][0] == 2 && matriu[0][1] == 2 && matriu[0][2] == 2 /*a1-a2-a3*/ ||
                matriu[1][0] == 2 && matriu[1][1] == 2 && matriu[1][2] == 2 /*b1-b2-b3*/ ||
                matriu[2][0] == 2 && matriu[2][1] == 2 && matriu[2][2] == 2 /*c1-c2-c3*/ ||
                matriu[0][0] == 2 && matriu[1][1] == 2 && matriu[2][2] == 2 /*a1-b2-c3*/ ||
                matriu[0][0] == 2 && matriu[1][0] == 2 && matriu[2][0] == 2 /*a1-b1-c1*/ ||
                matriu[0][1] == 2 && matriu[1][1] == 2 && matriu[2][1] == 2 /*a2-b2-c2*/ ||
                matriu[0][2] == 2 && matriu[1][2] == 2 && matriu[2][2] == 2 /*a3-b3-c3*/ ||
                matriu[0][2] == 2 && matriu[1][1] == 2 && matriu[2][0] == 2 /*a3-b2-c1*/) {
            System.out.println("VICTORIA DEL JUGADOR2!!");
        }
        int resultat = 0;
        //Ara tindrem que confirma que es empat, per aixo mirarem que estiguen totes les posicions ocuapdesamb aquest try ho farem.
        try {
            for (int i = 0; i < matriu.length; i++) {
                for (int j = 0; j < matriu.length; j++) {
                    resultat = resultat + matriu[i][j];
                }
            }
            //Aquest al donar 13 voldra dir que estan totes ocupades ja que la suma amb el 2 i 1 donara 13 en totes les fitxes ficades aquesta si es empat donara anira a empatExeption
            if (resultat == 13)
                throw new empatExeption();

        } catch (empatExeption e) {
            System.out.println("EMPAT!");
            finalitzar = 13;

        }
    }
    private static void Partida() {
        //Aqui indiquem el metode que ens fara un nou tauler per seguri jugant
        printejarTablero();
        //Introduïm el torn 0 per poder reiniciar la partida des de nou en cas de que vulguin reiniciar la partida
        torn =0;
        //Anirem a triar jugador mentre sigui diferent 13
        do {
            TriarJugador();
        } while (finalitzar !=13);
        finalitzar =0;
        //una vegada finalitzada i sigui 13 la suma anirem al metode per repetir la partida
        RepetirPartida();
    }
    private static void RepetirPartida() {//Aquest metode es per repeitr la partida
        Scanner teclat = new Scanner(System.in);
        //Preguntem er fer una nova partida
        System.out.println("Voleu fer un altra partida? (Si/No)");
        String repetir = teclat.nextLine();
        repetir = repetir.toUpperCase();
        //En cas de ficar si aquest l'enviara al metode de partida per tornar-ho a començar
        if (Objects.equals(repetir, "SI")||Objects.equals(repetir, "si")||Objects.equals(repetir, "Si")||Objects.equals(repetir, "sI")) {
            Partida();
            //En cas de que no ens despedim i acabem el joc.
        } else if (Objects.equals(repetir, "NO")||Objects.equals(repetir, "no")||Objects.equals(repetir, "No")||Objects.equals(repetir, "nO")) {
            System.out.println("Fins un altra!");
        } else {
            //Si no a ficat el que toca li saltarar el missatge d'error i repetim el metode.
            System.out.println("Aquesta resposta es incorrecta");
            RepetirPartida();
        }
    }
    private static void printejarTablero() {
        System.out.println();
        //Crearem el tauler de nou
        String[] lletres = new String[]{"A ", "B ", "C "};
        System.out.println("  1 2 3");
        for (int files = 0; files < matriu.length; files++) {
            System.out.print(lletres[files]);
            for (int columnes = 0; columnes < matriu.length; columnes++) {
                //La diferncia es que en aquest cas fiquem que tots siguin 0 oer poder repetir la partida de nou
                matriu[files][columnes] = 0;
                if (matriu[files][columnes] == 0)
                    System.out.print("⚪ ");
            }
            System.out.println();
        }
        System.out.println();

    }


    public static void main(String[] args) {
        Partida();
    }

    public static class buidaExeption extends Exception {
    }

    public static class ocupadaExeption extends Exception {
    }

    public static class guanyador1Exeption extends Exception {
    }

    public static class guanyador2Exeption extends Exception {
    }

    public static class empatExeption extends Exception {
    }
}




