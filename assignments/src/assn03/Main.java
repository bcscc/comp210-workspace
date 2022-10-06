package assn03;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Integer>();
        list.add('a');
        list.add('b');
        list.add('c');

        LinkedList list2 = new LinkedList();
        list2.add(1);
        list2.add(2);
        list2.add(3);


        list.merge(list2);
        System.out.println(list);

    }
}