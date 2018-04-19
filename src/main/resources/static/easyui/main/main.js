/*****************************************************************************************
 * tree  -----  menu  构建west面板菜单异步tree
 *****************************************************************************************/

function addTab(title, url){
    console.info(title + "\t" + url);
    if ($('#tabs').tabs('exists', title)){
        $('#tabs').tabs('select', title);
    } else {
        console.info("not exists");
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        $('#tabs').tabs('add',{
            title:title,
            content:content,
            closable:true
        });
    }
}

/**************************************************************************************************
 *  datagrid ------ userinfo   center面板用户管理  datagrid使用的函数
 **************************************************************************************************/
function dateFormat(value) {
    var date = new Date(value);
    y = date.getFullYear();
    m = date.getMonth() + 1;
    d = date.getDate();
    h = date.getHours();
    i = date.getMinutes();
    s = date.getSeconds();
    // 可根据需要在这里定义时间格式
    return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
}

$(".datagrid-header div").css("textAlign", "left");             // 如果想让列和标题以不同的形式展示可以为header添加css样式


function searchUser() {
    console.info(serializeObject(userSearchForm));
    // load和reload的区别  Reload the rows. Same as the 'load' method but stay on current page.
    dataGrid.datagrid("load",              // 可以将这些参数封装为一个对象在后台接收，这样可以减少参数的显示，同时复用datagrid查询url
        serializeObject(userSearchForm)            // 将表单序列化为对象

    );
}

function clearUser() {
    userSearchForm.find("input").val("");       // 置空搜索条件
    dataGrid.datagrid("load", {});              // 原始搜索
}

// 自定义函数，实现将表单序列化为对象
function serializeObject(form) {            //将form表单元素的值序列化为对象
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
}

// 动态改变编辑状态
$.extend($.fn.datagrid.methods, {
    addEditor : function (jq, param) {
        if(param instanceof Array) {
            console.info("addEditor Array...");
            $.each(param, function (index, item) {
                var e = $(jq).datagrid("getColumnOption", item.field);
                e.editor = item.editor;
            });
        } else {
            console.info("addEditor");
            var e = $(jq).datagrid("getColumnOption", param.field);
            e.editor = param.editor;
        }
    },
    removeEditor : function (jq, param) {
        if(param instanceof Array) {
            console.info("addEditor Array...");
            $.each(param, function (index, item) {
                var e = $(jq).datagrid("getColumnOption", item);
                e.editor = {};
            });
        } else {
            console.info("removeEditor");
            var e = $(jq).datagrid("getColumnOption", param);
            e.editor = {};
        }
    }
});



// 通过函数完成editor的变化
// 添加记录的时候改变editor    createTime modifiedTime后台自己完成，不进行前台添加  密码开启前台输入
function changeEditorAddRow(){
    dataGrid.datagrid("addEditor",{
        field : "password",
        editor : {
            type : "validatebox",
            options : {
                required : true
            }
        }
    });

    dataGrid.datagrid("removeEditor", ["createTime", "modifiedTime"])
}
// 修改记录的时候改变editor  createTime modifiedTime开启编辑 密码关闭编辑，通过修改密码入口进入
function changeEditorEditRow() {
    dataGrid.datagrid("addEditor", [
        {
            field : "createTime",
            editor : {
                type : "datetimebox",
                options : {
                    editable : false
                }
            }
        },
        {
            field : "modifiedTime",
            editor : {
                type : "datetimebox",
                options : {
                    editable : false
                }
            }
        }]);

    dataGrid.datagrid("removeEditor", "password");
}


function remove() {
    var rows = dataGrid.datagrid("getSelections");
    if(rows.length > 0) {           // 保证有选中数据行
        $.messager.confirm("请确认", "您确定要删除当前所选择的记录吗？", function (b) {
            console.info(b);
            if(b) {
                var ids = [];
                for(var i=0; i<rows.length; i++) {
                    ids.push(rows[i].id);
                }
                console.info(ids);
                if(ids[0] != undefined) {                   // 防止删除新建未保存行记录
                    $.ajax({
                        url : "deleteUser",
                        data : {ids : ids},
                        dataType : "json",
                        success : function (data) {
                            if(data != null || data == -1 || data == "") {
                                $.messager.show({title : "提示", msg : "成功删除" + data + "行数据"});
                                dataGrid.datagrid("reload",{});
                                dataGrid.datagrid("unselectAll");
                            } else {
                                $.messager.alert("提示", "删除失败！");
                            }
                        }
                    });
                } else {
                    $.messager.alert("提示","当前选中数据无效");
                }

            }
        })
    }else {
        $.messager.show({title : "提示", msg : "请选择要删除的记录"});
    }
}


function edit() {
    var rows = dataGrid.datagrid("getSelections");
    console.info(rows.length);
    if(rows.length > 1) {
        console.info("一次只能编辑一行编辑");
        $.messager.show({title : "提示", msg : "一次只能编辑一行数据"});
        return;
    }else if(rows.length == 1 && editRow == undefined) {
        changeEditorEditRow();

        editRow = dataGrid.datagrid("getRowIndex",rows[0]);
        console.info("第" + (editRow + 1 )+ "行正在被修改");
        dataGrid.datagrid("beginEdit", editRow);
        // dataGrid.datagrid("unselectAll");

    } else {
        if(editRow != undefined) {
            $.messager.show({title : "提示", msg : "已有行在编辑！"});
        }else {
            $.messager.show({title : "提示", msg : "请先选中行！"});
        }
    }
}

function append() {

    if(editRow != undefined) {
        console.info("一次只能编辑一行编辑");
        // dataGrid.datagrid("endEdit", editRow);
        $.messager.show({title : "提示", msg : "一次只能添加一行数据"});
        return;
    }
    if(editRow == undefined){
        changeEditorAddRow();
        // 插入行
        dataGrid.datagrid("insertRow", {
            index : 0,
            row : {}
        });
        dataGrid.datagrid("beginEdit", 0);

        editRow = 0;
    }

}

