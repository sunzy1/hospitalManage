package cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cm.dto.ClientDto;
import cm.dto.MedicineDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cm.pojo.EasyUIResult;
import cm.pojo.Medicine;
import cm.services.MedicineService;

@RequestMapping("Medicine")
@Controller
public class MedicineController {
	// 药品controller
	@Autowired
	private MedicineService medicineService;

	// 通过mno查询药品信息
	@RequestMapping("QueryMedicineByMno")
	@ResponseBody
	public Medicine queryMedicineByMno(String mno) {
		Medicine medicine = medicineService.queryMedicineByMno(mno);
		return medicine;
	}

	/*
	 * // 多条件药品信息保存session
	 * 
	 * @RequestMapping("QueryMultiMedicine")
	 * 
	 * @ResponseBody public String queryMultiMedicine(Medicine
	 * medicine,HttpSession session) { return
	 * medicineService.queryMultiMedicine(medicine,session);
	 * 
	 * } // 多条件药品查询url
	 * 
	 * @RequestMapping("GetMultiMedicine")
	 * 
	 * @ResponseBody public EasyUIResult getMultiMedicine(@RequestParam(value =
	 * "page", defaultValue = "1") Integer page,
	 * 
	 * @RequestParam(value = "rows", defaultValue = "5") Integer rows,
	 * HttpSession session) { return medicineService.getMultiMedicine(page,
	 * rows,session);
	 * 
	 * }
	 */

	 // 通过mno删除药品信息
	  @RequestMapping(value = "DeleteMedicine", produces = "text/html;charset=UTF-8")
	  @ResponseBody public String deleteMedicineByMno(String mno) { return
	  medicineService.deleteMedicineByMno(mno); }

	// 批量删除
	@RequestMapping(value = "DeleteRows", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteMedicineByRows(@RequestParam(value = "array[]") String[] array) {
		try {

			return medicineService.deleteMedicineByRows(array);

		} catch (Exception e) {
			// TODO: handle exception
			return "操作异常，可能某些药品被顾客购买过，" + "无法删该药品，请重新选择";
		}
	}

	// 保存药品信息
	@RequestMapping(value = "SaveMedicine", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveMedicine(MedicineDto medicineDto) {
		return medicineService.saveMedicine(medicineDto);
	}

	// 修改药品信息
	@RequestMapping(value = "ModifyMedicine", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyMedicine(MedicineDto medicineDto) {
		return medicineService.modifyMedicine(medicineDto);
	}

	// easyui返回json
	@RequestMapping("GetMessage")
	@ResponseBody
	public EasyUIResult queryAllMedicine(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "5") Integer rows,HttpServletRequest request) {
		String str_medicineDto =  request.getParameter("medicineDto");
		MedicineDto medicineDto = null;
		if(!StringUtils.isEmpty(str_medicineDto)){
			medicineDto = JSON.parseObject(str_medicineDto,MedicineDto.class);
		}
		return medicineService.queryAllMedicine(page, rows,medicineDto);

	}

	// 获得药品信息
	@RequestMapping("GetAllMedicine")
	@ResponseBody
	public List<Medicine> getAllMedicine() {
		List<Medicine> allMedicine = medicineService.getAllMedicine();
		return allMedicine;
	}
	// 修改药品信息
	@RequestMapping(value = "exportMedicine", produces = "text/html;charset=UTF-8")
	public void exportMedicine(String data, HttpServletResponse response) {
		MedicineDto medicineDto= JSONObject.parseObject(data,MedicineDto.class);
		 medicineService.exportMedicine(medicineDto,response);
	}
}
