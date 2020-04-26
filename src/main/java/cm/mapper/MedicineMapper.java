package cm.mapper;

import java.util.List;

import cm.dto.MedicineDto;
import cm.pojo.Medicine;

public interface MedicineMapper {
	public Medicine queryMedicineByMno(String mno);

	public List<Medicine> queryAllMedicine(MedicineDto medicineDto);

	public void saveMedicine(Medicine medicine);

	public void deleteMedicineByMno(String mno);

	public void modifyMedicine(Medicine medicine);

	public List<Medicine> queryMultiMedicine(Medicine medicine);
}
