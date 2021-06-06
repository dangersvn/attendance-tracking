package miu.edu.attendance.service;

import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.AvailableTimeSlotDto;

public interface TimeSlotService {
    
    AvailableTimeSlotDto getAllTimeSlots();
}
