package LAB6;

import LAB5.WorkInterface;

import java.util.List;

/**
 * class whose show on screen results work
 */
public class Interface {
    public static void main(String [] args){
        try {
            ReadFile file = null;
            try {
                file = new ReadFile("words.txt");
                file.read();
            } catch (Exception e) {
                e.printStackTrace();
            }


            WordHandler.showWordsPart();
            long end = 0,start=0;


            start = System.nanoTime();
            String res = WordHandler.getLongestConcatenatedWord(file.getDictionaryWords());
            end = System.nanoTime();
            System.out.println(String.format("the longest concatenated word: %s , time : %s",res,WorkInterface.changeTimeFormat(end - start)));

            start = System.nanoTime();
            String res2 = WordHandler.getLongestConcatenatedWord2();
            end = System.nanoTime();
            System.out.println(String.format("the 2nd longest concatenated word: %s, time : %s", res2, WorkInterface.changeTimeFormat(end - start)));

            start = System.nanoTime();
            Integer numberConcatWords = WordHandler.getNumberConcatWords();
            start = System.nanoTime();
            System.out.println(String.format("the total count of concatenated words in the file : %s, time: %s", numberConcatWords, WorkInterface.changeTimeFormat(end - start)));



            System.out.println(WordHandler.wordPart.get("methylcholanthrenes"));
            System.out.println(WordHandler.wordPart.get("ethylenediaminetetraacetates"));
            System.out.println(WordHandler.wordPart.get("electroencephalographically"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
