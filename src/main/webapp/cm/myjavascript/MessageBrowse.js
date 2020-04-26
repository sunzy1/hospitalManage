// 信息浏览begin
// <!-- 信息浏览-BCM-BAM-BMM+MessageBrowse  -->
// 浏览顾客信息begin
function BCMclick(){
		if(access.indexOf("信息浏览功能") < 0 ){
            $.messager.alert('警告','该用户没有此功能');
            event.stopPropagation();	
         }
    	if(!$('#output').tabs('exists','浏览患者信息')) {
				     $('#output').tabs('add',{ 
					    title:'浏览患者信息',
					    href:'./tabs/client/Browse.html',
					    closable:true
					});
				 }
		else
		{
		$('#output').tabs('select', '浏览患者信息');
		}
}
// 浏览顾客信息end
// 浏览经办人信息begin
/*function BAMclick(){
		if(access.indexOf("信息浏览功能") < 0 ){
            $.messager.alert('警告','该用户没有此功能');
            event.stopPropagation();	
         }
    	if(!$('#output').tabs('exists','浏览经办人信息')) {
				     $('#output').tabs('add',{ 
					    title:'浏览经办人信息',    
					     href:'./tabs/agency/Browse.html',
					    closable:true
					});
				 }
		else
		{
		$('#output').tabs('select', '浏览经办人信息');
		}
}*/

// 浏览经办人信息end
// 浏览药品信息begin
function BMMclick(){
		if(access.indexOf("信息浏览功能") < 0 ){
            $.messager.alert('警告','该用户没有此功能');
            event.stopPropagation();	
         }
    	if(!$('#output').tabs('exists','浏览药品信息')) {
				     $('#output').tabs('add',{ 
					    title:'浏览药品信息',    
					     href:'./tabs/medicine/Browse.html',
					    closable:true
					});
				 }
		else
		{
		$('#output').tabs('select', '浏览药品信息');
		}
}

// 浏览药品信息end
// 信息浏览end

function clientQuery() {
    var SCMName=$("#SCMName").textbox('getValue');
    var SCMDisease=$("#SCMDisease").textbox('getValue');
    var SCMStartDate=$("#SCMStartDate").textbox('getValue');
    var SCMEndDate=$("#SCMEndDate").textbox('getValue');
    var clientDto = new Object();
    clientDto.cname=SCMName;
    clientDto.csymptom=SCMDisease;
    clientDto.startDate=SCMStartDate;
    clientDto.endDate=SCMEndDate;
    $('#clientdatagrid').datagrid('load',{
        "clientDto":JSON.stringify(clientDto)
    });
}
	function prescriptionQuery(){
		var mno=$("#prescriptionNo").textbox('getValue');
		var mname=$("#prescriptionCode").textbox('getValue');
		var startDate=$("#prescriptionStartDate").textbox('getValue');
		var endDate=$("#prescriptionEndDate").textbox('getValue');
		var medicineDto = new Object();
		medicineDto.mno=mno;
		medicineDto.mname=mname;
		medicineDto.startDate=startDate;
		medicineDto.endDate=endDate;
		$('#medicineid').datagrid('load',{
			"medicineDto":JSON.stringify(medicineDto)
		});
	}

