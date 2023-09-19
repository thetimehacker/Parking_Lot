package Hello;
import java.sql.Array;
import java.util.*;
import java.time.*;


public class hello {
//    static ArrayList<String>[] parking_spot = new ArrayList[4]; // -skr
    static ArrayList<String> parking_spot = new ArrayList(5000);
    static ArrayList<String> vehicle_id = new ArrayList(5000);
    static int[] fee=new int[3];
    static int car=100, bike=200,truck=50;


    //Variable to get current time
    static LocalTime currentTime = LocalTime.now();
    static String s = currentTime.toString();
    static String ct = s.substring(0, 5);  //current time


    // queue to store available parking slots
    static Queue<Integer> qb = new LinkedList<>(); // - skr
    static Queue<Integer> qc = new LinkedList<>();
    static Queue<Integer> qt = new LinkedList<>();


    //using unordered_map to map every vehicle id with index - c++
    static Map<String,Integer> m= new HashMap<>();
    //hashmap functions to use -> put, get, containsKey, remove

    //call this function for the first time to initialize everything
    public static void parking_lot(){
           for(int i=0;i<200;i++)  qb.add(i);
           for(int i=200;i<300;i++)  qc.add(i);
           for(int i=300;i<350;i++)  qt.add(i);

           //initializing parking spot with empty
           for(int i=0;i<350;i++){
               parking_spot.add("empty");
           }

           //initializing vehicle id with empty
            for(int i=0;i<350;i++){
                vehicle_id.add("empty");
            }
    }

    public static void add(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vehicle type: 1 - bike, 2 - car, 3 - truck");
        int vt; //vehicle type
        vt = sc.nextInt();
        System.out.print("Enter vehicle number: ");

        //Buffered Input: If you're using multiple Scanner objects in your program,
        // there may be buffered input that interferes with subsequent Scanner calls.
        // Make sure to consume any newline characters left in the input buffer by
        // calling scanner.nextLine() before reading the next input.
        sc.nextLine();
        String vno = sc.nextLine();

        if(m.containsKey(vno)){
            System.out.println("----->> Vehicle Number already registered :(");
            return;
        }

        switch(vt){
            case 1: {
                //using poll() function to get the element and delete it from the queue

                if (!qb.isEmpty()) {
                    int index = qb.poll(); // pop element from the queue and store it in this
                    parking_spot.add(index, ct); // add time to this spot for bike
                    vehicle_id.add(index,vno);
                    m.put(vno,index);
                    System.out.println("------>> Parking Slot booked :) ");
                    System.out.print("------>> bike timing : ");
                    System.out.print(ct);
                } else {
                    System.out.println("------>> No Parking Slot available :( ");
                }
            }
            break;

            case 2: {
                //using poll() function to get the element and delete it from the queue

                if (!qc.isEmpty()) {
                    int index = qc.poll(); // pop element from the queue and store it in this
                    parking_spot.add(index, ct); // add time to this spot for bike
                    vehicle_id.add(index,vno);
                    m.put(vno,index);
                    System.out.println("------>> Parking Slot booked :) ");
                    System.out.print("------>> car timing : ");
                    System.out.print(ct);
                } else {
                    System.out.println("------>> No Parking Slot available :( ");
                }
            }
            break;

            case 3: {
                    //using poll() function to get the element and delete it from the queue
                    if (!qt.isEmpty()) {
                        int index = qt.poll(); // pop element from the queue and store it in this
                        parking_spot.add(index, ct); // add time to this spot for bike
                        vehicle_id.add(index,vno);
                        m.put(vno,index);
                        System.out.println("------>> Parking Slot booked :) ");
                        System.out.print("------>> truck timing : ");
                        System.out.print(ct);
                    } else {
                        System.out.println("------>> No Parking Slot available :( ");
                    }
                }
                break;
        }
    }

    public static void delete(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Vehicle number to delete : ");
        String vid = sc.nextLine(); // get vehicle number to delete it from the database
        System.out.println();
        //2 ways to delete something
        //1- to search for that vehicle id in the whole list
        //2- to map every id with a index of parking_spot or vehicle_id vector
        if(m.containsKey(vid)){
            //get index from map
            int index = m.get(vid);
            m.remove(vid);




            // TO CALCULATE CHARGES BEFORE REMOVING






            //remove from parkingspot and vehicle id
            // parking_spot.remove(vid);
            // instead of remove - add empty to the string

            parking_spot.add(index,"empty");
            vehicle_id.add(index,"empty");

            // add this index back to the queue
            if(index<200)qb.add(index);
            else if(index<300)qc.add(index);
            else qt.add(index);
            System.out.println("---->>> Successfully deleted the given id :) ");
            System.out.println();
            // ---> now everything looks fine

        }
        else{
            System.out.println("---->>> Vehicle Number not available in database :(");
            System.out.println();
        }

        //2nd option is more efficient - will map everything in parking_lot function
    }
    public static void main(String[] arg){

        parking_lot(); //will be used only once
        Scanner sc= new Scanner(System.in);

        while(true){
            System.out.println("--------------Main Menu------------- ");
            System.out.println("1. Book Parking Slot");
            System.out.println("2. Exit Parking lot");
            System.out.println("3. Exit");
            System.out.print("Enter a choice (1-3) : ");
            int choice;
            choice = sc.nextInt();

            System.out.println();

            boolean flag_to_exit = false;
            switch(choice){
                case 1: {
                    add();
                }
                break;

                case 2:{
                    delete();
                }
                break;

                case 3:{
                    flag_to_exit= true;
                }
                break;

                default :
                {
                    System.out.println(" ----> Wrong Choice :( ");
                }
                break;
            }

            if(flag_to_exit)break;
        }

    }
}
