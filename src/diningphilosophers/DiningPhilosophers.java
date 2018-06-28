/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diningphilosophers;

/**
 * The purpose of this program is to demonstrate
 * the Dining Philosophers problem,
 * using the simple case of just 2 philosophers
 * See: https://en.wikipedia.org/wiki/Dining_philosophers_problem
 *
 * @author gpcorser
 */
public class DiningPhilosophers {
    
    // boolean array of fork statuses (lifted or not) 
    static boolean forkLifted [] = {false, false};
    
    // length of forkLifted array
    static int numberOfForks = 2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Philosopher aristotle = new Philosopher();
        aristotle.name = "Aristotle";
        
        Philosopher socrates = new Philosopher();
        socrates.name = "Socrates";
        
        // if each philosophers lifts left fork first,
        // then neither can ever eat, 
        // because both forks must be lifted to eat.
        aristotle.liftLeftFork();
        socrates.liftLeftFork();
        aristotle.liftRightFork();
        socrates.liftRightFork();
        
        resetForks();
        
        // if each philosophers lifts even fork first,
        // then one philosopher can eat
        aristotle.liftEvenFork();
        socrates.liftEvenFork();
        aristotle.liftOddFork();
        socrates.liftOddFork();
        
    }
    public static void resetForks() {
        forkLifted[0] = false;
        forkLifted[1] = false;
        System.out.println();
        System.out.println("Reset Forks");
        System.out.println();
    }
}

class Philosopher {
    String name = "Plato"; // name is always Plato, unless changed
    int philosopherNumber = 0; 
    static int numberOfPhilosophers = 0; 
    int leftFork = 0;
    int rightFork = 0;
    boolean rightForkUp = false;
    boolean leftForkUp = false;
    boolean philosopherIsEven = false;
    
    Philosopher() { // constructor
        
        // philosophers are numbered from 0 to n-1
        philosopherNumber = numberOfPhilosophers;
        // numberOfPhilosophers is n
        numberOfPhilosophers++; 
        
        leftFork = philosopherNumber; // leftFork always == phil. num.
        rightFork = philosopherNumber + 1;
        // right fork is zero for highest numbered philosopher
        if (philosopherNumber == DiningPhilosophers.numberOfForks - 1) 
            rightFork = 0;
    }
    void eat() {
        System.out.println(name + ": Yum!");
    }
    void think() {
        System.out.println(name + ": Hm.");
    }
    void liftLeftFork() {
        if(DiningPhilosophers.forkLifted[leftFork]) {
            System.out.println(name + ": Can't lift left fork: " 
                    + leftFork);
        }
        else {
            DiningPhilosophers.forkLifted[leftFork] = true;
            leftForkUp = true;
            System.out.println(name + ": Successfully lifted left fork: " 
                    + leftFork);
        }
    }
    void liftRightFork() {
        if(DiningPhilosophers.forkLifted[rightFork]) {
            System.out.println(name + ": Can't lift right fork: " 
                    + rightFork);
        }
        else {
            DiningPhilosophers.forkLifted[rightFork] = true;
            rightForkUp = true;
            System.out.println(name + ": Successfully lifted right fork: " 
                    + rightFork);
        }
    } 
    void liftEvenFork() {
        if(leftFork % 2 == 0) liftLeftFork();
        else liftRightFork();
    }
    void liftOddFork() {
        if(leftFork % 2 != 0) liftLeftFork();
        else liftRightFork();
    }
}
