package com.myapp.word;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();


        return new Word(0, level, word, meaning);
    }

    public void addWord(){
        Word one = (Word) add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다. \n");
    }

    @Override
    public int update(Object obj) {return 0;}

    public void updateItem(){
        System.out.print("=> 수정할 단어 검색 : ");
        String search = s.next();
        ArrayList<Integer> searchList = this.listAll(search);
        System.out.print("=> 수정할 번호 선택 : ");
        int num = s.nextInt();
        System.out.print("=> 뜻 입력 : ");
        s.nextLine();
        String newMeaning = s.nextLine();
        Word newWord = list.get(searchList.get(num - 1));
        newWord.setMeaning(newMeaning);
        System.out.println("단어 수정이 성공적으로 되었습니다!!");
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    public void deleteItem(){
        System.out.print("=> 삭제할 단어 검색 : ");
        String search = s.next();
        ArrayList<Integer> searchList = this.listAll(search);
        System.out.print("=> 수정할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine();
        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String input = s.next();
        if(input.equalsIgnoreCase("y")) {
            list.remove((int)searchList.get(num-1));
            System.out.println("\n선택한 단어 삭제 완료 !!!\n");
        } else {
            System.out.println("\n취소되었습니다.\n");
        }
    }

    @Override
    public void selectOne(int id) {
    }

    public void listAll(){
        System.out.println("--------------------");
        for (int i = 0; i< list.size(); i++){
            System.out.print(i+1 + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------");
    }
    public ArrayList<Integer> listAll(String str){
        ArrayList<Integer> newList = new ArrayList<>();
        System.out.println("--------------------");
        int j = 0;
        for (int i = 0; i< list.size(); i++){
            if(list.get(i).getWord().contains(str)) {
                System.out.print(j + 1 + " ");
                System.out.println(list.get(i).toString());
                newList.add(i);
                j++;
            }
        }
        System.out.println("--------------------");
        return newList;
    }
    public ArrayList<Word> listAll(int level){
        int j = 0;
        ArrayList<Word> newList = new ArrayList<>();
        System.out.println("--------------------");
        for (int i = 0; i< list.size(); i++){
            int wordLevel = list.get(i).getLevel();
            if(wordLevel != level) continue;
            System.out.print(i + 1 + " ");
            System.out.println(list.get(i).toString());
            newList.add(list.get(i));
        }
        System.out.println("--------------------");
        return newList;
    }
    public void searchLevel(){
        System.out.print("=> 레벨(1:초급, 2:중급, 3:고급) 선택: ");
        int inputLevel = s.nextInt();
        listAll(inputLevel);
    }
    public void searchWord(){
        System.out.print("=> 검색할 단어 입력 : ");
        String inputWord = s.next();
        listAll(inputWord);
    }
    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count = 0;
            while(true) {
                line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("=> " + count + "개 단어 로딩 완료!\n ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveFile(){
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt"));
            for(Word one : list) {
                pr.write(one.toFileString());
            }
            pr.close();
            System.out.println("\n모든 단어 파일 저장 완료 !!!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
