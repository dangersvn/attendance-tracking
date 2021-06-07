package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import org.springframework.stereotype.Service;

@Service
public interface BarcodeRecordService {
    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO);

}
