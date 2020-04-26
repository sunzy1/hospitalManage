package cm.services;

import java.util.List;
import java.util.Random;


import cm.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cm.mapper.ClientMapper;
import cm.pojo.Client;
import cm.pojo.EasyUIResult;
import org.springframework.util.StringUtils;


/*顾客service
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

			/*if (queryClientBycno(client.getCno()) != null) {
				return "客户编号已经存在，请重新输入编号";
			}*/
			System.out.println(client);
			if(StringUtils.isEmpty(client.getCno())){
				String cno=System.currentTimeMillis()+"";
				client.setCno(cno);
			}
			clientMapper.saveClient(client);
			return "保存成功";
			
		} catch (Exception e) {
			// TODO: handle exception
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

	public void deleteClientBycno(String cno) {
		// TODO Auto-generated method stub
		clientMapper.deleteClientBycno(cno);
	}

	public String modifyClient(Client client) {
		// TODO Auto-generated method stub
		Client queryClientBycno = queryClientBycno(client.getCno());
		clientMapper.modifyClient(client);
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
}
