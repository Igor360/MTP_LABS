package LAB6;

import lombok.Getter;
import lombok.Setter;

import java.security.Key;
import java.util.*;


/**
 * it is class whose search the longest concatenated word
 */
public class WordHandler {

   
    private static Map<Integer,List<String>> dictionaryWords = new HashMap<Integer, List<String>>();
    @Getter
    private static Set<Integer> keys = null;
    @Getter
    public static Map<String, List<String>> wordPart = new HashMap<>();


    /**
     * method whose return the Longest Concatenated Word
     * @return
     */
    public static String getLongestConcatenatedWord(Map<Integer,List<String>> dictionary){
            dictionaryWords = dictionary;
            keys = dictionaryWords.keySet();
            Map<Integer, List<String>> copyDic = copyDictionary();
            searchWord(copyDic);
            //delRepeatWords();
            int [] sizeConcatWorld = getSizeListConcat();
            Arrays.sort(sizeConcatWorld);
            return getWorldBySize(sizeConcatWorld[sizeConcatWorld.length-1]);
    }

    /**
     * method whose return the 2th Longest Concatenated Word
     * @return
     */
    public static String getLongestConcatenatedWord2(){
        int [] sizeConcatWorld = getSizeListConcat();
        Arrays.sort(sizeConcatWorld);
        String res = getWorldBySize(sizeConcatWorld[sizeConcatWorld.length-1]);
        return dictionaryWords.get(res.length()-1).get(0);
    }

    /**
     * the method whose copy the dictionary
     * @return
     */
    public static Map<Integer, List<String>> copyDictionary(){
        Map<Integer, List<String>> copyDic = new HashMap<>();
        Set<Integer> keys = dictionaryWords.keySet();
        for(Integer key: keys){
            copyDic.put(key, dictionaryWords.get(key));
        }
        return copyDic;
    }


    /**
     * the total count of all the concatenated words
     * @return
     */
    public static int getNumberConcatWords(){
        int iter = 0;
        Set<String> keys = wordPart.keySet();
        for (String key: keys) {
             if (wordPart.get(key).size() > 1){
                iter++;
             }
        }
        return iter;
    }


    /**
     * method whose search concatenated word
     * @param dic
     */
    private static void searchWord(Map<Integer,List<String>> dic){
        Set<Integer> keys = dic.keySet();
        if (keys.size() == 0){
            return;
        }
        int lastKey = (Integer) keys.toArray()[keys.size()-1];
        List<String> values = dic.get(lastKey);
        for (String value: values){
            searchInDictionaryWord(value);
            //addParts(value);
            if(!wordPart.containsKey(value)){
                setWorldPart(value, null);
            }
        }
        System.out.println(keys.size());
        dic.remove(lastKey);
        searchWord(dic);
    }

    /**
     * search concatenated words
     * @param word
     */
    private static void addParts(String word){
        char [] mainWord = deleteEnd(word.toCharArray());
        String timeValue = null;
        int base = 0;
        boolean is_number = false;
        for (int start = 0; start < mainWord.length;){
            base = start;
            is_number = false;
            for (int end = 2; (end+start) < mainWord.length; end+=1){
                timeValue = String.copyValueOf(mainWord, start, end);
                start += cmpWords(word, timeValue);
                if (base != start){
                    is_number = true;
                    break;
                }
            }
            if(!is_number){
                start++;
            }
        }
    }


    /**
     * method for comparing two words
     * @param mainWord
     * @param word
     * @return
     */
    private static int cmpWords(String mainWord, String word){
        int size = word.length();
        List<String> words = null;
        Integer key = null;
        if(dictionaryWords.containsKey(word.length())){
            words = dictionaryWords.get(word.length());
            if(words.contains(word))
            {
                setWorldPart(mainWord, word);
                return word.length();
            }
        }
        return 0;
    }

    /**
     * concatenated words
     * @param mainWord
     */
    private static void searchInDictionaryWord(String mainWord) {
        String newWord = String.valueOf(deleteEnd(mainWord.toCharArray()));
        for (Integer key : keys) {
            List<String> words = dictionaryWords.get(key);
            if (words  != null)
            for (String val : words) {
                if(!val.equals(newWord))
                if (mainWord.contains(val) && val.length() > 0 && val != mainWord) {
                    setWorldPart(mainWord, val);
                }
            }
        }
    }

    /**
     * The method that determines the size of lists of concatenated words
     * @return
     */
    private static int [] getSizeListConcat(){
        int [] sizeConcatWord  = new int[wordPart.size()];
        int iter = 0;
        Set<String> keys = wordPart.keySet();
        for (String key: keys) {
            sizeConcatWord[iter] = wordPart.get(key).size();
            iter++;
            if(iter > sizeConcatWord.length){
                break;
            }
        }
        return sizeConcatWord;
    }

    /**
     * The method that returns the key to the size of the list of concatenated words
     * @param size
     * @return
     */
    private static String getWorldBySize(int size){
        Set<String> keys = wordPart.keySet();
        String word = null;
        for (String key: keys) {
            if (wordPart.get(key).size() == size){
                word = key ;
               return  word;
            }
        }
        return word;
    }


    /**
     * setter for variable wordPart
     * @param key
     * @param value
     */
    public static void setWorldPart(String key, String value){
        List<String> listwords = new ArrayList<String>();
        if(wordPart.containsKey(key)){
            wordPart.get(key).add(value);
        }else {
            listwords.add(value);
            wordPart.put(key, listwords);
        }
    }

    /**
     * method that takes a list of words
     */
    private static void delRepeatWords(){
        Set<String> keys = wordPart.keySet();
        List<String> words = null;
        for (String key : keys) {
                words = wordPart.get(key);
                deleteRepeatPartWord(words);
        }
    }

    /**
     * method that removes duplicate words in the list
     * @param words
     */
    private static void deleteRepeatPartWord(List<String> words){
        int size = words.size();
        int iter = 1;
        String word = null;
        String val = null;
        while (iter < size){
            word = words.get(size-iter);
            for (int i = 0; i < size-iter; i++){
                val = words.get(i);
                if (word != null && comapareTwoWords(word, val)){
                    words.remove(val);
                    i--;
                }
                size = words.size();
            }
            iter++;
        }
    }

    /**
     * method for comparing two words
     * @param mainWord
     * @param word
     * @return
     */
    private static boolean comapareTwoWords(String mainWord,String word){
        char [] words = deleteEnd(word.toCharArray());
        String  cmpWord = null;
        if (words.length > 2){
            boolean isCompare = false;
            MAIN:for (int end = 2; end < words.length; end++){
                for (int start = 0; start < end; start++){
                cmpWord = String.copyValueOf(words, start , end-start);
                    if (mainWord.contains(cmpWord) && cmpWord.length() >= 2){
                        isCompare = true;
                        break MAIN;
                    }
                }
            }
            return isCompare;
        }else {
            if(mainWord.contains(word)) {
                 return true;
            }else {
                return false;
            }
        }
    }


    /**
     * A method that removes the end of a word if it ends with 's'
     * @param word
     * @return
     */
    private static char [] deleteEnd(char [] word){
        if(word.length > 0 && word[word.length-1] == 's'){
            char[] newWord = new char[word.length-1];
            for (int i = 0; i < newWord.length; i++){
                newWord[i] = word[i];
            }
            return newWord;
        }else {
            return word;
        }
    }

    /**
     * method whose showing concatenated words
     */
    public static void showWordsPart(){
        Set<String> keys = wordPart.keySet();
        for (String key: keys) {
            System.out.println(String.format("%s : %s \n", key, wordPart.get(key).toString()));
        }
    }

    /**
     * getter for variable dictionaryWords
     * @return
     */
    public static Map<Integer,List<String>> getDictionaryWords() {
        return dictionaryWords;
    }

}
