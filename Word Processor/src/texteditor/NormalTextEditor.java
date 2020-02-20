package texteditor;

import patterns.Font;
import patterns.FontStyle;
import patterns.UnsharedCharacter;

import java.util.ArrayList;

/**
 * @author shwetan
 * This class reprsents normal text editor example
 * without flyweight pattern
 */
public class NormalTextEditor {
    public void create(){
        String text = "CS 635 Advanced Object-Oriented Design & Programming\n" +
                "Fall Semester, 2018\n" +
                "Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object\n" +
                "Nov 19, 2019\n" +
                "Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney, 5500 Campanile Drive, San\n" +
                "Diego, CA 92182-7700 USA. OpenContent (http://www.opencontent.org/opl.shtml) license defines the " +
                "copyright on this document.";
        int fontOneLength = 144;
        int fontTwoStart = 144;
        char[] charArray = text.toCharArray();
        ArrayList<UnsharedCharacter> characterList = new ArrayList<>();
        for(int i=0;i<fontOneLength;i++){
            characterList.add(new UnsharedCharacter(charArray[i], new Font("TIMES NEW ROMAN", FontStyle.BOLD.getStyeleCode(),12)));
        }
        for(int j=fontTwoStart;j<charArray.length;j++){
            characterList.add(new UnsharedCharacter(charArray[j],new Font("Calibri",FontStyle.ITALIC.getStyeleCode(),12)));
        }
    }
}
