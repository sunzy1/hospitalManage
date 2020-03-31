package cm.mapper;

import java.util.List;
import cm.pojo.Agency;

public interface AgencyMapper {
	public Agency queryAgencyByAno(String ano);

	public void saveAgency(Agency agency);

	public List<Agency> queryAllAgency();

	public void deleteAgencyByAno(String ano);

	public void modifyAgency(Agency agency);
}
