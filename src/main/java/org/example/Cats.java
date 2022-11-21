package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import java.util.List;
import java.util.TreeMap;

public class Cats {

    public static void kittenColors(String female, String male) {

        Map<String, ArrayList<String>> colorGenesM = new HashMap<>();
        //B-D- Black
        colorGenesM.put("Black",new ArrayList<>(Arrays.asList("BbDdo","BbDDo","BbdDo","bBDDo","bBDdo","bBdDo","BBDDo","BBDdo","BBdDo")));
        //B-dd Blue
        colorGenesM.put("Blue",new ArrayList<>(Arrays.asList("Bbddo", "BBddo","bBddo")));
        //bbD-: Chocolate
        colorGenesM.put("Chocolate",new ArrayList<>(Arrays.asList("bbDdo", "bbdDo","bbDDo")));
        //Lilac bbdd
        colorGenesM.put("Lilac",new ArrayList<>(Arrays.asList("bbddo")));
        //D-O: Red
        colorGenesM.put("Red",new ArrayList<>(Arrays.asList("bbDdO","bbDDO","bbdDO","BbDdO","BbDDO","BbdDO","bBDDO","bBDdO","bBdDO","BBDDO","BBDdO","BBdDO")));
        //ddO: Cream
        colorGenesM.put("Cream",new ArrayList<>(Arrays.asList("bbddO","bBddO","BbddO", "BBddO")));
        Map<String, ArrayList<String>> colorGenesF = new HashMap<>();
        colorGenesF.put("Black",new ArrayList<>(Arrays.asList("BbDdoo","BbDDoo","BbdDoo","bBDDoo","bBDdoo","bBdDoo","BBDDoo","BBDdoo","BBdDoo")));
        colorGenesF.put("Blue",new ArrayList<>(Arrays.asList("Bbddoo","bBddoo", "BBddoo")));
        colorGenesF.put("Chocolate",new ArrayList<>(Arrays.asList("bbdDoo", "bbDdoo","bbDDoo")));
        colorGenesF.put("Lilac",new ArrayList<>(Arrays.asList("bbddoo")));
        colorGenesF.put("Cream",new ArrayList<>(Arrays.asList("bbddOO","bBddOO","BbddOO", "BBddOO")));
        colorGenesF.put("Red",new ArrayList<>(Arrays.asList("bbDdOO","bbDDOO","bbdDOO","BbDdOO","BbDDOO","BbdDOO","bBDDOO","bBDdOO"
                ,"bBdDOO","BBDDOO","BBDdOO","BBdDOO")));
       //B-D-Oo: Black-Red Tortie
        colorGenesF.put("Black-Red Tortie",new ArrayList<>(Arrays.asList("bBDDOo","bBDdOo","bBdDOo","bBDDoO","bBDdoO","bBdDoO",
                "BbDDOo","BbDdOo","BbdDOo","BbDDoO","BbDdoO","BbdDoO","BBDDOo","BBDdOo","BBdDOo","BBDDoO","BBDdoO","BBdDoO")));
        //B-ddOo: Blue-Cream Tortie
        colorGenesF.put("Blue-Cream Tortie",new ArrayList<>(Arrays.asList("BbddOo","bBddOo", "BBddOo","BbddoO","bBddoO", "BBddoO")));
        //bbD-Oo: Chocolate-Red Tortie
        colorGenesF.put("Chocolate-Red Tortie",new ArrayList<>(Arrays.asList("bbDdOo", "bbDDOo","bbdDOo","bbDdoO", "bbDDoO","bbdDoO")));
        //bbddOo: Lilac-Cream Tortie
        colorGenesF.put("Lilac-Cream Tortie",new ArrayList<>(Arrays.asList("bbddOo", "bbddOo", "bbddoO", "bbddoO")));

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
        Map<String, BigDecimal>  colorProbability = new LinkedHashMap<>();
        colorProbability.put("Black", new BigDecimal(0.0));
        colorProbability.put("Blue", new BigDecimal(0.0));
        colorProbability.put("Chocolate", new BigDecimal(0.0));
        colorProbability.put("Lilac", new BigDecimal(0.0));
        colorProbability.put("Red", new BigDecimal(0.0));
        colorProbability.put("Cream", new BigDecimal(0.0));
        colorProbability.put("Black-Red Tortie", new BigDecimal(0.0));
        colorProbability.put("Blue-Cream Tortie", new BigDecimal(0.0));
        colorProbability.put("Chocolate-Red Tortie", new BigDecimal(0.0));
        colorProbability.put("Lilac-Cream Tortie", new BigDecimal(0.0));
        for(String father : fatherColorGenes){
            for(String mother: motherColorGenes){
                ArrayList<String> temp = combineGenes(father,mother);
                for (String s: temp){

                    String color = maleColors.get(s + mother.charAt(mother.length()-1));
                    colorProbability.put(color,colorProbability.get(color)
                            .add(new BigDecimal
                                    ("1.0").divide(new BigDecimal(temp.size()*2*combo*2),20, RoundingMode.HALF_UP)));
                    color = maleColors.get(s + mother.charAt(mother.length()-2));
                    colorProbability.put(color,colorProbability.get(color)
                            .add(new BigDecimal
                                    ("1.0").divide(new BigDecimal(temp.size()*2*combo*2),20, RoundingMode.HALF_UP)));


                    color = femaleColors.get(s + father.charAt(father.length()-1) + mother.charAt(mother.length()-1));
                    colorProbability.put(color,colorProbability.get(color)
                            .add(new BigDecimal
                                    ("1.0").divide(new BigDecimal(temp.size()*4*combo),20, RoundingMode.HALF_UP)));

                    color = femaleColors.get(s + father.charAt(father.length()-1) + mother.charAt(mother.length()-2));
                    colorProbability.put(color,colorProbability.get(color)
                            .add(new BigDecimal
                                    ("1.0").divide(new BigDecimal(temp.size()*4*combo),20, RoundingMode.HALF_UP)));


                }
            }

        }
        for(String key : colorProbability.keySet()){
            System.out.println(key);
            System.out.println(colorProbability.get(key));
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
