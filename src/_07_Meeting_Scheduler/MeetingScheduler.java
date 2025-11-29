package _07_Meeting_Scheduler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MeetingScheduler {
    /*
     * Your task is to code a method to find a meeting time for two people
     * given their schedules.
     * 
     * Code the method below so that it returns a Schedule object that contains
     * all the times during the week that are in BOTH people's schedules. The
     * Schedule class is included in this package.
     * 
     * Example:
     * person1 availability - Monday at 9, Tuesday at 14, and Friday 10
     * person2 availability - Tuesday at 14, Friday 8, and Monday at 9
     * The returned HashMap should contain: Tuesday 14 and Monday 9
     * 
     * The returned Schedule object represents the times both people are
     * available for a meeting.
     * 
     * Time availability is always at the top of the hour, so 9:30 is not valid
     * Time availability always represents 1 hour
     * Assume both schedules are in the same time zones
     */
    public static Schedule getMutualAvailability(Schedule person1, Schedule person2) {
        Schedule mutualAvailability = new Schedule();
//      another way to complete the task
//        for(String oneDay :  person1.getSchedule().keySet()) {
//            for (String twoDay : person2.getSchedule().keySet()) {
//                if (Objects.equals(twoDay, oneDay)) {
//                    ArrayList<Integer> availableHours = getAvailableTimes(twoDay, person1, person2);
//                    for (Integer hour : availableHours){
//                        mutualAvailability.addAvailability(twoDay, hour);
//                    }
//                }
//            }
//        }
        for(String oneDay :  person1.getSchedule().keySet()) {
            if (person2.getSchedule().containsKey(oneDay)) {
                ArrayList<Integer> availableHours = getAvailableTimes(oneDay, person1, person2);
                for (Integer hour : availableHours) {
                    mutualAvailability.addAvailability(oneDay, hour);
                }
            }
        }
        return mutualAvailability;
    }
    public static ArrayList<Integer>  getAvailableTimes(String day, Schedule person1, Schedule person2){
        ArrayList<Integer> mutualHours = new ArrayList<>();
        ArrayList<Integer> person1Hours = person1.getSchedule().get(day);
        ArrayList<Integer> person2Hours = person2.getSchedule().get(day);
        for (Integer person1Hour :  person1Hours) {
            if (person2Hours.contains(person1Hour)){
                mutualHours.add(person1Hour);
            }
        }
        return mutualHours;
    }
}
