package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//project:LIBRARAY MANAGMENT SYSTEM
class Student{
    int id;
    String name;
    int book_issued=0;
    Student(int id,String name,int book_issued){
        this.id=id;
        this.name=name;
        this.book_issued=book_issued;
    }
}
class Book{
    int id;
    String Book_name;
    int quantity;
    String publictaion;
    Book(int id,String Book_name,String publication, int quantity){
        this.id=id;
        this.Book_name=Book_name;
        this.publictaion=publication;
        this.quantity=quantity;
    }
}
public class Project1 {
   static List<Book> list=new ArrayList<>();
   static List<Student> slist=new ArrayList<>();
   static Scanner sc=new Scanner(System.in);
    static int add() {
        System.out.println("Enter book id");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Book name");
        String name = sc.nextLine();
        System.out.println("Enter publication ");
        String publictaion = sc.nextLine();
        System.out.println("Enter quantity of boook");
        int quantity = sc.nextInt();
        if (search(id) == 1) {
            System.out.println("enter the quantity of this book");
            int q = sc.nextInt();
            for (Book book : list) {
                if (book.id == id) {
                    book.quantity = book.quantity + q;
                    return 0;
                }
            }
            return 0;
        } else {
            Book obj = new Book(id, name, publictaion, quantity);
            list.add(obj);
        }
        return 0;
    }
   static int search(int id) {
       int size = list.size();
       for (Book book : list) {
           if (book.id == id) {
               System.out.println("Book id:" + book.id);
               System.out.println("Book name:" + book.Book_name);

               System.out.println("Book publication:" + book.publictaion);
               System.out.println("Book quantity:" + book.quantity);
               return 1;
           }
       }
           return 0;
   }
   static int delete(int id){
        if(search(id)==1) {
            for (Book book : list) {
                if (book.id == id) {
                    list.remove(book);
                    return 1;
                }
            }
        }
        return 0;
    }
    static int lend(int id){
        if(search(id)==1){
            for(Book book:list){
                if(book.id==id && book.quantity>0){
                    book.quantity=book.quantity-1;
                    return 1;

                }
            }
        }
        return 0;
    }
    static void index(){
        Scanner ab=new Scanner(System.in);

        System.out.println("**----WELCOME! library managment system ----**");
        System.out.println(" 1: search book");
        System.out.println("2: add book");
        System.out.println("3: delete book");
        System.out.println("4: book loan");
        System.out.println("5: book return");
        System.out.println("6: exit");
        System.out.println("enter option contains(1-6)");
        int chioce=ab.nextInt();
        switch (chioce){
            case 1:
                System.out.println("Enter the book ID");
                int id=sc.nextInt();
                int test=search(id);
                if(test==0){
                    System.out.println("No such book found");

                }
                break;
            case 2:
                System.out.println("ADD");
                add();
                break;
            case 3:
                System.out.println("Enter id of book");
                int bid=sc.nextInt();
                if(delete(bid)==1){
                    System.out.println("remove succesfully");
                }
                else{
                    System.out.println("Book not found");
                }
                break;
            case 4:
                System.out.println("lend");
                System.out.println("Enter roll no");
                int sroll=sc.nextInt();
                //check student already issue book or not
                for(Student st:slist){
                    if(st.id==sroll){
                        System.out.println("ypu can not issue book , please return the existing book");
                        index();
                    }
                }
                System.out.println("Enter name");
                sc.nextLine();
                String sname=sc.nextLine();
                System.out.println("Ente the book id you want to issue");
                int issue=sc.nextInt();
                Student sobj=new Student(sroll,sname,issue);
                slist.add(sobj);
                if(lend(issue)==1){
                    System.out.println("Book issued successfully");
                    search(issue);
                }
                else{
                    System.out.println("Book not found in librrary");
                    slist.remove(sobj)
                    ;
                }
                break;
            case 5:
                System.out.println("Return");
                System.out.println("Enter your roll Number:");
                int Broll = sc.nextInt();
                int count =0;

                for(Student st : slist){
                    if(st.id== Broll){
                        for(Book book: list){
                            if(book.id == st.book_issued){
                                book.quantity = book.quantity +1;
                                slist.remove(st);
                                System.out.println("Book Returned Successfully");
                                count =1;
                                break;
                            }
                            if(count==1)
                                break;
                        }
                    }
                    if(count ==1)
                        break;
                }
                if(count==0){
                    System.out.println("Access Denied");
                }
                 break;
            case 6:
                System.exit(0);
            default:
                System.out.println("enter valid option");

        }

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("**------LIBRARAY MANAGMENT SYSTEM LOGIN-----**");
        System.out.println("Enter account number for login");
        int a=sc.nextInt();
        System.out.println("Enter the password");
        int b=sc.nextInt();
        if(a==123456 && b==123456) {
            while (true) {
                index();
            }
        }
        else{
            System.out.println("SORRY! please enter valid account noumber and password");
        }
    }
}
