package autonoleggio.consolemanagement;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import autonoleggio.colors.ColorManager;

public class ConsoleManagement implements ConsoleManage {

	private Scanner scan = new Scanner(System.in);
	private int ret;

	// colours ----------------------------------------- 
		String okMsgColour =ColorManager.ANSI_GREEN;
		String retryMsgColour =ColorManager.ANSI_YELLOW;
		String errMsgColour =ColorManager.ANSI_RED;

	// check--------------------------------------------
	private int year_max = 1920;
	private String intRegex = "[0-9]+";
	private String sCfRegex = "^[A-Z]{3}[A-Z]{3}\\d{2}(M|F)[A-Z]{2}$";
	// Regex targa italiana (2 lettere, 3 numeri, 2lettere) ex: AC012IT
    private String sTargaRegex = "^[A-Z]{2}\\d{3}[A-Z]{2}$";
    private String sessoRegex = "^(MASCHIO|FEMMINA)$";
    private String userNameRegex = "^[a-zA-Z0-9]{8,12}$";
    private String pwdRegex="^(?=.*[!@#$%^&*()-+]).{8}$";
    private String sMailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private String sPatenteRegex = "^[A-Z]{4}\\d{2}$";
	// --------------------------------------------------
    public enum Sesso {  //COSI è SVINCOLATO
    	MASCHIO, FEMMINA
    }
 // --------------------------------------------------
	public ConsoleManagement() { // si potrebbe rendere singleton
		super();

	}
	
	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getOkMsgColour() {
		return okMsgColour;
	}

	public void setOkMsgColour(String okMsgColour) {
		this.okMsgColour = okMsgColour;
	}

	public String getRetryMsgColour() {
		return retryMsgColour;
	}

	public void setRetryMsgColour(String retryMsgColour) {
		this.retryMsgColour = retryMsgColour;
	}


	public String getErrMsgColour() {
		return errMsgColour;
	}



	public void setErrMsgColour(String errMsgColour) {
		this.errMsgColour = errMsgColour;
	}



	// metody utility -------------------------------------------------------------------
	public int giveInt(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
		ret = 0; // pulizia del flow

		int output = 0;

		int count = 1; // conto il primo tentativo
		System.out.println(msgToPrint);
		while (count <= tentativi) {

			String number = scan.nextLine();
			if (!number.matches(intRegex)) {
				if (count <= tentativi - 1) {
					System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
				} else {
					System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);

					break;
				}
				count++;

			} else {
				ret = 1;

				output = Integer.parseInt(number);
				System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
				break;
			}
		}
		
		
		
		return output;
	}
	
	public String giveString(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
		ret = 0; // pulizia del flow
		String result = ""; 
	    int count = 1; // conto il primo tentativo
	    System.out.println(msgToPrint);
	    while (count <= tentativi) {
	        String input = scan.nextLine();
	        if (input.isEmpty()) { // Verifica se la stringa � vuota
	            if (count <= tentativi - 1) {
	                System.out.println(retryMsgColour+riprova+ColorManager.ANSI_RESET);
	            } else {
	                System.out.println(errMsgColour+msgErr+ColorManager.ANSI_RESET);
	                result = "";
	                break;
	            }
	            count++;
	        } else {
	        	ret = 1;
	            result = input;
	            System.out.println(okMsgColour+msgOk+ColorManager.ANSI_RESET);
	            break;
	        }
	    }
	    return result.toUpperCase();
	}
	
	// aggiungi controlli ( 18 anni? )
    public LocalDate giveData(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
        ret = 0; // pulizia del flow
        LocalDate result = null;
        int count = 1; // conto il primo tentativo
        System.out.println(msgToPrint);
        while (count <= tentativi) {
            String input = scan.nextLine();
            if (!input.matches("\\d{2}/\\d{2}/\\d{4}")) { // Verifica se la stringa non � nel formato DD/MM/YYYY
                if (count <= tentativi - 1) {
                    System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
                } else {
                    System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);
                    break;
                }
                count++;
            } else {
                ret = 1;
                String[] parts = input.split("/");
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);
                try {
                    result = LocalDate.of(year, month, day);
                    System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
                } catch (Exception e) {
                    System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);
                }
                break;
            }
        }
        return result;
    }
	
	//add mail
    public String giveMail(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
        ret = 0; // pulizia del flow
        String result = null;
        int count = 1; // conto il primo tentativo
        System.out.println(msgToPrint);
        while (count <= tentativi) {
            String input = scan.nextLine();
            Pattern pattern = Pattern.compile(sMailRegex);
            Matcher matcher = pattern.matcher(input);
            if (!matcher.matches()) { // Verifica se la stringa non � un indirizzo email valido
                if (count <= tentativi - 1) {
                    System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
                } else {
                    System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);
                    break;
                }
                count++;
            } else {
                ret = 1;
                result = input;
                System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
                break;
            }
        }
        return result;
    }
	
	public String[] giveCf(String sRequestMsg, String sRetryMsg, String errorMsg, int iNumTry) {
		ret = 0; // pulizia del flow
        int n = 0;
        String[] rCf = new String[2];
        while (true) {
            System.out.println(sRequestMsg);
            String sCf = scan.nextLine();
            if (!sCf.toUpperCase().matches(sCfRegex)) {
                n++;
                if (n == iNumTry) {
                    System.out.println(errorMsg);
                    rCf[0] = "0";
                    break;
                }
                System.out.println(sRetryMsg);
            } else {
                rCf[0] = "1";
                rCf[1] = sCf.toUpperCase();
                break;
            }
        }
        return rCf;
    }

	   // giveTarga()
    public String giveTarga(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
        ret = 0; // clean flow;
        String output = null;
        int count = 1; // conto il primo tentativo
    	System.out.println(msgToPrint);
    	while (count <= tentativi) {

    		String sTarga = scan.nextLine().toUpperCase();
    		if (!sTarga.matches(sTargaRegex)) {
    			if (count <= tentativi - 1) {
    				System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
    			} else {
    				System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);

    				break;
    			}
    			count++;

    		} else {
    			ret = 1;

    			output = sTarga;
    			System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
    			break;
    		}
    	}
    	
    	return output;
    }


    public Sesso giveSesso(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
        ret = 0; // clean flow;
        Sesso output = null;
        int count = 1; // conto il primo tentativo
    	System.out.println(msgToPrint);
    	while (count <= tentativi) {

    		String sesso = scan.nextLine().toUpperCase();
    		if (!sesso.matches(sessoRegex)) {
    			if (count <= tentativi - 1) {
    				System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
    			} else {
    				System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);

    				break;
    			}
    			count++;

    		} else {
    			ret = 1;

    			output = (sesso.equals(Sesso.MASCHIO.toString())?Sesso.MASCHIO:Sesso.FEMMINA);
    			System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
    			break;
    		}
    	}
    	
    	return output;
    }
    

    public String giveUserName(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
        ret = 0; // clean flow;
        String output = null;
        int count = 1; // conto il primo tentativo
    	System.out.println(msgToPrint);
    	while (count <= tentativi) {

    		String username = scan.nextLine().toUpperCase();
    		if (!username.matches(userNameRegex)) {
    			if (count <= tentativi - 1) {
    				System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
    			} else {
    				System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);

    				break;
    			}
    			count++;

    		} else {
    			ret = 1;

    			output = username;
    			System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
    			break;
    		}
    	}
    	
    	return output;
    }
    
    
    public String givePassword(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
        ret = 0; // clean flow;
        String output = null;
        int count = 1; // conto il primo tentativo
    	System.out.println(msgToPrint);
    	while (count <= tentativi) {

    		String password = scan.nextLine();
    		if (!password.matches(pwdRegex)) {
    			if (count <= tentativi - 1) {
    				System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
    			} else {
    				System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);

    				break;
    			}
    			count++;

    		} else {
    			ret = 1;

    			output = password;
    			System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
    			break;
    		}
    	}
    	
    	return output;
    }
    
    
    public String givePatente(String msgToPrint, int tentativi, String riprova, String msgOk, String msgErr) {
        ret = 0; // clean flow;
        String output = null;
        int count = 1; // conto il primo tentativo
    	System.out.println(msgToPrint);
    	while (count <= tentativi) {

    		String password = scan.nextLine();
    		if (!password.matches(sPatenteRegex)) {
    			if (count <= tentativi - 1) {
    				System.out.println(retryMsgColour + riprova + ColorManager.ANSI_RESET);
    			} else {
    				System.out.println(errMsgColour + msgErr + ColorManager.ANSI_RESET);

    				break;
    			}
    			count++;

    		} else {
    			ret = 1;

    			output = password;
    			System.out.println(okMsgColour + msgOk + ColorManager.ANSI_RESET);
    			break;
    		}
    	}
    	
    	return output;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
