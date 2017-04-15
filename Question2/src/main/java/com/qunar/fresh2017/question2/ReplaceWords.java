package com.qunar.fresh2017.question2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用indexedWordList按规则替换sentenceList中内容
 * Created by shuai.lv on 2017/2/22.
 */
public class ReplaceWords {

    private static final String NATURE_ORDER= "natureOrder";
    private static final String INDEX_ORDER= "indexOrder";
    private static final String CHAR_ORDER= "charOrder";
    private static final String CHAR_ORDER_DESC= "charOrderDESC";

    private List<IndexedWord> natureOrderList; //自然排序文本列表,便于按自然序查找
    private List<IndexedWord> charOrderList;   //文本排序列表，便于按文本顺序和文本倒序查找
    private HashMap<Integer,String> indexMap;  //哈希map存储，index为key，便于按索引查找

    public ReplaceWords(List<IndexedWord> natureOrderList) {
        this.natureOrderList = natureOrderList;
    }

    /*初始化*/
    public void initial(){
        charOrderList = new ArrayList<IndexedWord>();
        charOrderList.addAll(natureOrderList);
        Collections.sort(charOrderList, new Comparator<IndexedWord>() {
            @Override
            public int compare(IndexedWord o1, IndexedWord o2) {
                return o1.getWord().compareTo(o2.getWord());
            }
        });

        indexMap = new HashMap<Integer, String>();
        for (IndexedWord indexedWord : natureOrderList) {
            if (!indexMap.containsKey(indexedWord.getIndex())) {
                indexMap.put(indexedWord.getIndex(),indexedWord.getWord());
            }
        }
    }

    public String findByNatureOrder(int index){
        return natureOrderList.get(index).getWord();
    }

    public String findByIndexOrder(int index){
        return indexMap.get(index);
    }

    public String findByCharOrder(int index){
        return charOrderList.get(index).getWord();
    }

    public String findByCharOrderDESC(int index){
        return charOrderList.get(charOrderList.size() +1 - index).getWord();
    }


    public String replace(String line){
        Pattern pattern = Pattern.compile("\\$[a-zA-Z]+\\([0-9]+\\)");
        Matcher matcher = pattern.matcher(line);
        String result = "";
        while (matcher.find()){
            String s = matcher.group();
            String order = s.split("\\$")[1].split("\\(")[0]; //取出排序方式
            String index = s.split("\\(")[1].split("\\)")[0]; //取出索引号

            /*根据排序方式，选择对应的查找方法进行替换*/
            if(order.equals(NATURE_ORDER)){
                result = line.replace(s, findByNatureOrder(Integer.parseInt(index)));
            }
            if(order.equals(INDEX_ORDER)){
                result = line.replace(s, findByIndexOrder(Integer.parseInt(index)));
            }
            if(order.equals(CHAR_ORDER)){
                result = line.replace(s, findByCharOrder(Integer.parseInt(index)));
            }
            if(order.equals(CHAR_ORDER_DESC)){
                result = line.replace(s, findByCharOrderDESC(Integer.parseInt(index)));
            }
        }
        return result;
    }

    public List<String> replaceAll(List<String> sentenceList){
        List<String> resultList = new ArrayList<String>();
        for (String line : sentenceList) {
            if(!line.contains("$")){ //如果不包含$，直接跳过，减小解析字符串开销
                resultList.add(line);
            }else{
                resultList.add(replace(line));
            }
        }
        return resultList;
    }

}
