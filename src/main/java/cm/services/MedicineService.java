package cm.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cm.dto.MedicineDto;
import cm.utils.ExportExcelUtils;
import cm.utils.ToolsUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cm.mapper.MedicineMapper;
import cm.pojo.Agency;
import cm.pojo.EasyUIResult;
import cm.pojo.Medicine;

@Service
@Transactional
public class MedicineService {
	@Autowired
	private MedicineMapper medicineMapper;

	public Medicine queryMedicineByMno(String mno) {
		// TODO Auto-generated method stub
		Medicine medicine = medicineMapper.queryMedicineByMno(mno);
		return medicine;

	}

	public EasyUIResult queryAllMedicine(Integer page, Integer rows, MedicineDto medicineDto) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<Medicine> medicines = medicineMapper.queryAllMedicine(medicineDto);
		if(ToolsUtils.notEmpity(medicines)){
			for (Medicine medicine: medicines) {
				medicine.setMefficacy(ToolsUtils.deleteJKH(medicine.getMefficacy()));
				medicine.setMremark(ToolsUtils.deleteJKH(medicine.getMremark()));
			}
		}
		PageInfo<Medicine> pageInfo = new PageInfo<Medicine>(medicines);
		return new EasyUIResult(pageInfo.getTotal(), medicines);
	}

	public String saveMedicine(MedicineDto medicineDto) {
		// TODO Auto-generated method stub
		try{
			Medicine qm=queryMedicineByMno(medicineDto.getMno());
			if (qm != null) {
				medicineDto.setMid(qm.getMid());
				this.modifyMedicine(medicineDto);
				return "药品编号已经存在，此次为更新成功";
			}else{
				medicineMapper.saveMedicine(medicineDto);
				return "保存成功";
			}
		}catch (Exception e){
			return "保存药方信息异常，保存失败";
		}
	}

	public String deleteMedicineByMno(String mno) {
		// TODO Auto-generated method stub
		try {
			medicineMapper.deleteMedicineByMno(mno);
		} catch (Exception e) {
			// TODO: handle exception
			return "删除失败，可能是该药方正在被修改，" + "无法删除此药品";
		}
		return "删除成功";
	}

	public String modifyMedicine(MedicineDto medicineDto) {
		// TODO Auto-generated method stub
		Medicine queryMedicineByMno = queryMedicineByMno(medicineDto.getMno());
		if (queryMedicineByMno == null) {
				return "这个药品编号数据库不存在，无法更改！可能是页面修改药品编号导致的！";
		}
		try {
			medicineDto.setMid(queryMedicineByMno.getMid());
			medicineMapper.modifyMedicine(medicineDto);
		} catch (Exception e) {
			// TODO: handle exception
			return "修改失败，可能有人正在修改此药方！";
		}
		return "修改成功";
	}

	public List<Medicine> getAllMedicine() {
		// TODO Auto-generated method stub
		MedicineDto dto =new MedicineDto();
		List<Medicine> queryAllMedicine = medicineMapper.queryAllMedicine(dto);
		if(ToolsUtils.notEmpity(queryAllMedicine)){
			for (Medicine medicine: queryAllMedicine) {
				medicine.setMefficacy(ToolsUtils.deleteJKH(medicine.getMefficacy()));
				medicine.setMremark(ToolsUtils.deleteJKH(medicine.getMremark()));
			}
		}
		return queryAllMedicine;
	}

	public String deleteMedicineByRows(String[] array) {
		// TODO Auto-generated method stub
		try {
			for (String mno : array) {
				medicineMapper.deleteMedicineByMno(mno);
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 抛出异常让spring捕获，进行事务回滚
			throw new RuntimeException();

		}
		return "删除成功";
	}

	public String queryMultiMedicine(Medicine medicine, HttpSession session) {
		// TODO Auto-generated method stub
		try {
			List<Medicine> medicines = medicineMapper.queryMultiMedicine(medicine);
			session.setAttribute("medicines", medicines);
			System.out.println(medicines);
			System.out.println("@@@@@");
			return "";
		} catch (Exception e) {
			// TODO: handle exception
			return "操作异常，请刷新后操作";
		}

		// System.out.println(medicine);
		// List<Medicine> medicine1 = (List<Medicine>)
		// medicineMapper.queryMultiMedicine(medicine);
		// System.out.println(medicine1);
	}

	public EasyUIResult getMultiMedicine(Integer page, Integer rows, HttpSession session) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<Medicine> medicines = (List<Medicine>) session.getAttribute("medicines");
		System.out.println(medicines);
		PageInfo<Medicine> pageInfo = new PageInfo<Medicine>(medicines);
		return new EasyUIResult(pageInfo.getTotal(), medicines);
	}
	public void exportMedicine(MedicineDto medicineDto, HttpServletResponse response){
		List<Medicine> medicines = medicineMapper.queryAllMedicine(medicineDto);
		HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
		HSSFCellStyle cellstyle = hssfWorkbook.createCellStyle();
		//2.创建sheet页
		HSSFSheet sheets = hssfWorkbook.createSheet("药方信息");
		ExportExcelUtils exportExcelUtils = new ExportExcelUtils(hssfWorkbook,sheets);
		if(null!=medicines && medicines.size()>0){
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
			cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
			cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			//3.填充数据
			String[] columHeader ={"药方编号","药方名称","服用方式","详细内容","备注","录入日期"};
			List<String> columHeaderlist = Arrays.asList(columHeader);
			int rowNum=0;
			//设置表主标题
			rowNum=exportExcelUtils.createNormalHead("药方信息",rowNum,
					columHeader.length-1,400,HSSFCellStyle.ALIGN_CENTER);
			rowNum = exportExcelUtils.createColumHeader(rowNum, columHeaderlist);
			//设置表列名
			sheets.setColumnWidth( 0,4000);
			sheets.setColumnWidth( 1,4000);
			sheets.setColumnWidth( 2,2000);
			sheets.setColumnWidth( 3,20000);
			sheets.setColumnWidth( 4,10000);
			sheets.setColumnWidth( 5,3000);
			for(Medicine medicine : medicines){
				HSSFRow rows =sheets.createRow(rowNum);
				HSSFCell cell0= rows.createCell(0);
				HSSFCell cell1= rows.createCell(1);
				HSSFCell cell2= rows.createCell(2);
				HSSFCell cell3= rows.createCell(3);
				HSSFCell cell4= rows.createCell(4);
				HSSFCell cell5= rows.createCell(5);
				//"药方编号","药方名称","服用方式","详细内容","备注","录入日期"
				cell0.setCellValue(medicine.getMno());
				cell1.setCellValue(medicine.getMname());
				cell2.setCellValue(medicine.getMmode());
				cell3.setCellValue(ToolsUtils.deleteJKH(medicine.getMefficacy()));
				cell4.setCellValue(ToolsUtils.deleteJKH(medicine.getMremark()));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cell5.setCellValue(medicine.getMdate()!=null?sdf.format(medicine.getMdate()):"");
				rowNum++;
			}
			exportExcelUtils.setRegionStyle(sheets,new CellRangeAddress(1,rowNum-1,0,columHeader.length-1),cellstyle);
		}
		exportExcelUtils.outputExcel("药方信息",response);
	}
}
