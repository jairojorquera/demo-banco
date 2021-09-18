package jairojorquera.demo.banco.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jjorquerar
 */
public class ValidadorUtils {

    public static Boolean validaRut(String rut) {
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if (matcher.matches() == false) {
            return false;
        }
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(obtieneDigitoVerificadorRut(stringRut[0]));
    }

    public static String obtieneDigitoVerificadorRut(String rut) {
        Integer M = 0, S = 1, T = Integer.parseInt(rut);
        for (; T != 0; T = (int) Math.floor(T /= 10)) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }
        return (S > 0) ? String.valueOf(S - 1) : "k";
    }

    public static Boolean validaEmail(String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Boolean validaPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
