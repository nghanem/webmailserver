package com.nabeeh.webmail;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;



public class SpamFilter {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    /*
     * You need to set original training set path
     */
    String hamPath = "./Spam/ham_train/";
    String spamPath = "./Spam/spam_train/";

    HashMap<String, Integer> hamTable = new HashMap<String, Integer>();
    HashMap<String, Integer> spamTable = new HashMap<String, Integer>();
    HashMap spamProbTable = new HashMap();

    LuceneSearch luceneForSpam = new LuceneSearch();

    double HAM_SIZE, SPAM_SIZE;

    public SpamFilter() {
        luceneForSpam.SpamDataIndex();
        HAM_SIZE = DBManager.getTrainSize("TrainSize_HamSize");
        SPAM_SIZE = DBManager.getTrainSize("TrainSize_SpamSize");
    }

    public boolean spamChecker(String content) {

        String[] words = commonTenWords(parse(content));

        double spamPossibility = luceneForSpam.spamSearch(words);


        if (spamPossibility > 0.9) {
            return true;
        }
        return false;
    }

    public String[] commonTenWords(String[] words) {
        HashMap<String, Integer> wordSet = new HashMap<String, Integer>();

        for (String w : words) {
            if (w.equals("")) {
                continue;
            }
            int value = 0;
            if (wordSet.containsKey(w)) {
                value = wordSet.get(w);
                wordSet.remove(w);
            }
            wordSet.put(w, value + 1);
        }

        Object[] values = wordSet.values().toArray();
        Arrays.sort(values);

        int size = Math.min(10, words.length);
        String[] finalWords = new String[size];
        int index = 0;
        for (int i = values.length - 1; i >= 0; --i) {
            Iterator iter = wordSet.keySet().iterator();
            String key;
            while (iter.hasNext()) {
                key = iter.next().toString();
                if (wordSet.get(key) == values[i]) {
                    finalWords[index++] = key;
                }
                if (index == size) {
                    return finalWords;
                }
            }
        }
        return finalWords;
    }

    private String[] parse(String content) {

        if (content.contains("<")) {
            content = content.replaceAll("<.*?>", "");
        }
        content = content.replaceAll("\\W", " ");

        String[] words = content.split("( )+");

        return words;
    }

    public void calculateHamFrequency() {
        try {
            File file = new File(hamPath);
            if (file.list().length != 0) {
                String[] children = file.list();

                for (String child : children) {
                    HashMap<String, Integer> currentHam = new HashMap<String, Integer>();

                    String content = "";
                    File hamFile = new File(hamPath + child);
                    Reader fis;

                    fis = new FileReader(hamFile);

                    BufferedReader in = new BufferedReader(fis);
                    String line = in.readLine();
                    while (!line.equals("")) {
                        if (line == null)
                            break;
                        line = in.readLine();
                    }

                    while (line != null) {
                        content += line.trim();
                        line = in.readLine();
                    }
                    if (content.contains("<")) {
                        content = content.replaceAll("<.*?>", "");
                    }
                    content = content.replaceAll("\\d", "");
                    content = content.replaceAll("\\W", " ");

                    String[] words = content.split("( )+");

                    String word;
                    for (String w : words) {
                        word = w.toLowerCase();
                        if (currentHam.containsKey(word) || word.equals("")
                                || word.length() == 1 || word.length() > 50) {
                            continue;
                        } else {
                            currentHam.put(word, 1);
                            int value = 0;
                            if (hamTable.containsKey(word)) {
                                value = hamTable.get(word);
                                hamTable.remove(word);
                            }
                            hamTable.put(word, value + 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DBManager.saveTrainFreq(hamTable, "tb_HamFreq");
    }

    public void calculateSpamFrequency() {
        try {
            File file = new File(spamPath);
            if (file.list().length != 0) {
                String[] children = file.list();

                for (String child : children) {
                    HashMap<String, Integer> currentSpam = new HashMap<String, Integer>();

                    String content = "";
                    File spamFile = new File(spamPath + child);
                    Reader fis;

                    fis = new FileReader(spamFile);

                    BufferedReader in = new BufferedReader(fis);
                    String line = in.readLine();
                    while (!line.equals("")) {
                        if (line == null)
                            break;
                        line = in.readLine();
                    }

                    while (line != null) {
                        content += line.trim();
                        line = in.readLine();
                    }
                    if (content.contains("<")) {
                        content = content.replaceAll("<.*?>", "");
                    }
                    content = content.replaceAll("\\d", "");
                    content = content.replaceAll("\\W", " ");

                    String[] words = content.split("( )+");

                    String word;
                    for (String w : words) {
                        word = w.toLowerCase();
                        if (currentSpam.containsKey(word) || word.equals("")
                                || word.length() == 1 || word.length() > 50) {
                            continue;
                        } else {
                            currentSpam.put(word, 1);
                            int value = 0;
                            if (spamTable.containsKey(word)) {
                                value = spamTable.get(word);
                                spamTable.remove(word);
                            }
                            spamTable.put(word, value + 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DBManager.saveTrainFreq(spamTable, "tb_SpamFreq");
    }

    public void readFreqTable() {
        hamTable = DBManager.readFreqTable("tb_HamFreq");
        spamTable = DBManager.readFreqTable("tb_SpamFreq");
    }

    public void readDataTable() {
        spamProbTable = DBManager.readDataTable("tb_SpamData");
    }

    public void generateProbTable() {

        Iterator iter = spamTable.keySet().iterator();

        String word;
        double sfreq, hfreq;
        while (iter.hasNext()) {
            word = iter.next().toString();
            sfreq = spamTable.get(word) / SPAM_SIZE;

            if (!Double.isNaN(sfreq)) {
                if (hamTable.containsKey(word)) {
                    hfreq = hamTable.get(word) / HAM_SIZE;
                    if (!Double.isNaN(hfreq)) {
                        spamProbTable.put(word, sfreq / (sfreq + 2 * hfreq));
                    }
                } else {
                    spamProbTable.put(word, 0.99);
                }
            }
        }

        iter = hamTable.keySet().iterator();
        while (iter.hasNext()) {
            word = iter.next().toString();
            if (!spamProbTable.containsKey(word)) {
                spamProbTable.put(word, 0.01);
            }
        }
        DBManager.saveTrainData(spamProbTable, "tb_SpamData");
    }

    public void trainData() {
        try {
            calculateHamFrequency();
            calculateSpamFrequency();
            generateProbTable();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> adjustFreq(
            HashMap<String, Integer> hashMap, String[] contents) {

        for (String content : contents) {
            String[] words = parse(content);
            String word;
            for (String w : words) {
                word = w.toLowerCase();
                if (word.equals("") || word.length() == 1 || word.length() > 50) {
                    continue;
                } else {
                    int value = 0;
                    if (hashMap.containsKey(word)) {
                        value = hashMap.get(word);
                        hashMap.remove(word);
                    }
                    hashMap.put(word, value + 1);
                }
            }
        }

        return hashMap;
    }

    public void adjustData(ArrayList<Integer> processMailList,
            String correctType) {

        Iterator iterMailId = processMailList.iterator();

        String[] contents = new String[processMailList.size()];
        int id, index = 0;
        while (iterMailId.hasNext()) {
            id = Integer.parseInt(iterMailId.next().toString());
            contents[index++] = DBManager.getSingleMailContent(id);
        }

        readFreqTable();

        HAM_SIZE = DBManager.getTrainSize("TrainSize_HamSize");
        SPAM_SIZE = DBManager.getTrainSize("TrainSize_SpamSize");

        if (correctType.equals("spam")) {
            SPAM_SIZE += processMailList.size();
            DBManager.truncateTable("tb_SpamFreq");
            DBManager.updateTrainDataSize(SPAM_SIZE, "TrainSize_SpamSize");
            this.spamTable = adjustFreq(this.spamTable, contents);
            DBManager.saveTrainFreq(this.spamTable, "tb_SpamFreq");
        } else {
            HAM_SIZE += processMailList.size();
            DBManager.truncateTable("tb_HamFreq");
            DBManager.updateTrainDataSize(HAM_SIZE, "TrainSize_HamSize");
            this.hamTable = adjustFreq(this.hamTable, contents);
            DBManager.saveTrainFreq(this.hamTable, "tb_HamFreq");
        }

        DBManager.truncateTable("tb_SpamData");

        generateProbTable();
    }
}
