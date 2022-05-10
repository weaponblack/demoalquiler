package com.prog2.helpers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Keyboard {

    public static Scanner sc = new Scanner(System.in).useDelimiter("[\n]+|[\r\n]+");
    public static final char ENTER = 13;

    public static String readString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static int readInt(String message) {
        boolean ok;
        int value = Integer.MIN_VALUE;
        System.out.print(message);

        do {
            try {
                ok = true;
                value = sc.nextInt();
            } catch (InputMismatchException e) {
                ok = false;
                System.out.print(">> Valor erróneo. " + message);
            } finally {
                sc.nextLine();
            }

        } while (!ok);

        return value;
    }

    public static long readLong(String message) {
        boolean ok;
        long value = Long.MIN_VALUE;
        System.out.print(message);

        do {
            try {
                ok = true;
                value = sc.nextLong();
            } catch (InputMismatchException e) {
                ok = false;
                System.out.print(">> Valor erróneo. " + message);
            } finally {
                sc.nextLine();
            }

        } while (!ok);

        return value;
    }

    public static double readDouble(String message) {
        boolean ok;
        double value = Double.NaN;
        System.out.print(message);

        do {
            try {
                ok = true;
                value = sc.nextDouble();
            } catch (InputMismatchException e) {
                ok = false;
                System.out.print(">> Valor erróneo. " + message);
            } finally {
                sc.nextLine();
            }

        } while (!ok);

        return value;
    }

    public static boolean readBoolean(String message) {
        boolean ok;
        boolean value = false;
        System.out.print(message);

        do {
            try {
                ok = true;
                String str = sc.next().toLowerCase();
                if (str.equals("si")) {
                    value = true;
                } else if (str.equals("no")) {
                    value = false;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                ok = false;
                System.out.println(">> Se esperaba \"si\" o \"no\". " + message);
            } finally {
                sc.nextLine();
            }
        } while (!ok);
        return value;
    }

    public static char readChar(String message) {
        System.out.println(message);
        String value = sc.nextLine();
        char character = ENTER;
        if (value.length() > 0) {
            character = value.trim().charAt(0);
        }
        return character;
    }

    public static Calendar readDate(String message, String format) {
        boolean ok;
        Calendar date = Calendar.getInstance();
        System.out.println(message);

        do {
            try {
                ok = true;
                String strDate = sc.next();
                if (!strDate.toLowerCase().equals("hoy")) {
                    date.setTime(new SimpleDateFormat(format).parse(strDate));
                }

            } catch (IllegalArgumentException iae) {
                ok = false;
                System.out.print(">> Formato de fecha erroneo. " + message);
            } catch (ParseException pe) {
                ok = false;
                System.out.print(">> Fecha erronea. " + message);
            } finally {
                sc.nextLine();
            }

        } while (!ok);
        return date;
    }


    
}