package cm.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


import cm.dto.ClientDto;
import cm.utils.ExportExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cm.mapper.ClientMapper;
import cm.pojo.Client;
import cm.pojo.EasyUIResult;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;


/*患者service
处理业务逻辑*/
@Service
@Transactional
public class ClientService {
	@Autowired
	private ClientMapper clientMapper;

	public Client queryClientBycno(String cno) {
		// TODO Auto-generated method stub
		Client client = clientMapper.queryClientBycno(cno);
		return client;
	}

	public String saveClient(Client client) {
		// TODO Auto-generated method stub
		try {
			Client queryClient =queryClientBycno(client.getCno());
			 if(null!=queryClient){
			 	this.modifyClient(client);
				 return "数据已保存成功，此次操作为更新操作";
			 }else{
				 clientMapper.saveClient(client);
				 return "保存成功";
			 }
		} catch (Exception e) {
			return "操作异常，请重新操作";
		}

	}

	public EasyUIResult queryAllClient(Integer page, Integer rows, ClientDto clientDto) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<Client> clients = clientMapper.queryAllClient(clientDto);
		PageInfo<Client> pageInfo = new PageInfo<Client>(clients);
		return new EasyUIResult(pageInfo.getTotal(), clients);
	}

	public String deleteClientBycno(String cno) {
		// TODO Auto-generated method stub
		try{
			clientMapper.deleteClientBycno(cno);
			return "删除患者信息成功，患者编号："+cno;
		}catch (Exception e){
			return "患者编号："+cno+"删除患者信息失败"+e.getMessage();
		}
	}

	public String modifyClient(Client client) {
		// TODO Auto-generated method stub
		Client queryClientBycno = queryClientBycno(client.getCno());
		if(null!=queryClientBycno && !StringUtils.isEmpty(queryClientBycno.getCid())){
			/*queryClientBycno.setCaddress(client.getCaddress());
			queryClientBycno.setCage(client.getCage());
			queryClientBycno.setCname(client.getCname());
			queryClientBycno.setCphone(client.getCphone());
			queryClientBycno.setCremark(client.getCremark());
			queryClientBycno.setCsymptom(client.getCsymptom());
			queryClientBycno.setCsex(client.getCsex());*/
			client.setCid(queryClientBycno.getCid());
		}else{
			return "修改失败，数据库无此数据无法修改";
		}
		try{
			clientMapper.modifyClient(client);
		}catch (Exception e){
			return (e.getMessage()+"修改失败");
		}
		return "修改成功";
	}

	public String deleteClientByRows(String[] array) {
		// TODO Auto-generated method stub
		try {
			for (String cno : array) {
				System.out.println(cno);
				deleteClientBycno(cno);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "操作异常，请刷新后操作";
		}
		return "删除成功";
	}
	public void exportClient(ClientDto clientDto, HttpServletResponse response){
		try {
			List<Client> clientList = clientMapper.queryAllClient(clientDto);
			//1.创建工作空间
			HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
			HSSFCellStyle cellstyle = hssfWorkbook.createCellStyle();
			//2.创建sheet页
			HSSFSheet sheets = hssfWorkbook.createSheet("患者信息");
			ExportExcelUtils exportExcelUtils = new ExportExcelUtils(hssfWorkbook,sheets);
			if(null!=clientList && clientList.size()>0){
				cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
				cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				//3.填充数据
				String[] columHeader ={"患者编号","姓名","性别","年龄","住址","联系方式","症状","备注","经办人","录入日期","已购药品"};
				List<String> columHeaderlist = Arrays.asList(columHeader);
				int rowNum=0;
				//设置表主标题
				rowNum=exportExcelUtils.createNormalHead("患者信息",rowNum,
						columHeader.length-1,400,HSSFCellStyle.ALIGN_CENTER);
				rowNum = exportExcelUtils.createColumHeader(rowNum, columHeaderlist);
				//设置表列名
				sheets.setColumnWidth( 0,4000);
				sheets.setColumnWidth( 1,2000);
				sheets.setColumnWidth( 2,2000);
				sheets.setColumnWidth( 3,2000);
				sheets.setColumnWidth( 4,8000);
				sheets.setColumnWidth( 5,4000);
				sheets.setColumnWidth( 6,15000);
				sheets.setColumnWidth( 7,10000);
				sheets.setColumnWidth( 8,2000);
				sheets.setColumnWidth( 9,4000);
				sheets.setColumnWidth( 10,4000);
				for(Client client :clientList){
					HSSFRow rows =sheets.createRow(rowNum);
					HSSFCell cell0= rows.createCell(0);
					HSSFCell cell1= rows.createCell(1);
					HSSFCell cell2= rows.createCell(2);
					HSSFCell cell3= rows.createCell(3);
					HSSFCell cell4= rows.createCell(4);
					HSSFCell cell5= rows.createCell(5);
					HSSFCell cell6= rows.createCell(6);
					HSSFCell cell7= rows.createCell(7);
					HSSFCell cell8= rows.createCell(8);
					HSSFCell cell9= rows.createCell(9);
					HSSFCell cell10= rows.createCell(10);
//"患者编号","姓名","性别","年龄","住址","联系方式","症状","备注","经办人","录入日期","已购药品"};
					cell0.setCellValue(client.getCno());
					cell1.setCellValue(client.getCname());
					cell2.setCellValue(client.getCsex());
					cell3.setCellValue(client.getCage());
					cell4.setCellValue(client.getCaddress());
					cell5.setCellValue(client.getCphone());
					cell6.setCellValue(client.getCsymptom());
					cell7.setCellValue(client.getCremark());
					cell8.setCellValue(client.getAno());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cell9.setCellValue(client.getCdate()!=null?sdf.format(client.getCdate()):"");
					cell10.setCellValue(client.getMno());
					rowNum++;
				}
				exportExcelUtils.setRegionStyle(sheets,new CellRangeAddress(1,rowNum-1,0,columHeader.length-1),cellstyle);
			}
			exportExcelUtils.outputExcel("患者信息",response);
		}catch (Exception e){
			e.printStackTrace();

		}
	}
}
