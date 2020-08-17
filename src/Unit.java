
import java.util.*;

public class Unit {
    private final Map<String,String> languagesAndContent; //key = language, value = text

    public Unit(Map<String,String> languagesAndContent)
    {
        this.languagesAndContent = new HashMap<>(languagesAndContent);
    }

    public String makeString(List<String> languages) //create the final text that contains one or more languages. Any language that is not supported by this Unit will be ignored.
    {
        String finalString = "";
        Iterator<String> languagesIterator = languages.iterator();
        while(languagesIterator.hasNext())
        {
            String i = languagesIterator.next();
            if(languagesAndContent.containsKey(i)) {
                finalString += languagesAndContent.get(i);
                if(languagesIterator.hasNext())
                {
                    finalString +="/";
                }
            }
            else {
                System.out.println("Warning: language "+i+" is ignored.");
                if (finalString.length()>0)
                    finalString = finalString.substring(0,finalString.length()-1);
            }
        }
        return finalString;
    }




}
