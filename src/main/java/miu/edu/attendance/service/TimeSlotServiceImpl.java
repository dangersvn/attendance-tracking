package miu.edu.attendance.service;

import miu.edu.attendance.dto.AvailableTimeSlotDto;
import miu.edu.attendance.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {
    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Override
    public AvailableTimeSlotDto getAllTimeSlots() {
        AvailableTimeSlotDto dto = new AvailableTimeSlotDto();
        dto.setTimeSlots(timeSlotRepository.findAll());
        return dto;
    }
}
