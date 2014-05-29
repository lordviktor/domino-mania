package dominomania;

import java.util.ArrayList;
import java.util.Scanner;

import br.uff.networks.domino_mania.model.Tile;

public class Game {

    protected int numberOfPlayers;
    protected int currentPlayer;
    protected Table table;
    protected ArrayList<Player> players;
    protected Pack deck;
    Scanner read;

    public Game() {
        this.table = new Table();
        this.read = new Scanner(System.in);
        this.players = new ArrayList<>();
        this.deck = new Pack();

        this.currentPlayer = 0;

        processGame();

    }

    public void calculatePlayer() {
        if (currentPlayer == numberOfPlayers - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    } //calcula qual jogador irá jogar na rodada presente

    public void insertLeft(Tile t) {
        this.table.insertLeft(t);
    } // insere a esquerda do tabuleiro

    public void insertRight(Tile t) {
        this.table.insertRight(t);
    } // insere a direita do tabuleiro

    public void insertPlayers() {
        System.out.println("Quantos jogadores vão jogar?");
        numberOfPlayers = read.nextInt();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Nome do jogador " + (i + 1));
            players.add(new Player(read.next(), new Hand()));
        }
        orderPlayers();
        System.out.println("Começa o jogo");
    } //insere os jogadores no jogo

    public void inicialMethod() {
        this.deck = new Pack();
        for (Player playertemp : players) {
            playertemp.hand.chooseHand(deck);
        }
    } //escolhe a mão do jogador a cada nova rodada

    private void orderPlayers() {
        Player firstPlayer = null;
        int tempHigherTile = -1;
        for (Player playerTemp : players) {
            if (playerTemp.hand.getHigherTile() > tempHigherTile) {
                tempHigherTile = playerTemp.hand.getHigherTile();
                firstPlayer = playerTemp;
            }
        }

        if (firstPlayer != null) {
            players.remove(firstPlayer);
            players.add(0, firstPlayer);
        }

        int k = 1;
        for (Player playerTemp : players) {
            playerTemp.setTeam(k);
            k = 1 - k;
        }
    } //escolhe primeiro jogador de acordo com a carroça

    public void processPlay() {

        table.showTable();

        int option;

        System.out.println("Jogada " + players.get(currentPlayer).getNome() + " Time - " + players.get(currentPlayer).getTeam());
        System.out.println("1 - Mostrar mão");
        System.out.println("2 - Jogar peça na esquerda");
        System.out.println("3 - Jogar peça na direita");
        System.out.println("4 - Comprar peça");
        System.out.println("5 - Encerrar jogada");

        option = read.nextInt();

        if (option == 1) {
            players.get(currentPlayer).showHand();
        } else if (option == 2) {
            System.out.println("Qual peça deseja jogar? ");
            int j = read.nextInt();
            if (table.insertLeft(players.get(currentPlayer).getTile(j))) {
                players.get(currentPlayer).deleteTile(j);
                calculatePlayer();
            } else {
                System.out.println("Jogada negada");
            }
        } else if (option == 3) {
            System.out.println("Qual peça deseja jogar? ");
            int j = read.nextInt();
            if (table.insertRight(players.get(currentPlayer).getTile(j))) {
                players.get(currentPlayer).deleteTile(j);
                calculatePlayer();
            } else {
                System.out.println("Jogada Negada");
            }

        } else if (option == 4) {
            players.get(currentPlayer).hand.buyCard();
        } else {
            calculatePlayer();
        }
    } // menu de jogadas

    public void processGame() {

        boolean isGameFinished = false;

        insertPlayers();
        
        while (!isGameFinished) {
            
            inicialMethod(); //escolhe a mão do jogador toda rodada.
            table.clearTable(); //limpa a mesa.

            boolean finishRound = false;

            while (!finishRound) {
                processPlay();
                if (players.get(currentPlayer).isEmpty()) {
                    finishRound = true;
                    System.out.println("Jogador " + players.get(currentPlayer).getNome() + " ganhou");
                }
            }
            System.out.println("Quer jogar mais um round?");
            if(read.next().equals("nao")){
                isGameFinished = true;
            }
        }
    } // fluxo principal do jogo
}
