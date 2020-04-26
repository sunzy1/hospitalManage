// 信息录入begin

// <!-- 信息录入-ECM-EAM-EMM+MessageEntry   -->
// 录入顾客信息begin
function ECMclick(){
        if(access.indexOf("信息录入功能") < 0 ){
            $.messager.alert('警告','该用户没有此功能');
            event.stopPropagation();    
         }
    	if(!$('#output').tabs('exists','录入患者信息')) {
				     $('#output').tabs('add',{ 
				     	// id:'ECM',   
					    title:'录入患者信息',
					      // fit:true,
					     href:'./tabs/client/Entry.html',
					    closable:true
					});
				 }
        else
        {
        $('#output').tabs('select', '录入患者信息');
        }
}


// 录入顾客信息end
// 录入经办人信息begin
/*function EAMclick(){
        if(access.indexOf("信息录入功能") < 0 ){
            $.messager.alert('警告','该用户没有此功能');
            event.stopPropagation();    
         }
    	if(!$('#output').tabs('exists','录入经办人信息')) {
				     $('#output').tabs('add',{ 
					    title:'录入经办人信息',    
					     href:'./tabs/agency/Entry.html',
					    closable:true
					});
				 }
        else
        {
        $('#output').tabs('select', '录入经办人信息');
        }
}*/

// 录入经办人信息end
// 点击录入药品信息菜单begin
function EMMclick(){
        if(access.indexOf("信息录入功能") < 0 ){
            $.messager.alert('警告','该用户没有此功能');
            event.stopPropagation();    
         }
    	if(!$('#output').tabs('exists','录入药品信息')) {
				     $('#output').tabs('add',{ 
				     	// id:'ECM',   
					    title:'录入药品信息',    
					      // fit:true,
					    href:'./tabs/medicine/Entry.html',
					    closable:true
					});
				 }
        else
        {
        $('#output').tabs('select', '录入药品信息');
        }
 }

//  点击录入药品信息菜单end
//录入药品信息提交begin
function EMMclickEntry() {
    /*if($("#emmno").val().length>12||$("#emmno").val().length==0) {
        $.messager.alert('提示', '编号不能长度大于12且不为空');
        return ;
    }*/
    /*if($("#emmname").val().length>50) {
        $.messager.alert('提示', '名称不能长度大于50');
        return ;
    }*/
    var diseaseRemark = CKEDITOR.instances.diseaseRemark.getData();
    if(diseaseRemark.length>1300){
        $.messager.alert('提示', '备注长度不能大于1300字');
        return ;
    }
    $("#emremark").val(diseaseRemark);
    var detailedContent = CKEDITOR.instances.detailedContent.getData();
    if(detailedContent.length>1300) {
        $.messager.alert('提示', '详细内容长度不能大于1300字');
        return ;
    }
    $("#emmefficacy").val(detailedContent);
    $('#EMMform').form({
        url:'../Medicine/SaveMedicine',
        onSubmit: function(){
            // do some check
            // return false to prevent submit;
        },
        success:function(data){
            $.messager.alert('提示', data);
        }
    });
// submit the form
    $('#EMMform').submit();
}
//录入药品信息提交end

// 信息录入end