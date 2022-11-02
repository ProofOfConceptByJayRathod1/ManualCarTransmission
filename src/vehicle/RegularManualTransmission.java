package vehicle;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static vehicle.RegularManualTransmission.checkConditinons;
import static vehicle.RegularManualTransmission.manualTransmissionCar;

public class RegularManualTransmission implements ManualTransmission {
    static SpeedRange speedRange=new SpeedRange(0,25,25,40,40,60,60,80,80,1000);
    static Transmission manualTransmissionCar=new Transmission(1,0, speedRange);




    /* Main method*/
    public static void main(String[] args) {

        if(manualTransmissionCar.getGear()==1 && manualTransmissionCar.getSpeed()==0) {System.out.println("OK: everything is OK");

        }
        manualTransmissionCar.status();   
        manualTransmissionCar.increaseGear(); //1
        manualTransmissionCar.status();
            for(int itr=0;itr<26;itr++) manualTransmissionCar.increaseSpeed();
            manualTransmissionCar.status();
            manualTransmissionCar.increaseGear();//2
            manualTransmissionCar.status();
           for(int itr=0;itr<15;itr++) manualTransmissionCar.increaseSpeed();
           manualTransmissionCar.getStatus();
            

    }

    @Override
    public String getStatus() {
        return manualTransmissionCar.status();
    }

    @Override
    public Integer getSpeed() {
        return manualTransmissionCar.getSpeed();
    }

    @Override
    public Integer getGear() {
        return manualTransmissionCar.getGear();
    }
    @Override
    public Transmission increaseSpeed() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear(),manualTransmissionCar.getSpeed()+1, speedRange))){ //if it returns true, we can increase unit speed.
            manualTransmissionCar.setSpeed(manualTransmissionCar.getSpeed()+1);
            return manualTransmissionCar;}
        else throw new IllegalArgumentException("Know how to drive a car");
    }

    @Override
    public Transmission decreaseSpeed() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear(),manualTransmissionCar.getSpeed()-1, speedRange))){ //if it returns true, we can decrease unit speed.
            manualTransmissionCar.setSpeed(manualTransmissionCar.getSpeed()-1);
            return manualTransmissionCar;}
        else throw new IllegalArgumentException("Know how to drive a car");
    }

    @Override
    public Transmission increaseGear() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear()+1,manualTransmissionCar.getSpeed(), speedRange))){ //if it returns true, we can increase a gear.
            manualTransmissionCar.setGear(manualTransmissionCar.getGear()+1);
            return manualTransmissionCar;}
    else throw new IllegalArgumentException("Know how to drive a car");
    }

    @Override
    public Transmission decreaseGear() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear()-1,manualTransmissionCar.getSpeed(), speedRange))){ //if it returns true, we can decrease a gear.
            manualTransmissionCar.setGear(manualTransmissionCar.getGear()-1);
            return manualTransmissionCar;}
        else throw new IllegalArgumentException("Know how to drive a car");
    }


    public static boolean checkConditinons(Transmission newTemporaryObjectToValidateTransmission){

        HashMap<Integer, List<Integer>> gearSpeedMapping=new HashMap<Integer, List<Integer>>();
        gearSpeedMapping.put(1, Arrays.asList(manualTransmissionCar.getSpeedRange().l1,manualTransmissionCar.getSpeedRange().h1) );
        gearSpeedMapping.put(2, Arrays.asList(manualTransmissionCar.getSpeedRange().l2,manualTransmissionCar.getSpeedRange().h2) );
        gearSpeedMapping.put(3, Arrays.asList(manualTransmissionCar.getSpeedRange().l3,manualTransmissionCar.getSpeedRange().h3) );
        gearSpeedMapping.put(4, Arrays.asList(manualTransmissionCar.getSpeedRange().l4,manualTransmissionCar.getSpeedRange().h4) );
        gearSpeedMapping.put(5, Arrays.asList(manualTransmissionCar.getSpeedRange().l5,manualTransmissionCar.getSpeedRange().h5) );


        if(newTemporaryObjectToValidateTransmission.getGear()==0 && newTemporaryObjectToValidateTransmission.getSpeed()==-1 ||newTemporaryObjectToValidateTransmission.getGear()==2 && newTemporaryObjectToValidateTransmission.getSpeed()==1) {System.out.println("OK: everything is OK");}
    if(newTemporaryObjectToValidateTransmission.getGear()==0){ System.out.println("Cannot decrease gear. Reached minimum gear."); return false;}
    if(newTemporaryObjectToValidateTransmission.getGear()==6){ System.out.println("Cannot decrease gear. Reached maximum gear."); return false;}
    if(newTemporaryObjectToValidateTransmission.getSpeed()==-1){ System.out.println("Cannot decrease speed. Reached minimum speed."); return false;}
    if(newTemporaryObjectToValidateTransmission.getSpeed()> manualTransmissionCar.getSpeedRange().h5 /*possibly top speed*/){ System.out.println("Cannot increase speed. Reached maximum speed."); return false;}
    if(gearSpeedMapping.get( newTemporaryObjectToValidateTransmission.getGear()).get(1)< newTemporaryObjectToValidateTransmission.getSpeed() ){ System.out.println("Cannot decrease gear, decrease speed first."); return false;}
    if(gearSpeedMapping.get( newTemporaryObjectToValidateTransmission.getGear()).get(0)> newTemporaryObjectToValidateTransmission.getSpeed()){ System.out.println("Cannot increase gear, increase speed first."); return false;}
    if(newTemporaryObjectToValidateTransmission.getSpeed()>gearSpeedMapping.get( newTemporaryObjectToValidateTransmission.getGear()).get(0)){ System.out.println("Cannot decrease speed, decrease gear first"); return false;}
    if(newTemporaryObjectToValidateTransmission.getSpeed()>gearSpeedMapping.get( newTemporaryObjectToValidateTransmission.getGear()).get(1)){ System.out.println("Cannot increase speed, increase gear first."); return false;}
    if(newTemporaryObjectToValidateTransmission.getGear()>0 && newTemporaryObjectToValidateTransmission.getGear() <5){ System.out.println("OK: you may decrease the gear."); return true;}
    if(newTemporaryObjectToValidateTransmission.getGear()>0 && newTemporaryObjectToValidateTransmission.getGear() <5){ System.out.println("OK: you may increase the gear."); return true;}
        return false;
    }
}



//Class Transmission

class Transmission {


    Integer gear;
    Integer speed;

    SpeedRange speedRange;

    public SpeedRange getSpeedRange() {
        return speedRange;
    }

    public void setSpeedRange(SpeedRange speedRange) {
        this.speedRange = speedRange;
    }

    public Transmission(Integer gear, Integer speed, SpeedRange speedRange) {
        this.gear = gear;
        this.speed = speed;
        this.speedRange = speedRange;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "gear=" + gear +
                ", speed=" + speed +
                ", speedRange=" + speedRange +
                '}';
    }



    public void setGear(Integer gear) {
        this.gear = gear;
    }


    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Transmission(Integer gear, Integer speed) {
        this.gear = gear;
        this.speed = speed;
    }

    public Transmission() {
        //default initially everything is 0 as car needs to be started
        gear=1;
        speed=0;
    }
    /*Report the status of the vehicle (as a result of any of the above opera!ons).*/
    public String status() {
        return "Transmission{" +
                "gear=" + gear +
                ", speed=" + speed +
                '}';
    }






    public String getStatus() {
        return manualTransmissionCar.status();
    }


    public Integer getSpeed() {
        return manualTransmissionCar.speed;
    }


    public Integer getGear() {
        return manualTransmissionCar.gear;
    }

    public Transmission increaseSpeed() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear(),manualTransmissionCar.getSpeed()+1, speedRange))){ //if it returns true, we can increase unit speed.
            manualTransmissionCar.setSpeed(manualTransmissionCar.getSpeed()+1);
            return manualTransmissionCar;}
        else throw new IllegalArgumentException("Know how to drive a car");
    }

    public Transmission decreaseSpeed() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear(),manualTransmissionCar.getSpeed()-1, speedRange))){ //if it returns true, we can decrease unit speed.
            manualTransmissionCar.setSpeed(manualTransmissionCar.getSpeed()-1);
            return manualTransmissionCar;}
        else throw new IllegalArgumentException("Know how to drive a car");
    }


    public Transmission increaseGear() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear()+1,manualTransmissionCar.getSpeed(), speedRange))){ //if it returns true, we can increase a gear.
            manualTransmissionCar.setGear(manualTransmissionCar.getGear()+1);
            return manualTransmissionCar;}
        else throw new IllegalArgumentException("Know how to drive a car");
    }

     Transmission decreaseGear() {
        if(checkConditinons(new Transmission(manualTransmissionCar.getGear()-1,manualTransmissionCar.getSpeed(), speedRange))){ //if it returns true, we can decrease a gear.
            manualTransmissionCar.setGear(manualTransmissionCar.getGear()-1);
            return manualTransmissionCar;}
        else throw new IllegalArgumentException("Know how to drive a car");
    }




}


//Class Speed Range
 class SpeedRange {
    Integer l1;
    Integer h1;
    Integer l2;
    Integer h2;
    Integer l3;
    Integer h3;
    Integer l4;
    Integer h4;
    Integer l5;
    Integer h5;

    public SpeedRange() {
        l1=0;
        h1=25;
        l2=25;
        h2=40;
        l3=40;
        h3=60;
        l4=60;
        h4=80;
        l5=80;
        h5=1000;

    }


    /*The class offers a constructor that takes the speed ranges for the 5 gears as two integral numbers each: low and high.
Thus it takes arguments as l1,h1,l2,h2,...,l5,h5. For each gear x, lx should be less than or equal to hx. Furthermore the
lower speed for gear 1 should be strictly lesser than that of gear 2, and so on. Finally (only) adjacent-gear ranges may
overlap (e.g. l2 may be between l1 and h1, etc.) but the ranges cannot be non-overlapping (i.e. each speed is covered by
a gear range). The lower speed of the first gear should be 0, and the highest speed in the last gear represents the speed
limit of the vehicle (it cannot go faster than this). If any of these condi!ons are not fulfilled by the inputs then the
constructor should throw an IllegalArgumentException with an appropriate message.*/
    public SpeedRange(Integer l1, Integer h1, Integer l2, Integer h2, Integer l3, Integer h3, Integer l4, Integer h4, Integer l5, Integer h5) {
        if(l1<=h1 && l2<=h2 && l3<=h3 && l4<=h4 && l5<=h5 && h1<=l2 && h2<=l3 && h3<=l4 && h4<=l5 && l1==0) {
            this.l1 = l1;
            this.h1 = h1;
            this.l2 = l2;
            this.h2 = h2;
            this.l3 = l3;
            this.h3 = h3;
            this.l4 = l4;
            this.h4 = h4;
            this.l5 = l5;
            this.h5 = h5;
        }
        else{
            throw new IllegalArgumentException("Illegal Speed Range");
        }
    }
}





 interface ManualTransmission {


    /*
     * Get status method would implement the working of the following code:
     *Increase the speed by a certain fixed amount.
     *Decrease the speed by a certain fixed amount.
     *Increase the gear by one.
     *Decrease the gear by one.
     *Decrease the gear by one.
     *
     * */
    public String getStatus(); // Return the status of this transmission as a String without any addi!onal parameters.
    public Integer getSpeed(); // Get the current speed of the vehicle as a whole number.
    public Integer getGear(); // Get the current gear of the vehicle as a whole number.
    /*
     * Increase the speed by a fixed amount without changing gears and return the resul!ng transmission object.
     *  If the speed cannot be increased, then it should return an object with the same speed as before.
     * The speed change amount is up to the implementation and is not an argument of this method.
     * */
    public Transmission increaseSpeed();
    /*
     * Decrease the speed by a fixed amount without changing gears and return the resul!ng transmission object.
     * If the speed cannot be decreased, then it should return an object with the same speed as before.
     * The speed change amount is up to the implementation and is not an argument of this method.
     * */
    public Transmission decreaseSpeed();
    /*
     *Increase the gear by one without changing speed and return the resul!ng transmission object.
     *If the gear cannot be increased, then it should return an object with the same gear as before.*/
    public Transmission increaseGear();
    /*
     * Decrease the gear by one without changing speed and return the resul!ng transmission object.
     * If the gear cannot be decreased, then it should return an object with the same gear as before.*/
    public Transmission decreaseGear();


}



