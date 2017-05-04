package common;

/**
 * Created by Paweł Brzoza on 21.03.2016.
 */

public class Game {


    public String password = "";

    public String dashes = "";

    public int length_pass = 0;


    public void set_password(String password)
    {
        clear();
        this.password = password.toUpperCase();
        length_pass = password.length();
        for (int i = 0; i < length_pass ; i++) {


            if(password.charAt(i) != ' ') {
                dashes = dashes + '-';
                Controller.counterLettersGuessed++;
            }
            else dashes = dashes + ' ';
        }
    }

    public void clear() {
        Controller.counterLettersGuessed = 0;
        password = "";
        dashes = "";
        length_pass = 0;
    }

    public String getPassword() {
        return password;
    }

    public String getDashes() {
        return dashes;
    }

}
