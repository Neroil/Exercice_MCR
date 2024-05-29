package org.example;

// LegacyPrinter interface
interface LegacyPrinter {
    void printText(String text);
    void printCharacters(char[] chars);
}

class LegacyPrinterImpl implements LegacyPrinter{

    public LegacyPrinterImpl(){

    }

    @Override
    public void printText(String text) {
        System.out.println(text);
    }

    @Override
    public void printCharacters(char[] chars) {
        System.out.println(chars);
    }
}

// ModernPrinter interface
interface ModernPrinter {
    void print(String message);
}

class LegacyPrinterAdapter implements ModernPrinter{

    private final LegacyPrinter printer;

    public LegacyPrinterAdapter(LegacyPrinter l){
        printer = l;
    }

    @Override
    public void print(String message) {
        printer.printText(message);
    }
}


public class Main {



    public static void main(String[] args) {
        // Create an instance of LegacyPrinterImpl
        LegacyPrinter legacyPrinter = new LegacyPrinterImpl();

        // Create an instance of LegacyPrinterAdapter
        ModernPrinter modernPrinter = new LegacyPrinterAdapter(legacyPrinter);

        // Use the ModernPrinter interface to print messages
        modernPrinter.print("Hello, Adapter Pattern!");
        modernPrinter.print("This is a legacy printer.");
    }
}