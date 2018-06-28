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
    static boolean forkLifted [] = {false, false, false, false};
    
    // length of forkLifted array
    static int numberOfForks = 4;
    
    // current time slice (counter)
    static int t = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Philosopher p0 = new Philosopher();
        p0.name = "Aristotle";
        
        Philosopher p1 = new Philosopher();
        p1.name = "Socrates";
        
        Philosopher p2 = new Philosopher();
        p2.name = "Plato";
        
        Philosopher p3 = new Philosopher();
        p3.name = "Heroditus";
        
        // if each philosophers lifts left fork first,
        // then neither can ever eat, 
        // because both forks must be lifted to eat.
        p0.liftLeftFork();
        p1.liftLeftFork();
        p2.liftLeftFork();
        p3.liftLeftFork();
        p0.liftRightFork();
        p1.liftRightFork();
        p2.liftRightFork();
        p3.liftRightFork();
        
        resetForks();
        
        // if there are an even number of philosophers, and
        // if each philosopher lifts even fork first,
        // then either the even (or odd) philosophers can eat
        // Note that the even and odd philosophers 
        // are processed in separate batches
        
        p0.liftEvenFork(); // even philosopher
        p2.liftEvenFork(); // even philosopher
        p1.liftEvenFork(); // odd philosopher
        p3.liftEvenFork(); // odd philosopher
        p0.liftOddFork();
        p2.liftOddFork();
        p1.liftOddFork();
        p3.liftOddFork();  
        
        System.out.println();
        
        t++;
        
        p0.eat();
        p1.eat();
        p2.eat();
        p3.eat();
        
        System.out.println();
        
        t++;
        
        p0.eat();
        p1.eat();
        p2.eat();
        p3.eat();
        
        System.out.println();
        
        p0.liftEvenFork(); // even philosopher
        p2.liftEvenFork(); // even philosopher
        p1.liftEvenFork(); // odd philosopher
        p3.liftEvenFork(); // odd philosopher
        p0.liftOddFork();
        p2.liftOddFork();
        p1.liftOddFork();
        p3.liftOddFork();  
        
        System.out.println();
        
        p0.eat();
        p1.eat();
        p2.eat();
        p3.eat();
        
        System.out.println();
        
    }
    public static void resetForks() {
        forkLifted[0] = false;
        forkLifted[1] = false;
        forkLifted[2] = false;
        forkLifted[3] = false;
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
    int lastAte = -100;
    
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
        if(leftForkUp && rightForkUp 
                && DiningPhilosophers.t - lastAte > 10) {
            System.out.println(name + ": Yum!");
            lastAte = DiningPhilosophers.t;
            // drop the forks
            DiningPhilosophers.forkLifted[leftFork] = false;
            DiningPhilosophers.forkLifted[rightFork] = false;
        }
        else {
            System.out.println(name + ": Can't eat" );
            think();
        }
    }
    void think() {
        System.out.println(name + " thinks: Hm.");
    }
    void liftLeftFork() {
        if (DiningPhilosophers.t - lastAte > 10) {
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
    }
    void liftRightFork() {
        if (DiningPhilosophers.t - lastAte > 10) {
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
