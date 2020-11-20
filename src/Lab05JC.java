import com.csc241.*;

import java.io.File;
import java.util.Scanner;

public class Lab05JC {

    public static void main(String[] args) {
        HshTbl hashtable = new HshTbl();
        Scanner scanner = new Scanner(System.in);
        StringBuilder msg = new StringBuilder("");
        String yn = "Y";
        String searchYn = "Y";

        String fileName = null;
        while( Character.toUpperCase( yn.charAt( 0 ) ) == 'Y' ){
            boolean fileExists = false;

            msg.delete(0, msg.length());

            if (!fileExists){
                msg.append("\nEnter the file you want to read - ");

                System.out.print(msg);

                msg.delete(0, msg.length());

                fileName = scanner.nextLine();

                File file = new File(fileName);

                fileExists = file.exists();

                if (!fileExists) {

                    msg.delete(0, msg.length());

                    msg.append(fileName).append(" could not be opened");

                    System.out.print(msg);
                }
            }
            if (fileExists){
                File file = new File(fileName);

                long recordSize = file.length() / Data.DATALN;

                msg.append("\nPreparing to read ").append(fileName);

                System.out.print(msg);

                msg.delete(0, msg.length());

                msg.append("\n").append(fileName).append(" has ").append(recordSize).append(" records. ");

                System.out.print(msg);

                msg.delete(0, msg.length());

                hashtable.createFromFile(fileName);
                searchYn = "Y";


                while(Character.toUpperCase(searchYn.charAt(0)) == 'Y') {
                    msg.delete(0, msg.length());

                    msg.append("\nEnter the search SSN (no separators please) - ");

                    System.out.print(msg);
                    String ssnInput = scanner.nextLine();

                    msg.delete(0, msg.length());

                  msg.append("\nSearching for SSN ").append(Data.fmtSsn(ssnInput)).append("\n");

                    System.out.print(msg);
                    Data data = new Data();
                    data.setSsn(ssnInput);
                    hashtable.search(data);
                    msg.delete(0, msg.length());

                    msg.append("\n\nDo you wish to continue with another SSN (Y\\N)? - ");

                    System.out.print(msg);

                    searchYn = scanner.nextLine();
                }


            }


            msg.delete(0, msg.length());

            msg.append("\nDo you wish to continue with another file (Y\\N)? - ");

            System.out.print(msg);

            yn = scanner.nextLine();



        }


    }
}

