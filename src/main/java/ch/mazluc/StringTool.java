package ch.mazluc;

public class StringTool {

    private StringTool(){
        // utility
    }

    public static String join(String separator, String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i < strings.length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String trimAll(String string) {
        return string.replaceAll("\\s|\\{|\\}|\"", "");
    }

    public static Object[] toArray(String s) {
        s = s.replaceAll("\\[|\\]", "");
        String[] splitted = s.split(",");
        Object[] result = new Object[splitted.length];
        for (int i = 0; i < splitted.length; i++) {
            result[i] = parseString(splitted[i]);
        }
        return result;
    }

    public static Object parseString(String s) {
        s = trimAll(s);
        if (s.isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e2) {
                if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(s);
                } else if (s.length() == 1) {
                    return s.charAt(0);
                } else {
                    return s;
                }
            }
        }
    }
}
