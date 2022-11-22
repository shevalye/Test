package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import java.util.List;
import java.util.TreeMap;

public class Cats3 {

    public static void kittenColors(String female, String male) {

        Map<String, ArrayList<String>> colorGenesM = new HashMap<>();
        //B-D- Black
        colorGenesM.put("Black",new ArrayList<>(Arrays.asList("BbDdo","BbDDo","BBDDo","BBDdo")));
        //B-dd Blue
        colorGenesM.put("Blue",new ArrayList<>(Arrays.asList("Bbddo", "BBddo")));
        //bbD-: Chocolate
        colorGenesM.put("Chocolate",new ArrayList<>(Arrays.asList("bbDdo", "bbDDo")));
        //Lilac bbdd
        colorGenesM.put("Lilac",new ArrayList<>(Arrays.asList("bbddo","bbddo","bbddo","bbddo","bbddo","bbddo","bbddo","bbddo","bbddo","bbddo","bbddo","bbddo")));
        //D-O: Red
        colorGenesM.put("Red",new ArrayList<>(Arrays.asList("bbDdO","bbDDO","BbDdO","BbDDO","BBDDO","BBDdO")));
        //ddO: Cream
        colorGenesM.put("Cream",new ArrayList<>(Arrays.asList("bbddO","BbddO", "BBddO")));
        Map<String, ArrayList<String>> colorGenesF = new HashMap<>();
        colorGenesF.put("Black",new ArrayList<>(Arrays.asList("BbDdoo","BbDDoo","BBDDoo","BBDdoo")));
        colorGenesF.put("Blue",new ArrayList<>(Arrays.asList("Bbddoo","BBddoo","Bbddoo","BBddoo","Bbddoo", "BBddoo","Bbddoo","BBddoo")));
        colorGenesF.put("Chocolate",new ArrayList<>(Arrays.asList( "bbDdoo","bbDDoo")));
        colorGenesF.put("Lilac",new ArrayList<>(Arrays.asList("bbddoo")));
        colorGenesF.put("Cream",new ArrayList<>(Arrays.asList("bbddOO","BbddOO", "BBddOO")));
        colorGenesF.put("Red",new ArrayList<>(Arrays.asList("bbDdOO","bbDDOO","BbDdOO","BbDDOO","BBDDOO","BBDdOO")));
        //B-D-Oo: Black-Red Tortie
        colorGenesF.put("Black-Red Tortie",new ArrayList<>(Arrays.asList("BbDDOo","BbDdOo","BBDDOo","BBDdOo","BbDDoO","BbDdoO","BBDDoO","BBDdoO")));
        //B-ddOo: Blue-Cream Tortie
        colorGenesF.put("Blue-Cream Tortie",new ArrayList<>(Arrays.asList("BbddOo","BBddOo","BbddoO","BBddoO")));
        //bbD-Oo: Chocolate-Red Tortie
        colorGenesF.put("Chocolate-Red Tortie",new ArrayList<>(Arrays.asList("bbDdOo", "bbDDOo","bbDdoO", "bbDDoO")));
        //bbddOo: Lilac-Cream Tortie
        colorGenesF.put("Lilac-Cream Tortie",new ArrayList<>(Arrays.asList("bbddOo","bbddoO")));

        HashMap<String,String> femaleColors = new HashMap<>();
        for(Map.Entry<String,ArrayList<String>> entry: colorGenesF.entrySet()){
            for(String gene : entry.getValue()){
                femaleColors.put(gene,entry.getKey());
            }
        }
        HashMap<String,String> maleColors = new HashMap<>();
        for(Map.Entry<String,ArrayList<String>> entry: colorGenesM.entrySet()){
            for(String gene : entry.getValue()){
                maleColors.put(gene,entry.getKey());
            }
        }
        ArrayList<String> fatherColorGenes = colorGenesM.get(male);
        ArrayList<String> motherColorGenes = colorGenesF.get(female);
        int combo = fatherColorGenes.size() * motherColorGenes.size();
        Map<String, Integer>  colorProbability = new LinkedHashMap<>();
        colorProbability.put("Black", 0);
        colorProbability.put("Blue", 0);
        colorProbability.put("Chocolate", 0);
        colorProbability.put("Lilac", 0);
        colorProbability.put("Red", 0);
        colorProbability.put("Cream", 0);
        colorProbability.put("Black-Red Tortie", 0);
        colorProbability.put("Blue-Cream Tortie", 0);
        colorProbability.put("Chocolate-Red Tortie", 0);
        colorProbability.put("Lilac-Cream Tortie", 0);
        ArrayList<String> t = combineGenes("BbDDO","BbDDOo");
        for(String father : fatherColorGenes){
            for(String mother: motherColorGenes){
                ArrayList<String> temp = combineGenes(father,mother);
                t =temp;
                for (String s: temp){
                    if (maleColors.get(s + mother.charAt(mother.length()-1))!=null) {
                        String color = maleColors.get(s + mother.charAt(mother.length() - 1));
                        colorProbability.put(color, colorProbability.get(color)
                                + 1);
                    }
                    if(maleColors.get(s + mother.charAt(mother.length()-2))!=null) {
                       String color = maleColors.get(s + mother.charAt(mother.length() - 2));
                        colorProbability.put(color, colorProbability.get(color) + 1);
                    }
                    if(femaleColors.get(s + father.charAt(father.length()-1) + mother.charAt(mother.length()-1))!=null) {
                     String  color = femaleColors.get(s + father.charAt(father.length() - 1) + mother.charAt(mother.length() - 1));
                        colorProbability.put(color, colorProbability.get(color)
                                + 1);
                    }
                    if(femaleColors.get(s + father.charAt(father.length()-1) + mother.charAt(mother.length()-2))!=null) {
                    String  color = femaleColors.get(s + father.charAt(father.length() - 1) + mother.charAt(mother.length() - 2));
                        colorProbability.put(color, colorProbability.get(color)
                                + 1);
                    }

                }
            }

        }
        MyComparator comparator = new MyComparator(colorProbability);
        Map<String, Integer> newMap = new TreeMap<String, Integer>(comparator);
        newMap.putAll(colorProbability);

        for(String key : newMap.keySet()){
            System.out.println(key);
            System.out.println(colorProbability.get(key)/(combo*t.size()*4.0));
        }

        };
    static class MyComparator implements Comparator<Object> {

        Map<String, Integer> map;

        public MyComparator(Map<String, Integer> map) {
            this.map = map;
        }

        public int compare(Object o1, Object o2) {

            if ((map.get(o2)).compareTo(
                    map.get(o1))==0){
                return ((String) o1).compareTo((String) o2);
            }
            else {
                return (( map.get(o2)).compareTo(
                        map.get(o1)));
            }
        }
    }


    public static ArrayList<String> combineGenes(String father, String mother){
        ArrayList<String> temp = new ArrayList<>();
        temp.add(father.charAt(0)+""+ mother.charAt(0));
        temp.add(father.charAt(0)+""+ mother.charAt(1));
        temp.add(father.charAt(1)+""+ mother.charAt(0));
        temp.add(father.charAt(1)+""+ mother.charAt(1));

        for(int i = 2; i < father.length()-2; i++){
            ArrayList<String> temp1 = new ArrayList<>();
            for(String s : temp) {
                temp1.add(s + father.charAt(i)+""+ mother.charAt(i));
                temp1.add(s + father.charAt(i)+""+ mother.charAt(i+1));
                temp1.add(s + father.charAt(i+1)+""+ mother.charAt(i));
                temp1.add(s + father.charAt(i+1)+""+ mother.charAt(i+1));

            }
            temp = temp1;
            i++;
        }
        return temp;

    }
}