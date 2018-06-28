/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diningphilosophers;

/**
 *
 * @author gpcorser
 */
public class DiningPhilosophers {
    
    // boolean array of fork statuses (lifted or not) 
    static boolean forkLifted [] = {false, false};
    
    // could have just use length of forkLifted array
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
    }
}

class Philosopher {
    String name = "Plato";
    int philosopherNumber = 0;
    static int numberOfPhilosophers = 0;
    // Note: all Forks are created BEFORE any Philosopher is created
    int leftFork = 0;
    int rightFork = 0;
    boolean rightForkUp = false;
    boolean leftForkUp = false;
    
    Philosopher() { // constructor
        numberOfPhilosophers++;
        philosopherNumber = numberOfPhilosophers;
        
        rightFork = philosopherNumber - 1;
        leftFork = philosopherNumber;
        if (philosopherNumber == DiningPhilosophers.numberOfForks) 
            leftFork = 1;
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
        }
    } 
}
