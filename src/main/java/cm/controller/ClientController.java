package cm.controller;

import cm.dto.ClientDto;
import cm.dto.ClientExportDto;
import cm.dto.MedicineDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.pojo.Client;
import cm.pojo.EasyUIResult;
import cm.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("Client")
@Controller
public class ClientController {
	// 顾客controller
	@Autowired
	// 注入service
	private ClientService clientService;

	// 按编号查询
	@RequestMapping("GetClient")
	@ResponseBody
	public Client queryClientBycno(String cno) {
		Client client = clientService.queryClientBycno(cno);
		return client;
	}

	// 按编号删除
	@RequestMapping(value="DeleteClient", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteClientBycno(String cno) {
		return clientService.deleteClientBycno(cno);
	}
//	批量删除
	@RequestMapping(value = "DeleteRows", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteClientByRows(
			@RequestParam(value = "array[]") String[] array) {
		return clientService.deleteClientByRows(array);
		
//		clientService.deleteClientBycno(cno);
	}

	// 保存顾客信息
	@RequestMapping(value = "SaveClient", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveClient(Client client) {
		return clientService.saveClient(client);
	}

	/*@RequestParam(value = "page", defaultValue = "1") Integer page,
	@RequestParam(value = "rows", defaultValue = "5") Integer rows,
	@RequestParam(value = "clientDto",defaultValue = "") String StrClientDto,*/
	@RequestMapping("GetMessage")
	@ResponseBody
	// easyui返回json
	public EasyUIResult queryAllClient( HttpServletRequest request) {
		String strPage=request.getParameter("page");
		String strRows=request.getParameter("rows");
		if(StringUtils.isEmpty(strPage)){
			strPage="1";
		}
		if (StringUtils.isEmpty(strRows))
		{
			strRows="5";
		}
		Integer page=(Integer.parseInt(strPage));
		Integer rows = Integer.parseInt(strRows);
		String strClientDto = request.getParameter("clientDto");
		System.out.println(strClientDto);
		ClientDto clientDto =new ClientDto();
		if(!StringUtils.isEmpty(strClientDto)){
			clientDto=JSON.parseObject(strClientDto,ClientDto.class);
		}
		EasyUIResult queryAllClient = clientService.queryAllClient(page, rows,clientDto);
		return queryAllClient;
	}

	// 修改客户信息
	@RequestMapping(value = "ModifyClient", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyClient(Client client) {
		return clientService.modifyClient(client);
	}
	// 修改客户信息
	@RequestMapping(value = "exportClient", produces = "text/html;charset=UTF-8")
	public void exportClient(ClientExportDto clientExportDto, HttpServletResponse response) {
		ClientDto dto=new ClientDto();
		if(null!=clientExportDto){
			try{
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
				if(!StringUtils.isEmpty(clientExportDto.getStartDate())){
					Date startDate = sdf.parse(clientExportDto.getStartDate());
					dto.setStartDate(startDate);
				}
				if(!StringUtils.isEmpty(clientExportDto.getEndDate())){
					Date endDate = sdf.parse(clientExportDto.getEndDate());
					dto.setEndDate(endDate);
				}
			}catch (ParseException e){
				e.printStackTrace();
			}
			dto.setMno(clientExportDto.getCno());
			dto.setCname(clientExportDto.getCname());
			dto.setCsymptom(clientExportDto.getCsymptom());
			dto.setCremark(clientExportDto.getCremark());
		}
        clientService.exportClient(dto,response);
	}
}
