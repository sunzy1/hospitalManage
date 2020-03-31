package cm.mapper;

import java.util.List;

import cm.dto.ClientDto;
import cm.pojo.Client;

public interface ClientMapper {
	public Client queryClientBycno(String cno);

	public void saveClient(Client client);

	public List<Client> queryAllClient(ClientDto clientDto);

	public void deleteClientBycno(String cno);

	public void modifyClient(Client client);
}
