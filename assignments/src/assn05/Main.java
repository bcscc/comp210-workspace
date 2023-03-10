package assn05;


public class Main {

    public static void main(String[] args) {
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        SimpleEmergencyRoom ersys = new SimpleEmergencyRoom();
        ersys.addPatient(1, 1);
        ersys.addPatient(2, 3);
        ersys.addPatient(3, 1);
        ersys.dequeue();

       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        MinBinHeapER ermin = new MinBinHeapER();
        ermin.enqueue(1, 1);
        ermin.enqueue(2, 2);
        ermin.enqueue(3, 3);
        ermin.enqueue(4, 4);
        ermin.dequeue();

        /*
        Part 3
         */

        MinBinHeapER transfer = new MinBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
        double[] results = compareRuntimes();
        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i] + " ");
        }
    }

    public static void fillER(MinBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);
        // Code for (1) Here
        double startTimeSimp = System.nanoTime();
        while (simplePQ.size() != 0) {
            simplePQ.dequeue();
        }
        double endTimeSimp = System.nanoTime();
        results[0] = endTimeSimp - startTimeSimp;
        results[1] = results[0] / 100000;

        MinBinHeapER binHeap = new MinBinHeapER();
        fillER(binHeap);
        // Code for (2) Here
        double startTimeBin = System.nanoTime();
        while (binHeap.size() != 0) {
            binHeap.dequeue();
        }
        double endTimeBin = System.nanoTime();
        results[2] = endTimeBin - startTimeBin;
        results[3] = results[2] / 100000;

        return results;
    }



}

