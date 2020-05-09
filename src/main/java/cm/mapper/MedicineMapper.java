package cm.mapper;

import java.util.List;

import cm.dto.MedicineDto;
import cm.pojo.Medicine;

public interface MedicineMapper {
	public Medicine queryMedicineByMno(String mno);

	public List<Medicine> queryAllMedicine(MedicineDto medicineDto);

	public void saveMedicine(MedicineDto medicineDto);

	public void deleteMedicineByMno(String mno);

	public void modifyMedicine(MedicineDto medicineDto);

	public List<Medicine> queryMultiMedicine(Medicine medicine);
}
