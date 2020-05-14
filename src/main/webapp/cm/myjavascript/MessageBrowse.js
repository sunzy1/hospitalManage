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
    	if(!$('#output').tabs('exists','浏览药方信息')) {
				     $('#output').tabs('add',{ 
					    title:'浏览药方信息',
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
    var SCMRemark = $("#SCMRemark").textbox('getValue');
    var clientDto = new Object();
    clientDto.cname=SCMName;
    clientDto.csymptom=SCMDisease;
    clientDto.startDate=SCMStartDate;
    clientDto.endDate=SCMEndDate;
	clientDto.cremark=SCMRemark;
    $('#clientdatagrid').datagrid('load',{
        "clientDto":JSON.stringify(clientDto)
    });
	$("#clientdatagrid").datagrid('reload');

}
	function prescriptionQuery(){
		var mno=$("#prescriptionNo").textbox('getValue');
		var mname=$("#prescriptionName").textbox('getValue');
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
        $("#medicineid").datagrid('reload');

    }
function clientExport() {
	var SCMName=$("#SCMName").textbox('getValue');
	var SCMDisease=$("#SCMDisease").textbox('getValue');
	var SCMStartDate=$("#SCMStartDate").textbox('getValue');
	var SCMEndDate=$("#SCMEndDate").textbox('getValue');
	var SCMRemark = $("#SCMRemark").textbox('getValue');
	var mapObj={};
	if(isNotEmpty(SCMName)){
		mapObj['cname']=SCMName;
	}
	if(isNotEmpty(SCMDisease)){
		mapObj['csymptom']=SCMDisease;
	}
	if(isNotEmpty(SCMStartDate)){
		mapObj['startDate']=SCMStartDate;
	}
	if(isNotEmpty(SCMEndDate)){
		mapObj['endDate']=SCMEndDate;
	}
	if(isNotEmpty(SCMRemark)){
		mapObj['cremark']=SCMRemark;
	}
	var actionUrl="../Client/exportClient";
	makeTempForm(mapObj,actionUrl);
	/*var clientDto = new Object();
	clientDto.cname=SCMName;
	clientDto.csymptom=SCMDisease;
	clientDto.startDate=SCMStartDate;
	clientDto.endDate=SCMEndDate;
	clientDto.cremark=SCMRemark;
	//?clientDto="+JSON.stringify(clientDto)
	var path="../Client/exportClient?data="+escape(JSON.stringify(clientDto));
	window.location.href=path;*/
	/*$.post('../Client/exportClient',{
		"clientDto":JSON.stringify(clientDto)
	},function (data) {

	});*/
}

function prescriptionExport() {
    var mno=$("#prescriptionNo").textbox('getValue');
    var mname=$("#prescriptionName").textbox('getValue');
    var startDate=$("#prescriptionStartDate").textbox('getValue');
    var endDate=$("#prescriptionEndDate").textbox('getValue');
    var medicineDto = new Object();
	var mapObj={};
	if(isNotEmpty(mno)){
		mapObj['mno']=mno;
	}
	if(isNotEmpty(mname)){
		mapObj['mname']=mname;
	}
	if(isNotEmpty(startDate)){
		mapObj['startDate']=startDate;
	}
	if(isNotEmpty(endDate)){
		mapObj['endDate']=endDate;
	}
	var actionUrl="../Medicine/exportMedicine";
	makeTempForm(mapObj,actionUrl);
	/*medicineDto.mno=mno;
    medicineDto.mname=mname;
    medicineDto.startDate=startDate;
    medicineDto.endDate=endDate;*/
	/*encodeURIComponent*/
	/*+escape(JSON.stringify(medicineDto))*/
    /*var path="../Medicine/exportMedicine?data="+escape(JSON.stringify(medicineDto));
    window.location.href=path;*/
}
function makeTempForm( mapObj,actionUrl) {
	var tempForm = document.createElement("form");
	tempForm.id = "tempForm";
	tempForm.name = "tempForm";
	// 添加到 body 中
	document.body.appendChild(tempForm);
	for(var prop in mapObj){
		if(mapObj.hasOwnProperty(prop)){
			var formInput = document.createElement("input");
			formInput.type="text";
			formInput.name=prop;
			formInput.value=mapObj[prop];
			tempForm.appendChild(formInput);
		}
		}
	// form 的提交方式
	tempForm.method = "POST";
	// form 提交路径
	tempForm.action = actionUrl;
	// 对该 form 执行提交
	tempForm.submit();
	// 删除该 form
	document.body.removeChild(tempForm);
}
function isNotEmpty(str) {
	if(undefined !=str && null!=str && ''!=str){
		return true;
	}
}