import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MultiMarkDown {
    public static void main(String[] args)
    {
        String content ="";
       try
       {
           content = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
       }
       catch (IOException e)
       {
           System.out.println("Unable to open file.");
       }

       List<String> languages = Arrays.asList(args);
       languages = languages.subList(1,languages.size());
       ParserReplacer aParser = new ParserReplacer(content);

       String newFileName = "out";
       try
       {
           File f = new File(args[0]);
           newFileName = f.getName().replaceFirst("[.][^.]+$", "");
       }
       catch (Exception e)
       {
           System.out.println("Error while reading file");
       }

        for(String i : languages)
        {
            newFileName+="_";
            newFileName+=i;
        }
        newFileName+=".md";
        boolean createFileSuccess = false;
        try
        {
            File outputFile = new File(newFileName);
            if(!outputFile.createNewFile()) //create the file and see if it already exists.
            {
                System.out.println("The programme is trying to create file "+newFileName+" but it already exists. Would you like to override it? (y/n)");
                Scanner s = new Scanner(System.in);
                if(!s.nextLine().equals("y")) //if user does not enter 'y', then abort and terminate programme.
                {
                    System.out.println("Aborted");
                    System.exit(0);
                }
            }
            FileWriter writer = new FileWriter(outputFile);
            writer.write(aParser.generate(languages));
            writer.close();
            System.out.println("Success");
        }
        catch(Exception e)
        {
            System.out.println("Error while reading file");
        }





    }
}
