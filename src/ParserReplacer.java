import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserReplacer {
    private final String completeText;

    public ParserReplacer(String completeText)
    {
        this.completeText = completeText;
    }

    public String generate(List<String> languages)
    {
        Pattern p = Pattern.compile("(?<!\\\\)\\{%(.*)(?<!\\\\)%}"); //matching (no \){%....(no \)%}
        Matcher m = p.matcher(completeText);
        String result = "";
        int prevEnd = 0;
        while(m.find())
        {
            int s = m.start();
            int e = m.end();
            result+=completeText.substring(prevEnd,s);
            result+=parse(completeText.substring(s,e)).makeString(languages);
            prevEnd = e;
        }
        result+=completeText.substring(prevEnd);
        return result;
    }


    private Unit parse(String toBeParsed) //toBeParsed is of format {% la = "..." lb = "..." %}
    {
        toBeParsed = toBeParsed.substring(2,toBeParsed.length()-2); //remove {% and }%
        Pattern p = Pattern.compile("([^\\s]+)\\s*:\\s*\"(.*?)(?<!\\\\)\""); //matches langName : "text"
        Matcher m = p.matcher(toBeParsed);
        Map<String,String> langtoText = new HashMap<>();
        while(m.find())
        {
            langtoText.put(m.group(1),m.group(2).replaceAll("\\\\\"","\""));
        }
        return new Unit(langtoText);
    }

}
