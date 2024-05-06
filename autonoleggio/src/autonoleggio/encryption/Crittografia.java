package autonoleggio.encryption;

public class Crittografia {

	
 
    public static String criptaPassword(String password) {

        char[] a = new char[password.length()] ; 
        for(int i=0 ; i<password.length(); i++)
        {
            a[i] = (char) (password.charAt(i)+3);
        }

        return new String(a);
    }

    public static String decriptaPassword(String password) {
        char[] a = new char[password.length()] ; 
        for(int i=0 ; i<password.length(); i++)
        {
            a[i] = (char) (password.charAt(i)-3);
        }

        return new String(a);
    }

}
