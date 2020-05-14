
$(function(){
    $("#medicineDetail").dialog({
        width:1300,
        height:600,
        modal:true,
        closed:true,
        buttons:[
            {
                text:"修改",
                iconCls:'icon-ok',
                handler:function(){
                    medicineModify();
                    $("#medicineDetail").dialog("close");
                    prescriptionQuery();
                }
            },
            {
                text:"删除",
                iconCls:'icon-remove',
                handler:function(){
                    $.messager.confirm("删除确认","你确定要删除此药方信息吗？",function(r){
                        if(r){
                            medicineDelete();
                            $("#medicineDetail").dialog("close");
                            prescriptionQuery();
                        }
                    });
                }
            },
            {
                text:"取消",
                iconCls:'icon-cancel',
                handler:function(){
                    $("#medicineDetail").dialog("close");
                    prescriptionQuery();
                }
            }
        ]
    })
    //患者详情页面
    $("#SCMBrowerdialog").dialog({
        width:1250,
        height:600,
        modal:true,
        closed:true,
        buttons:[
            {
                text:"修改",
                iconCls:'icon-ok',
                handler:function(){
                    clientModify();
                    $("#SCMBrowerdialog").dialog("close");
                    clientQuery();
                }
            },
            {
                text:"删除",
                iconCls:'icon-remove',
                handler:function(){
                    $.messager.confirm("删除确认","你确定要删除此患者信息吗？",function(r){
                        if(r){
                            clientDelete();
                            $("#SCMBrowerdialog").dialog("close");
                            clientQuery();
                        }
                    });
                }
            },
            {
                text:"取消",
                iconCls:'icon-cancel',
                handler:function(){
                    $("#SCMBrowerdialog").dialog("close");
                    clientQuery();
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
        $('#emdate').datebox({
            required:true
        });
        if(null==data.mdate || undefined==data.mdate || ''==data.mdate){
            $("#emdate").datebox('setValue',new Date().format("yyyy-MM-dd"));
        }else{
            $("#emdate").datebox('setValue',data.mdate);
        }
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
            $('#SCMBrowerdialog').dialog('close');
            $.messager.alert('警告','无法查看详情，未查询到数据！');
            return;
        }
        $('#SCMBrowerdialog').dialog('open');
        $("#SCMBrowercno").val(data.cno);
        $("#SCMBrowercname").val(data.cname);
        $("#SCMBrowercsex").val(data.csex);
        $("#SCMBrowercage").val(data.cage);
        $("#SCMBrowercphone").val(data.cphone);
        $("#SCMBrowerano").val(data.ano);
        $('#SCMBrowercdate').datebox({
            required:true
        });
        if(null==data.cdate || undefined==data.cdate || ''==data.cdate){
            $("#SCMBrowercdate").datebox('setValue',new Date().format("yyyy-MM-dd"));
        }else{
            $("#SCMBrowercdate").datebox('setValue',data.cdate);
        }
        $("#SCMBrowermno").val(data.mno);
        $("#SCMBrowercsymptom").val(data.csymptom);
        $("#SCMBrowercremark").val(data.cremark);
    });
}

function clientModify() {
     var SCMBrowercsymptom=$("#SCMBrowercsymptom").val();
    if(SCMBrowercsymptom.length>1300){
        $.messager.alert('提示', '病症长度不能大于1500字');
        return ;
    }
    var SCMBrowercremark = $("#SCMBrowercremark").val();
    if(SCMBrowercremark.length>1300) {
        $.messager.alert('提示', '药方内容长度不能大于1500字');
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
function clientDelete() {
    var SCMBrowercno=$("#SCMBrowercno").val();
    var SCMBrowercname=$("#SCMBrowercname").val();
    if(undefined==SCMBrowercno||SCMBrowercno==''){
        $.messager.alert('提示', '患者编号为空，无法删除！');
        return ;
    }
    $.post('../Client/DeleteClient', {
        cno:SCMBrowercno
    }, function(data) {
        if(undefined ==data || data==''){
            $.messager.alert('警告','无法删除，未查询到数据！');
            return;
        }
        $.messager.alert('提示', data+"；患者姓名："+SCMBrowercname);
        return;
    });
}
function medicineDelete() {
    var emmno = $("#emmno").val();
    var emname = $("#emname").val();
    if(undefined==emmno || emmno==''){
        $.messager.alert('提示', '药单编号为空，无法删除！');
        return ;
    }
    $.post(
        '../Medicine/DeleteMedicine',{
            mno:emmno
        },function (data) {
            if(undefined ==data || data==''){
                $.messager.alert('警告','无法删除，未查询到数据！');
                return;
            }
            $.messager.alert('提示', data+"；药方名称："+emname);
            return;
        }
    );
}
function getDate(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
        function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
}