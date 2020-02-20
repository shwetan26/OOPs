package texteditor;

import patterns.*;
import java.util.ArrayList;

/**
 * @author shwetan
 * This class represents text editor using flyweight pattern
 */
public class FlyweightTextEditor{

    public void create() {
        String text = "CS 635 Advanced Object-Oriented Design & Programming\n" +
                "Fall Semester, 2018\n" +
                "Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object\n" +
                "Nov 19, 2019\n" +
                "Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney, 5500 Campanile Drive, San\n" +
                "Diego, CA 92182-7700 USA. OpenContent (http://www.opencontent.org/opl.shtml) license defines the " +
                "copyright on this document.";

        FontFactory fontFactory = FontFactory.getInstance();
        Font fontA = fontFactory.getFont("TIMES NEW ROMAN",FontStyle.BOLD.getStyeleCode(),12);
        Font fontB = fontFactory.getFont("Calibri",FontStyle.ITALIC.getStyeleCode(),12);
        RunArray runArray = new RunArray();
        runArray.addRun(0,144,fontA);
        runArray.addRun(144,212,fontB);
        char[] charArray = text.toCharArray();
        ArrayList<FlyweightCharacter> characterList = new ArrayList<FlyweightCharacter>();
        CharacterFactory characterFactory = CharacterFactory.getFactoryInstance();
        for(int i=0;i<charArray.length;i++){
            FlyweightCharacter character = characterFactory.getCharacter(charArray[i]);
            if(!characterList.contains(character)){
                characterList.add(character);
            }
        }
    }
}
