package JavaPractice;
import java.util.*;

public class AddressBookMain {
    static Hashtable<Integer, ArrayList<ContactStore>> dictionary  = new Hashtable<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("enter the number of addressbooks ");
        int addressBookLimit = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= addressBookLimit; i++) {
            ArrayList<ContactStore> arrayList = new <ContactStore>ArrayList();
            boolean check = true;
            while (check) {
                ContactStore contactStore = new ContactStore();
                System.out.printf("Enter 1.Add new Contact\n 2.Edit the Details\n 3.Delete the Contact\n 4.Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        contactStore.setContactDetails();
                        boolean checkDuplicate = contactStore.searchForDuplication(arrayList, contactStore);
                        if(!checkDuplicate) {
                            arrayList.add(contactStore);
                        }
                        else
                            System.out.println("Contact already Exists");
                    }
                    case 2 -> contactStore.editDetails(arrayList);
                    case 3 -> contactStore.deleteDetails(arrayList);
                    case 4 -> check = false;
                }
            }
            dictionary.put(i, arrayList);
            ContactStore.writeToFileInOpenCsv(arrayList);
            ContactStore.writeToFileInJson(arrayList);
        }
        System.out.println(dictionary);
        ContactStore.writeToFile(dictionary);
        System.out.println("Reading AddressBooks from File");
        ContactStore.readFromFile();
        System.out.println();
        System.out.println("Reading AddressBook using OpenCSV");
        ContactStore.readFromFileInOpenCsv();
        System.out.println();
        System.out.println("Reading AddressBook using JSON");
        ContactStore.readFromFileInJson();
        System.out.println();
        ContactStore.search(dictionary);
        System.out.println("AddressBooks after Sorting based on FirstName:");
        ContactStore.sortByFirstName(dictionary);
        System.out.println("AddressBooks Sorted based on City :");
        ContactStore.sortByCity(dictionary);
        System.out.println("AddressBooks Sorted based on State :");
        ContactStore.sortByState(dictionary);
    }
}