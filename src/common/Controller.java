package common;

/**
 * Created by Paweł Brzoza on 07.03.2016.
 */

import client.TcpClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;

import javafx.scene.text.Text;
import server.TcpServer;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class Controller implements Initializable {

    @FXML
    private Button button00;
    @FXML
    private Button button01;
    @FXML
    private Button button02;
    @FXML
    private Button button03;
    @FXML
    private Button button04;
    @FXML
    private Button button05;
    @FXML
    private Button button06;
    @FXML
    private Button button07;
    @FXML
    private Button button08;
    @FXML
    private Button button09;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button15;
    @FXML
    private Button button16;
    @FXML
    private Button button17;
    @FXML
    private Button button18;
    @FXML
    private Button button19;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Button button23;
    @FXML
    private Button button24;
    @FXML
    private Button button25;
    @FXML
    private Button button26;
    @FXML
    private Button button27;
    @FXML
    private Button button28;
    @FXML
    private Button button29;
    @FXML
    private Button button30;
    @FXML
    private Button button31;
    @FXML
    private Button button32;
    @FXML
    private Button button33;
    @FXML
    private Button button34;
    @FXML
    private Button buttonClient;
    @FXML
    private Button buttonServer;

    @FXML
    private Circle a;
    @FXML
    private Line b;
    @FXML
    private Line c;
    @FXML
    private Line d;
    @FXML
    private Line e;
    @FXML
    private Line f;

    @FXML
    private TextField fieldEnterIp;
    @FXML
    private TextField fieldEnterPassword;

    @FXML
    private GridPane grid;

    @FXML
    public Label labelEnteredPassword;

    @FXML
    private Pane paneOfHangman;

    @FXML
    private Text gameTitle;
    @FXML
    private Text textEnterIp;
    @FXML
    private Text textEnterPassword;
    @FXML
    public Text textWaiting;
    @FXML
    private Text textGuessedPassword;
    @FXML
    private Text textHanged;

    @FXML
    private Text pointsOpponent;
    @FXML
    private Text pointsOpponentInt;

    @FXML
    private Text yourPoints;
    @FXML
    private Text yourPointsInt;


    Game newGame;
    private Button[] buttons;

    int counterFails;
    static int counterLettersGuessed;

    int yourpoints;
    int pointsopponent;

    public TcpClient client;
    public TcpServer server;

    static int mode;

    @FXML
    void handleButtonAction1(ActionEvent event) {

        mode = 0;

        visibleMainOff();

        server = new TcpServer();
        server.start();

        setPass();

        Thread listener = new Thread(new Listener());
        listener.start();

        visiblePointsON();

    }
    @FXML
    void handleButtonAction2(ActionEvent event) {

        mode = 1;

        visibleMainOff();

        enterIP();

        visiblePointsON();


    }

    public void enterIP(){

        textEnterIp.setVisible(true);
        fieldEnterIp.setVisible(true);

        fieldEnterIp.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {


                client = new TcpClient(fieldEnterIp.getText());
                client.start();

                Thread listener = new Thread(new Listener());
                listener.start();

                textEnterIp.setVisible(false);
                fieldEnterIp.setVisible(false);



                try {
                    sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                guessPassword();

            }
        });
    }

    public void guessPassword() {

        if(mode == 0) {
            System.out.println("[SERWER] : Ustawiam haslo na: " + server.getInputLine());
            newGame.set_password(server.getInputLine());
        }
        else if(mode == 1) {
            System.out.println("[CLIENT] : Ustawiam haslo na: " + client.getInputLine());
            newGame.set_password(client.getInputLine());
        }

        labelEnteredPassword.setText(newGame.getDashes());

        labelEnteredPassword.setVisible(true);
        grid.setVisible(true);
        paneOfHangman.setVisible(true);

        cilckAndCheckButton();

    }
    
    public void cilckAndCheckButton() {

        for (int i = 0; i < buttons.length; i++) {

                final int x = i;

                buttons[i].setOnAction(event -> {

                    String buttonText = buttons[x].getText();

                    buttons[x].setDefaultButton(true);
                    buttons[x].setDisable(true);

                    char charCilcked = buttonText.charAt(0);

                    boolean clicked = false;

                        for (int j = 0; j < newGame.password.length() ; j++) {


                            if (newGame.password.charAt(j) == charCilcked) {
                                clicked=true;
                                counterLettersGuessed--;

                                System.out.println("pozostało do odgadnięcia " + counterLettersGuessed + " liter");

                                char arrayOfChar[] = newGame.dashes.toCharArray();
                                arrayOfChar[j] = newGame.password.charAt(j);
                                newGame.dashes = new String(arrayOfChar);
                                labelEnteredPassword.setText(newGame.getDashes());

                            }
                        }

                    if(clicked ==false) {
                        
                        System.out.println("nie zgadles ...");
                        
                        counterFails++;

                        if (counterFails == 1)
                            a.setVisible(true);
                        else if (counterFails == 2)
                            b.setVisible(true);
                        else if (counterFails == 3)
                            c.setVisible(true);
                        else if (counterFails == 4)
                            d.setVisible(true);
                        else if (counterFails == 5)
                            e.setVisible(true);
                        else if (counterFails == 6) {
                            f.setVisible(true);
                            textHanged.setVisible(true);

                            paneOfHangman.setVisible(false);
                            labelEnteredPassword.setVisible(false);
                            grid.setVisible(false);
                            visibleHangmanOff();

                            counterFails = 0;
                            //pointsopponent++;
                            //pointsOpponentInt.setText(String.valueOf(pointsopponent));


                            if(mode == 0)
                                server.setOutputLine("666");
                            else if(mode == 1)
                                client.setOutputLine("666");

                            textHanged.setVisible(true);

                            clearButtons();
                            setPass();

                            return;


                        }

                        System.out.println("Nieudana próba numer: " + counterFails);
                    }

                    if(counterLettersGuessed <= 0)
                    {
                        System.out.println("Udało Ci sie odgadnąć hasło! ");


                        paneOfHangman.setVisible(false);
                        labelEnteredPassword.setVisible(false);
                        grid.setVisible(false);
                        visibleHangmanOff();

                        yourpoints++;
                        counterFails = 0;

                        yourPointsInt.setText(String.valueOf(yourpoints));

                        if(mode == 0)
                            server.setOutputLine("666");
                        if(mode == 1)
                            client.setOutputLine("666");

                        textGuessedPassword.setVisible(true);
                        clearButtons();
                        setPass();

                        return;
                    }

                });

            }
        }

    public void setPass(){

        textEnterPassword.setVisible(true);
        fieldEnterPassword.setVisible(true);

        fieldEnterPassword.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER) {

                newGame.set_password( fieldEnterPassword.getText() );

                if(mode == 0)
                    server.setOutputLine(newGame.getPassword());
                if(mode == 1)
                    client.setOutputLine(newGame.getPassword());

                textEnterPassword.setVisible(false);
                fieldEnterPassword.setVisible(false);
                textHanged.setVisible(false);
                textGuessedPassword.setVisible(false);
                textWaiting.setVisible(true);


            }
        });
    }

    public class Listener implements Runnable {

        @Override
        public void run() {

            while(true) {

                if (mode == 0) {

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    if (server.getInputLine().equals("666") ) {

                        textWaiting.setVisible(false);

                        while (true) {

                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                            if (!(server.getInputLine().equals("666"))) {

                                Platform.runLater(() -> Controller.this.guessPassword());

                                break;
                            }
                        }

                    }
                }

                if (mode == 1) {

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    if ((client.getInputLine().equals("666"))) {

                        textWaiting.setVisible(false);

                        while (true) {

                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                            if (!(client.getInputLine().equals("666"))) {

                                Platform.runLater(() -> {
                                    Controller.this.guessPassword();
                                });

                                break;
                            }
                        }

                    }
                }
            }
        }

    }

    public void visibleMainOff(){

        buttonServer.setVisible(false);
        buttonClient.setVisible(false);
        paneOfHangman.setVisible(false);
        gameTitle.setVisible(false);
    }

    public void visibleHangmanOff(){

        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);


    }

    public void visiblePointsON(){
        yourPoints.setVisible(true);
        yourPointsInt.setVisible(true);
        //pointsOpponent.setVisible(true);
//        pointsOpponentInt.setVisible(true);
    }

    public void clearButtons(){
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setDisable(false);
            buttons[i].setDefaultButton(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        newGame = new Game();

        counterFails = 0;
        counterLettersGuessed = 0;

        yourpoints = 0;
        pointsopponent = 0;

        buttons = new Button[]{ button00, button01, button02, button03, button04, button05, button06, button07, button08, button09,
                button10, button11, button12, button13, button14, button15, button16, button17, button18, button19,
                button20, button21, button22, button23, button24, button25, button26, button27, button28, button29,
                button30, button31, button32, button33, button34 };

    }

}