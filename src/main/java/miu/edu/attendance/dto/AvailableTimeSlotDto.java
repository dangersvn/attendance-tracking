package miu.edu.attendance.dto;

import lombok.Data;
import miu.edu.attendance.domain.TimeSlot;

import java.util.ArrayList;

@Data
public class AvailableTimeSlotDto {
    Iterable<TimeSlot> timeSlots = new ArrayList<>();

    public TimeSlot getMorningSession() {
        for (TimeSlot timeSlot : timeSlots) {
            if(timeSlot.getAbbreviation().equalsIgnoreCase("AM")
                && timeSlot.getBeginTime().getHour() == 10) {
                return timeSlot;
            }
        }
        return null;
    }

    public TimeSlot getAfternoonSession() {
        for (TimeSlot timeSlot : timeSlots) {
            if(timeSlot.getAbbreviation().equalsIgnoreCase("PM")) {
                return timeSlot;
            }
        }
        return null;
    }
}
