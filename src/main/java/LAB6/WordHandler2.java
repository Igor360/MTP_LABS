package LAB6;

import lombok.Getter;

import java.util.*;

public class WordHandler2 {

    @Getter
    private static Map<Integer,List<String>> dictionaryWords = new HashMap<Integer, List<String>>();
    private static Set<Integer> keys = null;
    private static Map<String, List<String>> wordPart = new HashMap<>();

    public static String getLongestConcatenatedWord(Map<Integer,List<String>> dictionary){
        String result = null;
       // sortDataBySize(listWords);
        dictionaryWords = dictionary;
        keys = dictionary.keySet();
        Map<Integer, List<String>> copyDic = copyDictionary();
        searchWord(copyDic);
        delRepeatWords();
        return getConcatWorld();
    }

    public static Map<Integer, List<String>> copyDictionary(){
        Map<Integer, List<String>> copyDic = new HashMap<>();
        Set<Integer> keys = dictionaryWords.keySet();
        for(Integer key: keys){
            copyDic.put(key, dictionaryWords.get(key));
        }
        return copyDic;
    }

    public static String getLongestConcatenatedWord2(){
        int [] sizeConcatWorld = getSizeListConcat();
        Arrays.sort(sizeConcatWorld);
        String res = getWorldBySize(sizeConcatWorld[sizeConcatWorld.length-1]);
        return dictionaryWords.get(res.length()-1).get(0);
    }

    private static void sortDataBySize(List<String> words){
        int sizeWord = 0;
        List<String> listwords = new ArrayList<String>();
        for (String word: words) {
            sizeWord = word.length();
            if(dictionaryWords.containsKey(sizeWord)){
                dictionaryWords.get(sizeWord).add(word);
            }else {
                listwords.add(word);
                dictionaryWords.put(sizeWord, listwords);
            }
            sizeWord = 0;
            listwords = new ArrayList<String>();
        }
        keys = dictionaryWords.keySet();
    }

    private static void searchWord(Map<Integer,List<String>> dic){
        Set<Integer> keys = dic.keySet();
        if (keys.size() == 0){
            return;
        }
        int lastKey = (Integer) keys.toArray()[keys.size()-1];
        List<String> values = dic.get(lastKey);
        for (String value: values){
            addParts(value);
        }
        System.out.println(keys.size());
        dic.remove(lastKey);
        searchWord(dic);
    }

    private static void addParts(String word){
        searchInDictionaryWord(word);

    }

    private static void searchInDictionaryWord(String mainWord) {
        for (Integer key : keys) {
            List<String> words = dictionaryWords.get(key);
            if (words  != null)
                for (String val : words) {
                    if (mainWord.contains(val) && val.length() > 0 && val != mainWord) {
                        setWorldPart(mainWord, val);
                    }
                }
        }
    }

    private static String getConcatWorld(){
        String res = null;
        int [] sizeConcatWorld = getSizeListConcat();
        Arrays.sort(sizeConcatWorld);
        return getWorldBySize(sizeConcatWorld[sizeConcatWorld.length-1]);
    }

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

    private static String getWorldBySize(int size){
        Set<String> keys = wordPart.keySet();
        String resultKey= null;
        for (String key: keys) {
            if (wordPart.get(key).size() == size){
                resultKey = key;
            }
        }
        return resultKey;
    }



    public static void setWorldPart(String key, String value){
        List<String> listwords = new ArrayList<String>();
        if(wordPart.containsKey(key)){
            wordPart.get(key).add(value);
        }else {
            listwords.add(value);
            wordPart.put(key, listwords);
        }
    }

    private static void delRepeatWords(){
        Set<String> keys = wordPart.keySet();
        List<String> words = new ArrayList<>();
        for (String key : keys) {
            words = wordPart.get(key);
            deleteRepeatPartWord(key, words);
        }

    }

    private static void deleteRepeatPartWord(String key, List<String> words){
        int size = words.size();
        int iter = 1;
        char [] mainWordChars = key.toCharArray();
        String word = null;
        String val = null;
        for(int end = 0; end < mainWordChars.length; end++){
            for (int start = 0; start < end; start++){
                word = String.valueOf(mainWordChars, start, end-start);

            }
        }
     }

    private static String comapareTwoWords(String mainWord, List<String> words){
        String previousWord = null;
        String newWord = null;
        for (int i = 0; i < words.size(); i++){
            newWord = words.get(i);
            if (mainWord.contains(newWord)){
                if  (previousWord != null){

                }else {
                   previousWord = words.get(i);

                }

            }
        }
        return null;
    }

    private static char [] deleteEnd(char [] word){
        if(word[word.length-1] == 's'){
            char[] newWord = new char[word.length-2];
            for (int i = 0; i < newWord.length; i++){
                newWord[i]=word[i];
            }
            return newWord;
        }else {
            return word;
        }
    }


    public static void showWordsPart(){
        Set<String> keys = wordPart.keySet();
        for (String key: keys) {
            System.out.println(String.format("%s : %s \n", key, wordPart.get(key).toString()));
        }
    }

    public static Map<String,List<String>> getWordPart(){
        return wordPart;
    }

    public static int getDictionarySize(){
        return dictionaryWords.size();
    }

    public static Map<Integer, List<String>> getDictionaryWords() {
        return dictionaryWords;
    }

    private static void addParts2(String word){
        char [] mainWord = deleteEnd(word.toCharArray());
        String timeValue = null;
        int base = 0;
        boolean is_number = false;
        for (int start = 0; start < mainWord.length;){
            base = start;
            is_number = false;
            for (int end = 3; (end+start) < mainWord.length; end+=1){
                timeValue = String.copyValueOf(mainWord, start, end);
                //start += cmpWords(word, timeValue);
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

}
