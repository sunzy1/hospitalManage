
$(function(){
    $("#medicineDetail").dialog({
        width:1700,
        height:800,
        modal:true,
        closed:true,
        buttons:[
            {
                text:"修改",
                iconCls:'icon-ok',
                handler:function(){
                    medicineModify();
                    $("#medicineDetail").dialog("close")
                }
            },
            {
                text:"取消",
                iconCls:'icon-cancel',
                handler:function(){
                    $("#medicineDetail").dialog("close")
                }
            }
        ]
    })
    //患者详情页面
    $("#SCMBrowerdialog").dialog({
        width:1700,
        height:800,
        modal:true,
        closed:true,
        buttons:[
            {
                text:"修改",
                iconCls:'icon-ok',
                handler:function(){
                    clientModify();
                    $("#SCMBrowerdialog").dialog("close")
                }
            },
            {
                text:"取消",
                iconCls:'icon-cancel',
                handler:function(){
                    $("#SCMBrowerdialog").dialog("close")
                }
            }
        ]
    })

});
function prescriptionClick(mno) {
    $.post('../Medicine/QueryMedicineByMno', {
        mno:mno
    }, function(data) {
        if(undefined ==data || data==''){
            $.messager.alert('警告','无法查看详情，未查询到数据！');
            return;
        }
        $("#emmno").val(data.mno);
        $("#mmode").val(data.mmode);
        $("#emname").val(data.mname);
        CKEDITOR.instances.diseaseRemark.setData(data.mremark);
        CKEDITOR.instances.detailedContent.setData(data.mefficacy);
    });
}

function medicineModify() {
    var diseaseRemark = CKEDITOR.instances.diseaseRemark.getData();
    if(diseaseRemark.length>1300){
        $.messager.alert('提示', '病症长度不能大于1300字');
        return ;
    }
    $("#emremark").val(diseaseRemark);
    var detailedContent = CKEDITOR.instances.detailedContent.getData();
    if(detailedContent.length>1300) {
        $.messager.alert('提示', '详细内容长度不能大于1300字');
        return ;
    }
    $("#emmefficacy").val(detailedContent);
    $('#medicineform').form({
        url:'../Medicine/ModifyMedicine',
        onSubmit: function(){
            // do some check
            // return false to prevent submit;
        },
        success:function(data){
            $.messager.alert('提示', data);
        }
    });
// submit the form
    $('#medicineform').submit();
}

function clientDetailClick(cno) {
    $.post('../Client/GetClient', {
        cno:cno
    }, function(data) {
        if(undefined ==data || data==''){
            $.messager.alert('警告','无法查看详情，未查询到数据！');
            return;
        }
        $("#SCMBrowercno").val(data.cno);
        $("#SCMBrowercname").val(data.cname);
        $("#SCMBrowercsex").val(data.csex);
        $("#SCMBrowercage").val(data.cage);
        $("#SCMBrowercphone").val(data.cphone);
        $("#SCMBrowerano").val(data.ano);
        $("#SCMBrowercdate").val(data.cdate);
        $("#SCMBrowermno").val(data.mno);
        $("#SCMBrowercsymptom").val(data.csymptom);
        $("#SCMBrowercremark").val(data.cremark);
    });
}

function clientModify() {
     var SCMBrowercsymptom=$("#SCMBrowercsymptom").val();
    if(SCMBrowercsymptom.length>1300){
        $.messager.alert('提示', '病症长度不能大于1300字');
        return ;
    }
    var SCMBrowercremark = $("#SCMBrowercremark").val();
    if(SCMBrowercremark.length>1300) {
        $.messager.alert('提示', '备注内容长度不能大于1300字');
        return ;
    }
    $('#clientform').form({
        url:'../Client/ModifyClient',
        onSubmit: function(){
            // do some check
            // return false to prevent submit;
        },
        success:function(data){
            $.messager.alert('提示', data);
        }
    });
// submit the form
    $('#clientform').submit();
}