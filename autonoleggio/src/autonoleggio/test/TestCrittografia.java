package autonoleggio.test;

import java.time.LocalDateTime;

import autonoleggio.encryption.Crittografia;

public class TestCrittografia {

	public static void main(String[] args) {

		System.out.println(LocalDateTime.now());
		String test = "BAT9999!"
				+ "";
		
		
		//test criptaggio   
        
        String criptata = Crittografia.criptaPassword(test);
        System.out.println(criptata);
        String decriptata = Crittografia.decriptaPassword(criptata);
        System.out.println(decriptata);

        if(decriptata.equals(test)) {
        	System.out.println("test valido");
        } else {
        	System.out.println("test non valido");
        }


	}

}
