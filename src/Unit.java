
import java.util.*;

public class Unit {
    private final Map<String,String> languagesAndContent; //key = language, value = text

    public Unit(Map<String,String> languagesAndContent)
    {
        this.languagesAndContent = new HashMap<>(languagesAndContent);
    }

    public String makeString(List<String> languages)
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
        }
        return finalString;
    }

    //for testing. to be deleted.
    public static void main(String[] args)
    {
        HashMap<String,String> landC = new HashMap<>();
        landC.put("en","Hello everyone");
        landC.put("fr","Bonjour tout le monde");
        Unit aUnit = new Unit(landC);
        ArrayList<String> langs = new ArrayList<>();
        langs.add("en");
        langs.add("fr");
        System.out.println(aUnit.makeString(langs));


    }


}
