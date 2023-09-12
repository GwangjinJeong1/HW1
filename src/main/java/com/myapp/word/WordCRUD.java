package com.myapp.word;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;

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
    public int update(Object obj) {

        return 0;
    }

    public void updateItem(){
        System.out.println("=> 수정할 단어 검색 : ");
        String search = s.next();
        ArrayList<Word> searchList = this.listAll(search);
        System.out.println("=> 수정할 번호 선택 : ");
        int num = s.nextInt();
        System.out.println("=> 뜻 입력 : ");



        System.out.println("단어가 수정되었습니다.\n");
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    public void deleteItem(){
        System.out.println("=> 삭제할 단어 검색 : ");
        String search = s.nextLine();
        ArrayList<Word> searchList = new ArrayList<>();

        System.out.println("단어가 삭제되었습니다.\n");
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
    public ArrayList<Word> listAll(String str){
        ArrayList<Word> newList = new ArrayList<>();
        System.out.println("--------------------");
        for (int i = 0; i< list.size(); i++){
            if(list.get(i).toString().contains(str)) {
                System.out.print(i + 1 + " ");
                System.out.println(list.get(i).toString());
                newList.add(list.get(i));
            }
        }
        System.out.println("--------------------");
        return newList;
    }
}
