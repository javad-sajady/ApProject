package ApProject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner=new Scanner(System.in);
        new Cli(scanner).login();
    }
}
