import java.util.*;

class Order {
	//fields
    String lastName;
    int orderNumber;
    double cost;

    //constructors 
    public Order(String lastName, int orderNumber, double cost) {
        this.lastName = lastName;
        this.orderNumber = orderNumber;
        this.cost = cost;
    } 
}

class Display {
	//creation of queue
    Queue<Order> queue = new LinkedList<>();
    
    //sorters to order by last name and order number
    List<Order> sortedName = new ArrayList<>();
    List<Order> sortedNumber = new ArrayList<>();

    public void updateDisplay(Order newOrder) {
        if (newOrder != null) {
            queue.add(newOrder);
        }

        sortedName = new ArrayList<>(queue);
        sortedNumber = new ArrayList<>(queue);

        //compares last names to determine order
        sortedName.sort(Comparator.comparing(order -> order.lastName));
        
        //compares order number to determine order
        sortedNumber.sort(Comparator.comparingInt(order -> order.orderNumber));

        //Operations
        System.out.println("\nOrder Queue:");
        for (Order order : queue) {
            System.out.println("Last Name: " + order.lastName + ", Order Number: " + order.orderNumber + ", Total Cost: " + order.cost);
        }

        System.out.println("\nSorted by Last Name:");
        for (Order order : sortedName) {
            System.out.println("Last Name: " + order.lastName + ", Order Number: " + order.orderNumber + ", Total Cost: " + order.cost);
        }

        System.out.println("\nSorted by Order Number:");
        for (Order order : sortedNumber) {
            System.out.println("Last Name: " + order.lastName + ", Order Number: " + order.orderNumber + ", Total Cost: " + order.cost);
        }
    }

}

class Main {
    Display view = new Display();

  //methods
    
    //adds order. will require last name, order number, and cost of order
    public void addOrder(String lastName, int orderNumber, double cost) {
        Order newOrder = new Order(lastName, orderNumber, cost);
        view.updateDisplay(newOrder);
    }

    //removes orders
    public void removeOrder() {
        if (!view.queue.isEmpty()) {
            Order removedOrder = view.queue.poll();
            if (removedOrder != null) {
                System.out.println("Removed order: Last Name ~ " + removedOrder.lastName + ", Order Number ~ " + removedOrder.orderNumber);
            } else {
                System.out.println("Error: Unable to remove order."); //error handling
                return;
            }

            // Check if there's only one order left after removal
            if (view.queue.isEmpty()) {
                view.updateDisplay(null); // Update display as there's no order left
            } else {
                view.updateDisplay(removedOrder); // Update display after removal
            }
        } else {
            System.out.println("No orders in queue available to remove."); //when there is no order in queue
        }
    }


    //shows orders in queue if available
    public void displayOrders() {
        if (!view.queue.isEmpty()) {
            view.updateDisplay(null);
        } else {
            System.out.println("No orders in queue are available to display.");
        }
    }

    //test class for Order Queue
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	//menu interface
        	System.out.println("\n------------------------");
            System.out.println("\n---Order List Handler---");
            System.out.println("\n------------------------");
            System.out.println("1. Add New Order");
            System.out.println("2. Remove Order in Queue");
            System.out.println("3. Display Orders in Queue");
            System.out.println("4. Exit Program");

            //user input
            System.out.print("Select Options (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            //case system to handle user inputs
            switch (choice) {
                case 1: //adding order
                	System.out.println("---------ORDER ADDER--------");
                    System.out.print("Enter Last Name of Customer: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Order Number: ");
                    int orderNumber = scanner.nextInt();
                    System.out.print("Enter Total Cost of Purchase: ");
                    double totalCost = scanner.nextDouble();
                    main.addOrder(lastName, orderNumber, totalCost);
                    break;
                case 2: //removing order
                	System.out.println("---------ORDER REMOVER--------");
                    main.removeOrder();
                    break;
                case 3: //displaying orders in queue
                	System.out.println("---------ORDERS IN QUEUE--------");
                    main.displayOrders();
                    break;
                case 4: //exit
                	System.out.println("---------CLOSING PROGRAM--------");
                    scanner.close();
                    return;
                    //if user enters invalid option
                default:
                    System.out.println("Option Invalid. Please enter a number between 1 and 4.");
            }
        }
    }
}
