/*
*  Card -> belongs to a suit and has a number
    The available suits are-> heart, spade, club, diamond
    The numbers are from 1 -> 13 [ 1 -->is Ace, 2-->9(normal codes), 10->13 -> special cards(Queen, King, KickBack)
    isSpecial -> Joker
*

   00P 
   -> abstraction, encapsulation, inheritance, polymorphism
   abstraction -> using simple things to represent complexity
   abstraction in Java means things like classes objects and variables
   -> encapsulation -the practice of keeping fields within a class private
    then providing access to them via public methods. it provides a protective barrier
    that keeps data and code safe
   -> inheritance - create new classes that share some of the attributes of exisitng classes
   -> polymorphism -> use the same word to mean different things in different context
   method overloading , method overriding
*
*
**/
public class DeckOfCards{

    // you have the size of the card
    public  final deckSize = 52;

    public  int currentCard;

    public enum cardTypes{
        SPADES,
        CLUB,
        HEARTS,
        DIAMOND
    }

    private Card[] deckOfCards;


    // constructor
    public DeckOfCards(){

        deckOfCards = new Card[deckSize];

        currentCard = 0;

        int count=0;

        // we initate the card size
        for(String cType: cardTypes){
            for(int cardNo=1; cardNo<=13; cardNo++){
                deckOfCards[count] = new Card(cType, cardNo);
            }
        }

        //shuffle cards, deal cards
    }

    /*
    ** we should be able to deal, shuffle, when you deal you give the top card
    **
    *** */

    public Card dealCard(){
        if(currentCard >= deckOfCards.length ){
            System.out.println("There are no cards available");

            return null;
        } else {

            Card returnCard = deckOfCards[currentCard];

            currentCard += 1;

            return returnCard;

        }
    }


    public void shuffleCard(int n){
        // n represents the number of times that we need to shuffle
        // check that we have cards in the deck
        if(currentCard >= deckOfCards.length){

            System.out.println("There are no cards avaialble");

            return;

        } else {
            for(int i=0; i<=n; i++){

                // we should pickt two cards and swap them
                int firstCard = random.nextInt(deckSize-currentCard);

                int secondCard = random.nextInt(deckSize-currentCard);

                int temp = deckOfCards[firstCard];

                deckOfCards[firstCard] = deckOfCards[secondCard];

                deckOfCards[secondCard] = temp;

            }
        }
    }


    // card subclass
    class Card{

        public String cardType;

        public String cardNo;

        //but for the card no we want 1-> Ace
        // 10-->13 --> K, Q, J, K

        HashMap<Integer, String> specialCards = new HashMap<>();
        specialCards.put(1, "A");
        specialCards.put(11, "J");
        specialCards.put(12, "Q");
        specialCards.put(13, "K");

        //constructor
        public Card(String cardType, int cardNo ){
            this.cardType = cardType;

            if(specialCards.contains(cardNo)){
                this.cardNo = specialCards.get(cardNo);
            } else {
                this.cardNo = String.valueOf(cardNo);
            }
        }

    }
}

/*
* call center: 3 level of emloyees respondent, manager and director
  an incoming telephone call --> a call center has incoming telephone calls, so it can be a queue
  a stream of calls that come in
  call-> respondent who is free if they can't handle the call, escalate to manager
  call-> director

  call is an object call can be passed around
  we have class employee implemented differently for a respondent manager and director
  a call center has employees and calls
*
*
**
* */
public class CallCentre{

    ArrayDeque<Call> incomingCallsQueue;

    List<List<Employee>> availableEmployees;

    //constructor
    public CallCentre(){

        incomingCallsQueue = new ArrayDeque<>();

        // create 3 lists to hold the respondent, manager, director
        for(int i=0; i<3; i++){
            List<Employee> newList = new ArrayList<>();
            availableEmployees.add(newList);
        }

    }

    public void addCall(Call newCall){

        incomingCallsQueue.offer(newCall);
    }

    // receives call and checks the priority and checks for an available employee
    public void assignCall(){

        if(incomingCallsQueue.size() == 0){
            System.out.println("There are no pending calls at the moment!");
            return ;
        } else {

            Call currentCall = incomingCallsQueue.poll();

            int callPriority = currentCall.priority;

            List<Employee> currentEmployees = availableEmployees.get(callPriority-1);

            for(int i=0; i<currentEmployees; i++){

                Employee employee = currentEmployees[i];

                if(!employee.isBusy){

                    employee.recieveCall(currentCall);
                    return;
                }

            }
        }

        // if we get here, we ass
    


        //FIFO 
        i



    }

    public class Employee{

        private int employeeId;

        private String name;

        private Strig address;

        private String position;

        private String reportsTo;

        private Boolean isBusy;


        Employee(String name, String address){
            this.name = name;
            this.address = address;
            this.isBusy = false;
            this.employeeId = (int) (1000.0*Math.random());
        }

        public boolean recieveCall(Call call){
            if(this.isBusy){

                System.out.println("The employee is busy")
                return false;

            } else {
                call.handler = this.employeeId; // assign a call to receice call

                this.isBusy = true; // set the employee as busy

                return true;
            }
        }

        public void escalateCall(Call call, List<Employee> emloyees){

            for(int i=0; i<employees.size(); i++){

                Employee currentEmployee = employees[i];

                if(!currentEmployee.isBusy){

                    currentEmployee.recieveCall(call);
                    return;

                }
            }

            System.out.println("There is no avaialable employee at the moment");

        }


        

    }

    public class Respondent extends Employee{

        public Respondent(String name, String address){
            super(name, address);
            position="RESPONDENT";
            reportsTo="MANAGER";
        }
    }

    public class Manager extends Employee{

        public Manager(String name, String address){
            super(name, address);
            position="MANAGER";
            reportsTo="DIRECTOR";
        }
    }

    public class Director extends Employee{

        public Director(String name, String address){
            super(name, address);
            position="DIRECTOR";
            reportsTo="";
        }
    }

    public class Call{

        private int priority;
        public Employee handler;
        //call has rank/priority who is handling the call
        public Call(int priority){
            this.priority = priority;
            this.handler = null;
        }
    }
}

