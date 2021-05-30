package login.domain;

public class LoginSampleException extends Exception {


    //Exception metode: Bruges bla. til at kontrollere login om oplysninger stemmer fx. i password indtastning i register fra LoginController klassen
    //Hvis ikke, smides exception med String msg (ex. The two passwords did not match)
    public LoginSampleException(String msg) {
        super(msg);
    }
}