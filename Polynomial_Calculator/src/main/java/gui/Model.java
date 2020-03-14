package gui;

import util.WrongPolynomialException;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    private static final String regex = "([+-]?(?:(?:\\d*+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))";

    private String simplifyString(String str) {
        String regx = " *";
        char[] ca = regx.toCharArray();
        for(char c : ca) {
            str = str.replace(""+c, "");
        }
        return str;
    }

    public List<String> checkPolynomial(String str) {
        String strSimp = simplifyString(str);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strSimp);

        String notStr = matcher.replaceAll("");

        if(!notStr.equals(""))
            throw new WrongPolynomialException("Please enter another polynomial");

        matcher = pattern.matcher(strSimp);

        List<String> monomialList = new LinkedList<String>();
        while(matcher.find()) {
            monomialList.add(matcher.group());
        }

        return monomialList;
    }
}
